// Generated from SQL.g4 by ANTLR 4.13.1

package parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQ=1, NEQ=2, LT=3, LTE=4, GT=5, GTE=6, PLUS=7, MINUS=8, ASTERISK=9, SEMICOLON_SYMBOL=10, 
		COMMA=11, SINGLE_QUOTE=12, INTEGER_VALUE=13, DOUBLE_VALUE=14, LINKAGE=15, 
		AND=16, CHAR=17, CREATE=18, DELETE=19, DOUBLE=20, FROM=21, INDEX=22, INSERT=23, 
		INT=24, INTEGER=25, INTO=26, KEY=27, LB=28, ON=29, OR=30, PRIMARY=31, 
		RB=32, SELECT=33, SET=34, STRING=35, TABLE=36, USINGBTREE=37, UPDATE=38, 
		VALUES=39, WHERE=40, XOR=41, IDENTIFIER=42, STRING_VALUE=43, UNICODE_STRING=44, 
		SIMPLE_COMMENT=45, BRACKETED_COMMENT=46, WS=47;
	public static final int
		RULE_query = 0, RULE_simpleStatement = 1, RULE_createStatement = 2, RULE_selectStatement = 3, 
		RULE_insertStatement = 4, RULE_updateStatement = 5, RULE_deleteStatement = 6, 
		RULE_createTable = 7, RULE_createIndex = 8, RULE_columnDefinition = 9, 
		RULE_columnsToBeUpdated = 10, RULE_dataType = 11, RULE_valueList = 12, 
		RULE_table = 13, RULE_selectCondition = 14, RULE_updateCondition = 15, 
		RULE_deleteCondition = 16, RULE_column = 17, RULE_value = 18, RULE_index = 19, 
		RULE_operator = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"query", "simpleStatement", "createStatement", "selectStatement", "insertStatement", 
			"updateStatement", "deleteStatement", "createTable", "createIndex", "columnDefinition", 
			"columnsToBeUpdated", "dataType", "valueList", "table", "selectCondition", 
			"updateCondition", "deleteCondition", "column", "value", "index", "operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", 
			"';'", "','", "'''", null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'('", null, null, null, "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EQ", "NEQ", "LT", "LTE", "GT", "GTE", "PLUS", "MINUS", "ASTERISK", 
			"SEMICOLON_SYMBOL", "COMMA", "SINGLE_QUOTE", "INTEGER_VALUE", "DOUBLE_VALUE", 
			"LINKAGE", "AND", "CHAR", "CREATE", "DELETE", "DOUBLE", "FROM", "INDEX", 
			"INSERT", "INT", "INTEGER", "INTO", "KEY", "LB", "ON", "OR", "PRIMARY", 
			"RB", "SELECT", "SET", "STRING", "TABLE", "USINGBTREE", "UPDATE", "VALUES", 
			"WHERE", "XOR", "IDENTIFIER", "STRING_VALUE", "UNICODE_STRING", "SIMPLE_COMMENT", 
			"BRACKETED_COMMENT", "WS"
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
	public String getGrammarFileName() { return "SQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QueryContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SQLParser.EOF, 0); }
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public TerminalNode SEMICOLON_SYMBOL() { return getToken(SQLParser.SEMICOLON_SYMBOL, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				match(EOF);
				}
				break;
			case CREATE:
			case DELETE:
			case INSERT:
			case SELECT:
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(43);
				simpleStatement();
				}
				setState(49);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SEMICOLON_SYMBOL:
					{
					setState(44);
					match(SEMICOLON_SYMBOL);
					setState(46);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(45);
						match(EOF);
						}
						break;
					}
					}
					break;
				case EOF:
					{
					setState(48);
					match(EOF);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class SimpleStatementContext extends ParserRuleContext {
		public CreateStatementContext createStatement() {
			return getRuleContext(CreateStatementContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public InsertStatementContext insertStatement() {
			return getRuleContext(InsertStatementContext.class,0);
		}
		public UpdateStatementContext updateStatement() {
			return getRuleContext(UpdateStatementContext.class,0);
		}
		public DeleteStatementContext deleteStatement() {
			return getRuleContext(DeleteStatementContext.class,0);
		}
		public SimpleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSimpleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSimpleStatement(this);
		}
	}

	public final SimpleStatementContext simpleStatement() throws RecognitionException {
		SimpleStatementContext _localctx = new SimpleStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_simpleStatement);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				createStatement();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 5);
				{
				setState(57);
				deleteStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateStatementContext extends ParserRuleContext {
		public CreateTableContext createTable() {
			return getRuleContext(CreateTableContext.class,0);
		}
		public CreateIndexContext createIndex() {
			return getRuleContext(CreateIndexContext.class,0);
		}
		public CreateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateStatement(this);
		}
	}

	public final CreateStatementContext createStatement() throws RecognitionException {
		CreateStatementContext _localctx = new CreateStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_createStatement);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				createTable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				createIndex();
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(SQLParser.SELECT, 0); }
		public TerminalNode ASTERISK() { return getToken(SQLParser.ASTERISK, 0); }
		public TerminalNode FROM() { return getToken(SQLParser.FROM, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SQLParser.WHERE, 0); }
		public SelectConditionContext selectCondition() {
			return getRuleContext(SelectConditionContext.class,0);
		}
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSelectStatement(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_selectStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(SELECT);
			setState(65);
			match(ASTERISK);
			setState(66);
			match(FROM);
			setState(67);
			table();
			{
			setState(68);
			match(WHERE);
			setState(69);
			selectCondition();
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

	@SuppressWarnings("CheckReturnValue")
	public static class InsertStatementContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(SQLParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(SQLParser.INTO, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public List<TerminalNode> LB() { return getTokens(SQLParser.LB); }
		public TerminalNode LB(int i) {
			return getToken(SQLParser.LB, i);
		}
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<TerminalNode> RB() { return getTokens(SQLParser.RB); }
		public TerminalNode RB(int i) {
			return getToken(SQLParser.RB, i);
		}
		public TerminalNode VALUES() { return getToken(SQLParser.VALUES, 0); }
		public ValueListContext valueList() {
			return getRuleContext(ValueListContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLParser.COMMA, i);
		}
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterInsertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitInsertStatement(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(INSERT);
			setState(72);
			match(INTO);
			setState(73);
			table();
			setState(74);
			match(LB);
			setState(75);
			column();
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(76);
				match(COMMA);
				setState(77);
				column();
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
			match(RB);
			setState(84);
			match(VALUES);
			setState(85);
			match(LB);
			setState(86);
			valueList();
			setState(87);
			match(RB);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateStatementContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(SQLParser.UPDATE, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode SET() { return getToken(SQLParser.SET, 0); }
		public ColumnsToBeUpdatedContext columnsToBeUpdated() {
			return getRuleContext(ColumnsToBeUpdatedContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SQLParser.WHERE, 0); }
		public UpdateConditionContext updateCondition() {
			return getRuleContext(UpdateConditionContext.class,0);
		}
		public UpdateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUpdateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUpdateStatement(this);
		}
	}

	public final UpdateStatementContext updateStatement() throws RecognitionException {
		UpdateStatementContext _localctx = new UpdateStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_updateStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(UPDATE);
			setState(90);
			table();
			setState(91);
			match(SET);
			setState(92);
			columnsToBeUpdated();
			{
			setState(93);
			match(WHERE);
			setState(94);
			updateCondition();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteStatementContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(SQLParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(SQLParser.FROM, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SQLParser.WHERE, 0); }
		public DeleteConditionContext deleteCondition() {
			return getRuleContext(DeleteConditionContext.class,0);
		}
		public DeleteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDeleteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDeleteStatement(this);
		}
	}

	public final DeleteStatementContext deleteStatement() throws RecognitionException {
		DeleteStatementContext _localctx = new DeleteStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_deleteStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(DELETE);
			setState(97);
			match(FROM);
			setState(98);
			table();
			{
			setState(99);
			match(WHERE);
			setState(100);
			deleteCondition();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(SQLParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(SQLParser.TABLE, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public List<TerminalNode> LB() { return getTokens(SQLParser.LB); }
		public TerminalNode LB(int i) {
			return getToken(SQLParser.LB, i);
		}
		public List<ColumnDefinitionContext> columnDefinition() {
			return getRuleContexts(ColumnDefinitionContext.class);
		}
		public ColumnDefinitionContext columnDefinition(int i) {
			return getRuleContext(ColumnDefinitionContext.class,i);
		}
		public List<TerminalNode> RB() { return getTokens(SQLParser.RB); }
		public TerminalNode RB(int i) {
			return getToken(SQLParser.RB, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLParser.COMMA, i);
		}
		public TerminalNode PRIMARY() { return getToken(SQLParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(SQLParser.KEY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public CreateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateTable(this);
		}
	}

	public final CreateTableContext createTable() throws RecognitionException {
		CreateTableContext _localctx = new CreateTableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_createTable);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(CREATE);
			setState(103);
			match(TABLE);
			setState(104);
			table();
			setState(105);
			match(LB);
			setState(106);
			columnDefinition();
			setState(111);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(107);
					match(COMMA);
					setState(108);
					columnDefinition();
					}
					} 
				}
				setState(113);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			{
			setState(114);
			match(COMMA);
			setState(115);
			match(PRIMARY);
			setState(116);
			match(KEY);
			setState(117);
			match(LB);
			setState(118);
			match(IDENTIFIER);
			setState(119);
			match(RB);
			}
			setState(121);
			match(RB);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateIndexContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(SQLParser.CREATE, 0); }
		public TerminalNode INDEX() { return getToken(SQLParser.INDEX, 0); }
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public TerminalNode ON() { return getToken(SQLParser.ON, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode LB() { return getToken(SQLParser.LB, 0); }
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode RB() { return getToken(SQLParser.RB, 0); }
		public TerminalNode USINGBTREE() { return getToken(SQLParser.USINGBTREE, 0); }
		public CreateIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateIndex(this);
		}
	}

	public final CreateIndexContext createIndex() throws RecognitionException {
		CreateIndexContext _localctx = new CreateIndexContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_createIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(CREATE);
			setState(124);
			match(INDEX);
			setState(125);
			index();
			setState(126);
			match(ON);
			setState(127);
			table();
			setState(128);
			match(LB);
			setState(129);
			column();
			setState(130);
			match(RB);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USINGBTREE) {
				{
				setState(131);
				match(USINGBTREE);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnDefinitionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ColumnDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnDefinition(this);
		}
	}

	public final ColumnDefinitionContext columnDefinition() throws RecognitionException {
		ColumnDefinitionContext _localctx = new ColumnDefinitionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_columnDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(IDENTIFIER);
			setState(135);
			dataType();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnsToBeUpdatedContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(SQLParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(SQLParser.EQ, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLParser.COMMA, i);
		}
		public ColumnsToBeUpdatedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnsToBeUpdated; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnsToBeUpdated(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnsToBeUpdated(this);
		}
	}

	public final ColumnsToBeUpdatedContext columnsToBeUpdated() throws RecognitionException {
		ColumnsToBeUpdatedContext _localctx = new ColumnsToBeUpdatedContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_columnsToBeUpdated);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			column();
			setState(138);
			match(EQ);
			setState(139);
			value();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(140);
				match(COMMA);
				setState(141);
				column();
				setState(142);
				match(EQ);
				setState(143);
				value();
				}
				}
				setState(149);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DataTypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(SQLParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(SQLParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(SQLParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(SQLParser.CHAR, 0); }
		public TerminalNode LB() { return getToken(SQLParser.LB, 0); }
		public TerminalNode RB() { return getToken(SQLParser.RB, 0); }
		public TerminalNode INT() { return getToken(SQLParser.INT, 0); }
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDataType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dataType);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(INTEGER);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(DOUBLE);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				match(STRING);
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				match(CHAR);
				setState(154);
				match(LB);
				setState(155);
				match(INTEGER);
				setState(156);
				match(RB);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 5);
				{
				setState(157);
				match(INT);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValueListContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLParser.COMMA, i);
		}
		public ValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitValueList(this);
		}
	}

	public final ValueListContext valueList() throws RecognitionException {
		ValueListContext _localctx = new ValueListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_valueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			value();
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(161);
				match(COMMA);
				setState(162);
				value();
				}
				}
				setState(167);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTable(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectConditionContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> LINKAGE() { return getTokens(SQLParser.LINKAGE); }
		public TerminalNode LINKAGE(int i) {
			return getToken(SQLParser.LINKAGE, i);
		}
		public SelectConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSelectCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSelectCondition(this);
		}
	}

	public final SelectConditionContext selectCondition() throws RecognitionException {
		SelectConditionContext _localctx = new SelectConditionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_selectCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			column();
			setState(171);
			operator();
			setState(172);
			value();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LINKAGE) {
				{
				{
				setState(173);
				match(LINKAGE);
				setState(174);
				column();
				setState(175);
				operator();
				setState(176);
				value();
				}
				}
				setState(182);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateConditionContext extends ParserRuleContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UpdateConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUpdateCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUpdateCondition(this);
		}
	}

	public final UpdateConditionContext updateCondition() throws RecognitionException {
		UpdateConditionContext _localctx = new UpdateConditionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_updateCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			column();
			setState(184);
			match(EQ);
			setState(185);
			value();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteConditionContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(SQLParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(SQLParser.EQ, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(SQLParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SQLParser.AND, i);
		}
		public DeleteConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDeleteCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDeleteCondition(this);
		}
	}

	public final DeleteConditionContext deleteCondition() throws RecognitionException {
		DeleteConditionContext _localctx = new DeleteConditionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_deleteCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			column();
			setState(188);
			match(EQ);
			setState(189);
			value();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(190);
				match(AND);
				setState(191);
				column();
				setState(192);
				match(EQ);
				setState(193);
				value();
				}
				}
				setState(199);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumn(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INTEGER_VALUE() { return getToken(SQLParser.INTEGER_VALUE, 0); }
		public TerminalNode DOUBLE_VALUE() { return getToken(SQLParser.DOUBLE_VALUE, 0); }
		public TerminalNode STRING_VALUE() { return getToken(SQLParser.STRING_VALUE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8796093046784L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IndexContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitIndex(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(SQLParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(SQLParser.LT, 0); }
		public TerminalNode LTE() { return getToken(SQLParser.LTE, 0); }
		public TerminalNode GT() { return getToken(SQLParser.GT, 0); }
		public TerminalNode GTE() { return getToken(SQLParser.GTE, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 126L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	public static final String _serializedATN =
		"\u0004\u0001/\u00d1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000/\b\u0000\u0001\u0000\u0003\u0000"+
		"2\b\u0000\u0003\u00004\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001;\b\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002?\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004O\b\u0004\n\u0004"+
		"\f\u0004R\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007n\b\u0007\n\u0007"+
		"\f\u0007q\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0085"+
		"\b\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u0092\b\n\n\n\f\n\u0095\t\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u009f\b\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00a4"+
		"\b\f\n\f\f\f\u00a7\t\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e"+
		"\u00b3\b\u000e\n\u000e\f\u000e\u00b6\t\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00c4\b\u0010\n"+
		"\u0010\f\u0010\u00c7\t\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0000"+
		"\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(\u0000\u0002\u0002\u0000\r\u000e++\u0001"+
		"\u0000\u0001\u0006\u00ce\u00003\u0001\u0000\u0000\u0000\u0002:\u0001\u0000"+
		"\u0000\u0000\u0004>\u0001\u0000\u0000\u0000\u0006@\u0001\u0000\u0000\u0000"+
		"\bG\u0001\u0000\u0000\u0000\nY\u0001\u0000\u0000\u0000\f`\u0001\u0000"+
		"\u0000\u0000\u000ef\u0001\u0000\u0000\u0000\u0010{\u0001\u0000\u0000\u0000"+
		"\u0012\u0086\u0001\u0000\u0000\u0000\u0014\u0089\u0001\u0000\u0000\u0000"+
		"\u0016\u009e\u0001\u0000\u0000\u0000\u0018\u00a0\u0001\u0000\u0000\u0000"+
		"\u001a\u00a8\u0001\u0000\u0000\u0000\u001c\u00aa\u0001\u0000\u0000\u0000"+
		"\u001e\u00b7\u0001\u0000\u0000\u0000 \u00bb\u0001\u0000\u0000\u0000\""+
		"\u00c8\u0001\u0000\u0000\u0000$\u00ca\u0001\u0000\u0000\u0000&\u00cc\u0001"+
		"\u0000\u0000\u0000(\u00ce\u0001\u0000\u0000\u0000*4\u0005\u0000\u0000"+
		"\u0001+1\u0003\u0002\u0001\u0000,.\u0005\n\u0000\u0000-/\u0005\u0000\u0000"+
		"\u0001.-\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/2\u0001\u0000"+
		"\u0000\u000002\u0005\u0000\u0000\u00011,\u0001\u0000\u0000\u000010\u0001"+
		"\u0000\u0000\u000024\u0001\u0000\u0000\u00003*\u0001\u0000\u0000\u0000"+
		"3+\u0001\u0000\u0000\u00004\u0001\u0001\u0000\u0000\u00005;\u0003\u0004"+
		"\u0002\u00006;\u0003\u0006\u0003\u00007;\u0003\b\u0004\u00008;\u0003\n"+
		"\u0005\u00009;\u0003\f\u0006\u0000:5\u0001\u0000\u0000\u0000:6\u0001\u0000"+
		"\u0000\u0000:7\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:9\u0001"+
		"\u0000\u0000\u0000;\u0003\u0001\u0000\u0000\u0000<?\u0003\u000e\u0007"+
		"\u0000=?\u0003\u0010\b\u0000><\u0001\u0000\u0000\u0000>=\u0001\u0000\u0000"+
		"\u0000?\u0005\u0001\u0000\u0000\u0000@A\u0005!\u0000\u0000AB\u0005\t\u0000"+
		"\u0000BC\u0005\u0015\u0000\u0000CD\u0003\u001a\r\u0000DE\u0005(\u0000"+
		"\u0000EF\u0003\u001c\u000e\u0000F\u0007\u0001\u0000\u0000\u0000GH\u0005"+
		"\u0017\u0000\u0000HI\u0005\u001a\u0000\u0000IJ\u0003\u001a\r\u0000JK\u0005"+
		"\u001c\u0000\u0000KP\u0003\"\u0011\u0000LM\u0005\u000b\u0000\u0000MO\u0003"+
		"\"\u0011\u0000NL\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000PN\u0001"+
		"\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QS\u0001\u0000\u0000\u0000"+
		"RP\u0001\u0000\u0000\u0000ST\u0005 \u0000\u0000TU\u0005\'\u0000\u0000"+
		"UV\u0005\u001c\u0000\u0000VW\u0003\u0018\f\u0000WX\u0005 \u0000\u0000"+
		"X\t\u0001\u0000\u0000\u0000YZ\u0005&\u0000\u0000Z[\u0003\u001a\r\u0000"+
		"[\\\u0005\"\u0000\u0000\\]\u0003\u0014\n\u0000]^\u0005(\u0000\u0000^_"+
		"\u0003\u001e\u000f\u0000_\u000b\u0001\u0000\u0000\u0000`a\u0005\u0013"+
		"\u0000\u0000ab\u0005\u0015\u0000\u0000bc\u0003\u001a\r\u0000cd\u0005("+
		"\u0000\u0000de\u0003 \u0010\u0000e\r\u0001\u0000\u0000\u0000fg\u0005\u0012"+
		"\u0000\u0000gh\u0005$\u0000\u0000hi\u0003\u001a\r\u0000ij\u0005\u001c"+
		"\u0000\u0000jo\u0003\u0012\t\u0000kl\u0005\u000b\u0000\u0000ln\u0003\u0012"+
		"\t\u0000mk\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000\u0000om\u0001\u0000"+
		"\u0000\u0000op\u0001\u0000\u0000\u0000pr\u0001\u0000\u0000\u0000qo\u0001"+
		"\u0000\u0000\u0000rs\u0005\u000b\u0000\u0000st\u0005\u001f\u0000\u0000"+
		"tu\u0005\u001b\u0000\u0000uv\u0005\u001c\u0000\u0000vw\u0005*\u0000\u0000"+
		"wx\u0005 \u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0005 \u0000\u0000z"+
		"\u000f\u0001\u0000\u0000\u0000{|\u0005\u0012\u0000\u0000|}\u0005\u0016"+
		"\u0000\u0000}~\u0003&\u0013\u0000~\u007f\u0005\u001d\u0000\u0000\u007f"+
		"\u0080\u0003\u001a\r\u0000\u0080\u0081\u0005\u001c\u0000\u0000\u0081\u0082"+
		"\u0003\"\u0011\u0000\u0082\u0084\u0005 \u0000\u0000\u0083\u0085\u0005"+
		"%\u0000\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0011\u0001\u0000\u0000\u0000\u0086\u0087\u0005*\u0000"+
		"\u0000\u0087\u0088\u0003\u0016\u000b\u0000\u0088\u0013\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0003\"\u0011\u0000\u008a\u008b\u0005\u0001\u0000\u0000"+
		"\u008b\u0093\u0003$\u0012\u0000\u008c\u008d\u0005\u000b\u0000\u0000\u008d"+
		"\u008e\u0003\"\u0011\u0000\u008e\u008f\u0005\u0001\u0000\u0000\u008f\u0090"+
		"\u0003$\u0012\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u008c\u0001"+
		"\u0000\u0000\u0000\u0092\u0095\u0001\u0000\u0000\u0000\u0093\u0091\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0015\u0001"+
		"\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0096\u009f\u0005"+
		"\u0019\u0000\u0000\u0097\u009f\u0005\u0014\u0000\u0000\u0098\u009f\u0005"+
		"#\u0000\u0000\u0099\u009a\u0005\u0011\u0000\u0000\u009a\u009b\u0005\u001c"+
		"\u0000\u0000\u009b\u009c\u0005\u0019\u0000\u0000\u009c\u009f\u0005 \u0000"+
		"\u0000\u009d\u009f\u0005\u0018\u0000\u0000\u009e\u0096\u0001\u0000\u0000"+
		"\u0000\u009e\u0097\u0001\u0000\u0000\u0000\u009e\u0098\u0001\u0000\u0000"+
		"\u0000\u009e\u0099\u0001\u0000\u0000\u0000\u009e\u009d\u0001\u0000\u0000"+
		"\u0000\u009f\u0017\u0001\u0000\u0000\u0000\u00a0\u00a5\u0003$\u0012\u0000"+
		"\u00a1\u00a2\u0005\u000b\u0000\u0000\u00a2\u00a4\u0003$\u0012\u0000\u00a3"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"\u0019\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0005*\u0000\u0000\u00a9\u001b\u0001\u0000\u0000\u0000\u00aa\u00ab"+
		"\u0003\"\u0011\u0000\u00ab\u00ac\u0003(\u0014\u0000\u00ac\u00b4\u0003"+
		"$\u0012\u0000\u00ad\u00ae\u0005\u000f\u0000\u0000\u00ae\u00af\u0003\""+
		"\u0011\u0000\u00af\u00b0\u0003(\u0014\u0000\u00b0\u00b1\u0003$\u0012\u0000"+
		"\u00b1\u00b3\u0001\u0000\u0000\u0000\u00b2\u00ad\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b6\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u001d\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b8\u0003\"\u0011\u0000\u00b8"+
		"\u00b9\u0005\u0001\u0000\u0000\u00b9\u00ba\u0003$\u0012\u0000\u00ba\u001f"+
		"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0003\"\u0011\u0000\u00bc\u00bd\u0005"+
		"\u0001\u0000\u0000\u00bd\u00c5\u0003$\u0012\u0000\u00be\u00bf\u0005\u0010"+
		"\u0000\u0000\u00bf\u00c0\u0003\"\u0011\u0000\u00c0\u00c1\u0005\u0001\u0000"+
		"\u0000\u00c1\u00c2\u0003$\u0012\u0000\u00c2\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c3\u00be\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c6!\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c9\u0005*\u0000\u0000\u00c9#\u0001\u0000\u0000\u0000\u00ca\u00cb\u0007"+
		"\u0000\u0000\u0000\u00cb%\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005*\u0000"+
		"\u0000\u00cd\'\u0001\u0000\u0000\u0000\u00ce\u00cf\u0007\u0001\u0000\u0000"+
		"\u00cf)\u0001\u0000\u0000\u0000\r.13:>Po\u0084\u0093\u009e\u00a5\u00b4"+
		"\u00c5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}