package engine;

import exceptions.DBAppException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.AntlrErrorListener;
import parser.QueryProcessing;
import parser.SQLLexer;
import parser.SQLParser;
import table.Table;
import table.page.Page;
import table.page.PageAddress;
import table.page.tuple.Tuple;
import utilities.*;
import utilities.bplustree.BPlusTree;

import java.io.*;
import java.util.*;

public class DBApp {
    private int maxSize;
    private int fanOut;
    public DBApp() {
        init();
    }

    // this does whatever initialization you would like
    // or leave it empty if there is no code you want to
    // execute at application startup
    public void init() {
        maxSize = FileConfigManager.getMaximumRowsCountinPage();
        fanOut = FileConfigManager.getFanOut();
        FileConfigManager.filesInit();
    }


    // following method creates one table only
    // strClusteringKeyColumn is the name of the column that will be the primary
    // key and the clustering column as well. The data type of that column will
    // be passed in htblColNameType
    // htblColNameValue will have the column name as key and the data
    // type as value
    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String, String> htblColNameType) throws DBAppException, IOException {
        Validation.checkExistence(strTableName, "table", "not exists");
        Validation.checkSupportedDataTypes(htblColNameType);
        Validation.checkDuplicateColumns(htblColNameType);
        if (htblColNameType==null || htblColNameType.isEmpty()){
            throw new DBAppException("No columns were provided, Cannot create table");
        }
        StringBuilder s = new StringBuilder();
        for (String key : htblColNameType.keySet()) {
            String value = htblColNameType.get(key);
            String bool = (key.equalsIgnoreCase(strClusteringKeyColumn)) ? "True" : "False";
            s.append(strTableName).append(",").append(key).append(",").append(value).append(",").append(bool).append(",null,null\n");
        }
        try {
            FileWriter outfile = new FileWriter(("metadata.csv"), true);
            outfile.append(s.toString());
            outfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table t = new Table(strTableName, strClusteringKeyColumn, htblColNameType.get(strClusteringKeyColumn));
        Serializer.serialize("Tables/" + strTableName, t);
        File file = new File("Pages/" + strTableName);
        file.mkdir();
        File file2 = new File("Indices/" + strTableName);
        file2.mkdir();
    }

//FIXED: THE FILEWRITER APPENDS TO THE FILE INSTEAD OF OVERWRITING IT


    // following method creates a B+tree index
    public void createIndex(String strTableName,
                            String strColName,
                            String strIndexName) throws DBAppException, IOException {
        Validation.checkExistence(strTableName, "table", "exists");
        Validation.checkExistence(strColName, "column", "exists");
        //Validation.checkExistence(strIndexName, "index", "not exists");
        //We made an assumption that we wont create an index if the column already has an index
        Validation.checkColAlrHasAnIndex(strTableName, strColName);
        Validation.checkIndexNameForTheSameTable(strTableName, strIndexName);
        Vector<String> lines = new Vector<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("metadata.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(strTableName))
                    if (parts[1].equalsIgnoreCase(strColName)) {
                        parts[4] = strIndexName;
                        parts[5] = "B+Tree";
                        strColName = parts[1];
                    }
                lines.add(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BPlusTree tree = new BPlusTree(fanOut);
        Table t = (Table) Serializer.deserialize("Tables/" + strTableName);
        for (PageAddress p : t.getPages()) {
            String pageAddress = p.getPageAddress();
            Page page = (Page) Serializer.deserialize("Pages/" + strTableName + "/" + pageAddress);
            for (Tuple tuple : page.getTuples()) {
                tree.insert((Comparable) tuple.getValues().get(strColName), p);
            }
        }

        Serializer.serialize("Indices/" + strTableName + "/" + strIndexName, tree);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("metadata.csv"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // following method inserts one row only.
    // htblColNameValue must include a value for the primary key
    public void insertIntoTable(String strTableName,
                                Hashtable<String, Object> htblColNameValue) throws DBAppException, IOException {
        Validation.checkExistence(strTableName, "table", "exists");
        Validation.checkColumnCount(htblColNameValue, strTableName);
        Validation.checkIfNulls(htblColNameValue);
        Validation.correctEntries(htblColNameValue, strTableName);

        Hashtable<String, String[]> tableInfo = CSVManager.readCSVFile(strTableName);
        Hashtable<BPlusTree, String[]> BPlusTrees = new Hashtable<>();
        String clusteringKey = null;
        for (String column : tableInfo.keySet()) {
            String[] colInfo = tableInfo.get(column);
            if (!colInfo[2].equalsIgnoreCase("null")) {
                BPlusTree Bplustree = (BPlusTree) Serializer.deserialize("Indices/" + strTableName + "/" + colInfo[2]);
                BPlusTrees.put(Bplustree, new String[]{column, colInfo[2]});
            }
            if (colInfo[1].equalsIgnoreCase("True")) {
                clusteringKey = column;
            }
        }

        Table table = (Table) Serializer.deserialize("Tables/" + strTableName);
        Tuple tuple = new Tuple(htblColNameValue, clusteringKey,strTableName);

        if (table.getPages().size()==0) {
            Page first = table.createPage();
            first.getTuples().add(tuple);
            ((PageAddress) table.getPages().get(0)).setMinValue(tuple.getClusteringKeyValue());
            insertIntoIndex(BPlusTrees, tuple, table.getPages().get(0));
            Serializer.serialize("Pages/" + strTableName + "/" + ((PageAddress) table.getPages().get(0)).getPageAddress(), first);
            Serializer.serialize("Tables/" + strTableName, table);
        } else {
            int pageNumIndex = Miscellaneous.binarySearch(table.getPages(), tuple);
            if (pageNumIndex < 0) {
                pageNumIndex = -(pageNumIndex + 1);
                pageNumIndex = pageNumIndex == 0 ? 0 : pageNumIndex - 1;
            }
            PageAddress pageAddress = (PageAddress) (table.getPages().get(pageNumIndex));
            String pageLocation = "Pages/" + strTableName + "/" + pageAddress.getPageAddress();

            Page page = (Page) Serializer.deserialize(pageLocation);
            int rowNum = Miscellaneous.binarySearch(page.getTuples(), tuple);
            if (rowNum < 0)
                rowNum = -(rowNum + 1);
            else
                throw new DBAppException("Duplicate Clustering/Primary Key");


            page.getTuples().add(rowNum, tuple);
            pageAddress.setMinValue(((Tuple) page.getTuples().get(0)).getClusteringKeyValue());
            insertIntoIndex(BPlusTrees, tuple, pageAddress);
            PageAddress newPageAddress;
            while (page.getTuples().size() > maxSize) {

                Tuple overflowTuple = (Tuple) page.getTuples().remove(page.getTuples().size() - 1);
                Serializer.serialize(pageLocation, page);
                if (++pageNumIndex >= table.getPages().size())
                    page = table.createPage();
                else
                    page = (Page) Serializer.deserialize("Pages/" + strTableName + "/" + ((PageAddress) (table.getPages().get(pageNumIndex))).getPageAddress());
                page.getTuples().add(0, overflowTuple);
                pageLocation = "Pages/" + strTableName + "/" + ((PageAddress) table.getPages().get(pageNumIndex)).getPageAddress();
                newPageAddress = (PageAddress) (table.getPages().get(pageNumIndex));
                newPageAddress.setMinValue(overflowTuple.getClusteringKeyValue());
                modifyIndex(BPlusTrees, overflowTuple, pageAddress, newPageAddress);
                pageAddress = newPageAddress;
            }
            Serializer.serialize(pageLocation, page);

            Serializer.serialize("Tables/" + strTableName, table);
        }
        for (BPlusTree bplustree : BPlusTrees.keySet()) {
            String indexName = BPlusTrees.get(bplustree)[1];
            Serializer.serialize("Indices/" + strTableName + "/" + indexName, bplustree);
        }
    }

    private static void insertIntoIndex(Hashtable<BPlusTree, String[]> bPlusTrees, Tuple tuple, PageAddress pageAddress) {
        for (BPlusTree bplustree : bPlusTrees.keySet()) {
            String colName = bPlusTrees.get(bplustree)[0];
            bplustree.insert((Comparable) tuple.getValues().get(colName), pageAddress);
        }
    }

    private static void modifyIndex(Hashtable<BPlusTree, String[]> bPlusTrees, Tuple tuple, PageAddress oldValue, PageAddress newValue) {
        for (BPlusTree bplustree : bPlusTrees.keySet()) {
            String colName = bPlusTrees.get(bplustree)[0];
            bplustree.modify((Comparable) tuple.getValues().get(colName), oldValue, newValue);
        }
    }


    // following method updates one row only
    // htblColNameValue holds the key and new value
    // htblColNameValue will not include clustering key as column name
    // strClusteringKeyValue is the value to look for to find the row to update.
    public void updateTable(String strTableName,
                            String strClusteringKeyValue,
                            Hashtable<String, Object> htblColNameValue) throws DBAppException, IOException {
        Validation.checkIfNulls(htblColNameValue);
        Validation.correctEntries(htblColNameValue, strTableName);
        Validation.checkIfClusteringKeyIsBeingUpdated(strTableName, htblColNameValue);

        Hashtable<String, String[]> csv = CSVManager.readCSVFile(strTableName);
        String clusteringColumn=CSVManager.getClusteringColumn(csv);
        Comparable key = Miscellaneous.DataTypeConverter(csv.get(clusteringColumn)[0], strClusteringKeyValue);
        PageAddress pageAddress = null;


        if (!csv.get(clusteringColumn)[2].equalsIgnoreCase("null")) {
            BPlusTree bplustree = (BPlusTree) Serializer.deserialize("Indices/" + strTableName + "/" + csv.get(clusteringColumn)[2]);
            if (bplustree.search(key) != null)
                pageAddress = bplustree.search(key).iterator().next();
        }
        else {
            Table table = (Table) Serializer.deserialize("Tables/" + strTableName);
            int pageNumIndex = Miscellaneous.binarySearch(table.getPages(), key);
            if (pageNumIndex < 0)
                pageNumIndex = -(pageNumIndex + 1) - 1;
            if (pageNumIndex >= 0) {
                pageAddress = table.getPages().get(pageNumIndex);
            }
        }
        if (pageAddress !=null) {
            String pageLocation = "Pages/" + strTableName + "/" + pageAddress.getPageAddress();
            Page page = ((Page) Serializer.deserialize(pageLocation));

            int rowNumIndex = Miscellaneous.binarySearch(page.getTuples(), key);
            if (rowNumIndex >= 0) {
                Tuple tuple = page.getTuples().get(rowNumIndex);
                for (String key1 : htblColNameValue.keySet()) {
                    Comparable value = (Comparable) htblColNameValue.get(key1);
                    String indexName = csv.get(key1)[2];
                    if ((!(indexName.equalsIgnoreCase("null")))) {
                        BPlusTree bplustree = (BPlusTree) Serializer.deserialize("Indices/" + strTableName + "/" + indexName);
                        Comparable oldval = (Comparable) tuple.getValues().get(key1);
                        bplustree.delete(oldval, pageAddress);
                        bplustree.insert(value, pageAddress);
                        Serializer.serialize("Indices/" + strTableName + "/" + indexName, bplustree);
                    }
                    tuple.getValues().put(key1, value);
                }
            }
            Serializer.serialize(pageLocation, page);
        }

    }

    // following method could be used to delete one or more rows.
    // htblColNameValue holds the key and value. This will be used in search
    // to identify which rows/tuples to delete.
    // htblColNameValue enteries are ANDED together
    public void deleteFromTable(String strTableName,
                                Hashtable<String, Object> htblColNameValue) throws DBAppException, IOException {
        Validation.checkExistence(strTableName, "table", "exists");
        Validation.correctEntries(htblColNameValue, strTableName);
        Table table = (Table) Serializer.deserialize("Tables/"+strTableName);
        Hashtable<String, String[]> tableInfo = CSVManager.readCSVFile(strTableName);
        String clusteringColumn = CSVManager.getClusteringColumn(tableInfo);

        // if clustering key is present, use it since it'll take O(log n) time to delete and there's only one tuple with that value
        // since the clustering key is the primary key so it'll be better to use the clustering key than the index
        if (htblColNameValue.containsKey(clusteringColumn)) {
            deleteIfClusteringKeyFound(strTableName, htblColNameValue, table, clusteringColumn, tableInfo);
            Serializer.serialize("Tables/" + strTableName, table);
            return;
        }


        Hashtable<BPlusTree, String[]> BPlusTrees = new Hashtable<>();
        Hashtable<String,BPlusTree> bTreesFinder = new Hashtable<>();

        for(String colName : tableInfo.keySet()){
            String[] colInfo = tableInfo.get(colName);
            if (!colInfo[2].equalsIgnoreCase("null")){
                BPlusTree Bplustree = (BPlusTree) Serializer.deserialize("Indices/" + strTableName + "/" + colInfo[2]);
                BPlusTrees.put(Bplustree, new String[]{colName, colInfo[2]});
                bTreesFinder.put(colName,Bplustree);
            }

        }

        Set<PageAddress> pagesToSearchIn = null;
        for(String key : htblColNameValue.keySet()){
            String[] colInfo = tableInfo.get(key);
            if (!colInfo[2].equalsIgnoreCase("null")) {
                Comparable value = (Comparable) htblColNameValue.get(key);
                BPlusTree Bplustree = bTreesFinder.get(key);
                if (Bplustree.search(value) != null) {
                    if (pagesToSearchIn != null)
                        pagesToSearchIn.retainAll(Bplustree.search(value));
                    else {
                        pagesToSearchIn = new HashSet<>();
                        pagesToSearchIn.addAll(Bplustree.search(value));
                    }
                }

            }
        }

        if (pagesToSearchIn == null) {
            pagesToSearchIn = new HashSet<>();
            pagesToSearchIn.addAll(table.getPages());
        }

        for (PageAddress pageAddress : pagesToSearchIn) {
            String pageLocation = "Pages/" + strTableName + "/" + pageAddress.getPageAddress();
            Page page = ((Page) Serializer.deserialize(pageLocation));
            boolean toBeSerialized=true;
            for (int j = 0; j < page.getTuples().size(); j++) {
                boolean willBeDeleted = true;
                for (String columnName : htblColNameValue.keySet()) {
                    Comparable valueToBeDeleted = (Comparable) htblColNameValue.get(columnName);
                    Object valueInTuple = page.getTuples().get(j).getValues().get(columnName);
                    if (valueToBeDeleted.compareTo(valueInTuple) != 0) {
                        willBeDeleted = false;
                        break;
                    }

                }
                if (willBeDeleted) {
                    //delete from index
                    for (BPlusTree bplustree : BPlusTrees.keySet()) {
                        String colName = BPlusTrees.get(bplustree)[0];
                        bplustree.delete((Comparable) page.getTuples().get(j).getValues().get(colName), pageAddress);
                    }
                    //delete from pages
                    page.getTuples().remove(j);
                    j--;
                    if (page.getTuples().size() == 0) {
                        table.getPages().remove(pageAddress);
                        File file = new File( pageLocation + ".ser");
                        file.delete();
                        toBeSerialized=false;
                    }
                }
            }
            if (toBeSerialized) {
                pageAddress.setMinValue(page.getTuples().get(0).getClusteringKeyValue());
                Serializer.serialize(pageLocation, page);
            }
        }
        for (BPlusTree bplustree: BPlusTrees.keySet()){
            String indexName = BPlusTrees.get(bplustree)[1];
            Serializer.serialize("Indices/" + strTableName + "/" + indexName, bplustree);

        }
        Serializer.serialize("Tables/" + strTableName, table);
    }

    private static void deleteIfClusteringKeyFound(String strTableName, Hashtable<String, Object> htblColNameValue, Table table, String clusteringColumn, Hashtable<String, String[]> tableInfo) {
        int pageNumIndex = Miscellaneous.binarySearch(table.getPages(), (Comparable) htblColNameValue.get(clusteringColumn));
        if (pageNumIndex < 0)
            pageNumIndex = -(pageNumIndex + 1) - 1;
        if (pageNumIndex >= 0) {
            PageAddress pageAddress = (PageAddress) (table.getPages().get(pageNumIndex));
            String pageLocation = "Pages/" + strTableName + "/" + pageAddress.getPageAddress();
            Page page = ((Page) Serializer.deserialize(pageLocation));
            int rowNumIndex = Miscellaneous.binarySearch(page.getTuples(), (Comparable) htblColNameValue.get(clusteringColumn));
            if (rowNumIndex >= 0) {
                Tuple t= page.getTuples().remove(rowNumIndex);
                if (page.getTuples().size() == 0) {
                    table.getPages().remove(pageAddress);
                    File file = new File(pageLocation + ".ser");
                    file.delete();
                } else {
                    pageAddress.setMinValue(page.getTuples().get(0).getClusteringKeyValue());
                    Serializer.serialize(pageLocation, page);
                }
                for (String columnName : tableInfo.keySet()) {
                    String[] colInfo = tableInfo.get(columnName);
                    if (!colInfo[2].equalsIgnoreCase("null")) {
                        BPlusTree bplustree = (BPlusTree) Serializer.deserialize("Indices/" + strTableName + "/" + colInfo[2]);
                        bplustree.delete((Comparable) t.getValues().get(columnName), pageAddress);
                        Serializer.serialize("Indices/" + strTableName + "/" + colInfo[2], bplustree);
                    }
                }
            }
        }
    }

    public Iterator selectFromTable(SQLTerm[] arrSQLTerms,
                                    String[] strarrOperators) throws DBAppException, IOException {
        Validation.validateQuery(arrSQLTerms, strarrOperators);
        Vector<SQLTerm> operands = new Vector<>();
        Vector<HashSet<Tuple>> resultSets = new Vector<>();
        ArrayList<String> operators = new ArrayList<>();
        operands.add(arrSQLTerms[0]);
        Vector<PageAddress> pages = (Vector<PageAddress>) ((Table) Serializer.deserialize("Tables/" + arrSQLTerms[0]._strTableName)).getPages();
        for (int i = 0; i < strarrOperators.length; i++) {
            if (strarrOperators[i].compareToIgnoreCase("AND") == 0) {
                operands.add(arrSQLTerms[i + 1]);
            } else {
                operators.add(strarrOperators[i]);
                resultSets.add(ResultSetManager.createAResultSet(operands, pages)); //create a result set from the operands
                operands.clear();
                operands.add(arrSQLTerms[i + 1]);
            }
        }
        if (!operands.isEmpty())
            resultSets.add(ResultSetManager.createAResultSet(operands, pages));

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).compareToIgnoreCase("XOR") == 0) {
                HashSet<Tuple> temp1 = new HashSet<>();
                temp1.addAll(resultSets.get((i+1)));
                temp1.removeAll(resultSets.get(i));
                resultSets.get(i).removeAll(resultSets.get(i+1));
                resultSets.get(i).addAll(temp1);
                resultSets.remove(i+1);
                operators.remove(i);
                i--;
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            resultSets.get(0).addAll(resultSets.get(1));
            resultSets.remove(1);
        }

        return resultSets.get(0).iterator();
    }


    // below method returns Iterator with result set if passed // strbufSQL is a select, otherwise returns null.
    public Iterator parseSQL( StringBuffer strbufSQL ) throws DBAppException, IOException {
        SQLLexer lexer = new SQLLexer(CharStreams.fromString(strbufSQL.toString(), "UTF-8"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLParser parser = new SQLParser(tokens);
        parser.removeErrorListeners(); // Remove default listeners
        parser.addErrorListener(new AntlrErrorListener());
        try {
            SQLParser.QueryContext query = parser.query();
            if (query.simpleStatement() != null) {
                if (query.simpleStatement().createStatement() != null) {
                    if (query.simpleStatement().createStatement().createTable() != null)
                        return QueryProcessing.createTable(query.simpleStatement().createStatement().createTable(), this);
                    else if (query.simpleStatement().createStatement().createIndex() != null)
                        return QueryProcessing.createIndex(query.simpleStatement().createStatement().createIndex(), this);
                } else if (query.simpleStatement().insertStatement() != null) {
                    return QueryProcessing.insertIntoTable(query.simpleStatement().insertStatement(), this);
                } else if (query.simpleStatement().updateStatement() != null) {
                    return QueryProcessing.updateTable(query.simpleStatement().updateStatement(), this);
                } else if (query.simpleStatement().deleteStatement() != null) {
                    return QueryProcessing.deleteFromTable(query.simpleStatement().deleteStatement(), this);
                } else if (query.simpleStatement().selectStatement() != null) {
                    return QueryProcessing.selectFromTable(query.simpleStatement().selectStatement(), this);
                }
            }

            return null;
        }
        catch (RuntimeException e){
            throw new DBAppException("Invalid SQL query");
        }

    }


    public static void main(String[] args) throws DBAppException, IOException {
        //DBApp dbApp = new DBApp();

}
}



