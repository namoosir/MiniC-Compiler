//
// Created by Fan Long on 12/2/20.
//
#include "GrammarCompareChecker.h"
#include "antlr4-runtime.h"
#include "grammars/MiniC/MiniCLexer.h"
#include "grammars/MiniC/MiniCParser.h"
#include <iostream>

using namespace antlr4;

namespace minicc {
    antlrcpp::Any GrammarCompareChecker::visitExpr(minicc::MiniCParser::ExprContext *exprContext) {
        if (exprContext->compare) {
            auto left = (minicc::MiniCParser::ExprContext*) exprContext->children[0];
            auto right = (minicc::MiniCParser::ExprContext*) exprContext->children[2];
            if (left->compare || right->compare) {
                // Valid = false;
                // std::cerr << "Compare operators cannot associate with each other (" << exprContext->start->getLine() << ":" << exprContext->start->getCharPositionInLine() << ") !\n";
            }
        }
        return MiniCBaseVisitor::visitExpr(exprContext);
    }
}
