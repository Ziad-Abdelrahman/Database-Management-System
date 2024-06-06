package utilities;

import exceptions.DBAppException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;

public class Validation {
    public static void checkExistence(String name, String ofWhat, String mode) throws DBAppException, IOException {
        int contentIndex;
        ofWhat = ofWhat.toLowerCase();
        switch (ofWhat) {
            case "table":
                contentIndex = 0;
                break;
            case "column":
                contentIndex = 1;
                break;
            default:
                contentIndex = 4;
                break;
        }
        boolean exists = mode.equals("exists");
        BufferedReader br = new BufferedReader(new FileReader("metadata.csv"));
        String line = br.readLine();
        boolean flag = false;
        while (line != null) {
            String[] content = line.split(",");
            if (content[contentIndex].equals(name)) {
                flag = true;
                break;
            } else {
                line = br.readLine();
            }
        }
        br.close();
        if (exists) {
            if (!flag) {
                switch (ofWhat) {
                    case "table":
                        throw new DBAppException("Table does not exist");
                    case "column":
                        throw new DBAppException("Column does not exist");
                    case "index":
                        throw new DBAppException("Index does not exist");
                }
            }
        } else {
            if (flag) {
                switch (ofWhat) {
                    case "table":
                        throw new DBAppException("Table already exists");
                    case "index":
                        throw new DBAppException("Index already exists");
                }
            }
        }
    }

    public static void correctEntries(Hashtable<String, Object> colValues, String tableName) throws DBAppException {
        // Check if the columns exist in the table
        // Check if the data types are compatible

        Hashtable<String, String[]> colWithDataTypes = CSVManager.readCSVFile(tableName);
        boolean flag = true;
        for (String columnName : colValues.keySet()) {

            if (!colWithDataTypes.containsKey(columnName)) {
                throw new DBAppException("Column " + columnName + " does not exist");
            }
            String dataType = colWithDataTypes.get(columnName)[0];
            Object value = colValues.get(columnName);
            if (!compatibleDataTypes(dataType, value)) {
                throw new DBAppException("Incompatible Data Types: Expected " + dataType + " but got " + value.getClass().getName());
            }
        }
    }

    public static Hashtable<String, String[]> checkColumnCount(Hashtable<String, Object> colValues, String tableName) throws DBAppException {
        Hashtable<String, String[]> colWithDataTypes = CSVManager.readCSVFile(tableName);
        if (colWithDataTypes.size() > colValues.size()) {
            throw new DBAppException("Insufficient number of columns");
        }
        if (colWithDataTypes.size() < colValues.size()) {
            throw new DBAppException("Incorrect number of columns");
        }
        return colWithDataTypes;
    }

    public static boolean compatibleDataTypes(String dataType, Object value) {
        if (dataType.equalsIgnoreCase("java.lang.integer")) {
            if (!(value instanceof Integer))
                return false;
        } else if (dataType.equalsIgnoreCase("java.lang.double")) {
            if (!(value instanceof Double || value instanceof Integer))
                return false;
        } else {
            if (!(value instanceof String))
                return false;
        }
        return true;
    }

    public static void validateQuery(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException, IOException {
        if (arrSQLTerms.length != strarrOperators.length + 1) {
            throw new DBAppException("Invalid query");
        }
        validateInBetweenOperators(strarrOperators);
        validateSQLTermOperators(arrSQLTerms);
        String tableName = arrSQLTerms[0]._strTableName;
        checkExistence(tableName, "table", "exists");
        for (SQLTerm sql : arrSQLTerms) {
            if (!tableName.equalsIgnoreCase(sql._strTableName)) {
                throw new DBAppException("SQL joins are not supported");
            }
            checkExistence(sql._strColumnName, "column", "exists");
            validateDataType(sql._strTableName, sql._strColumnName, sql._objValue);
        }
    }

    public static void validateInBetweenOperators(String[] strarrOperators) throws DBAppException {
        for (String operator : strarrOperators) {
            if (!(operator.equalsIgnoreCase("AND") ||
                    operator.equalsIgnoreCase("OR") ||
                    operator.equalsIgnoreCase("XOR")))
                throw new DBAppException("Invalid Operator: " + operator);
        }
    }

    public static void validateSQLTermOperators(SQLTerm[] arrSQLTerms) throws DBAppException {
        for (SQLTerm sqlTerm : arrSQLTerms) {
            if (!sqlTerm._strOperator.equals("=") &&
                    !sqlTerm._strOperator.equals("!=") &&
                    !sqlTerm._strOperator.equals(">") &&
                    !sqlTerm._strOperator.equals("<") &&
                    !sqlTerm._strOperator.equals(">=") &&
                    !sqlTerm._strOperator.equals("<="))
                throw new DBAppException("Invalid Operator: " + sqlTerm._strOperator);

        }
    }

    public static void validateDataType(String strTableName, String columnName, Object value) throws DBAppException {
        Hashtable<String, String[]> colWithDataTypes = CSVManager.readCSVFile(strTableName);
        String dataType = colWithDataTypes.get(columnName)[0];
        if (!compatibleDataTypes(dataType, value))
            throw new DBAppException("Incompatible Data Types: Expected " + dataType + " but got " + value.getClass().getName());
    }

    public static void checkIfClusteringKeyIsBeingUpdated(String strTableName, Hashtable<String, Object> htblColNameValue) throws DBAppException {
        Hashtable<String, String[]> colWithDataTypes = CSVManager.readCSVFile(strTableName);
        String clusteringKey = CSVManager.getClusteringColumn(colWithDataTypes);
        for (String columnName : htblColNameValue.keySet())
            if (columnName.equalsIgnoreCase(clusteringKey))
                throw new DBAppException("You can't update the clustering key");
    }

    public static void checkIfNulls(Hashtable<String, Object> htblColNameValue) throws DBAppException {
        for (String columnName : htblColNameValue.keySet())
            if (htblColNameValue.get(columnName) == null)
                throw new DBAppException("You can't insert null values");

    }

    public static void checkSupportedDataTypes(Hashtable<String, String> ht) throws DBAppException {
        for (String key : ht.keySet()) {
            String value = ht.get(key);
            if (!value.equalsIgnoreCase("java.lang.Integer") && !value.equalsIgnoreCase("java.lang.Double") && !value.equalsIgnoreCase("java.lang.String"))
                throw new DBAppException("Unsupported data type");
        }
    }

    public static void checkDuplicateColumns(Hashtable<String, String> ht) throws DBAppException {
        HashSet<String> set = new HashSet<>();
        for (String key : ht.keySet())
            if (!set.contains(key.toLowerCase()))
                set.add(key.toLowerCase());
            else
                throw new DBAppException("Duplicate columns");
    }
    public static void checkColAlrHasAnIndex(String tableName, String columnName) throws DBAppException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("metadata.csv"));
        String line = br.readLine();
        boolean flag = false;
        while (line != null) {
            String[] content = line.split(",");
            if (content[0].equalsIgnoreCase(tableName) && content[1].equalsIgnoreCase(columnName) ) {
                if(!content[4].equalsIgnoreCase("null"))
                    flag = true;
                break;
            } else {
                line = br.readLine();
            }
        }
        br.close();
        if (flag) {
            throw new DBAppException("There is already index for the given column");
        }
    }

    public static void checkIndexNameForTheSameTable(String tablename,String indexName) throws DBAppException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("metadata.csv"));
            String line = br.readLine();
            boolean flag = false;
            while (line != null) {
                String[] content = line.split(",");
                if (content[0].equalsIgnoreCase(tablename) && content[4].equalsIgnoreCase(indexName)) {
                    flag = true;
                    break;
                } else {
                    line = br.readLine();
                }
            }
            br.close();
            if (flag) {
                throw new DBAppException("There is already index with the same name for the given table");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
