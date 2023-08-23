//
// Created by Fan Long on 2020/12/6.
//

#ifndef MINICC_IRGENERATOR_H
#define MINICC_IRGENERATOR_H

#include "ASTVisitor.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/LLVMContext.h"
#include "Program.h"

namespace minicc {

    class IRGenerator : public ASTVisitor {
        std::unique_ptr<llvm::LLVMContext> TheContext;
        std::unique_ptr<llvm::Module> TheModule;
        std::unique_ptr<llvm::IRBuilder<>> TheBuilder;
        std::string ModuleName;

        llvm::Function* EnclosingFunction;
        llvm::BasicBlock* EnclosingForExitBB;
        std::map<Expr*, llvm::Value*> ValueForExpr;

        llvm::Type* toLLVMType(Type t);

        std::vector<llvm::Type*> toLLVMTypes(std::vector<Type> types);

        llvm::Function* findOrGenFunctionProto(const std::string &name, Program* root);
    public:
        explicit IRGenerator(const std::string moduleName) : ASTVisitor(), TheModule(), TheBuilder(), ModuleName(moduleName), EnclosingFunction(nullptr), EnclosingForExitBB(nullptr), ValueForExpr() {
            TheContext = std::make_unique<llvm::LLVMContext>();
        }

        std::unique_ptr<llvm::Module> getModule() {
            TheBuilder.reset();
            return std::move(TheModule);
        }

        void visitProgram(Program* prog) override;

        void visitVarDecl(VarDeclaration *decl) override;

        void visitFuncDecl(FuncDeclaration *func) override;

        void visitIfStmt(IfStatement *stmt) override;

        void visitForStmt(ForStatement *stmt) override;

        void visitWhileStmt(WhileStatement *stmt) override;

        void visitReturnStmt(ReturnStatement *stmt) override;

        void visitBreakStmt(BreakStatement *stmt) override;

        void visitScope(ScopeStatement *stmt) override;

        void visitUnaryExpr(UnaryExpr *expr) override;

        void visitBinaryExpr(BinaryExpr *expr) override;

        void visitCallExpr(CallExpr *expr) override;

        void visitVarExpr(VarExpr *expr) override;

        void visitAssignmentExpr(AssignmentExpr *expr) override;

        void visitIntLiteralExpr(IntLiteralExpr *literal) override;

        void visitBoolLiteralExpr(BoolLiteralExpr *literal) override;
    };
}

#endif //MINICC_IRGENERATOR_H
