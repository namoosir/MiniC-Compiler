//
// Created by Fan Long on 2020/12/5.
//

#ifndef MINICC_SYMBOLTABLE_H
#define MINICC_SYMBOLTABLE_H

#include "Types.h"
#include <cassert>
#include <map>

namespace llvm {
    class Value;
}

namespace minicc {


    struct VarSymbolEntry {
        Type VarType;
        llvm::Value *LLVMValue;

        explicit VarSymbolEntry(Type varType) : VarType(varType), LLVMValue(nullptr) { }
    };

    class VarSymbolTable {

        std::map<std::string, VarSymbolEntry> Table;

    public:
        bool containsVar(const std::string &name) const {
            return Table.count(name) != 0;
        }

        void addVariableSymbol(const std::string &name, Type varType) {
            Table.insert(std::make_pair(name, VarSymbolEntry(varType)));
        }

        VarSymbolEntry get(const std::string &name) {
            auto it = Table.find(name);
            assert( it != Table.end());
            return it->second;
        }

        void setLLVMValue(const std::string &name, llvm::Value* val) {
            auto it = Table.find(name);
            assert( it != Table.end());
            it->second.LLVMValue = val;
        }
    };

    struct FuncSymbolEntry {
        Type ReturnType;
        std::vector<Type> ParameterTypes;
        bool HasBody;

        FuncSymbolEntry(Type retType, const std::vector<Type> &paraTypes, bool hasBody) : ReturnType(retType), ParameterTypes(paraTypes), HasBody(hasBody) { }
    };

    class FuncSymbolTable {
        std::map<std::string, FuncSymbolEntry> Table;
    public:
        typedef std::map<std::string, FuncSymbolEntry>::iterator Iterator;

        bool containsFunc(const std::string &name) const {
            return Table.count(name) != 0;
        }

        void addFunctionSymbol(const std::string &name, Type retType, const std::vector<Type> &paraTypes, bool hasBody) {
            Table.insert(std::make_pair(name, FuncSymbolEntry(retType, paraTypes, hasBody)));
        }

        void setFunctionBody(const std::string &name, bool flag) {
            auto it = Table.find(name);
            assert( it != Table.end());
            it->second.HasBody = flag;
        }

        FuncSymbolEntry get(const std::string &name) {
            auto it = Table.find(name);
            assert( it != Table.end());
            return it->second;
        }

        Iterator begin() {
            return Table.begin();
        }

        Iterator end() {
            return Table.end();
        }
    };
}

#endif //MINICC_SYMBOLTABLE_H
