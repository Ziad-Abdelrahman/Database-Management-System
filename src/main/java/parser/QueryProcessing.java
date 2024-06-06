package parser;

import engine.DBApp;
import exceptions.DBAppException;
import utilities.CSVManager;
import utilities.SQLTerm;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

public class QueryProcessing {

    public static Iterator createTable(SQLParser.CreateTableContext statement, DBApp dbApp) throws DBAppException, IOException {
        String tableName = statement.table().getText();
        if (statement.IDENTIFIER()==null)
            throw new DBAppException("No Primary key specified");
        String clusteringKey = statement.IDENTIFIER().getText();
        Hashtable<String, String> htblColNameType = new Hashtable<>();
        for(SQLParser.ColumnDefinitionContext column : statement.columnDefinition()) {
            String columnName = column.IDENTIFIER().getText();
            String columnType = column.dataType().getText();
            switch (columnType.toLowerCase()) {
                case "int" : case "integer":
                    columnType = "java.lang.Integer";
                    break;
                case "double":
                    columnType = "java.lang.Double";
                    break;
                default:
                    columnType = "java.lang.String";
                    break;
            }
            htblColNameType.put(columnName, columnType);
        }
        dbApp.createTable(tableName, clusteringKey, htblColNameType);
        return null;
    }
    public static Iterator createIndex(SQLParser.CreateIndexContext statement, DBApp dbApp) throws DBAppException, IOException {
        String tableName = statement.table().getText();
        String indexName = statement.index().getText();
        String columnName = statement.column().getText();
        dbApp.createIndex(tableName, columnName, indexName);
        return null;
    }

    public static Iterator insertIntoTable(SQLParser.InsertStatementContext statement, DBApp dbApp) throws DBAppException, IOException {
        String tableName = statement.table().getText();
        if (statement.valueList().value().size() != statement.column().size())
            throw new DBAppException("Number of columns and values don't match");
        Hashtable<String, Object> htblColNameValue = new Hashtable<>();
        for (int i = 0; i < statement.column().size(); i++) {
            String columnName = statement.column(i).getText();
            SQLParser.ValueContext valueInQuery= statement.valueList().value(i);
            if (valueInQuery.STRING_VALUE()!=null)
                htblColNameValue.put(columnName, getStringWithoutQuotes(valueInQuery.STRING_VALUE().getText()));
            else if (valueInQuery.DOUBLE_VALUE()!=null)
                htblColNameValue.put(columnName, Double.parseDouble(valueInQuery.DOUBLE_VALUE().getText()));
            else if (valueInQuery.INTEGER_VALUE()!=null) {
                String DataType = CSVManager.readCSVFile(tableName).get(columnName)[0];
                if (DataType.equalsIgnoreCase("java.lang.Integer"))
                    htblColNameValue.put(columnName, Integer.parseInt(valueInQuery.INTEGER_VALUE().getText()));
                else
                    htblColNameValue.put(columnName, Double.parseDouble(valueInQuery.INTEGER_VALUE().getText()));
            }
            }
        dbApp.insertIntoTable(tableName, htblColNameValue);
        return null;
    }

    public static Iterator updateTable(SQLParser.UpdateStatementContext statement, DBApp dbApp) throws DBAppException, IOException {
        String tableName = statement.table().getText();
        String clusteringKey = statement.updateCondition().value().getText();
        Hashtable<String,Object> htblColNameValue = new Hashtable<>();
        for(int i = 0;i<statement.columnsToBeUpdated().column().size(); i++){
            String columnName = statement.columnsToBeUpdated().column(i).getText();
            SQLParser.ValueContext valueInQuery= statement.columnsToBeUpdated().value(i);
            if (valueInQuery.STRING_VALUE()!=null)
                htblColNameValue.put(columnName, getStringWithoutQuotes(valueInQuery.STRING_VALUE().getText()));
            else if (valueInQuery.DOUBLE_VALUE()!=null)
                htblColNameValue.put(columnName, Double.parseDouble(valueInQuery.DOUBLE_VALUE().getText()));
            else if (valueInQuery.INTEGER_VALUE()!=null){
                String DataType = CSVManager.readCSVFile(tableName).get(columnName)[0];
                if (DataType.equalsIgnoreCase("java.lang.Integer"))
                    htblColNameValue.put(columnName, Integer.parseInt(valueInQuery.INTEGER_VALUE().getText()));
                else
                    htblColNameValue.put(columnName, Double.parseDouble(valueInQuery.INTEGER_VALUE().getText()));
                }

        }
        dbApp.updateTable(tableName,clusteringKey,htblColNameValue);
        return null;
    }

    public static Iterator deleteFromTable(SQLParser.DeleteStatementContext statement, DBApp dbApp) throws DBAppException, IOException {
        String tableName = statement.table().getText();
        Hashtable<String,Object> htblColNameValue=new Hashtable<>();

        for (int i = 0; i < statement.deleteCondition().value().size(); i++) {
            String columnName = statement.deleteCondition().column(i).getText();
            SQLParser.ValueContext valueInQuery= statement.deleteCondition().value(i);
            if (valueInQuery.STRING_VALUE()!=null)
                htblColNameValue.put(columnName, getStringWithoutQuotes(valueInQuery.STRING_VALUE().getText()));
            else if (valueInQuery.DOUBLE_VALUE()!=null)
                htblColNameValue.put(columnName, Double.parseDouble(valueInQuery.DOUBLE_VALUE().getText()));
            else if (valueInQuery.INTEGER_VALUE()!=null){
                String DataType = CSVManager.readCSVFile(tableName).get(columnName)[0];
                if (DataType.equalsIgnoreCase("java.lang.Integer"))
                    htblColNameValue.put(columnName, Integer.parseInt(valueInQuery.INTEGER_VALUE().getText()));
                else
                    htblColNameValue.put(columnName, Double.parseDouble(valueInQuery.INTEGER_VALUE().getText()));
            }
        }
        dbApp.deleteFromTable(tableName,htblColNameValue);
        return null;
    }

    public static Iterator selectFromTable(SQLParser.SelectStatementContext statement,DBApp dbApp) throws DBAppException, IOException{
        SQLTerm[] arrSQLTerms=new SQLTerm[statement.selectCondition().value().size()];
        String[] strarrOperators=new String[statement.selectCondition().LINKAGE().size()];

        for (int i=0;i<statement.selectCondition().value().size();i++){
            SQLTerm sqlTerm = new SQLTerm();
            sqlTerm._strTableName = statement.table().getText();
            sqlTerm._strColumnName = statement.selectCondition().column(i).getText();
            sqlTerm._strOperator = statement.selectCondition().operator(i).getText();
            if (statement.selectCondition().value(i).STRING_VALUE()!=null)
                sqlTerm._objValue = getStringWithoutQuotes(statement.selectCondition().value(i).STRING_VALUE().getText());
            else if (statement.selectCondition().value(i).DOUBLE_VALUE()!=null)
                sqlTerm._objValue = Double.parseDouble(statement.selectCondition().value(i).DOUBLE_VALUE().getText());
            else if (statement.selectCondition().value(i).INTEGER_VALUE()!=null) {
                String DataType = CSVManager.readCSVFile(sqlTerm._strTableName).get(sqlTerm._strColumnName)[0];
                if (DataType.equalsIgnoreCase("java.lang.Integer"))
                    sqlTerm._objValue = Integer.parseInt(statement.selectCondition().value(i).INTEGER_VALUE().getText());
                else
                    sqlTerm._objValue = Double.parseDouble(statement.selectCondition().value(i).INTEGER_VALUE().getText());
            }

            arrSQLTerms[i]=sqlTerm;
            if (i!=statement.selectCondition().value().size()-1)
                strarrOperators[i]=statement.selectCondition().LINKAGE(i).getText();
        }
        return dbApp.selectFromTable(arrSQLTerms,strarrOperators);


    }

    public static String getStringWithoutQuotes(String s){
        return s.substring(1,s.length()-1);
    }
}
