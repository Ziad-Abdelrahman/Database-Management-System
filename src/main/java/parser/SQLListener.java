// Generated from SQL.g4 by ANTLR 4.13.1

package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLParser}.
 */
public interface SQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(SQLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(SQLParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(SQLParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(SQLParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateStatement(SQLParser.CreateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateStatement(SQLParser.CreateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(SQLParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(SQLParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(SQLParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(SQLParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(SQLParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(SQLParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(SQLParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(SQLParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(SQLParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(SQLParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(SQLParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(SQLParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(SQLParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(SQLParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnsToBeUpdated}.
	 * @param ctx the parse tree
	 */
	void enterColumnsToBeUpdated(SQLParser.ColumnsToBeUpdatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnsToBeUpdated}.
	 * @param ctx the parse tree
	 */
	void exitColumnsToBeUpdated(SQLParser.ColumnsToBeUpdatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(SQLParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(SQLParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#valueList}.
	 * @param ctx the parse tree
	 */
	void enterValueList(SQLParser.ValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#valueList}.
	 * @param ctx the parse tree
	 */
	void exitValueList(SQLParser.ValueListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(SQLParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(SQLParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectCondition}.
	 * @param ctx the parse tree
	 */
	void enterSelectCondition(SQLParser.SelectConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectCondition}.
	 * @param ctx the parse tree
	 */
	void exitSelectCondition(SQLParser.SelectConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#updateCondition}.
	 * @param ctx the parse tree
	 */
	void enterUpdateCondition(SQLParser.UpdateConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#updateCondition}.
	 * @param ctx the parse tree
	 */
	void exitUpdateCondition(SQLParser.UpdateConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#deleteCondition}.
	 * @param ctx the parse tree
	 */
	void enterDeleteCondition(SQLParser.DeleteConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#deleteCondition}.
	 * @param ctx the parse tree
	 */
	void exitDeleteCondition(SQLParser.DeleteConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(SQLParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(SQLParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SQLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(SQLParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(SQLParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(SQLParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(SQLParser.OperatorContext ctx);
}