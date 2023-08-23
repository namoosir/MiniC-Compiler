//
// Created by Fan Long on 2020/12/6.
//

#include "IRGenerator.h"
#include "llvm/IR/Module.h"
#include "Declarations.h"
#include "Program.h"
#include "Exprs.h"
#include "Statements.h"

namespace minicc {

    llvm::Type* IRGenerator::toLLVMType(Type minicType) {
        llvm::Type* primType;
        switch (minicType.primitiveType()) {
            case Type::Void:
                primType = llvm::Type::getVoidTy(*TheContext);
                break;
            case Type::Int:
                primType = llvm::Type::getInt32Ty(*TheContext);
                break;
            case Type::Bool:
                primType = llvm::Type::getInt1Ty(*TheContext);
                break;
        }
        if (minicType.arrayBound() == 0)
            return primType;
        else {
            return llvm::ArrayType::get(primType, minicType.arrayBound());
        }
    }

    std::vector<llvm::Type*> IRGenerator::toLLVMTypes(std::vector<Type> minicTypes) {
        std::vector<llvm::Type*> ret;
        ret.clear();
        for (size_t i = 0; i < minicTypes.size(); i++)
            ret.push_back(toLLVMType(minicTypes[i]));
        return ret;
    }

    llvm::Function* IRGenerator::findOrGenFunctionProto(const std::string &name, Program* root) {
        llvm::Function *F = TheModule->getFunction(name);
        if (F)
            return F;
        FuncSymbolEntry entry = root->funcTable()->get(name);
        std::vector<llvm::Type*> paraTypes = toLLVMTypes(entry.ParameterTypes);
        llvm::Type* retType = toLLVMType(entry.ReturnType);
        llvm::FunctionType *funcType = llvm::FunctionType::get(retType, paraTypes, false);
        F = llvm::Function::Create(funcType, llvm::Function::ExternalLinkage, name, TheModule.get());
        return F;
    }

    void IRGenerator::visitProgram(Program *prog) {
        TheModule = std::make_unique<llvm::Module>(ModuleName, *TheContext);
        TheBuilder = std::make_unique<llvm::IRBuilder<>>(*TheContext);
        ValueForExpr.clear();
        EnclosingFunction = nullptr;
        FuncSymbolTable *table = prog->funcTable();
        for (auto it = table->begin(); it != table->end(); ++it) {
            findOrGenFunctionProto(it->first, prog);
        }
        ASTVisitor::visitProgram(prog);
    }

    void IRGenerator::visitVarDecl(VarDeclaration *decl) {
        ASTNode* scope = decl->getParentScope();
        // This is a global variable declaration
        Type::PrimitiveTypeEnum primType = decl->declType();
        for (size_t i = 0; i < decl->numVarReferences(); i++) {
            VarReference* ref = decl->varReference(i);
            std::string name = ref->identifier()->name();
            if (scope->isProgram()) {
                Type varType;
                llvm::Constant* init;
                if (!ref->isArray()) {
                    varType = Type(primType);
                    init = llvm::ConstantInt::get(llvm::Type::getInt32Ty(*TheContext), 0);
                } else {
                    auto intLiteral = (IntLiteralExpr*) ref->indexExpr();
                    varType = Type(primType, intLiteral->value());
                    init = llvm::ConstantAggregateZero::get(toLLVMType(varType));
                }
                auto *globalVar = new llvm::GlobalVariable(*TheModule,
                                                           toLLVMType(varType),
                                                           false,
                                                           llvm::GlobalVariable::CommonLinkage,
                                                           init,
                                                           name);
                globalVar->setAlignment(llvm::MaybeAlign(4));
                scope->scopeVarTable()->setLLVMValue(name, globalVar);
            } else {
                llvm::Value* localVar;
                if (ref->isArray()) {
                    auto intLiteral = (IntLiteralExpr*) ref->indexExpr();
                    llvm::Value* arrayBound = llvm::ConstantInt::get(llvm::Type::getInt32Ty(*TheContext), intLiteral->value(), false);
                    localVar = TheBuilder->CreateAlloca(toLLVMType(Type(primType, intLiteral->value())), arrayBound, name);
                }
                else {
                    localVar = TheBuilder->CreateAlloca(toLLVMType(Type(primType)), nullptr, name);
                }
                scope->scopeVarTable()->setLLVMValue(name, localVar);
            }
        }
        ASTVisitor::visitVarDecl(decl);
    }

    void IRGenerator::visitFuncDecl(FuncDeclaration *func) {
        llvm::Function *F = findOrGenFunctionProto(func->name(), func->root());
        // Prepare the generation of the function body
        if (func->hasBody()) {
            llvm::BasicBlock *BB = llvm::BasicBlock::Create(*TheContext, "entry", F);
            TheBuilder->SetInsertPoint(BB);
            // We prepare alloca space in case if parameters are changed
            for (size_t i = 0; i < F->arg_size(); i++) {
                llvm::Argument* arg = F->getArg(i);
                llvm::Value* argLLVMValue = TheBuilder->CreateAlloca(toLLVMType(func->parameter(i)->type()));
                TheBuilder->CreateStore(arg, argLLVMValue);
                func->body()->scopeVarTable()->setLLVMValue(func->parameter(i)->name(), argLLVMValue);
            }
        }
        EnclosingFunction = F;
        ASTVisitor::visitFuncDecl(func);
        if (func->hasBody()) {
            // This is to cover the default return case for void functions
            // LLVM requires every function having a terminator statement like return
            if (!TheBuilder->GetInsertBlock()->getTerminator())
                TheBuilder->CreateRetVoid();
        }
    }

    void IRGenerator::visitIfStmt(IfStatement *stmt) {
        Expr* condExpr = stmt->condExpr();
        condExpr->accept(this);
        llvm::Value *condValue = ValueForExpr[condExpr];
        llvm::BasicBlock *thenBB = llvm::BasicBlock::Create(*TheContext, "then", EnclosingFunction);
        llvm::BasicBlock *afterBB = llvm::BasicBlock::Create(*TheContext, "after", EnclosingFunction);
        llvm::BasicBlock *elseBB = nullptr;
        if (stmt->hasElse()) {
            elseBB = llvm::BasicBlock::Create(*TheContext, "else", EnclosingFunction);
            TheBuilder->CreateCondBr(condValue, thenBB, elseBB);
        } else
            TheBuilder->CreateCondBr(condValue, thenBB, afterBB);
        TheBuilder->SetInsertPoint(thenBB);
        stmt->thenStmt()->accept(this);
        if (!TheBuilder->GetInsertBlock()->getTerminator())
            TheBuilder->CreateBr(afterBB);
        if (stmt->hasElse()) {
            TheBuilder->SetInsertPoint(elseBB);
            stmt->elseStmt()->accept(this);
            if (!TheBuilder->GetInsertBlock()->getTerminator())
                TheBuilder->CreateBr(afterBB);
        }
        TheBuilder->SetInsertPoint(afterBB);
    }

    void IRGenerator::visitForStmt(ForStatement *stmt) {
        Expr* initExpr = stmt->initExpr();
        if (initExpr)
            initExpr->accept(this);
        llvm::BasicBlock *condBB = llvm::BasicBlock::Create(*TheContext, "for.cond", EnclosingFunction);
        llvm::BasicBlock *bodyBB = llvm::BasicBlock::Create(*TheContext, "for.body", EnclosingFunction);
        llvm::BasicBlock *exitBB = llvm::BasicBlock::Create(*TheContext, "for.exit", EnclosingFunction);
        if (!TheBuilder->GetInsertBlock()->getTerminator())
            TheBuilder->CreateBr(condBB);
        Expr* condExpr = stmt->condExpr();
        TheBuilder->SetInsertPoint(condBB);
        if (condExpr) {
            condExpr->accept(this);
            // This is just an expression, it should always be false
            assert(!TheBuilder->GetInsertBlock()->getTerminator());
            TheBuilder->CreateCondBr(ValueForExpr[condExpr], bodyBB, exitBB);
        } else {
            assert(!TheBuilder->GetInsertBlock()->getTerminator());
            TheBuilder->CreateBr(bodyBB);
        }
        TheBuilder->SetInsertPoint(bodyBB);
        EnclosingForExitBB = exitBB;
        stmt->body()->accept(this);
        if (!TheBuilder->GetInsertBlock()->getTerminator()) {
            Expr* iterExpr = stmt->iterExpr();
            if (iterExpr)
                iterExpr->accept(this);
            if (!TheBuilder->GetInsertBlock()->getTerminator())
                TheBuilder->CreateBr(condBB);
        }
        TheBuilder->SetInsertPoint(exitBB);
    }

    void IRGenerator::visitWhileStmt(WhileStatement *stmt) {
        llvm::BasicBlock *condBB = llvm::BasicBlock::Create(*TheContext, "for.cond", EnclosingFunction);
        llvm::BasicBlock *bodyBB = llvm::BasicBlock::Create(*TheContext, "for.body", EnclosingFunction);
        llvm::BasicBlock *exitBB = llvm::BasicBlock::Create(*TheContext, "for.exit", EnclosingFunction);
        if (!TheBuilder->GetInsertBlock()->getTerminator())
            TheBuilder->CreateBr(condBB);
        Expr* condExpr = stmt->condExpr();
        TheBuilder->SetInsertPoint(condBB);
        if (condExpr) {
            condExpr->accept(this);
            // This is just an expression, it should always be false
            assert(!TheBuilder->GetInsertBlock()->getTerminator());
            TheBuilder->CreateCondBr(ValueForExpr[condExpr], bodyBB, exitBB);
        } else {
            assert(!TheBuilder->GetInsertBlock()->getTerminator());
            TheBuilder->CreateBr(bodyBB);
        }
        TheBuilder->SetInsertPoint(bodyBB);
        // handle nested for and while loops with break
        // at the same time.
        EnclosingForExitBB = exitBB;
        stmt->body()->accept(this);
        if (!TheBuilder->GetInsertBlock()->getTerminator())
            TheBuilder->CreateBr(condBB);
        TheBuilder->SetInsertPoint(exitBB);
    }

    void IRGenerator::visitReturnStmt(ReturnStatement *stmt) {
        ASTVisitor::visitReturnStmt(stmt);
        if (stmt->hasReturnExpr())
            TheBuilder->CreateRet(ValueForExpr[stmt->returnExpr()]);
        else
            TheBuilder->CreateRetVoid();
    }

    void IRGenerator::visitBreakStmt(BreakStatement *stmt) {
        ASTVisitor::visitBreakStmt(stmt);
        if (!TheBuilder->GetInsertBlock()->getTerminator())
            TheBuilder->CreateBr(EnclosingForExitBB);
    }


    void IRGenerator::visitUnaryExpr(UnaryExpr *expr) {
        ASTVisitor::visitUnaryExpr(expr);
        llvm::Value* subValue = ValueForExpr[(Expr*)expr->getChild(0)];
        llvm::Value* v;
        switch (expr->opcode()) {
            case Expr::Sub:
                v = TheBuilder->CreateNeg(subValue);
                break;
            case Expr::Not:
                v = TheBuilder->CreateNot(subValue);
                break;
            default:
                // Unreachable
                assert(false);
        }
        ValueForExpr[expr] = v;
    }

    void IRGenerator::visitBinaryExpr(BinaryExpr *expr) {
        Expr::ExprOpcode opcode = expr->opcode();
        if (opcode == Expr::And || opcode == Expr::Or) {
            expr->getChild(0)->accept(this);
            llvm::Value *subV0 = ValueForExpr[(Expr *) expr->getChild(0)];
            llvm::BasicBlock *current = TheBuilder->GetInsertBlock();
            llvm::BasicBlock *slow = llvm::BasicBlock::Create(*TheContext, "sc.slow", EnclosingFunction);
            llvm::BasicBlock *out = llvm::BasicBlock::Create(*TheContext, "sc.out", EnclosingFunction);
            if (opcode == Expr::And) {
                // This is just an expression, it should always be false
                assert(!TheBuilder->GetInsertBlock()->getTerminator());
                TheBuilder->CreateCondBr(subV0, slow, out);
            } else {
                // This is just an expression, it should always be false
                assert(!TheBuilder->GetInsertBlock()->getTerminator());
                TheBuilder->CreateCondBr(subV0, out, slow);
            }
            TheBuilder->SetInsertPoint(slow);
            expr->getChild(1)->accept(this);
            slow = TheBuilder->GetInsertBlock();
            assert(!TheBuilder->GetInsertBlock()->getTerminator());
            TheBuilder->CreateBr(out);
            TheBuilder->SetInsertPoint(out);
            llvm::PHINode* PN = TheBuilder->CreatePHI(llvm::Type::getInt1Ty(*TheContext), 2);
            if (opcode == Expr::And)
                PN->addIncoming(llvm::ConstantInt::get(llvm::Type::getInt1Ty(*TheContext), 0), current);
            else
                PN->addIncoming(llvm::ConstantInt::get(llvm::Type::getInt1Ty(*TheContext), 1), current);
            llvm::Value *subV1 = ValueForExpr[(Expr *)expr->getChild(1)];
            PN->addIncoming(subV1, slow);
            ValueForExpr[expr] = PN;
        } else {
            ASTVisitor::visitBinaryExpr(expr);
            llvm::Value *subV0 = ValueForExpr[(Expr *) expr->getChild(0)];
            llvm::Value *subV1 = ValueForExpr[(Expr *) expr->getChild(1)];
            llvm::Value *v;
            switch (opcode) {
                case Expr::Add:
                    v = TheBuilder->CreateAdd(subV0, subV1);
                    break;
                case Expr::Sub:
                    v = TheBuilder->CreateSub(subV0, subV1);
                    break;
                case Expr::Mul:
                    v = TheBuilder->CreateMul(subV0, subV1);
                    break;
                case Expr::Div:
                    v = TheBuilder->CreateSDiv(subV0, subV1);
                    break;
                case Expr::Equal:
                    v = TheBuilder->CreateICmpEQ(subV0, subV1);
                    break;
                case Expr::NotEqual:
                    v = TheBuilder->CreateICmpNE(subV0, subV1);
                    break;
                case Expr::Less:
                    v = TheBuilder->CreateICmpSLT(subV0, subV1);
                    break;
                case Expr::LessEqual:
                    v = TheBuilder->CreateICmpSLE(subV0, subV1);
                    break;
                case Expr::Greater:
                    v = TheBuilder->CreateICmpSGT(subV0, subV1);
                    break;
                case Expr::GreaterEqual:
                    v = TheBuilder->CreateICmpSGE(subV0, subV1);
                    break;
                default:
                    // Unreachable
                    assert(false);
            }
            ValueForExpr[expr] = v;
        }
    }

    void IRGenerator::visitCallExpr(CallExpr *expr) {
        ASTVisitor::visitCallExpr(expr);
        std::vector<llvm::Value*> vals;
        vals.clear();
        for (size_t i = 0; i < expr->numArgs(); i++)
            vals.push_back(ValueForExpr[expr->arg(i)]);
        llvm::Function *callee = TheModule->getFunction(expr->callee()->name());
        ValueForExpr[expr] = TheBuilder->CreateCall(callee, vals);
    }

    void IRGenerator::visitVarExpr(VarExpr *expr) {
        ASTVisitor::visitVarExpr(expr);
        auto ref = (VarReference*) expr->getChild(0);
        VarSymbolTable* table = expr->locateDeclaringTableForVar(ref->identifier()->name());
        VarSymbolEntry entry = table->get(ref->identifier()->name());
        llvm::Value *varValue = entry.LLVMValue;
        llvm::Type *varType = toLLVMType(entry.VarType);
        if (ref->isArray()) {
            std::vector<llvm::Value*> index;
            index.push_back(llvm::ConstantInt::get(llvm::Type::getInt32Ty(*TheContext), 0));
            index.push_back(ValueForExpr[ref->indexExpr()]);
            varValue = TheBuilder->CreateGEP(varType, varValue, index);
            ValueForExpr[expr] = TheBuilder->CreateLoad(toLLVMType(Type(entry.VarType.primitiveType())), varValue);
        }
        else{
            ValueForExpr[expr] = TheBuilder->CreateLoad(varType, varValue);
        }
    }

    void IRGenerator::visitAssignmentExpr(AssignmentExpr *expr) {
        ASTVisitor::visitAssignmentExpr(expr);
        auto ref = (VarReference*) expr->getChild(0);
        VarSymbolTable* table = expr->locateDeclaringTableForVar(ref->identifier()->name());
        VarSymbolEntry entry = table->get(ref->identifier()->name());
        llvm::Value *varValue = entry.LLVMValue;
        llvm::Type *varType = toLLVMType(entry.VarType);
        if (ref->isArray()) {
            std::vector<llvm::Value*> index;
            index.push_back(llvm::ConstantInt::get(llvm::Type::getInt32Ty(*TheContext), 0));
            index.push_back(ValueForExpr[ref->indexExpr()]);
            varValue = TheBuilder->CreateGEP(varType, varValue, index);
        }
        llvm::Value *assignedValue = ValueForExpr[(Expr*)expr->getChild(1)];
        TheBuilder->CreateStore(assignedValue, varValue);
        ValueForExpr[expr] = assignedValue;
    }

    void IRGenerator::visitIntLiteralExpr(IntLiteralExpr *literal) {
        ASTVisitor::visitIntLiteralExpr(literal);
        ValueForExpr[literal] = llvm::ConstantInt::get(llvm::Type::getInt32Ty(*TheContext), literal->value());
    }

    void IRGenerator::visitBoolLiteralExpr(BoolLiteralExpr *literal) {
        ASTVisitor::visitBoolLiteralExpr(literal);
        ValueForExpr[literal] = llvm::ConstantInt::get(llvm::Type::getInt1Ty(*TheContext), literal->value());
    }

    void IRGenerator::visitScope(ScopeStatement *stmt) {
        for (size_t i = 0; i < stmt->numChildren(); i++) {
            llvm::Instruction *term = TheBuilder->GetInsertBlock()->getTerminator();
            // If we insert into a basic block with terminators (ret/br),
            // we will actually insert into the follow up basic blocks.
            // This check will handle the case where "return" appear in the
            // middle of a scope.
            if (!term)
                stmt->getChild(i)->accept(this);
        }
    }

}
