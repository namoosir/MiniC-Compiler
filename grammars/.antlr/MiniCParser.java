// Generated from /home/zhiychen/Documents/CSC488Student/A6Starter/grammars/MiniC.g4 by ANTLR 4.9.2

#include <vector>
#include "Program.h"
#include "Declarations.h"
#include "Statements.h"
#include "Exprs.h"
#include "Terms.h"
#include <iostream>

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, ID=36, INT=37, WS=38, COMMENT=39;
	public static final int
		RULE_prog = 0, RULE_preamble = 1, RULE_decl = 2, RULE_vardecl = 3, RULE_scope = 4, 
		RULE_stmt = 5, RULE_varlist = 6, RULE_varlistentry = 7, RULE_vartype = 8, 
		RULE_rettype = 9, RULE_parameters = 10, RULE_parameterlist = 11, RULE_parameterentry = 12, 
		RULE_expropt = 13, RULE_expr = 14, RULE_var = 15, RULE_arguments = 16, 
		RULE_argumentlist = 17, RULE_varname = 18, RULE_funcname = 19, RULE_parametername = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "preamble", "decl", "vardecl", "scope", "stmt", "varlist", "varlistentry", 
			"vartype", "rettype", "parameters", "parameterlist", "parameterentry", 
			"expropt", "expr", "var", "arguments", "argumentlist", "varname", "funcname", 
			"parametername"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'#include'", "'\"minicio.h\"'", "'('", "')'", "';'", "'{'", "'}'", 
			"'if'", "'else'", "'for'", "'while'", "'break'", "'return'", "','", "'['", 
			"']'", "'int'", "'bool'", "'void'", "'-'", "'*'", "'/'", "'+'", "'=='", 
			"'!='", "'<'", "'<='", "'>'", "'>='", "'!'", "'&&'", "'||'", "'='", "'true'", 
			"'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"ID", "INT", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public minicc::Program * val;
		public DeclContext decl;
		public TerminalNode EOF() { return getToken(MiniCParser.EOF, 0); }
		public PreambleContext preamble() {
			return getRuleContext(PreambleContext.class,0);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);

		    ((ProgContext)_localctx).val =  new minicc::Program();
		    _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(42);
				preamble();
				 _localctx.val->setSyslibFlag(true); 
				}
				break;
			case EOF:
			case T__16:
			case T__17:
			case T__18:
				{
				 _localctx.val->setSyslibFlag(false); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) {
				{
				{
				setState(48);
				((ProgContext)_localctx).decl = decl();
				_localctx.val->addChild(((ProgContext)_localctx).decl.val); 
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreambleContext extends ParserRuleContext {
		public PreambleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preamble; }
	}

	public final PreambleContext preamble() throws RecognitionException {
		PreambleContext _localctx = new PreambleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_preamble);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__0);
			setState(59);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public minicc::Declaration * val;
		public minicc::FuncDeclaration * func;
		public VardeclContext vardecl;
		public RettypeContext rettype;
		public FuncnameContext funcname;
		public ParametersContext parameters;
		public ScopeContext scope;
		public VardeclContext vardecl() {
			return getRuleContext(VardeclContext.class,0);
		}
		public RettypeContext rettype() {
			return getRuleContext(RettypeContext.class,0);
		}
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				((DeclContext)_localctx).vardecl = vardecl();
				((DeclContext)_localctx).val =  ((DeclContext)_localctx).vardecl.val; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				((DeclContext)_localctx).rettype = rettype();
				setState(65);
				((DeclContext)_localctx).funcname = funcname();
				setState(66);
				match(T__2);
				setState(67);
				((DeclContext)_localctx).parameters = parameters();
				setState(68);
				match(T__3);
				setState(69);
				((DeclContext)_localctx).scope = scope();

				            ((DeclContext)_localctx).func =  new minicc::FuncDeclaration();
				            _localctx.func->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				            _localctx.func->addChild(((DeclContext)_localctx).rettype.val);
				            _localctx.func->addChild(((DeclContext)_localctx).funcname.val);
				            for (size_t i = 0; i < ((DeclContext)_localctx).parameters.val.size(); i++)
				                _localctx.func->addChild(((DeclContext)_localctx).parameters.val[i]);
				            _localctx.func->addChild(((DeclContext)_localctx).scope.val);
				            _localctx.func->setHasBody(true);
				            ((DeclContext)_localctx).val =  _localctx.func;
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				((DeclContext)_localctx).rettype = rettype();
				setState(73);
				((DeclContext)_localctx).funcname = funcname();
				setState(74);
				match(T__2);
				setState(75);
				((DeclContext)_localctx).parameters = parameters();
				setState(76);
				match(T__3);
				setState(77);
				match(T__4);

				            ((DeclContext)_localctx).func =  new minicc::FuncDeclaration();
				            _localctx.func->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				            _localctx.func->addChild(((DeclContext)_localctx).rettype.val);
				            _localctx.func->addChild(((DeclContext)_localctx).funcname.val);
				            for (size_t i = 0; i < ((DeclContext)_localctx).parameters.val.size(); i++)
				                _localctx.func->addChild(((DeclContext)_localctx).parameters.val[i]);
				            _localctx.func->setHasBody(false);
				            ((DeclContext)_localctx).val =  _localctx.func;
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VardeclContext extends ParserRuleContext {
		public minicc::VarDeclaration * val;
		public VartypeContext vartype;
		public VarlistContext varlist;
		public VartypeContext vartype() {
			return getRuleContext(VartypeContext.class,0);
		}
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public VardeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardecl; }
	}

	public final VardeclContext vardecl() throws RecognitionException {
		VardeclContext _localctx = new VardeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_vardecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			((VardeclContext)_localctx).vartype = vartype();
			setState(83);
			((VardeclContext)_localctx).varlist = varlist(0);
			setState(84);
			match(T__4);

			        ((VardeclContext)_localctx).val =  new minicc::VarDeclaration();
			        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
			        _localctx.val->addChild(((VardeclContext)_localctx).vartype.val);
			        for (size_t i = 0; i < ((VardeclContext)_localctx).varlist.val.size(); i++)
			            _localctx.val->addChild(((VardeclContext)_localctx).varlist.val[i]);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public minicc::ScopeStatement * val;
		public size_t declcnt;
		public VardeclContext vardecl;
		public StmtContext stmt;
		public List<VardeclContext> vardecl() {
			return getRuleContexts(VardeclContext.class);
		}
		public VardeclContext vardecl(int i) {
			return getRuleContext(VardeclContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scope);

		    ((ScopeContext)_localctx).val =  new minicc::ScopeStatement();
		    _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
		    ((ScopeContext)_localctx).declcnt =  0;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__5);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16 || _la==T__17) {
				{
				{
				setState(88);
				((ScopeContext)_localctx).vardecl = vardecl();

				        _localctx.val->addChild(((ScopeContext)_localctx).vardecl.val);
				        _localctx.declcnt ++;
				    
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__19) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << ID) | (1L << INT))) != 0)) {
				{
				{
				setState(96);
				((ScopeContext)_localctx).stmt = stmt();

				        _localctx.val->addChild(((ScopeContext)_localctx).stmt.val);
				    
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(T__6);

			        _localctx.val->setNumVarDecl(_localctx.declcnt);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public minicc::Statement * val;
		public ExprContext expr;
		public StmtContext stmt;
		public StmtContext s1;
		public StmtContext s2;
		public ExproptContext e1;
		public ExproptContext e2;
		public ExproptContext e3;
		public ScopeContext scope;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<ExproptContext> expropt() {
			return getRuleContexts(ExproptContext.class);
		}
		public ExproptContext expropt(int i) {
			return getRuleContext(ExproptContext.class,i);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stmt);
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				((StmtContext)_localctx).expr = expr(0);
				setState(108);
				match(T__4);

				        ((StmtContext)_localctx).val =  new minicc::ExprStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((StmtContext)_localctx).expr.val);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(T__7);
				setState(112);
				match(T__2);
				setState(113);
				((StmtContext)_localctx).expr = expr(0);
				setState(114);
				match(T__3);
				setState(115);
				((StmtContext)_localctx).stmt = stmt();

				        ((StmtContext)_localctx).val =  new minicc::IfStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((StmtContext)_localctx).expr.val);
				        _localctx.val->addChild(((StmtContext)_localctx).stmt.val);
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(T__7);
				setState(119);
				match(T__2);
				setState(120);
				((StmtContext)_localctx).expr = expr(0);
				setState(121);
				match(T__3);
				setState(122);
				((StmtContext)_localctx).s1 = stmt();
				setState(123);
				match(T__8);
				setState(124);
				((StmtContext)_localctx).s2 = stmt();

				        ((StmtContext)_localctx).val =  new minicc::IfStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((StmtContext)_localctx).expr.val);
				        _localctx.val->addChild(((StmtContext)_localctx).s1.val);
				        _localctx.val->addChild(((StmtContext)_localctx).s2.val);
				    
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				match(T__9);
				setState(128);
				match(T__2);
				setState(129);
				((StmtContext)_localctx).e1 = expropt();
				setState(130);
				match(T__4);
				setState(131);
				((StmtContext)_localctx).e2 = expropt();
				setState(132);
				match(T__4);
				setState(133);
				((StmtContext)_localctx).e3 = expropt();
				setState(134);
				match(T__3);
				setState(135);
				((StmtContext)_localctx).stmt = stmt();

				        ((StmtContext)_localctx).val =  new minicc::ForStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((StmtContext)_localctx).e1.val);
				        _localctx.val->addChild(((StmtContext)_localctx).e2.val);
				        _localctx.val->addChild(((StmtContext)_localctx).e3.val);
				        _localctx.val->addChild(((StmtContext)_localctx).stmt.val);
				    
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				match(T__10);
				setState(139);
				match(T__2);
				setState(140);
				((StmtContext)_localctx).expr = expr(0);
				setState(141);
				match(T__3);
				setState(142);
				((StmtContext)_localctx).stmt = stmt();

				    ((StmtContext)_localctx).val =  new minicc::WhileStatement();
				    _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    _localctx.val->addChild(((StmtContext)_localctx).expr.val);
				    _localctx.val->addChild(((StmtContext)_localctx).stmt.val);
				    
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(145);
				match(T__11);
				setState(146);
				match(T__4);

				        ((StmtContext)_localctx).val =  new minicc::BreakStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(148);
				match(T__12);
				setState(149);
				match(T__4);

				        ((StmtContext)_localctx).val =  new minicc::ReturnStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(151);
				match(T__12);
				setState(152);
				((StmtContext)_localctx).expr = expr(0);
				setState(153);
				match(T__4);

				        ((StmtContext)_localctx).val =  new minicc::ReturnStatement();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((StmtContext)_localctx).expr.val);
				    
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(156);
				((StmtContext)_localctx).scope = scope();

				        ((StmtContext)_localctx).val =  ((StmtContext)_localctx).scope.val;
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarlistContext extends ParserRuleContext {
		public std::vector<minicc::VarReference*> val;
		public VarlistContext v1;
		public VarlistentryContext varlistentry;
		public VarlistentryContext varlistentry() {
			return getRuleContext(VarlistentryContext.class,0);
		}
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public VarlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlist; }
	}

	public final VarlistContext varlist() throws RecognitionException {
		return varlist(0);
	}

	private VarlistContext varlist(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VarlistContext _localctx = new VarlistContext(_ctx, _parentState);
		VarlistContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_varlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(162);
			((VarlistContext)_localctx).varlistentry = varlistentry();

			        _localctx.val.clear();
			        _localctx.val.push_back(((VarlistContext)_localctx).varlistentry.val);
			    
			}
			_ctx.stop = _input.LT(-1);
			setState(172);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new VarlistContext(_parentctx, _parentState);
					_localctx.v1 = _prevctx;
					_localctx.v1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_varlist);
					setState(165);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(166);
					match(T__13);
					setState(167);
					((VarlistContext)_localctx).varlistentry = varlistentry();

					                  ((VarlistContext)_localctx).val =  ((VarlistContext)_localctx).v1.val;
					                  _localctx.val.push_back(((VarlistContext)_localctx).varlistentry.val);
					              
					}
					} 
				}
				setState(174);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VarlistentryContext extends ParserRuleContext {
		public minicc::VarReference* val;
		public minicc::ConstantLiteralExpr * tmp;
		public VarnameContext varname;
		public Token INT;
		public VarnameContext varname() {
			return getRuleContext(VarnameContext.class,0);
		}
		public TerminalNode INT() { return getToken(MiniCParser.INT, 0); }
		public VarlistentryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlistentry; }
	}

	public final VarlistentryContext varlistentry() throws RecognitionException {
		VarlistentryContext _localctx = new VarlistentryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varlistentry);
		try {
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				((VarlistentryContext)_localctx).varname = varname();

				        ((VarlistentryContext)_localctx).val =  new minicc::VarReference();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((VarlistentryContext)_localctx).varname.val);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				((VarlistentryContext)_localctx).varname = varname();
				setState(179);
				match(T__14);
				setState(180);
				((VarlistentryContext)_localctx).INT = match(INT);
				setState(181);
				match(T__15);

				        ((VarlistentryContext)_localctx).val =  new minicc::VarReference();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((VarlistentryContext)_localctx).varname.val);
				        ((VarlistentryContext)_localctx).tmp =  minicc::ConstantLiteralExpr::fromString((((VarlistentryContext)_localctx).INT!=null?((VarlistentryContext)_localctx).INT.getText():null));
				        _localctx.tmp->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(_localctx.tmp);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VartypeContext extends ParserRuleContext {
		public minicc::TypeReference* val;
		public VartypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vartype; }
	}

	public final VartypeContext vartype() throws RecognitionException {
		VartypeContext _localctx = new VartypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_vartype);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				match(T__16);

				        ((VartypeContext)_localctx).val =  new minicc::TypeReference(minicc::Type::Int);
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				match(T__17);

				        ((VartypeContext)_localctx).val =  new minicc::TypeReference(minicc::Type::Bool);
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RettypeContext extends ParserRuleContext {
		public minicc::TypeReference* val;
		public VartypeContext vartype;
		public VartypeContext vartype() {
			return getRuleContext(VartypeContext.class,0);
		}
		public RettypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rettype; }
	}

	public final RettypeContext rettype() throws RecognitionException {
		RettypeContext _localctx = new RettypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rettype);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				match(T__18);

				        ((RettypeContext)_localctx).val =  new minicc::TypeReference(minicc::Type::Void);
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case T__16:
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				((RettypeContext)_localctx).vartype = vartype();

				        ((RettypeContext)_localctx).val =  ((RettypeContext)_localctx).vartype.val;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public std::vector<minicc::Parameter*> val;
		public ParameterlistContext parameterlist;
		public ParameterlistContext parameterlist() {
			return getRuleContext(ParameterlistContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameters);
		try {
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{

				        _localctx.val.clear();
				    
				}
				break;
			case T__16:
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				((ParametersContext)_localctx).parameterlist = parameterlist(0);

				        ((ParametersContext)_localctx).val =  ((ParametersContext)_localctx).parameterlist.val;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterlistContext extends ParserRuleContext {
		public std::vector<minicc::Parameter*> val;
		public ParameterlistContext p1;
		public ParameterentryContext parameterentry;
		public ParameterentryContext parameterentry() {
			return getRuleContext(ParameterentryContext.class,0);
		}
		public ParameterlistContext parameterlist() {
			return getRuleContext(ParameterlistContext.class,0);
		}
		public ParameterlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterlist; }
	}

	public final ParameterlistContext parameterlist() throws RecognitionException {
		return parameterlist(0);
	}

	private ParameterlistContext parameterlist(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ParameterlistContext _localctx = new ParameterlistContext(_ctx, _parentState);
		ParameterlistContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_parameterlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(206);
			((ParameterlistContext)_localctx).parameterentry = parameterentry();

			        _localctx.val.clear();
			        _localctx.val.push_back(((ParameterlistContext)_localctx).parameterentry.val);
			    
			}
			_ctx.stop = _input.LT(-1);
			setState(216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ParameterlistContext(_parentctx, _parentState);
					_localctx.p1 = _prevctx;
					_localctx.p1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_parameterlist);
					setState(209);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(210);
					match(T__13);
					setState(211);
					((ParameterlistContext)_localctx).parameterentry = parameterentry();

					                  ((ParameterlistContext)_localctx).val = ((ParameterlistContext)_localctx).p1.val;
					                  _localctx.val.push_back(((ParameterlistContext)_localctx).parameterentry.val);
					              
					}
					} 
				}
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParameterentryContext extends ParserRuleContext {
		public minicc::Parameter* val;
		public VartypeContext vartype;
		public ParameternameContext parametername;
		public VartypeContext vartype() {
			return getRuleContext(VartypeContext.class,0);
		}
		public ParameternameContext parametername() {
			return getRuleContext(ParameternameContext.class,0);
		}
		public ParameterentryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterentry; }
	}

	public final ParameterentryContext parameterentry() throws RecognitionException {
		ParameterentryContext _localctx = new ParameterentryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parameterentry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			((ParameterentryContext)_localctx).vartype = vartype();
			setState(220);
			((ParameterentryContext)_localctx).parametername = parametername();

			        ((ParameterentryContext)_localctx).val =  new minicc::Parameter();
			        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
			        _localctx.val->addChild(((ParameterentryContext)_localctx).vartype.val);
			        _localctx.val->addChild(((ParameterentryContext)_localctx).parametername.val);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExproptContext extends ParserRuleContext {
		public minicc::Expr * val;
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExproptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expropt; }
	}

	public final ExproptContext expropt() throws RecognitionException {
		ExproptContext _localctx = new ExproptContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expropt);
		try {
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__19:
			case T__29:
			case T__33:
			case T__34:
			case ID:
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				((ExproptContext)_localctx).expr = expr(0);

				        ((ExproptContext)_localctx).val =  ((ExproptContext)_localctx).expr.val;
				    
				}
				break;
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 2);
				{

				        ((ExproptContext)_localctx).val =  nullptr;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public minicc::Expr * val;
		public bool compare;
		public minicc::VarReference * tmp;
		public bool minus_flag;
		public minicc::Expr * inttmp;
		public ExprContext e1;
		public Token INT;
		public ExprContext expr;
		public VarContext var;
		public FuncnameContext funcname;
		public ArgumentsContext arguments;
		public ParameternameContext parametername;
		public Token op;
		public ExprContext e2;
		public TerminalNode INT() { return getToken(MiniCParser.INT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ParameternameContext parametername() {
			return getRuleContext(ParameternameContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		((ExprContext)_localctx).minus_flag = false; ((ExprContext)_localctx).compare = false;
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(230);
				((ExprContext)_localctx).INT = match(INT);

				        ((ExprContext)_localctx).val =  minicc::ConstantLiteralExpr::fromString((((ExprContext)_localctx).INT!=null?((ExprContext)_localctx).INT.getText():null));
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case 2:
				{
				setState(232);
				match(T__19);
				setState(233);
				((ExprContext)_localctx).expr = expr(14);

				        ((ExprContext)_localctx).minus_flag =  true;
				        if (typeid(*((ExprContext)_localctx).expr.val) == typeid(minicc::IntLiteralExpr)){
				            delete ((ExprContext)_localctx).expr.val;
				            ((ExprContext)_localctx).expr.val = minicc::ConstantLiteralExpr::fromString((((ExprContext)_localctx).expr!=null?_input.getText(((ExprContext)_localctx).expr.start,((ExprContext)_localctx).expr.stop):null), _localctx.minus_flag);
				            ((ExprContext)_localctx).expr.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine()); 
				        }
				        ((ExprContext)_localctx).val =  new minicc::UnaryExpr();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        ((minicc::UnaryExpr*)_localctx.val)->setOpcode(minicc::Expr::Sub);
				        _localctx.val->addChild(((ExprContext)_localctx).expr.val);
				    
				}
				break;
			case 3:
				{
				setState(236);
				match(T__29);
				setState(237);
				((ExprContext)_localctx).expr = expr(10);

				        ((ExprContext)_localctx).val =  new minicc::UnaryExpr();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        ((minicc::UnaryExpr*)_localctx.val)->setOpcode(minicc::Expr::Not);
				        _localctx.val->addChild(((ExprContext)_localctx).expr.val);
				    
				}
				break;
			case 4:
				{
				setState(240);
				((ExprContext)_localctx).var = var();
				setState(241);
				match(T__32);
				setState(242);
				((ExprContext)_localctx).expr = expr(7);

				        ((ExprContext)_localctx).val =  new minicc::AssignmentExpr();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((ExprContext)_localctx).var.val);
				        _localctx.val->addChild(((ExprContext)_localctx).expr.val);
				    
				}
				break;
			case 5:
				{
				setState(245);
				match(T__33);

				        ((ExprContext)_localctx).val =  minicc::ConstantLiteralExpr::fromString("true");
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case 6:
				{
				setState(247);
				match(T__34);

				        ((ExprContext)_localctx).val =  minicc::ConstantLiteralExpr::fromString("false");
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				    
				}
				break;
			case 7:
				{
				setState(249);
				match(T__2);
				setState(250);
				((ExprContext)_localctx).expr = expr(0);
				setState(251);
				match(T__3);

				        ((ExprContext)_localctx).val =  ((ExprContext)_localctx).expr.val;
				    
				}
				break;
			case 8:
				{
				setState(254);
				((ExprContext)_localctx).var = var();

				        ((ExprContext)_localctx).val =  new minicc::VarExpr();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((ExprContext)_localctx).var.val);
				    
				}
				break;
			case 9:
				{
				setState(257);
				((ExprContext)_localctx).funcname = funcname();
				setState(258);
				match(T__2);
				setState(259);
				((ExprContext)_localctx).arguments = arguments();
				setState(260);
				match(T__3);

				        ((ExprContext)_localctx).val =  new minicc::CallExpr();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((ExprContext)_localctx).funcname.val);
				        for (size_t i = 0; i < ((ExprContext)_localctx).arguments.val.size(); i++)
				            _localctx.val->addChild(((ExprContext)_localctx).arguments.val[i]);
				    
				}
				break;
			case 10:
				{
				setState(263);
				((ExprContext)_localctx).parametername = parametername();

				        ((ExprContext)_localctx).val =  new minicc::VarExpr();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        ((ExprContext)_localctx).tmp =  new minicc::VarReference();
				        _localctx.tmp->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.tmp->addChild(((ExprContext)_localctx).parametername.val);
				        _localctx.val->addChild(_localctx.tmp);
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(293);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(268);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(269);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(270);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(14);

						                  ((ExprContext)_localctx).val =  new minicc::BinaryExpr();
						                  _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
						                  ((minicc::BinaryExpr*)_localctx.val)->setOpcode(minicc::Expr::opcodeFromString((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null)));
						                  _localctx.val->addChild(((ExprContext)_localctx).e1.val);
						                  _localctx.val->addChild(((ExprContext)_localctx).e2.val);
						              
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(273);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(274);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__22) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(275);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(13);

						                  ((ExprContext)_localctx).val =  new minicc::BinaryExpr();
						                  _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
						                  ((minicc::BinaryExpr*)_localctx.val)->setOpcode(minicc::Expr::opcodeFromString((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null)));
						                  _localctx.val->addChild(((ExprContext)_localctx).e1.val);
						                  _localctx.val->addChild(((ExprContext)_localctx).e2.val);
						              
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(278);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(279);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(280);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(12);

						                  ((ExprContext)_localctx).val =  new minicc::BinaryExpr();
						                  ((ExprContext)_localctx).compare =  true;
						                  _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
						                  ((minicc::BinaryExpr*)_localctx.val)->setOpcode(minicc::Expr::opcodeFromString((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null)));
						                  _localctx.val->addChild(((ExprContext)_localctx).e1.val);
						                  _localctx.val->addChild(((ExprContext)_localctx).e2.val);
						              
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(283);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(284);
						match(T__30);
						setState(285);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(10);

						                  ((ExprContext)_localctx).val =  new minicc::BinaryExpr();
						                  _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
						                  ((minicc::BinaryExpr*)_localctx.val)->setOpcode(minicc::Expr::And);
						                  _localctx.val->addChild(((ExprContext)_localctx).e1.val);
						                  _localctx.val->addChild(((ExprContext)_localctx).e2.val);
						              
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(288);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(289);
						match(T__31);
						setState(290);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(9);

						                  ((ExprContext)_localctx).val =  new minicc::BinaryExpr();
						                  _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
						                  ((minicc::BinaryExpr*)_localctx.val)->setOpcode(minicc::Expr::Or);
						                  _localctx.val->addChild(((ExprContext)_localctx).e1.val);
						                  _localctx.val->addChild(((ExprContext)_localctx).e2.val);
						              
						}
						break;
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public minicc::VarReference * val;
		public VarnameContext varname;
		public ExprContext expr;
		public VarnameContext varname() {
			return getRuleContext(VarnameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_var);
		try {
			setState(307);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(298);
				((VarContext)_localctx).varname = varname();

				        ((VarContext)_localctx).val =  new minicc::VarReference();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((VarContext)_localctx).varname.val);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(301);
				((VarContext)_localctx).varname = varname();
				setState(302);
				match(T__14);
				setState(303);
				((VarContext)_localctx).expr = expr(0);
				setState(304);
				match(T__15);

				        ((VarContext)_localctx).val =  new minicc::VarReference();
				        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
				        _localctx.val->addChild(((VarContext)_localctx).varname.val);
				        _localctx.val->addChild(((VarContext)_localctx).expr.val);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public std::vector<minicc::Expr*> val;
		public ArgumentlistContext argumentlist;
		public ArgumentlistContext argumentlist() {
			return getRuleContext(ArgumentlistContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arguments);
		try {
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{

				        _localctx.val.clear();
				    
				}
				break;
			case T__2:
			case T__19:
			case T__29:
			case T__33:
			case T__34:
			case ID:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				((ArgumentsContext)_localctx).argumentlist = argumentlist(0);

				        ((ArgumentsContext)_localctx).val =  ((ArgumentsContext)_localctx).argumentlist.val;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentlistContext extends ParserRuleContext {
		public std::vector<minicc::Expr*> val;
		public ArgumentlistContext a1;
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgumentlistContext argumentlist() {
			return getRuleContext(ArgumentlistContext.class,0);
		}
		public ArgumentlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentlist; }
	}

	public final ArgumentlistContext argumentlist() throws RecognitionException {
		return argumentlist(0);
	}

	private ArgumentlistContext argumentlist(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgumentlistContext _localctx = new ArgumentlistContext(_ctx, _parentState);
		ArgumentlistContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_argumentlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(316);
			((ArgumentlistContext)_localctx).expr = expr(0);

			        _localctx.val.clear();
			        _localctx.val.push_back(((ArgumentlistContext)_localctx).expr.val);
			    
			}
			_ctx.stop = _input.LT(-1);
			setState(326);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArgumentlistContext(_parentctx, _parentState);
					_localctx.a1 = _prevctx;
					_localctx.a1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_argumentlist);
					setState(319);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(320);
					match(T__13);
					setState(321);
					((ArgumentlistContext)_localctx).expr = expr(0);

					                  ((ArgumentlistContext)_localctx).val = ((ArgumentlistContext)_localctx).a1.val;
					                  _localctx.val.push_back(((ArgumentlistContext)_localctx).expr.val);
					              
					}
					} 
				}
				setState(328);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VarnameContext extends ParserRuleContext {
		public minicc::Identifier* val;
		public Token ID;
		public TerminalNode ID() { return getToken(MiniCParser.ID, 0); }
		public VarnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varname; }
	}

	public final VarnameContext varname() throws RecognitionException {
		VarnameContext _localctx = new VarnameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_varname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			((VarnameContext)_localctx).ID = match(ID);

			        ((VarnameContext)_localctx).val =  new minicc::Identifier((((VarnameContext)_localctx).ID!=null?((VarnameContext)_localctx).ID.getText():null));
			        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncnameContext extends ParserRuleContext {
		public minicc::Identifier* val;
		public Token ID;
		public TerminalNode ID() { return getToken(MiniCParser.ID, 0); }
		public FuncnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcname; }
	}

	public final FuncnameContext funcname() throws RecognitionException {
		FuncnameContext _localctx = new FuncnameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_funcname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			((FuncnameContext)_localctx).ID = match(ID);

			        ((FuncnameContext)_localctx).val =  new minicc::Identifier((((FuncnameContext)_localctx).ID!=null?((FuncnameContext)_localctx).ID.getText():null));
			        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameternameContext extends ParserRuleContext {
		public minicc::Identifier* val;
		public Token ID;
		public TerminalNode ID() { return getToken(MiniCParser.ID, 0); }
		public ParameternameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametername; }
	}

	public final ParameternameContext parametername() throws RecognitionException {
		ParameternameContext _localctx = new ParameternameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parametername);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			((ParameternameContext)_localctx).ID = match(ID);

			        ((ParameternameContext)_localctx).val =  new minicc::Identifier((((ParameternameContext)_localctx).ID!=null?((ParameternameContext)_localctx).ID.getText():null));
			        _localctx.val->setSrcLoc(_localctx->start->getLine(), _localctx->start->getCharPositionInLine());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return varlist_sempred((VarlistContext)_localctx, predIndex);
		case 11:
			return parameterlist_sempred((ParameterlistContext)_localctx, predIndex);
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 17:
			return argumentlist_sempred((ArgumentlistContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean varlist_sempred(VarlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean parameterlist_sempred(ParameterlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean argumentlist_sempred(ArgumentlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u0155\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\5\2\61\n\2\3"+
		"\2\3\2\3\2\7\2\66\n\2\f\2\16\29\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4S\n"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\7\6^\n\6\f\6\16\6a\13\6\3\6\3\6"+
		"\3\6\7\6f\n\6\f\6\16\6i\13\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00a2\n\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u00ad\n\b\f\b\16\b\u00b0\13\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\u00bb\n\t\3\n\3\n\3\n\3\n\5\n\u00c1\n\n\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u00c8\n\13\3\f\3\f\3\f\3\f\5\f\u00ce\n\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00d9\n\r\f\r\16\r\u00dc\13\r\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00e6\n\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20\u010d\n\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\7\20\u0128\n\20\f\20\16\20\u012b\13\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0136\n\21\3\22\3\22\3\22"+
		"\3\22\5\22\u013c\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u0147\n\23\f\23\16\23\u014a\13\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\2\6\16\30\36$\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*\2\5\3\2\27\30\4\2\26\26\31\31\3\2\32\37\2\u0165\2\60\3\2\2\2\4"+
		"<\3\2\2\2\6R\3\2\2\2\bT\3\2\2\2\nY\3\2\2\2\f\u00a1\3\2\2\2\16\u00a3\3"+
		"\2\2\2\20\u00ba\3\2\2\2\22\u00c0\3\2\2\2\24\u00c7\3\2\2\2\26\u00cd\3\2"+
		"\2\2\30\u00cf\3\2\2\2\32\u00dd\3\2\2\2\34\u00e5\3\2\2\2\36\u010c\3\2\2"+
		"\2 \u0135\3\2\2\2\"\u013b\3\2\2\2$\u013d\3\2\2\2&\u014b\3\2\2\2(\u014e"+
		"\3\2\2\2*\u0151\3\2\2\2,-\5\4\3\2-.\b\2\1\2.\61\3\2\2\2/\61\b\2\1\2\60"+
		",\3\2\2\2\60/\3\2\2\2\61\67\3\2\2\2\62\63\5\6\4\2\63\64\b\2\1\2\64\66"+
		"\3\2\2\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\2"+
		"9\67\3\2\2\2:;\7\2\2\3;\3\3\2\2\2<=\7\3\2\2=>\7\4\2\2>\5\3\2\2\2?@\5\b"+
		"\5\2@A\b\4\1\2AS\3\2\2\2BC\5\24\13\2CD\5(\25\2DE\7\5\2\2EF\5\26\f\2FG"+
		"\7\6\2\2GH\5\n\6\2HI\b\4\1\2IS\3\2\2\2JK\5\24\13\2KL\5(\25\2LM\7\5\2\2"+
		"MN\5\26\f\2NO\7\6\2\2OP\7\7\2\2PQ\b\4\1\2QS\3\2\2\2R?\3\2\2\2RB\3\2\2"+
		"\2RJ\3\2\2\2S\7\3\2\2\2TU\5\22\n\2UV\5\16\b\2VW\7\7\2\2WX\b\5\1\2X\t\3"+
		"\2\2\2Y_\7\b\2\2Z[\5\b\5\2[\\\b\6\1\2\\^\3\2\2\2]Z\3\2\2\2^a\3\2\2\2_"+
		"]\3\2\2\2_`\3\2\2\2`g\3\2\2\2a_\3\2\2\2bc\5\f\7\2cd\b\6\1\2df\3\2\2\2"+
		"eb\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7\t\2\2"+
		"kl\b\6\1\2l\13\3\2\2\2mn\5\36\20\2no\7\7\2\2op\b\7\1\2p\u00a2\3\2\2\2"+
		"qr\7\n\2\2rs\7\5\2\2st\5\36\20\2tu\7\6\2\2uv\5\f\7\2vw\b\7\1\2w\u00a2"+
		"\3\2\2\2xy\7\n\2\2yz\7\5\2\2z{\5\36\20\2{|\7\6\2\2|}\5\f\7\2}~\7\13\2"+
		"\2~\177\5\f\7\2\177\u0080\b\7\1\2\u0080\u00a2\3\2\2\2\u0081\u0082\7\f"+
		"\2\2\u0082\u0083\7\5\2\2\u0083\u0084\5\34\17\2\u0084\u0085\7\7\2\2\u0085"+
		"\u0086\5\34\17\2\u0086\u0087\7\7\2\2\u0087\u0088\5\34\17\2\u0088\u0089"+
		"\7\6\2\2\u0089\u008a\5\f\7\2\u008a\u008b\b\7\1\2\u008b\u00a2\3\2\2\2\u008c"+
		"\u008d\7\r\2\2\u008d\u008e\7\5\2\2\u008e\u008f\5\36\20\2\u008f\u0090\7"+
		"\6\2\2\u0090\u0091\5\f\7\2\u0091\u0092\b\7\1\2\u0092\u00a2\3\2\2\2\u0093"+
		"\u0094\7\16\2\2\u0094\u0095\7\7\2\2\u0095\u00a2\b\7\1\2\u0096\u0097\7"+
		"\17\2\2\u0097\u0098\7\7\2\2\u0098\u00a2\b\7\1\2\u0099\u009a\7\17\2\2\u009a"+
		"\u009b\5\36\20\2\u009b\u009c\7\7\2\2\u009c\u009d\b\7\1\2\u009d\u00a2\3"+
		"\2\2\2\u009e\u009f\5\n\6\2\u009f\u00a0\b\7\1\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"m\3\2\2\2\u00a1q\3\2\2\2\u00a1x\3\2\2\2\u00a1\u0081\3\2\2\2\u00a1\u008c"+
		"\3\2\2\2\u00a1\u0093\3\2\2\2\u00a1\u0096\3\2\2\2\u00a1\u0099\3\2\2\2\u00a1"+
		"\u009e\3\2\2\2\u00a2\r\3\2\2\2\u00a3\u00a4\b\b\1\2\u00a4\u00a5\5\20\t"+
		"\2\u00a5\u00a6\b\b\1\2\u00a6\u00ae\3\2\2\2\u00a7\u00a8\f\3\2\2\u00a8\u00a9"+
		"\7\20\2\2\u00a9\u00aa\5\20\t\2\u00aa\u00ab\b\b\1\2\u00ab\u00ad\3\2\2\2"+
		"\u00ac\u00a7\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\17\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\5&\24\2\u00b2"+
		"\u00b3\b\t\1\2\u00b3\u00bb\3\2\2\2\u00b4\u00b5\5&\24\2\u00b5\u00b6\7\21"+
		"\2\2\u00b6\u00b7\7\'\2\2\u00b7\u00b8\7\22\2\2\u00b8\u00b9\b\t\1\2\u00b9"+
		"\u00bb\3\2\2\2\u00ba\u00b1\3\2\2\2\u00ba\u00b4\3\2\2\2\u00bb\21\3\2\2"+
		"\2\u00bc\u00bd\7\23\2\2\u00bd\u00c1\b\n\1\2\u00be\u00bf\7\24\2\2\u00bf"+
		"\u00c1\b\n\1\2\u00c0\u00bc\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\23\3\2\2"+
		"\2\u00c2\u00c3\7\25\2\2\u00c3\u00c8\b\13\1\2\u00c4\u00c5\5\22\n\2\u00c5"+
		"\u00c6\b\13\1\2\u00c6\u00c8\3\2\2\2\u00c7\u00c2\3\2\2\2\u00c7\u00c4\3"+
		"\2\2\2\u00c8\25\3\2\2\2\u00c9\u00ce\b\f\1\2\u00ca\u00cb\5\30\r\2\u00cb"+
		"\u00cc\b\f\1\2\u00cc\u00ce\3\2\2\2\u00cd\u00c9\3\2\2\2\u00cd\u00ca\3\2"+
		"\2\2\u00ce\27\3\2\2\2\u00cf\u00d0\b\r\1\2\u00d0\u00d1\5\32\16\2\u00d1"+
		"\u00d2\b\r\1\2\u00d2\u00da\3\2\2\2\u00d3\u00d4\f\3\2\2\u00d4\u00d5\7\20"+
		"\2\2\u00d5\u00d6\5\32\16\2\u00d6\u00d7\b\r\1\2\u00d7\u00d9\3\2\2\2\u00d8"+
		"\u00d3\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\31\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de\5\22\n\2\u00de\u00df"+
		"\5*\26\2\u00df\u00e0\b\16\1\2\u00e0\33\3\2\2\2\u00e1\u00e2\5\36\20\2\u00e2"+
		"\u00e3\b\17\1\2\u00e3\u00e6\3\2\2\2\u00e4\u00e6\b\17\1\2\u00e5\u00e1\3"+
		"\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\35\3\2\2\2\u00e7\u00e8\b\20\1\2\u00e8"+
		"\u00e9\7\'\2\2\u00e9\u010d\b\20\1\2\u00ea\u00eb\7\26\2\2\u00eb\u00ec\5"+
		"\36\20\20\u00ec\u00ed\b\20\1\2\u00ed\u010d\3\2\2\2\u00ee\u00ef\7 \2\2"+
		"\u00ef\u00f0\5\36\20\f\u00f0\u00f1\b\20\1\2\u00f1\u010d\3\2\2\2\u00f2"+
		"\u00f3\5 \21\2\u00f3\u00f4\7#\2\2\u00f4\u00f5\5\36\20\t\u00f5\u00f6\b"+
		"\20\1\2\u00f6\u010d\3\2\2\2\u00f7\u00f8\7$\2\2\u00f8\u010d\b\20\1\2\u00f9"+
		"\u00fa\7%\2\2\u00fa\u010d\b\20\1\2\u00fb\u00fc\7\5\2\2\u00fc\u00fd\5\36"+
		"\20\2\u00fd\u00fe\7\6\2\2\u00fe\u00ff\b\20\1\2\u00ff\u010d\3\2\2\2\u0100"+
		"\u0101\5 \21\2\u0101\u0102\b\20\1\2\u0102\u010d\3\2\2\2\u0103\u0104\5"+
		"(\25\2\u0104\u0105\7\5\2\2\u0105\u0106\5\"\22\2\u0106\u0107\7\6\2\2\u0107"+
		"\u0108\b\20\1\2\u0108\u010d\3\2\2\2\u0109\u010a\5*\26\2\u010a\u010b\b"+
		"\20\1\2\u010b\u010d\3\2\2\2\u010c\u00e7\3\2\2\2\u010c\u00ea\3\2\2\2\u010c"+
		"\u00ee\3\2\2\2\u010c\u00f2\3\2\2\2\u010c\u00f7\3\2\2\2\u010c\u00f9\3\2"+
		"\2\2\u010c\u00fb\3\2\2\2\u010c\u0100\3\2\2\2\u010c\u0103\3\2\2\2\u010c"+
		"\u0109\3\2\2\2\u010d\u0129\3\2\2\2\u010e\u010f\f\17\2\2\u010f\u0110\t"+
		"\2\2\2\u0110\u0111\5\36\20\20\u0111\u0112\b\20\1\2\u0112\u0128\3\2\2\2"+
		"\u0113\u0114\f\16\2\2\u0114\u0115\t\3\2\2\u0115\u0116\5\36\20\17\u0116"+
		"\u0117\b\20\1\2\u0117\u0128\3\2\2\2\u0118\u0119\f\r\2\2\u0119\u011a\t"+
		"\4\2\2\u011a\u011b\5\36\20\16\u011b\u011c\b\20\1\2\u011c\u0128\3\2\2\2"+
		"\u011d\u011e\f\13\2\2\u011e\u011f\7!\2\2\u011f\u0120\5\36\20\f\u0120\u0121"+
		"\b\20\1\2\u0121\u0128\3\2\2\2\u0122\u0123\f\n\2\2\u0123\u0124\7\"\2\2"+
		"\u0124\u0125\5\36\20\13\u0125\u0126\b\20\1\2\u0126\u0128\3\2\2\2\u0127"+
		"\u010e\3\2\2\2\u0127\u0113\3\2\2\2\u0127\u0118\3\2\2\2\u0127\u011d\3\2"+
		"\2\2\u0127\u0122\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\37\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d\5&\24"+
		"\2\u012d\u012e\b\21\1\2\u012e\u0136\3\2\2\2\u012f\u0130\5&\24\2\u0130"+
		"\u0131\7\21\2\2\u0131\u0132\5\36\20\2\u0132\u0133\7\22\2\2\u0133\u0134"+
		"\b\21\1\2\u0134\u0136\3\2\2\2\u0135\u012c\3\2\2\2\u0135\u012f\3\2\2\2"+
		"\u0136!\3\2\2\2\u0137\u013c\b\22\1\2\u0138\u0139\5$\23\2\u0139\u013a\b"+
		"\22\1\2\u013a\u013c\3\2\2\2\u013b\u0137\3\2\2\2\u013b\u0138\3\2\2\2\u013c"+
		"#\3\2\2\2\u013d\u013e\b\23\1\2\u013e\u013f\5\36\20\2\u013f\u0140\b\23"+
		"\1\2\u0140\u0148\3\2\2\2\u0141\u0142\f\3\2\2\u0142\u0143\7\20\2\2\u0143"+
		"\u0144\5\36\20\2\u0144\u0145\b\23\1\2\u0145\u0147\3\2\2\2\u0146\u0141"+
		"\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"%\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014c\7&\2\2\u014c\u014d\b\24\1\2"+
		"\u014d\'\3\2\2\2\u014e\u014f\7&\2\2\u014f\u0150\b\25\1\2\u0150)\3\2\2"+
		"\2\u0151\u0152\7&\2\2\u0152\u0153\b\26\1\2\u0153+\3\2\2\2\25\60\67R_g"+
		"\u00a1\u00ae\u00ba\u00c0\u00c7\u00cd\u00da\u00e5\u010c\u0127\u0129\u0135"+
		"\u013b\u0148";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}