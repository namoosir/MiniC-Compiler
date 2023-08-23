grammar MiniC;

@header {
#include <vector>
#include "Program.h"
#include "Declarations.h"
#include "Statements.h"
#include "Exprs.h"
#include "Terms.h"
#include <iostream>
}

prog returns [minicc::Program *val]
@init {
    $val = new minicc::Program();
    $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
}
:	(preamble { $val->setSyslibFlag(true); } | { $val->setSyslibFlag(false); } ) (decl {$val->addChild($decl.val); } )* EOF
;
preamble:  '#include' '"minicio.h"';
decl returns [minicc::Declaration *val] locals [minicc::FuncDeclaration *func]
:   vardecl {$val = $vardecl.val; }
    |   rettype funcname '(' parameters ')' scope {
            $func = new minicc::FuncDeclaration();
            $func->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
            $func->addChild($rettype.val);
            $func->addChild($funcname.val);
            for (size_t i = 0; i < $parameters.val.size(); i++)
                $func->addChild($parameters.val[i]);
            $func->addChild($scope.val);
            $func->setHasBody(true);
            $val = $func;
        }
    |   rettype funcname '(' parameters ')' ';' {
            $func = new minicc::FuncDeclaration();
            $func->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
            $func->addChild($rettype.val);
            $func->addChild($funcname.val);
            for (size_t i = 0; i < $parameters.val.size(); i++)
                $func->addChild($parameters.val[i]);
            $func->setHasBody(false);
            $val = $func;
        }
    ;
vardecl returns [minicc::VarDeclaration *val]
:   vartype varlist ';' {
        $val = new minicc::VarDeclaration();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($vartype.val);
        for (size_t i = 0; i < $varlist.val.size(); i++)
            $val->addChild($varlist.val[i]);
    }
    ;
scope returns [minicc::ScopeStatement *val] locals [size_t declcnt]
@init {
    $val = new minicc::ScopeStatement();
    $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    $declcnt = 0;
}
:  '{' (vardecl {
        $val->addChild($vardecl.val);
        $declcnt ++;
    })* (stmt {
        $val->addChild($stmt.val);
    })* '}' {
        $val->setNumVarDecl($declcnt);
    }
    ;
stmt returns [minicc::Statement *val]
:   expr ';' {
        $val = new minicc::ExprStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($expr.val);
    }
    |   'if' '(' expr ')' stmt {
        $val = new minicc::IfStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($expr.val);
        $val->addChild($stmt.val);
    }
    |   'if' '(' expr ')' s1=stmt 'else' s2=stmt {
        $val = new minicc::IfStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($expr.val);
        $val->addChild($s1.val);
        $val->addChild($s2.val);
    }
    |   'for' '(' e1=expropt ';' e2=expropt ';' e3=expropt ')' stmt {
        $val = new minicc::ForStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($e1.val);
        $val->addChild($e2.val);
        $val->addChild($e3.val);
        $val->addChild($stmt.val);
    }
    |   'while' '(' expr ')' stmt {
    $val = new minicc::WhileStatement();
    $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    $val->addChild($expr.val);
    $val->addChild($stmt.val);
    }
    |   'break' ';' {
        $val = new minicc::BreakStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   'return' ';' {
        $val = new minicc::ReturnStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   'return' expr ';' {
        $val = new minicc::ReturnStatement();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($expr.val);
    }
    |   scope {
        $val = $scope.val;
    }
    ;
varlist returns [std::vector<minicc::VarReference*> val]
:    varlistentry {
        $val.clear();
        $val.push_back($varlistentry.val);
    }
    |   v1=varlist ',' varlistentry {
        $val = $v1.val;
        $val.push_back($varlistentry.val);
    }
    ;
varlistentry returns [minicc::VarReference* val] locals [minicc::ConstantLiteralExpr *tmp]
:   varname {
        $val = new minicc::VarReference();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($varname.val);
    }
    |   varname '[' INT ']' {
        $val = new minicc::VarReference();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($varname.val);
        $tmp = minicc::ConstantLiteralExpr::fromString($INT.text);
        $tmp->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($tmp);
    }
    ;
vartype returns [minicc::TypeReference* val]
:    'int' {
        $val = new minicc::TypeReference(minicc::Type::Int);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   'bool' {
        $val = new minicc::TypeReference(minicc::Type::Bool);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    ;
rettype returns [minicc::TypeReference* val]
:    'void' {
        $val = new minicc::TypeReference(minicc::Type::Void);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   vartype {
        $val = $vartype.val;
    }
    ;
parameters returns [std::vector<minicc::Parameter*> val]
:     /* eps */ {
        $val.clear();
    }
    |   parameterlist {
        $val = $parameterlist.val;
    }
    ;
parameterlist returns [std::vector<minicc::Parameter*> val]
:     parameterentry {
        $val.clear();
        $val.push_back($parameterentry.val);
    }
    |   p1=parameterlist ',' parameterentry {
        $val=$p1.val;
        $val.push_back($parameterentry.val);
    }
    ;
parameterentry returns [minicc::Parameter* val]
:     vartype parametername {
        $val = new minicc::Parameter();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($vartype.val);
        $val->addChild($parametername.val);
    }
    ;
expropt returns [minicc::Expr *val]
:    expr {
        $val = $expr.val;
    }
    |   /* eps */ {
        $val = nullptr;
    }
    ;
expr returns [minicc::Expr *val, bool compare] locals [minicc::VarReference *tmp, bool minus_flag, minicc::Expr *inttmp]
@init{$minus_flag=false; $compare=false;}
:   INT {
        $val = minicc::ConstantLiteralExpr::fromString($INT.text);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   '-' expr {
        $minus_flag = true;
        if (typeid(*$expr.val) == typeid(minicc::IntLiteralExpr)){
            delete $expr.val;
            $expr.val = minicc::ConstantLiteralExpr::fromString($expr.text, $minus_flag);
            $expr.val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine()); 
        }
        $val = new minicc::UnaryExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::UnaryExpr*)$val)->setOpcode(minicc::Expr::Sub);
        $val->addChild($expr.val);
    }
    |   e1=expr op=('*'|'/') e2=expr {
        $val = new minicc::BinaryExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::BinaryExpr*)$val)->setOpcode(minicc::Expr::opcodeFromString($op.text));
        $val->addChild($e1.val);
        $val->addChild($e2.val);
    }
    |   e1=expr op=('+'|'-') e2=expr {
        $val = new minicc::BinaryExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::BinaryExpr*)$val)->setOpcode(minicc::Expr::opcodeFromString($op.text));
        $val->addChild($e1.val);
        $val->addChild($e2.val);
    }
    |   e1=expr op=('==' | '!=' | '<' | '<=' | '>' | '>=') e2=expr {
        $val = new minicc::BinaryExpr();
        $compare = true;
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::BinaryExpr*)$val)->setOpcode(minicc::Expr::opcodeFromString($op.text));
        $val->addChild($e1.val);
        $val->addChild($e2.val);
    }
    |   '!' expr {
        $val = new minicc::UnaryExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::UnaryExpr*)$val)->setOpcode(minicc::Expr::Not);
        $val->addChild($expr.val);
    }
    |   e1=expr '&&' e2=expr {
        $val = new minicc::BinaryExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::BinaryExpr*)$val)->setOpcode(minicc::Expr::And);
        $val->addChild($e1.val);
        $val->addChild($e2.val);
    }
    |   e1=expr '||' e2=expr {
        $val = new minicc::BinaryExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        ((minicc::BinaryExpr*)$val)->setOpcode(minicc::Expr::Or);
        $val->addChild($e1.val);
        $val->addChild($e2.val);
    }
    |   var '=' expr {
        $val = new minicc::AssignmentExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($var.val);
        $val->addChild($expr.val);
    }
    |   'true' {
        $val = minicc::ConstantLiteralExpr::fromString("true");
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   'false' {
        $val = minicc::ConstantLiteralExpr::fromString("false");
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    |   '(' expr ')' {
        $val = $expr.val;
    }
    |   var {
        $val = new minicc::VarExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($var.val);
    }
    |   funcname '(' arguments ')' {
        $val = new minicc::CallExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($funcname.val);
        for (size_t i = 0; i < $arguments.val.size(); i++)
            $val->addChild($arguments.val[i]);
    }
    |   parametername {
        $val = new minicc::VarExpr();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $tmp = new minicc::VarReference();
        $tmp->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $tmp->addChild($parametername.val);
        $val->addChild($tmp);
    }
    ;
var returns [minicc::VarReference *val]
:    varname {
        $val = new minicc::VarReference();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($varname.val);
    }
    |   varname '[' expr ']' {
        $val = new minicc::VarReference();
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
        $val->addChild($varname.val);
        $val->addChild($expr.val);
    }
    ;
arguments returns [std::vector<minicc::Expr*> val]
:   /* epsilon */ {
        $val.clear();
    }
    |   argumentlist {
        $val = $argumentlist.val;
    }
    ;
argumentlist returns [std::vector<minicc::Expr*> val]
:   expr {
        $val.clear();
        $val.push_back($expr.val);
    }
    |   a1=argumentlist ',' expr {
        $val=$a1.val;
        $val.push_back($expr.val);
    }
    ;
varname returns [minicc::Identifier* val]
:   ID {
        $val = new minicc::Identifier($ID.text);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    ;
funcname returns [minicc::Identifier* val]
:   ID {
        $val = new minicc::Identifier($ID.text);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    ;
parametername  returns [minicc::Identifier* val]
:   ID {
        $val = new minicc::Identifier($ID.text);
        $val->setSrcLoc($ctx->start->getLine(), $ctx->start->getCharPositionInLine());
    }
    ;

ID:     [a-zA-Z][a-zA-Z0-9_]* ;
INT:    [0] | ([1-9][0-9]*) ;
WS:     [ \t\r\n]+ -> skip;
COMMENT: '//' (~[\r\n])* -> skip;
