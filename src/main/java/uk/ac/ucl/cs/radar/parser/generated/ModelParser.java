// Generated from Model.g4 by ANTLR 4.5.1
package uk.ac.ucl.cs.radar.parser.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ModelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		NOT=39, AND=40, OR=41, HexLiteral=42, DecimalLiteral=43, OctalLiteral=44, 
		FloatingPointLiteral=45, CharacterLiteral=46, StringLiteral=47, Identifier=48, 
		COMMENT=49, LINE_COMMENT=50, NEWLINE=51, SINGLESPACE=52, WS=53;
	public static final int
		RULE_model = 0, RULE_model_element = 1, RULE_objective_decl = 2, RULE_objective_def = 3, 
		RULE_quality_var_decl = 4, RULE_quality_var_def = 5, RULE_decision_def = 6, 
		RULE_decision_body = 7, RULE_option_name = 8, RULE_option_def = 9, RULE_parameter_def = 10, 
		RULE_distribution = 11, RULE_distribution_args = 12, RULE_distribution_arg = 13, 
		RULE_arithmetic_expr = 14, RULE_comparision = 15, RULE_number = 16, RULE_integerLiteral = 17, 
		RULE_optimisationDirection = 18, RULE_comparator = 19, RULE_var_name = 20;
	public static final String[] ruleNames = {
		"model", "model_element", "objective_decl", "objective_def", "quality_var_decl", 
		"quality_var_def", "decision_def", "decision_body", "option_name", "option_def", 
		"parameter_def", "distribution", "distribution_args", "distribution_arg", 
		"arithmetic_expr", "comparision", "number", "integerLiteral", "optimisationDirection", 
		"comparator", "var_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Model'", "';'", "'Objective'", "'='", "'with'", "'margin'", "'EV'", 
		"'('", "')'", "'Pr'", "'percentile'", "'-'", "'+'", "','", "'decision'", 
		"'{'", "':'", "'}'", "'deterministic'", "'normal'", "'normalCI'", "'geometric'", 
		"'exponential'", "'random'", "'uniform'", "'triangular'", "'^'", "'/'", 
		"'*'", "'%'", "'Max'", "'Min'", "'>'", "'>='", "'<'", "'<='", "'=='", 
		"'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "NOT", "AND", "OR", "HexLiteral", "DecimalLiteral", 
		"OctalLiteral", "FloatingPointLiteral", "CharacterLiteral", "StringLiteral", 
		"Identifier", "COMMENT", "LINE_COMMENT", "NEWLINE", "SINGLESPACE", "WS"
	};
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
	public String getGrammarFileName() { return "Model.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ModelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ModelContext extends ParserRuleContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<Model_elementContext> model_element() {
			return getRuleContexts(Model_elementContext.class);
		}
		public Model_elementContext model_element(int i) {
			return getRuleContext(Model_elementContext.class,i);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__0);
			setState(43);
			var_name();
			setState(44);
			match(T__1);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(45);
				match(NEWLINE);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==Identifier) {
				{
				{
				setState(51);
				model_element();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Model_elementContext extends ParserRuleContext {
		public Model_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model_element; }
	 
		public Model_elementContext() { }
		public void copyFrom(Model_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModelObjectiveListContext extends Model_elementContext {
		public List<Objective_declContext> objective_decl() {
			return getRuleContexts(Objective_declContext.class);
		}
		public Objective_declContext objective_decl(int i) {
			return getRuleContext(Objective_declContext.class,i);
		}
		public ModelObjectiveListContext(Model_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterModelObjectiveList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitModelObjectiveList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitModelObjectiveList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModelQualityVariableListContext extends Model_elementContext {
		public List<Quality_var_declContext> quality_var_decl() {
			return getRuleContexts(Quality_var_declContext.class);
		}
		public Quality_var_declContext quality_var_decl(int i) {
			return getRuleContext(Quality_var_declContext.class,i);
		}
		public ModelQualityVariableListContext(Model_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterModelQualityVariableList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitModelQualityVariableList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitModelQualityVariableList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Model_elementContext model_element() throws RecognitionException {
		Model_elementContext _localctx = new Model_elementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_model_element);
		try {
			int _alt;
			setState(67);
			switch (_input.LA(1)) {
			case T__2:
				_localctx = new ModelObjectiveListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(58); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(57);
						objective_decl();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(60); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case Identifier:
				_localctx = new ModelQualityVariableListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(63); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(62);
						quality_var_decl();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(65); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Objective_declContext extends ParserRuleContext {
		public OptimisationDirectionContext optimisationDirection() {
			return getRuleContext(OptimisationDirectionContext.class,0);
		}
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public Objective_defContext objective_def() {
			return getRuleContext(Objective_defContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public Objective_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjective_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjective_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjective_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Objective_declContext objective_decl() throws RecognitionException {
		Objective_declContext _localctx = new Objective_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_objective_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__2);
			setState(70);
			optimisationDirection();
			setState(71);
			var_name();
			setState(74);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(72);
				match(T__3);
				setState(73);
				objective_def();
				}
			}

			setState(80);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(76);
				match(T__4);
				setState(77);
				number();
				setState(78);
				match(T__5);
				}
			}

			setState(82);
			match(T__1);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(83);
				match(NEWLINE);
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Objective_defContext extends ParserRuleContext {
		public Objective_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective_def; }
	 
		public Objective_defContext() { }
		public void copyFrom(Objective_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ObjectiveBooleanProbabilityContext extends Objective_defContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectiveBooleanProbabilityContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectiveBooleanProbability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectiveBooleanProbability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectiveBooleanProbability(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectiveExpectationContext extends Objective_defContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectiveExpectationContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectiveExpectation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectiveExpectation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectiveExpectation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectivePercentileContext extends Objective_defContext {
		public Token op;
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectivePercentileContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectivePercentile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectivePercentile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectivePercentile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectiveProbabilityContext extends Objective_defContext {
		public ComparisionContext comparision() {
			return getRuleContext(ComparisionContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectiveProbabilityContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectiveProbability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectiveProbability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectiveProbability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Objective_defContext objective_def() throws RecognitionException {
		Objective_defContext _localctx = new Objective_defContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_objective_def);
		int _la;
		try {
			setState(138);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new ObjectiveExpectationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				match(T__6);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(90);
					match(SINGLESPACE);
					}
					}
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(96);
				match(T__7);
				setState(97);
				var_name();
				setState(98);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new ObjectiveBooleanProbabilityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(T__9);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(101);
					match(SINGLESPACE);
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107);
				match(T__7);
				setState(108);
				var_name();
				setState(109);
				match(T__8);
				}
				break;
			case 3:
				_localctx = new ObjectiveProbabilityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(T__9);
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(112);
					match(SINGLESPACE);
					}
					}
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(118);
				match(T__7);
				setState(119);
				comparision();
				setState(120);
				match(T__8);
				}
				break;
			case 4:
				_localctx = new ObjectivePercentileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
				match(T__10);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(123);
					match(SINGLESPACE);
					}
					}
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(129);
				match(T__7);
				setState(131);
				_la = _input.LA(1);
				if (_la==T__11 || _la==T__12) {
					{
					setState(130);
					((ObjectivePercentileContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__11 || _la==T__12) ) {
						((ObjectivePercentileContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(133);
				var_name();
				setState(134);
				match(T__13);
				setState(135);
				integerLiteral();
				setState(136);
				match(T__8);
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

	public static class Quality_var_declContext extends ParserRuleContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public Quality_var_defContext quality_var_def() {
			return getRuleContext(Quality_var_defContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public Quality_var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quality_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQuality_var_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQuality_var_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQuality_var_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quality_var_declContext quality_var_decl() throws RecognitionException {
		Quality_var_declContext _localctx = new Quality_var_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quality_var_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			var_name();
			setState(141);
			match(T__3);
			setState(142);
			quality_var_def();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(143);
				match(NEWLINE);
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Quality_var_defContext extends ParserRuleContext {
		public Quality_var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quality_var_def; }
	 
		public Quality_var_defContext() { }
		public void copyFrom(Quality_var_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QualityVariableDecisionContext extends Quality_var_defContext {
		public Decision_defContext decision_def() {
			return getRuleContext(Decision_defContext.class,0);
		}
		public QualityVariableDecisionContext(Quality_var_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQualityVariableDecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQualityVariableDecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQualityVariableDecision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QualityVariableArithmeticContext extends Quality_var_defContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public QualityVariableArithmeticContext(Quality_var_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQualityVariableArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQualityVariableArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQualityVariableArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QualityVariableParameterContext extends Quality_var_defContext {
		public Parameter_defContext parameter_def() {
			return getRuleContext(Parameter_defContext.class,0);
		}
		public QualityVariableParameterContext(Quality_var_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQualityVariableParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQualityVariableParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQualityVariableParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quality_var_defContext quality_var_def() throws RecognitionException {
		Quality_var_defContext _localctx = new Quality_var_defContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quality_var_def);
		try {
			setState(156);
			switch (_input.LA(1)) {
			case T__14:
				_localctx = new QualityVariableDecisionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				decision_def();
				}
				break;
			case T__7:
			case T__11:
			case T__12:
			case NOT:
			case HexLiteral:
			case DecimalLiteral:
			case OctalLiteral:
			case FloatingPointLiteral:
			case Identifier:
			case NEWLINE:
			case SINGLESPACE:
			case WS:
				_localctx = new QualityVariableArithmeticContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				arithmetic_expr(0);
				setState(151);
				match(T__1);
				}
				break;
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
				_localctx = new QualityVariableParameterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				parameter_def();
				setState(154);
				match(T__1);
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

	public static class Decision_defContext extends ParserRuleContext {
		public Decision_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decision_def; }
	 
		public Decision_defContext() { }
		public void copyFrom(Decision_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DecisionXORContext extends Decision_defContext {
		public Decision_bodyContext decision_body() {
			return getRuleContext(Decision_bodyContext.class,0);
		}
		public DecisionXORContext(Decision_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDecisionXOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDecisionXOR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDecisionXOR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decision_defContext decision_def() throws RecognitionException {
		Decision_defContext _localctx = new Decision_defContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_decision_def);
		try {
			_localctx = new DecisionXORContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__14);
			setState(159);
			decision_body();
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

	public static class Decision_bodyContext extends ParserRuleContext {
		public Token decision_Name;
		public TerminalNode StringLiteral() { return getToken(ModelParser.StringLiteral, 0); }
		public List<Option_nameContext> option_name() {
			return getRuleContexts(Option_nameContext.class);
		}
		public Option_nameContext option_name(int i) {
			return getRuleContext(Option_nameContext.class,i);
		}
		public List<Option_defContext> option_def() {
			return getRuleContexts(Option_defContext.class);
		}
		public Option_defContext option_def(int i) {
			return getRuleContext(Option_defContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public Decision_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decision_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDecision_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDecision_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDecision_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decision_bodyContext decision_body() throws RecognitionException {
		Decision_bodyContext _localctx = new Decision_bodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_decision_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__7);
			setState(162);
			((Decision_bodyContext)_localctx).decision_Name = match(StringLiteral);
			setState(163);
			match(T__8);
			setState(164);
			match(T__15);
			setState(172); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(165);
				option_name();
				setState(166);
				match(T__16);
				setState(167);
				option_def();
				setState(168);
				match(T__1);
				setState(170);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(169);
					match(NEWLINE);
					}
					break;
				}
				}
				}
				setState(174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0) );
			setState(176);
			match(T__17);
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

	public static class Option_nameContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(ModelParser.StringLiteral, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public Option_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOption_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOption_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOption_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_nameContext option_name() throws RecognitionException {
		Option_nameContext _localctx = new Option_nameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_option_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
				{
				{
				setState(178);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(StringLiteral);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
				{
				{
				setState(185);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Option_defContext extends ParserRuleContext {
		public Option_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_def; }
	 
		public Option_defContext() { }
		public void copyFrom(Option_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OptionParameterContext extends Option_defContext {
		public Parameter_defContext parameter_def() {
			return getRuleContext(Parameter_defContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public OptionParameterContext(Option_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOptionParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOptionParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOptionParameter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OptionExpressionContext extends Option_defContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public OptionExpressionContext(Option_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOptionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOptionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOptionExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_defContext option_def() throws RecognitionException {
		Option_defContext _localctx = new Option_defContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_option_def);
		int _la;
		try {
			int _alt;
			setState(217);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new OptionExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(191);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(196);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(197);
				arithmetic_expr(0);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
					{
					{
					setState(198);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				_localctx = new OptionParameterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
					{
					{
					setState(204);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(210);
				parameter_def();
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
					{
					{
					setState(211);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(216);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class Parameter_defContext extends ParserRuleContext {
		public DistributionContext distribution() {
			return getRuleContext(DistributionContext.class,0);
		}
		public List<Distribution_argContext> distribution_arg() {
			return getRuleContexts(Distribution_argContext.class);
		}
		public Distribution_argContext distribution_arg(int i) {
			return getRuleContext(Distribution_argContext.class,i);
		}
		public Parameter_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterParameter_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitParameter_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitParameter_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_defContext parameter_def() throws RecognitionException {
		Parameter_defContext _localctx = new Parameter_defContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameter_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			distribution();
			setState(220);
			match(T__7);
			setState(229);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NOT) | (1L << HexLiteral) | (1L << DecimalLiteral) | (1L << OctalLiteral) | (1L << FloatingPointLiteral) | (1L << Identifier) | (1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
				{
				setState(221);
				distribution_arg();
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(222);
					match(T__13);
					setState(223);
					distribution_arg();
					}
					}
					setState(228);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(231);
			match(T__8);
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

	public static class DistributionContext extends ParserRuleContext {
		public Token distributionValue;
		public DistributionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distribution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDistribution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDistribution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDistribution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistributionContext distribution() throws RecognitionException {
		DistributionContext _localctx = new DistributionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_distribution);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			((DistributionContext)_localctx).distributionValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25))) != 0)) ) {
				((DistributionContext)_localctx).distributionValue = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
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

	public static class Distribution_argsContext extends ParserRuleContext {
		public List<Distribution_argContext> distribution_arg() {
			return getRuleContexts(Distribution_argContext.class);
		}
		public Distribution_argContext distribution_arg(int i) {
			return getRuleContext(Distribution_argContext.class,i);
		}
		public Distribution_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distribution_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDistribution_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDistribution_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDistribution_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Distribution_argsContext distribution_args() throws RecognitionException {
		Distribution_argsContext _localctx = new Distribution_argsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_distribution_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			distribution_arg();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(236);
				match(T__13);
				setState(237);
				distribution_arg();
				}
				}
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Distribution_argContext extends ParserRuleContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public Distribution_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distribution_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDistribution_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDistribution_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDistribution_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Distribution_argContext distribution_arg() throws RecognitionException {
		Distribution_argContext _localctx = new Distribution_argContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_distribution_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			arithmetic_expr(0);
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

	public static class Arithmetic_exprContext extends ParserRuleContext {
		public Arithmetic_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expr; }
	 
		public Arithmetic_exprContext() { }
		public void copyFrom(Arithmetic_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprNotContext extends Arithmetic_exprContext {
		public TerminalNode NOT() { return getToken(ModelParser.NOT, 0); }
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprNotContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprDivContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprDivContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIdentifierContext extends Arithmetic_exprContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public ExprIdentifierContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprMultContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprMultContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprMult(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprMult(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprNumberContext extends Arithmetic_exprContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprNumberContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprCompareContext extends Arithmetic_exprContext {
		public ComparisionContext comparision() {
			return getRuleContext(ComparisionContext.class,0);
		}
		public ExprCompareContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPercentContext extends Arithmetic_exprContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprPercentContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprPercent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprPercent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprPercent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprSubContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprSubContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprOrContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public TerminalNode OR() { return getToken(ModelParser.OR, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprOrContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPreOperatorContext extends Arithmetic_exprContext {
		public Token op;
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprPreOperatorContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprPreOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprPreOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprPreOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprBracketContext extends Arithmetic_exprContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public ExprBracketContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprBracket(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAddContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprAddContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprAdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAndContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public TerminalNode AND() { return getToken(ModelParser.AND, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ExprAndContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPowerContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public ExprPowerContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_exprContext arithmetic_expr() throws RecognitionException {
		return arithmetic_expr(0);
	}

	private Arithmetic_exprContext arithmetic_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Arithmetic_exprContext _localctx = new Arithmetic_exprContext(_ctx, _parentState);
		Arithmetic_exprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_arithmetic_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				_localctx = new ExprPreOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
					{
					{
					setState(246);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(252);
				((ExprPreOperatorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
					((ExprPreOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(253);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(258);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				setState(259);
				arithmetic_expr(7);
				}
				break;
			case 2:
				{
				_localctx = new ExprNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
					{
					{
					setState(260);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(265);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(266);
				match(NOT);
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(267);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(272);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				}
				setState(273);
				arithmetic_expr(4);
				}
				break;
			case 3:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(274);
				number();
				}
				break;
			case 4:
				{
				_localctx = new ExprBracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(275);
				match(T__7);
				setState(276);
				arithmetic_expr(0);
				setState(277);
				match(T__8);
				}
				break;
			case 5:
				{
				_localctx = new ExprCompareContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(279);
				comparision();
				}
				break;
			case 6:
				{
				_localctx = new ExprIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(280);
				var_name();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(386);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(384);
					switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
					case 1:
						{
						_localctx = new ExprPowerContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(283);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(284);
						match(T__26);
						setState(285);
						arithmetic_expr(13);
						}
						break;
					case 2:
						{
						_localctx = new ExprDivContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(286);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(290);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(287);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(292);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(293);
						match(T__27);
						setState(297);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(294);
								_la = _input.LA(1);
								if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(299);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
						}
						setState(300);
						arithmetic_expr(12);
						}
						break;
					case 3:
						{
						_localctx = new ExprMultContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(301);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(305);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(302);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(307);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(308);
						match(T__28);
						setState(312);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(309);
								_la = _input.LA(1);
								if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(314);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
						}
						setState(315);
						arithmetic_expr(11);
						}
						break;
					case 4:
						{
						_localctx = new ExprAddContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(316);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(320);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(317);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(322);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(323);
						match(T__12);
						setState(327);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(324);
								_la = _input.LA(1);
								if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(329);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
						}
						setState(330);
						arithmetic_expr(10);
						}
						break;
					case 5:
						{
						_localctx = new ExprSubContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(331);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(335);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(332);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(337);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(338);
						match(T__11);
						setState(342);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(339);
								_la = _input.LA(1);
								if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(344);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
						}
						setState(345);
						arithmetic_expr(9);
						}
						break;
					case 6:
						{
						_localctx = new ExprAndContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(346);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(350);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(347);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(352);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(353);
						match(AND);
						setState(357);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(354);
								_la = _input.LA(1);
								if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(359);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
						}
						setState(360);
						arithmetic_expr(7);
						}
						break;
					case 7:
						{
						_localctx = new ExprOrContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(361);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(365);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(362);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(367);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(368);
						match(OR);
						setState(372);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(369);
								_la = _input.LA(1);
								if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(374);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
						}
						setState(375);
						arithmetic_expr(6);
						}
						break;
					case 8:
						{
						_localctx = new ExprPercentContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(376);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(380);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) {
							{
							{
							setState(377);
							_la = _input.LA(1);
							if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << SINGLESPACE) | (1L << WS))) != 0)) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(382);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(383);
						match(T__29);
						}
						break;
					}
					} 
				}
				setState(388);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
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

	public static class ComparisionContext extends ParserRuleContext {
		public ComparisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparision; }
	 
		public ComparisionContext() { }
		public void copyFrom(ComparisionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CompareExpressionContext extends ComparisionContext {
		public ComparatorContext relationalOp;
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public CompareExpressionContext(ComparisionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterCompareExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitCompareExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitCompareExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareVariablesContext extends ComparisionContext {
		public ComparatorContext relationalOp;
		public List<Var_nameContext> var_name() {
			return getRuleContexts(Var_nameContext.class);
		}
		public Var_nameContext var_name(int i) {
			return getRuleContext(Var_nameContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public CompareVariablesContext(ComparisionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterCompareVariables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitCompareVariables(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitCompareVariables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisionContext comparision() throws RecognitionException {
		ComparisionContext _localctx = new ComparisionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comparision);
		try {
			setState(397);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				_localctx = new CompareVariablesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(389);
				var_name();
				setState(390);
				((CompareVariablesContext)_localctx).relationalOp = comparator();
				setState(391);
				var_name();
				}
				break;
			case 2:
				_localctx = new CompareExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(393);
				var_name();
				setState(394);
				((CompareExpressionContext)_localctx).relationalOp = comparator();
				setState(395);
				arithmetic_expr(0);
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

	public static class NumberContext extends ParserRuleContext {
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	 
		public NumberContext() { }
		public void copyFrom(NumberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomicIntegerContext extends NumberContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public AtomicIntegerContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterAtomicInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitAtomicInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitAtomicInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomicFloatContext extends NumberContext {
		public TerminalNode FloatingPointLiteral() { return getToken(ModelParser.FloatingPointLiteral, 0); }
		public AtomicFloatContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterAtomicFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitAtomicFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitAtomicFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomicDecimalContext extends NumberContext {
		public TerminalNode DecimalLiteral() { return getToken(ModelParser.DecimalLiteral, 0); }
		public AtomicDecimalContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterAtomicDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitAtomicDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitAtomicDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_number);
		try {
			setState(402);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				_localctx = new AtomicIntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(399);
				integerLiteral();
				}
				break;
			case 2:
				_localctx = new AtomicFloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(400);
				match(FloatingPointLiteral);
				}
				break;
			case 3:
				_localctx = new AtomicDecimalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(401);
				match(DecimalLiteral);
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

	public static class IntegerLiteralContext extends ParserRuleContext {
		public Token intValue;
		public TerminalNode HexLiteral() { return getToken(ModelParser.HexLiteral, 0); }
		public TerminalNode OctalLiteral() { return getToken(ModelParser.OctalLiteral, 0); }
		public TerminalNode DecimalLiteral() { return getToken(ModelParser.DecimalLiteral, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			((IntegerLiteralContext)_localctx).intValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexLiteral) | (1L << DecimalLiteral) | (1L << OctalLiteral))) != 0)) ) {
				((IntegerLiteralContext)_localctx).intValue = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
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

	public static class OptimisationDirectionContext extends ParserRuleContext {
		public Token max;
		public Token min;
		public OptimisationDirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optimisationDirection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOptimisationDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOptimisationDirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOptimisationDirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptimisationDirectionContext optimisationDirection() throws RecognitionException {
		OptimisationDirectionContext _localctx = new OptimisationDirectionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_optimisationDirection);
		try {
			setState(408);
			switch (_input.LA(1)) {
			case T__30:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				((OptimisationDirectionContext)_localctx).max = match(T__30);
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				setState(407);
				((OptimisationDirectionContext)_localctx).min = match(T__31);
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

	public static class ComparatorContext extends ParserRuleContext {
		public Token logicalOperatorValue;
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			((ComparatorContext)_localctx).logicalOperatorValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
				((ComparatorContext)_localctx).logicalOperatorValue = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
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

	public static class Var_nameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ModelParser.Identifier, 0); }
		public Var_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterVar_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitVar_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitVar_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_nameContext var_name() throws RecognitionException {
		Var_nameContext _localctx = new Var_nameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_var_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			match(Identifier);
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
		case 14:
			return arithmetic_expr_sempred((Arithmetic_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmetic_expr_sempred(Arithmetic_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\67\u01a1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\7\2\61\n\2\f"+
		"\2\16\2\64\13\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\3\6\3=\n\3\r\3\16\3>\3"+
		"\3\6\3B\n\3\r\3\16\3C\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\5\4M\n\4\3\4\3\4\3"+
		"\4\3\4\5\4S\n\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\3\5\3\5\7\5^\n\5\f\5\16"+
		"\5a\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5i\n\5\f\5\16\5l\13\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\5t\n\5\f\5\16\5w\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\177\n\5"+
		"\f\5\16\5\u0082\13\5\3\5\3\5\5\5\u0086\n\5\3\5\3\5\3\5\3\5\3\5\5\5\u008d"+
		"\n\5\3\6\3\6\3\6\3\6\7\6\u0093\n\6\f\6\16\6\u0096\13\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7\u009f\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u00ad\n\t\6\t\u00af\n\t\r\t\16\t\u00b0\3\t\3\t\3\n\7\n\u00b6"+
		"\n\n\f\n\16\n\u00b9\13\n\3\n\3\n\7\n\u00bd\n\n\f\n\16\n\u00c0\13\n\3\13"+
		"\7\13\u00c3\n\13\f\13\16\13\u00c6\13\13\3\13\3\13\7\13\u00ca\n\13\f\13"+
		"\16\13\u00cd\13\13\3\13\7\13\u00d0\n\13\f\13\16\13\u00d3\13\13\3\13\3"+
		"\13\7\13\u00d7\n\13\f\13\16\13\u00da\13\13\5\13\u00dc\n\13\3\f\3\f\3\f"+
		"\3\f\3\f\7\f\u00e3\n\f\f\f\16\f\u00e6\13\f\5\f\u00e8\n\f\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\16\7\16\u00f1\n\16\f\16\16\16\u00f4\13\16\3\17\3\17\3\20"+
		"\3\20\7\20\u00fa\n\20\f\20\16\20\u00fd\13\20\3\20\3\20\7\20\u0101\n\20"+
		"\f\20\16\20\u0104\13\20\3\20\3\20\7\20\u0108\n\20\f\20\16\20\u010b\13"+
		"\20\3\20\3\20\7\20\u010f\n\20\f\20\16\20\u0112\13\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\5\20\u011c\n\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u0123\n\20\f\20\16\20\u0126\13\20\3\20\3\20\7\20\u012a\n\20\f\20\16\20"+
		"\u012d\13\20\3\20\3\20\3\20\7\20\u0132\n\20\f\20\16\20\u0135\13\20\3\20"+
		"\3\20\7\20\u0139\n\20\f\20\16\20\u013c\13\20\3\20\3\20\3\20\7\20\u0141"+
		"\n\20\f\20\16\20\u0144\13\20\3\20\3\20\7\20\u0148\n\20\f\20\16\20\u014b"+
		"\13\20\3\20\3\20\3\20\7\20\u0150\n\20\f\20\16\20\u0153\13\20\3\20\3\20"+
		"\7\20\u0157\n\20\f\20\16\20\u015a\13\20\3\20\3\20\3\20\7\20\u015f\n\20"+
		"\f\20\16\20\u0162\13\20\3\20\3\20\7\20\u0166\n\20\f\20\16\20\u0169\13"+
		"\20\3\20\3\20\3\20\7\20\u016e\n\20\f\20\16\20\u0171\13\20\3\20\3\20\7"+
		"\20\u0175\n\20\f\20\16\20\u0178\13\20\3\20\3\20\3\20\7\20\u017d\n\20\f"+
		"\20\16\20\u0180\13\20\3\20\7\20\u0183\n\20\f\20\16\20\u0186\13\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0190\n\21\3\22\3\22\3\22\5\22"+
		"\u0195\n\22\3\23\3\23\3\24\3\24\5\24\u019b\n\24\3\25\3\25\3\26\3\26\3"+
		"\26\2\3\36\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\7\3\2\16"+
		"\17\3\2\65\67\3\2\25\34\3\2,.\3\2#(\u01cc\2,\3\2\2\2\4E\3\2\2\2\6G\3\2"+
		"\2\2\b\u008c\3\2\2\2\n\u008e\3\2\2\2\f\u009e\3\2\2\2\16\u00a0\3\2\2\2"+
		"\20\u00a3\3\2\2\2\22\u00b7\3\2\2\2\24\u00db\3\2\2\2\26\u00dd\3\2\2\2\30"+
		"\u00eb\3\2\2\2\32\u00ed\3\2\2\2\34\u00f5\3\2\2\2\36\u011b\3\2\2\2 \u018f"+
		"\3\2\2\2\"\u0194\3\2\2\2$\u0196\3\2\2\2&\u019a\3\2\2\2(\u019c\3\2\2\2"+
		"*\u019e\3\2\2\2,-\7\3\2\2-.\5*\26\2.\62\7\4\2\2/\61\7\65\2\2\60/\3\2\2"+
		"\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\638\3\2\2\2\64\62\3\2\2\2"+
		"\65\67\5\4\3\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\3\3\2"+
		"\2\2:8\3\2\2\2;=\5\6\4\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?F\3\2"+
		"\2\2@B\5\n\6\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2E<\3\2"+
		"\2\2EA\3\2\2\2F\5\3\2\2\2GH\7\5\2\2HI\5&\24\2IL\5*\26\2JK\7\6\2\2KM\5"+
		"\b\5\2LJ\3\2\2\2LM\3\2\2\2MR\3\2\2\2NO\7\7\2\2OP\5\"\22\2PQ\7\b\2\2QS"+
		"\3\2\2\2RN\3\2\2\2RS\3\2\2\2ST\3\2\2\2TX\7\4\2\2UW\7\65\2\2VU\3\2\2\2"+
		"WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\7\3\2\2\2ZX\3\2\2\2[_\7\t\2\2\\^\7\66"+
		"\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7"+
		"\n\2\2cd\5*\26\2de\7\13\2\2e\u008d\3\2\2\2fj\7\f\2\2gi\7\66\2\2hg\3\2"+
		"\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7\n\2\2no\5*"+
		"\26\2op\7\13\2\2p\u008d\3\2\2\2qu\7\f\2\2rt\7\66\2\2sr\3\2\2\2tw\3\2\2"+
		"\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\7\n\2\2yz\5 \21\2z{\7\13"+
		"\2\2{\u008d\3\2\2\2|\u0080\7\r\2\2}\177\7\66\2\2~}\3\2\2\2\177\u0082\3"+
		"\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0083\u0085\7\n\2\2\u0084\u0086\t\2\2\2\u0085\u0084\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\5*\26\2\u0088\u0089\7\20"+
		"\2\2\u0089\u008a\5$\23\2\u008a\u008b\7\13\2\2\u008b\u008d\3\2\2\2\u008c"+
		"[\3\2\2\2\u008cf\3\2\2\2\u008cq\3\2\2\2\u008c|\3\2\2\2\u008d\t\3\2\2\2"+
		"\u008e\u008f\5*\26\2\u008f\u0090\7\6\2\2\u0090\u0094\5\f\7\2\u0091\u0093"+
		"\7\65\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0095\13\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u009f"+
		"\5\16\b\2\u0098\u0099\5\36\20\2\u0099\u009a\7\4\2\2\u009a\u009f\3\2\2"+
		"\2\u009b\u009c\5\26\f\2\u009c\u009d\7\4\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u0097\3\2\2\2\u009e\u0098\3\2\2\2\u009e\u009b\3\2\2\2\u009f\r\3\2\2\2"+
		"\u00a0\u00a1\7\21\2\2\u00a1\u00a2\5\20\t\2\u00a2\17\3\2\2\2\u00a3\u00a4"+
		"\7\n\2\2\u00a4\u00a5\7\61\2\2\u00a5\u00a6\7\13\2\2\u00a6\u00ae\7\22\2"+
		"\2\u00a7\u00a8\5\22\n\2\u00a8\u00a9\7\23\2\2\u00a9\u00aa\5\24\13\2\u00aa"+
		"\u00ac\7\4\2\2\u00ab\u00ad\7\65\2\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3"+
		"\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00a7\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\7\24"+
		"\2\2\u00b3\21\3\2\2\2\u00b4\u00b6\t\3\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b9"+
		"\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00ba\u00be\7\61\2\2\u00bb\u00bd\t\3\2\2\u00bc\u00bb\3"+
		"\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\23\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c3\t\3\2\2\u00c2\u00c1\3\2\2"+
		"\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7"+
		"\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00cb\5\36\20\2\u00c8\u00ca\t\3\2\2"+
		"\u00c9\u00c8\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc"+
		"\3\2\2\2\u00cc\u00dc\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d0\t\3\2\2\u00cf"+
		"\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2"+
		"\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d8\5\26\f\2\u00d5"+
		"\u00d7\t\3\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00db"+
		"\u00c4\3\2\2\2\u00db\u00d1\3\2\2\2\u00dc\25\3\2\2\2\u00dd\u00de\5\30\r"+
		"\2\u00de\u00e7\7\n\2\2\u00df\u00e4\5\34\17\2\u00e0\u00e1\7\20\2\2\u00e1"+
		"\u00e3\5\34\17\2\u00e2\u00e0\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3"+
		"\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7"+
		"\u00df\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\7\13"+
		"\2\2\u00ea\27\3\2\2\2\u00eb\u00ec\t\4\2\2\u00ec\31\3\2\2\2\u00ed\u00f2"+
		"\5\34\17\2\u00ee\u00ef\7\20\2\2\u00ef\u00f1\5\34\17\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\33\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\5\36\20\2\u00f6\35\3\2\2\2"+
		"\u00f7\u00fb\b\20\1\2\u00f8\u00fa\t\3\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd"+
		"\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fe\u0102\t\2\2\2\u00ff\u0101\t\3\2\2\u0100\u00ff\3\2"+
		"\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0105\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u011c\5\36\20\t\u0106\u0108\t"+
		"\3\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u0110\7)"+
		"\2\2\u010d\u010f\t\3\2\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110"+
		"\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2"+
		"\2\2\u0113\u011c\5\36\20\6\u0114\u011c\5\"\22\2\u0115\u0116\7\n\2\2\u0116"+
		"\u0117\5\36\20\2\u0117\u0118\7\13\2\2\u0118\u011c\3\2\2\2\u0119\u011c"+
		"\5 \21\2\u011a\u011c\5*\26\2\u011b\u00f7\3\2\2\2\u011b\u0109\3\2\2\2\u011b"+
		"\u0114\3\2\2\2\u011b\u0115\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011a\3\2"+
		"\2\2\u011c\u0184\3\2\2\2\u011d\u011e\f\17\2\2\u011e\u011f\7\35\2\2\u011f"+
		"\u0183\5\36\20\17\u0120\u0124\f\r\2\2\u0121\u0123\t\3\2\2\u0122\u0121"+
		"\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0127\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u012b\7\36\2\2\u0128\u012a\t"+
		"\3\2\2\u0129\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u012e\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0183\5\36"+
		"\20\16\u012f\u0133\f\f\2\2\u0130\u0132\t\3\2\2\u0131\u0130\3\2\2\2\u0132"+
		"\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0136\3\2"+
		"\2\2\u0135\u0133\3\2\2\2\u0136\u013a\7\37\2\2\u0137\u0139\t\3\2\2\u0138"+
		"\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2"+
		"\2\2\u013b\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u0183\5\36\20\r\u013e"+
		"\u0142\f\13\2\2\u013f\u0141\t\3\2\2\u0140\u013f\3\2\2\2\u0141\u0144\3"+
		"\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144"+
		"\u0142\3\2\2\2\u0145\u0149\7\17\2\2\u0146\u0148\t\3\2\2\u0147\u0146\3"+
		"\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u0183\5\36\20\f\u014d\u0151\f"+
		"\n\2\2\u014e\u0150\t\3\2\2\u014f\u014e\3\2\2\2\u0150\u0153\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151\3\2"+
		"\2\2\u0154\u0158\7\16\2\2\u0155\u0157\t\3\2\2\u0156\u0155\3\2\2\2\u0157"+
		"\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2"+
		"\2\2\u015a\u0158\3\2\2\2\u015b\u0183\5\36\20\13\u015c\u0160\f\b\2\2\u015d"+
		"\u015f\t\3\2\2\u015e\u015d\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2"+
		"\2\2\u0160\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163"+
		"\u0167\7*\2\2\u0164\u0166\t\3\2\2\u0165\u0164\3\2\2\2\u0166\u0169\3\2"+
		"\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016a\3\2\2\2\u0169"+
		"\u0167\3\2\2\2\u016a\u0183\5\36\20\t\u016b\u016f\f\7\2\2\u016c\u016e\t"+
		"\3\2\2\u016d\u016c\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u0172\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0176\7+"+
		"\2\2\u0173\u0175\t\3\2\2\u0174\u0173\3\2\2\2\u0175\u0178\3\2\2\2\u0176"+
		"\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2\2\2\u0178\u0176\3\2"+
		"\2\2\u0179\u0183\5\36\20\b\u017a\u017e\f\5\2\2\u017b\u017d\t\3\2\2\u017c"+
		"\u017b\3\2\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2"+
		"\2\2\u017f\u0181\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0183\7 \2\2\u0182"+
		"\u011d\3\2\2\2\u0182\u0120\3\2\2\2\u0182\u012f\3\2\2\2\u0182\u013e\3\2"+
		"\2\2\u0182\u014d\3\2\2\2\u0182\u015c\3\2\2\2\u0182\u016b\3\2\2\2\u0182"+
		"\u017a\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182\3\2\2\2\u0184\u0185\3\2"+
		"\2\2\u0185\37\3\2\2\2\u0186\u0184\3\2\2\2\u0187\u0188\5*\26\2\u0188\u0189"+
		"\5(\25\2\u0189\u018a\5*\26\2\u018a\u0190\3\2\2\2\u018b\u018c\5*\26\2\u018c"+
		"\u018d\5(\25\2\u018d\u018e\5\36\20\2\u018e\u0190\3\2\2\2\u018f\u0187\3"+
		"\2\2\2\u018f\u018b\3\2\2\2\u0190!\3\2\2\2\u0191\u0195\5$\23\2\u0192\u0195"+
		"\7/\2\2\u0193\u0195\7-\2\2\u0194\u0191\3\2\2\2\u0194\u0192\3\2\2\2\u0194"+
		"\u0193\3\2\2\2\u0195#\3\2\2\2\u0196\u0197\t\5\2\2\u0197%\3\2\2\2\u0198"+
		"\u019b\7!\2\2\u0199\u019b\7\"\2\2\u019a\u0198\3\2\2\2\u019a\u0199\3\2"+
		"\2\2\u019b\'\3\2\2\2\u019c\u019d\t\6\2\2\u019d)\3\2\2\2\u019e\u019f\7"+
		"\62\2\2\u019f+\3\2\2\2\65\628>CELRX_ju\u0080\u0085\u008c\u0094\u009e\u00ac"+
		"\u00b0\u00b7\u00be\u00c4\u00cb\u00d1\u00d8\u00db\u00e4\u00e7\u00f2\u00fb"+
		"\u0102\u0109\u0110\u011b\u0124\u012b\u0133\u013a\u0142\u0149\u0151\u0158"+
		"\u0160\u0167\u016f\u0176\u017e\u0182\u0184\u018f\u0194\u019a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}