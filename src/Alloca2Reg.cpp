//
// Created by Fan Long on 2020/12/9.
//

//add more header files if your want
#include "llvm/Pass.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/Instructions.h"
#include "llvm/IR/Value.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/IR/CFG.h"
#include "llvm/Support/Casting.h"
#include "llvm/IR/Constants.h"
#include "llvm/IR/BasicBlock.h"
#include "iostream"
#include <set>
#include <map>

using namespace llvm;

namespace {
    struct Alloca2RegPass : public FunctionPass {
        static char ID;
        Alloca2RegPass() : FunctionPass(ID) {}

        //declare more varibles if you like
        std::set<AllocaInst*> TargetAllocas;
        std::map<BasicBlock*, std::map<AllocaInst*, Value*> > Post;
        std::map<BasicBlock*, std::map<AllocaInst*, PHINode*> > Pre;

        void collectTargetAllocas(Function &F) {
            //start your code here 
            for (auto bb = F.begin(); bb != F.end(); ++bb) {
                for (auto it = (*bb).begin(); it != (*bb).end(); ++it) {
                    auto in = &*(it);
                    if (llvm::isa<llvm::AllocaInst>(in) && !((llvm::AllocaInst*)in)->getAllocatedType()->isArrayTy()) {
                        TargetAllocas.insert((llvm::AllocaInst*)in);
                    }
                }
            }
        }

        void recordReplaceLoadAndPopulatePrePost(Function &F) {
            for (auto bb = F.begin(); bb != F.end(); ++bb) {
                std::map<llvm::Value*, llvm::Value*> tempToStore;
                tempToStore.clear();

                auto tmp = *&bb;

                // std::cout << "in block" << tmp->getName().str() << std::endl;

                auto numPred = 0;

                while ((*&bb)->hasNPredecessorsOrMore(numPred)) {
                    numPred++;
                }

                for (auto it = (*bb).begin(); it != (*bb).end(); ++it) {
                    auto in = &*(it);
                    llvm::Value* op;

                    if (in->getOpcode() == llvm::Instruction::Store) {
                        op = ((llvm::StoreInst*)in)->getPointerOperand();
                    }
                    if (in->getOpcode() == llvm::Instruction::Load) {
                        op = ((llvm::LoadInst*)in)->getPointerOperand();
                    }

                    if (in->getOpcode() == llvm::Instruction::Store && TargetAllocas.count((llvm::AllocaInst*)op)) {
                        auto s = (llvm::StoreInst*)in;
                        auto val = s->getValueOperand();
                        tempToStore[op] = val;
                        // std::cout << tempToStore[op] << " adding to temptostore hahaha " << tempToStore.size() << std::endl;

                    }
                    if (in->getOpcode() == llvm::Instruction::Load && tempToStore.count(op)) {
                        auto l = (llvm::LoadInst*)in;
                        in->replaceAllUsesWith(tempToStore[op]);
                    }
                }

                if ((&*bb)->isEntryBlock()) {
                    for (auto alloc = TargetAllocas.begin(); alloc != TargetAllocas.end(); ++alloc) {
                        auto a = (llvm::AllocaInst*)(*alloc);
                        Pre[&*bb][a] = (llvm::PHINode *)llvm::UndefValue::get(a->getAllocatedType());
                    }
                } else {
                    for (auto alloc = TargetAllocas.begin(); alloc != TargetAllocas.end(); ++alloc) {
                        auto a = (llvm::AllocaInst*)(*alloc);
                        Pre[&*bb][a] = llvm::PHINode::Create(a->getAllocatedType(), numPred);
                    }
                }

                for (auto it = (*bb).begin(); it != (*bb).end(); ++it) {
                    auto in = &*(it);
                    if (in->getOpcode() == llvm::Instruction::Store) {
                        auto s = (llvm::StoreInst*)in;
                        auto op = s->getPointerOperand();
                        
                        if (TargetAllocas.count((llvm::AllocaInst*)op)) {
                            auto val = s->getValueOperand();
                            Post[&*bb][(llvm::AllocaInst*)op] = val;
                        }
                    }

                    if (in->getOpcode() == llvm::Instruction::Load) {
                        auto op = ((llvm::LoadInst*)in)->getPointerOperand();
                        if (TargetAllocas.count((AllocaInst*)op)) {
                            auto l = (llvm::LoadInst*)in;
                            in->replaceAllUsesWith(Pre[&*bb][(llvm::AllocaInst*)op]);
                        }
                    }
                }

                for (auto alloc = TargetAllocas.begin(); alloc != TargetAllocas.end(); ++alloc) {
                    if (!Post[&*bb][*alloc]) {
                        Post[&*bb][*alloc] = Pre[&*bb][*alloc];
                    }
                }
            }
        }

        void fillEdgesAndInsertPHI(Function &F) {
            for (auto bb = F.begin(); bb != F.end(); ++bb) {
                if ((&*bb)->isEntryBlock()) {
                    continue;
                }

                for (auto alloc = TargetAllocas.begin(); alloc != TargetAllocas.end(); ++alloc) {
                    auto a = (llvm::AllocaInst*)(*alloc);
                    for (auto pred = llvm::pred_begin(&*bb); pred != llvm::pred_end(&*bb); ++pred) {
                        Pre[&*bb][a]->addIncoming(Post[*pred][a], *pred);
                    }
                    (&*bb)->getInstList().insert((&*bb)->getFirstInsertionPt(), Pre[&*bb][a]);
                }
            }
        }

        void removePHI(Function &F) {
            auto removed = 4;

            while (removed > 0) {
                removed = 0;
                for (auto bb = F.begin(); bb != F.end(); ++bb) {
                    for (auto it = (*bb).begin(); it != (*bb).end(); ++it) {
                        auto in = &*(it);
                        if (in->getOpcode() == llvm::Instruction::PHI) {
                            if (in->getNumUses() == 0) {
                                it--;
                                in->eraseFromParent();
                                removed++;
                            }
                        }
                    }
                }
            }
            
            for (auto bb = F.begin(); bb != F.end(); ++bb) {
                for (auto it = (*bb).begin(); it != (*bb).end(); ++it) {
                    auto in = &*(it);
                    if (in->getOpcode() == llvm::Instruction::PHI) {
                        auto phi = (llvm::PHINode*)in;
                        auto val = phi->getIncomingValue(0);
                        auto i = 0;

                        for (i = 0; i < phi->getNumIncomingValues(); i++) {
                            if (val != phi->getIncomingValue(i)) {
                                break;
                            }
                        }

                        //all the same incoming values
                        if (i == phi->getNumIncomingValues()) {
                            phi->replaceAllUsesWith(val);
                            it--;
                            phi->eraseFromParent();
                        }
                    }
                }
            }

        }

        void removeLoadStoreAlloca(Function &F) {
            for (auto bb = F.begin(); bb != F.end(); ++bb) {
                for (auto it = (*bb).begin(); it != (*bb).end(); ++it) {
                    auto in = &*(it);
                    llvm::Value* op;

                    if (in->getOpcode() == llvm::Instruction::Store) {
                        op = ((llvm::StoreInst*)in)->getPointerOperand();
                    }
                    if (in->getOpcode() == llvm::Instruction::Load) {
                        op = ((llvm::LoadInst*)in)->getPointerOperand();
                    }

                    if (TargetAllocas.count((llvm::AllocaInst*)op) && (in->getOpcode() == llvm::Instruction::Store || in->getOpcode() == llvm::Instruction::Load)) {
                        it--;
                        in->eraseFromParent();
                    }
                    
                    if (in->getOpcode() == llvm::Instruction::Alloca && TargetAllocas.count((llvm::AllocaInst*)in)) {
                        it--;
                        in->eraseFromParent();
                    }
                }
            }
        }

        virtual bool runOnFunction(Function &F) {
            TargetAllocas.clear();
            Post.clear();
            Pre.clear();

            errs() << "Working on function called " << F.getName() << "!\n";
            collectTargetAllocas(F);
            //start your code here
            recordReplaceLoadAndPopulatePrePost(F);
            fillEdgesAndInsertPHI(F);
            removeLoadStoreAlloca(F);
            removePHI(F);

            return true;
        }
    };
}

char Alloca2RegPass::ID = 0;

static RegisterPass<Alloca2RegPass> X("alloca2reg", "Alloca-to-register pass for minic", false, false);

// Automatically enable the pass.
// http://adriansampson.net/blog/clangpass.html
static void registerAlloca2RegPass(const PassManagerBuilder &,
                                    legacy::PassManagerBase &PM) {
    PM.add(new Alloca2RegPass());
}
static RegisterStandardPasses
        RegisterMyPass(PassManagerBuilder::EP_EarlyAsPossible,
                       registerAlloca2RegPass);
