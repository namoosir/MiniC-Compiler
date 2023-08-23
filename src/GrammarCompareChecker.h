//
// Created by Fan Long on 12/2/20.
//

#ifndef MINICC_GRAMMARCOMPARECHECKER_H
#define MINICC_GRAMMARCOMPARECHECKER_H

#include "grammars/MiniC/MiniCBaseVisitor.h"

namespace minicc {
    class GrammarCompareChecker : public MiniCBaseVisitor {
    public:
        bool Valid;

        GrammarCompareChecker() : Valid(true) { }

        antlrcpp::Any visitExpr(MiniCParser::ExprContext *exprContext) override;
    };
}
#endif //MINICC_GRAMMARCOMPARECHECKER_H