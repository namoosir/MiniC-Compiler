//
// Created by Fan Long on 2020/12/4.
//
#include "ASTNode.h"
#include "Program.h"
#include "Statements.h"
#include "Declarations.h"
#include <typeinfo>

namespace minicc {
    bool ASTNode::isScope() {
        return typeid(*this) == typeid(Program) || typeid(*this) == typeid(ScopeStatement);
    }

    bool ASTNode::isForStatement() {
        return typeid(*this) == typeid(ForStatement);
    }

    bool ASTNode::isWhileStatement() {
        return typeid(*this) == typeid(WhileStatement);
    }

    bool ASTNode::isFuncDecl() {
        return typeid(*this) == typeid(FuncDeclaration);
    }

    bool ASTNode::isProgram() {
        return typeid(*this) == typeid(Program);
    }

    bool ASTNode::isReturn() {
        return typeid(*this) == typeid(ReturnStatement);
    }
}

