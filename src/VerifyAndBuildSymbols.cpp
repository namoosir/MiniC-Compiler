//
// Created by Fan Long on 2020/12/4.
//
#include "VerifyAndBuildSymbols.h"
#include "Declarations.h"
#include "Terms.h"
#include "Types.h"
#include "Exprs.h"
#include "Statements.h"
#include "Program.h"
#include <string>
#include <sstream>

namespace minicc {

    void VerifyAndBuildSymbols::visitASTNode(ASTNode *node) {
        node->setRoot(VisitingProgram);
        ASTVisitor::visitASTNode(node);
    }

    void VerifyAndBuildSymbols::visitProgram(Program *prog) {
        VisitingProgram = prog;
        if (prog->syslibFlag()) {
            // We manually populate the function symbol table
            prog->funcTable()->addFunctionSymbol("getint", Type(Type::Int), std::vector<Type>(), false);
            std::vector<Type> tmp;
            tmp.clear();
            tmp.push_back(Type(Type::Int));
            prog->funcTable()->addFunctionSymbol("putint", Type(Type::Void), tmp, false);
            prog->funcTable()->addFunctionSymbol("putnewline", Type(Type::Void), std::vector<Type>(), false);
        }
        ASTVisitor::visitProgram(prog);
    }

    void VerifyAndBuildSymbols::visitVarDecl(VarDeclaration *decl) {
        ASTVisitor::visitVarDecl(decl);
        ASTNode* scope = decl->getParentScope();
        Type::PrimitiveTypeEnum t = decl->declType();
        for (size_t i = 0; i < decl->numVarReferences(); i++) {
            VarReference* ref = decl->varReference(i);
            std::string name = ref->identifier()->name();
            scope->scopeVarTable()->containsVar(name);
            // Check1: Same variable cannot be declared twice in the same scope
            if (scope->scopeVarTable()->containsVar(name))
                Errors.push_back(ErrorMessage("Redefinition of variable/parameter \"" + name + "\" in the same scope!", ref->srcLoc()));
            else {
                if (ref->isArray()) {
                    auto *bound = dynamic_cast<IntLiteralExpr*>(ref->indexExpr());
                    assert( bound != nullptr);
                    scope->scopeVarTable()->addVariableSymbol(name, Type(t, bound->value()));
                }
                else
                    scope->scopeVarTable()->addVariableSymbol(name, Type(t));
            }
        }
    }

    void VerifyAndBuildSymbols::visitFuncDecl(FuncDeclaration *func) {
        Program* root = VisitingProgram;
        Type t = func->returnType();
        std::string name = func->name();
        std::vector<Type> parameterTypes;
        std::vector<std::string> parameterNames;
        parameterTypes.clear();
        parameterNames.clear();
        for (size_t i = 0; i < func->numParameters(); i++) {
            Parameter* para = func->parameter(i);
            Type t = para->type();
            std::string name = para->name();
            parameterTypes.push_back(para->type());
            parameterNames.push_back(para->name());
        }
        // If we already have this function, we are going to check the type matches with each other.
        if (root->funcTable()->containsFunc(name)) {
            FuncSymbolEntry entry = root->funcTable()->get(name);
            // Check2: Return type of the function does not match with each other
            if (entry.ReturnType != t)
                Errors.push_back(ErrorMessage("Definition of function \"" + name + "()\" with different return type!", func->srcLoc()));
            // Check3: Number of parameters should match with each other
            if (entry.ParameterTypes.size() != parameterTypes.size())
                Errors.push_back(ErrorMessage("Definition of function \"" + name + "()\" with different number of parameters!", func->srcLoc()));
            // Check4: Each parameter type should match with each other
            for (size_t i = 0; i < std::min(parameterTypes.size(), entry.ParameterTypes.size()); i++)
                if (entry.ParameterTypes[i] != parameterTypes[i])
                    Errors.push_back(ErrorMessage("Definition of function \"" + name + "()\" with different parameter type at position " + std::to_string(i) + "!", func->srcLoc()));
            // Check5: Only one definition of the function.
            if (func->hasBody())
                if (entry.HasBody)
                    Errors.push_back(ErrorMessage("Redefinition of function \"" + name + "()\"!", func->srcLoc()));
        }
        else
            root->funcTable()->addFunctionSymbol(name, t, parameterTypes, false);
        if (func->hasBody()) {
            root->funcTable()->setFunctionBody(name, true);
            ScopeStatement* body = func->body();
            // Check6: Parameters cannot have the same name
            for (size_t i = 0; i < parameterTypes.size(); i++)
                if (body->scopeVarTable()->containsVar(parameterNames[i]))
                    Errors.push_back(ErrorMessage("Redefinition of variable/parameter \"" + parameterNames[i] + "\" in the same scope!", func->parameter(i)->identifier()->srcLoc()));
                else
                    body->scopeVarTable()->addVariableSymbol(parameterNames[i], parameterTypes[i]);
        }
        ASTVisitor::visitFuncDecl(func);
        if (func->hasBody()) {
            // Check26: The last statement a function body must be return if the return type is not void
            ScopeStatement* body = func->body();
            Statement* lastStatement = nullptr;
            if (body->numChildren() > 0)
                lastStatement = (Statement*) body->getChild(body->numChildren() - 1);
            if (!func->returnType().isVoid() && (lastStatement == nullptr || !lastStatement->isReturn()))
                Errors.push_back(ErrorMessage("The function \"" + name + "()\" need to return a value at its end!", func->srcLoc()));
        }
    }

    void VerifyAndBuildSymbols::visitIfStmt(IfStatement *stmt) {
        ASTVisitor::visitIfStmt(stmt);
        // Check7: The conditional expression must have bool type
        Expr* expr = stmt->condExpr();
        if (!expr->exprType().isBool())
            Errors.push_back(ErrorMessage("Conditional expression in if statement has non-bool type!", expr->srcLoc()));
    }

    void VerifyAndBuildSymbols::visitForStmt(ForStatement *stmt) {
        ASTVisitor::visitForStmt(stmt);
        // Check8: The second expression in for must be either null or bool type
        Expr* expr = stmt->condExpr();
        if (expr != nullptr) {
            if (!expr->exprType().isBool())
                Errors.push_back(ErrorMessage("Conditional expression in for statement has non-bool type!", expr->srcLoc()));
        }
    }

    void VerifyAndBuildSymbols::visitWhileStmt(WhileStatement *stmt) {
        ASTVisitor::visitWhileStmt(stmt);
        // Check8: The condition in while must have bool type
        Expr* expr = stmt->condExpr();
        if (!expr->exprType().isBool())
            Errors.push_back(ErrorMessage("Conditional expression in while statement has non-bool type!", expr->srcLoc()));

    }



    void VerifyAndBuildSymbols::visitReturnStmt(ReturnStatement *stmt) {
        ASTVisitor::visitReturnStmt(stmt);
        FuncDeclaration *func = stmt->getParentFunction();
        Type retType = func->returnType();
        // Check9: Void function must have no expression to return
        if (retType.isVoid() && stmt->hasReturnExpr())
            Errors.push_back(ErrorMessage("Function has void return type, but the return statement has a returned expression!", stmt->srcLoc()));
        if (!retType.isVoid()) {
            // Check10: Non-Void function must have an expression to return
            if (!stmt->hasReturnExpr())
                Errors.push_back(ErrorMessage(
                        "Function has non-void return type, but the return statement has no returned expression!",
                        stmt->srcLoc()));
            else {
                Expr* retExpr = stmt->returnExpr();
                // Check11: The return type and the returned expression type must match
                if (retType != retExpr->exprType())
                    Errors.push_back(ErrorMessage(
                            "Function has return type \"" + retType.toString() + "\", but the returned expression has type \"" + retExpr->exprType().toString() + "\"!", stmt->srcLoc()));
            }
        }
    }

    void VerifyAndBuildSymbols::visitBreakStmt(BreakStatement *stmt) {
        ASTVisitor::visitBreakStmt(stmt);
        ForStatement *forStmt = stmt->getParentForStatement();
        WhileStatement *whileStmt = stmt->getParentWhileStatement();
        // Check12: Break statement must appear inside a for/while statement
        if (forStmt == nullptr && whileStmt == nullptr) {
            Errors.push_back(ErrorMessage("Break statement must appear inside a for/while statement!", stmt->srcLoc()));
        }
    }

    void VerifyAndBuildSymbols::visitUnaryExpr(UnaryExpr *expr) {
        ASTVisitor::visitUnaryExpr(expr);
        auto subExpr = (Expr*)expr->getChild(0);
        // Check13: Negate opcode must have int operand!
        if (expr->opcode() == Expr::Sub)
            if (!subExpr->exprType().isInt())
                Errors.push_back(ErrorMessage("Negate \"-\" opcode must have int operand!", expr->srcLoc()));
        // Check14: Not opcode must have bool operand
        if (expr->opcode() == Expr::Not)
            if (!subExpr->exprType().isBool())
                Errors.push_back(ErrorMessage("Not \"!\" opcode must have bool operand!", expr->srcLoc()));
        expr->setExprType(subExpr->exprType());
    }

    void VerifyAndBuildSymbols::visitBinaryExpr(BinaryExpr *expr) {
        ASTVisitor::visitBinaryExpr(expr);
        auto subExpr0 = (Expr*)expr->getChild(0);
        auto subExpr1 = (Expr*)expr->getChild(1);
        // Check15: For logical opcode, both operand need to be bool
        if (expr->opcode() == Expr::And || expr->opcode() == Expr::Or) {
            if (!subExpr0->exprType().isBool() || !subExpr1->exprType().isBool())
                Errors.push_back(ErrorMessage("\"&&\"/\"||\" opcode must have bool operand!", expr->srcLoc()));
        } else if (expr->opcode() == Expr::Equal || expr->opcode() == Expr::NotEqual) {
            // Check16: For equal and not equal opcode, both operand need to be the same primitive types
            if (subExpr0->exprType() != subExpr1->exprType() || subExpr0->exprType().arrayBound() || subExpr1->exprType().arrayBound())
                Errors.push_back(ErrorMessage("\"==\"/\"!=\" opcode must have same primitive type operand!", expr->srcLoc()));
        } else {
            // Check17: For arithmetic and other comparison operand, both operand need to be int
            if (!subExpr0->exprType().isInt() || !subExpr1->exprType().isInt())
                Errors.push_back(ErrorMessage("\"" + Expr::opcodeToString(expr->opcode()) + "\" opcode must have int type operand!", expr->srcLoc()));
        }
        if (expr->opcode() == Expr::Equal || expr->opcode() == Expr::NotEqual
            || expr->opcode() == Expr::Greater || expr->opcode() == Expr::GreaterEqual
            || expr->opcode() == Expr::Less || expr->opcode() == Expr::LessEqual)
            expr->setExprType(Type(Type::Bool));
        else
            expr->setExprType(subExpr0->exprType());
    }

    void VerifyAndBuildSymbols::visitCallExpr(CallExpr *expr) {
        ASTVisitor::visitCallExpr(expr);
        std::string calleeName = expr->callee()->name();
        Program* root = expr->root();
        // Check18: Call undeclared function
        if (!root->funcTable()->containsFunc(calleeName)) {
            Errors.push_back(ErrorMessage("Function " + calleeName + "() is not declared before use!", expr->srcLoc()));
            return;
        }
        size_t n = expr->numArgs();
        FuncSymbolEntry entry = root->funcTable()->get(calleeName);
        // Check19: The number of arguments must match the number of parameters
        if (entry.ParameterTypes.size() != n) {
            Errors.push_back(ErrorMessage("Function " + calleeName + "() is declared with " + std::to_string(entry.ParameterTypes.size()) + " parameters but called with " + std::to_string(n) + " arguments!", expr->srcLoc()));
        }
        else {
            for (size_t i = 0; i < n; i++) {
                Expr* argExpr = expr->arg(i);
                // Check20: The type of each parameter must match the argument
                if (entry.ParameterTypes[i] != argExpr->exprType())
                    Errors.push_back(ErrorMessage("Function " + calleeName + "() does not match the type of the call argument at position " + std::to_string(i) + "!", expr->srcLoc()));
            }
        }
        expr->setExprType(entry.ReturnType);
    }

    static Type verifyVarReference(std::vector<ErrorMessage> & Errors, Expr* expr, VarReference *ref) {
        // Check 21: Must reference declared variable
        VarSymbolTable* table = expr->locateDeclaringTableForVar(ref->identifier()->name());
        if (table == nullptr) {
            Errors.push_back(ErrorMessage("Variable " + ref->identifier()->name() + " is not declared before use!", expr->srcLoc()));
            return Type(Type::Void);
        }
        VarSymbolEntry entry = table->get(ref->identifier()->name());
        Type varType = entry.VarType;
        if (ref->isArray()) {
            Expr *index = ref->indexExpr();
            // Check 22: Index expression must have int type.
            if (!index->exprType().isInt())
                Errors.push_back(ErrorMessage("Array index expressions must have int operand!", index->srcLoc()));
            // Check 23: Variable must be declared as an array for indexing
            if (varType.arrayBound() == 0)
                Errors.push_back(ErrorMessage("Indexing an non-array variable!", expr->srcLoc()));
            return varType.getIndexedType();
        }
        else
            return varType;
    }

    void VerifyAndBuildSymbols::visitVarExpr(VarExpr *expr) {
        ASTVisitor::visitVarExpr(expr);
        auto ref = (VarReference*) expr->getChild(0);
        Type varType = verifyVarReference(Errors, expr, ref);
        expr->setExprType(varType);
    }

    void VerifyAndBuildSymbols::visitAssignmentExpr(AssignmentExpr *expr) {
        ASTVisitor::visitAssignmentExpr(expr);
        auto ref = (VarReference*) expr->getChild(0);
        Type varType = verifyVarReference(Errors, expr, ref);
        auto subExpr = (Expr*) expr->getChild(1);
        // Check 24: Var and assigned expression must have the same type
        if (varType != subExpr->exprType())
            Errors.push_back(ErrorMessage("Variable and the assignment expression do not have the same type!", expr->srcLoc()));
        expr->setExprType(varType);
    }

    void VerifyAndBuildSymbols::visitIntLiteralExpr(IntLiteralExpr *literal) {
        ASTVisitor::visitIntLiteralExpr(literal);
        // Check 25: Integer literal must be inside the range of int
        if (literal->exprType().isVoid()) {
            Errors.push_back(ErrorMessage("Integer literal must be inside the range of int!", literal->srcLoc()));
        }
        literal->setExprType(Type(Type::Int));
    }

    void VerifyAndBuildSymbols::visitBoolLiteralExpr(BoolLiteralExpr *literal) {
        ASTVisitor::visitBoolLiteralExpr(literal);
        assert( literal->exprType() == Type(Type::Bool));
    }

    std::string VerifyAndBuildSymbols::genErrorMessages() {
        std::stringbuf buf;
        std::ostream os(&buf);

        for (size_t i = 0; i < Errors.size(); i++) {
            os << Errors[i].Msg << " (" << Errors[i].SrcLoc.Line << ":" << Errors[i].SrcLoc.Row << ")\n";
        }

        return buf.str();
    }

}