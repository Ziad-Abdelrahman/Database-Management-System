package utilities;

import table.page.Page;
import table.page.PageAddress;
import table.page.tuple.Tuple;
import utilities.bplustree.BPlusTree;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class ResultSetManager {
    public static HashSet<Tuple> createAResultSet(Vector<SQLTerm> operands, Vector<PageAddress> pages) {
        IndexIdentifier id = getIndex(operands);
        SQLTerm cid = getClusteringKey(operands);
        HashSet<Tuple> result;
        if (id != null) {
            result = createFirstResultSetWithIndex(id);
            operands.remove(id.getTerm());
        }
        else if (cid != null) {
            result = createFirstResultSetWithClusteringKey(cid,pages);
            operands.remove(cid);
        } else {
            result = createFirstResultSetLinearly(operands.get(0), pages);
            operands.remove(0);
        }

        for (SQLTerm operand : operands) {
            filterSet(operand, result);
        }
        return result;
    }

    private static void filterSet(SQLTerm operand, HashSet<Tuple> result) {
        HashSet<Tuple> toRemove = new HashSet<>();
        Object key= Miscellaneous.DataTypeConverter(CSVManager.readCSVFile(operand._strTableName).get(operand._strColumnName)[0],operand._objValue.toString());
        for (Tuple t : result) {
            if (operand._strOperator.equals("=")) {
                if (((Comparable) t.getValues().get(operand._strColumnName)).compareTo(key) != 0)
                    toRemove.add(t);
            } else if (operand._strOperator.equals(">")) {
                if (((Comparable) t.getValues().get(operand._strColumnName)).compareTo(key) <= 0)
                    toRemove.add(t);
            } else if (operand._strOperator.equals("<")) {
                if (((Comparable) t.getValues().get(operand._strColumnName)).compareTo(key) >= 0)
                    toRemove.add(t);
            } else if (operand._strOperator.equals(">=")) {
                if (((Comparable) t.getValues().get(operand._strColumnName)).compareTo(key) < 0)
                    toRemove.add(t);
            } else if (operand._strOperator.equals("<=")) {
                if (((Comparable) t.getValues().get(operand._strColumnName)).compareTo(key) > 0)
                    toRemove.add(t);
            } else if (operand._strOperator.equals("!=")) {
                if (((Comparable) t.getValues().get(operand._strColumnName)).compareTo(key) == 0)
                    toRemove.add(t);
            }
        }
        for (Tuple t : toRemove) {
            result.remove(t);
        }
    }

    public static HashSet<Tuple> createFirstResultSetLinearly(SQLTerm operand, Vector<PageAddress> pages) {
        HashSet<Tuple> result = new HashSet<>();
        Object key= Miscellaneous.DataTypeConverter(CSVManager.readCSVFile(operand._strTableName).get(operand._strColumnName)[0],operand._objValue.toString());
        for (PageAddress address : pages) {
            Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + address.getPageAddress());
            for (Tuple tuple : page.getTuples()) {
                if (operand._strOperator.equals("=") && ((Comparable) tuple.getValues().get(operand._strColumnName)).compareTo(key) == 0)
                    result.add(tuple);
                else if (operand._strOperator.equals(">") && ((Comparable) tuple.getValues().get(operand._strColumnName)).compareTo(key) > 0)
                    result.add(tuple);
                else if (operand._strOperator.equals("<") && ((Comparable) tuple.getValues().get(operand._strColumnName)).compareTo(key) < 0)
                    result.add(tuple);
                else if (operand._strOperator.equals(">=") && ((Comparable) tuple.getValues().get(operand._strColumnName)).compareTo(key) >= 0)
                    result.add(tuple);
                else if (operand._strOperator.equals("<=") && ((Comparable) tuple.getValues().get(operand._strColumnName)).compareTo(key) <= 0)
                    result.add(tuple);
                else if (operand._strOperator.equals("!=") && ((Comparable) tuple.getValues().get(operand._strColumnName)).compareTo(key) != 0)
                    result.add(tuple);
            }
        }
        return result;
    }

    public static HashSet<Tuple> createFirstResultSetWithIndex(IndexIdentifier id) {
        SQLTerm operand = id.getTerm();
        BPlusTree index = id.getTree();
        HashSet<Tuple> result = new HashSet<>();
        HashSet<PageAddress> pageAddresses;
        Comparable key= Miscellaneous.DataTypeConverter(CSVManager.readCSVFile(operand._strTableName).get(operand._strColumnName)[0],operand._objValue.toString());
        if (operand._strOperator.equals("=")) {
            pageAddresses = index.search(key);
        } else if (operand._strOperator.equals(">")) {
            pageAddresses = index.searchGreaterThan(key);
        } else if (operand._strOperator.equals("<")) {
            pageAddresses = index.searchLessThan(key);
        } else if (operand._strOperator.equals(">=")) {
            pageAddresses = index.searchGreaterThanEqual(key);
        } else {
            pageAddresses = index.searchLessThanEqual(key);
        }
        if(pageAddresses != null)
            for (PageAddress address : pageAddresses) {
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + address.getPageAddress());
                for (Tuple tuple : page.getTuples()) {
                    if (operand._strOperator.equals("=")) {
                        if (((Comparable)tuple.getValues().get(operand._strColumnName)).compareTo(key)==0)
                            result.add(tuple);
                    } else if (operand._strOperator.equals(">")) {
                        if (((Comparable)tuple.getValues().get(operand._strColumnName)).compareTo(key)>0)
                            result.add(tuple);
                    } else if (operand._strOperator.equals("<")) {
                        if (((Comparable)tuple.getValues().get(operand._strColumnName)).compareTo(key)<0)
                            result.add(tuple);
                    } else if (operand._strOperator.equals(">=")) {
                        if (((Comparable)tuple.getValues().get(operand._strColumnName)).compareTo(key)>=0)
                            result.add(tuple);
                    } else {
                        if (((Comparable)tuple.getValues().get(operand._strColumnName)).compareTo(key)<=0)
                            result.add(tuple);
                    }
                }
            }
        return result;
    }

    public static IndexIdentifier getIndex(Vector<SQLTerm> operands) {
        for (SQLTerm operand : operands) {
            if (!operand._strOperator.equals("!=") && hasIndex(operand)) {
                Hashtable<String, String[]> csv = CSVManager.readCSVFile(operand._strTableName);
                BPlusTree btree = (BPlusTree) Serializer.deserialize("Indices/" +operand._strTableName+"/"+ csv.get(operand._strColumnName)[2]);
                IndexIdentifier id = new IndexIdentifier(btree, operand);
                return id;
            }
        }
        return null;
    }

    public static boolean hasIndex(SQLTerm operand) {
        Hashtable<String, String[]> csv = CSVManager.readCSVFile(operand._strTableName);
        return !csv.get(operand._strColumnName)[2].equalsIgnoreCase("null");
    }

    public static SQLTerm getClusteringKey(Vector<SQLTerm> operands) {
        for (SQLTerm operand : operands) {
            Hashtable<String, String[]> csv = CSVManager.readCSVFile(operand._strTableName);
            if (csv.get(operand._strColumnName)[1].equalsIgnoreCase("True")) {
                return operand;
            }
        }
        return null;
    }

    public static HashSet<Tuple> createFirstResultSetWithClusteringKey(SQLTerm operand, Vector<PageAddress> pages) {
        HashSet<Tuple> result = new HashSet<>();
        Comparable key= Miscellaneous.DataTypeConverter(CSVManager.readCSVFile(operand._strTableName).get(operand._strColumnName)[0],operand._objValue.toString());
        if (operand._strOperator.equals("=")) {
            int pageNum = Miscellaneous.binarySearch(pages, key); //get page index
            if (pageNum<0) {
                pageNum = -(pageNum+1)-1;
            }
            if (pageNum>=0) {
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + pages.get(pageNum).getPageAddress());
                // we looped on the whole tuples for the more general case where the clustering key doesn't have to be the
                // primary key
                for (Tuple tuple : page.getTuples())
                    if (tuple.getClusteringKeyValue().compareTo(key) == 0) {
                        result.add(tuple);
                    }
            }

        } else if (operand._strOperator.equals(">")) {
            boolean flag = true;
            for (int i = pages.size() - 1; i >= 0 && flag; i--) {
                String pageName = pages.get(i).getPageAddress();
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + pageName);
                for (int j = page.getTuples().size()-1 ; j >= 0; j--) {
                    if (page.getTuples().get(j).getClusteringKeyValue().compareTo(key) > 0)
                        result.add(page.getTuples().get(j));
                    else {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }

        } else if (operand._strOperator.equals("<")) {
            boolean flag = true;
            for (PageAddress address : pages) {
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + address.getPageAddress());
                for (Tuple tuple : page.getTuples()) {
                    if (tuple.getClusteringKeyValue().compareTo(key) < 0)
                        result.add(tuple);
                    else {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }
        } else if (operand._strOperator.equals(">=")) {
            boolean flag = true;
            for (int i = pages.size() - 1; i >= 0 && flag; i--) {
                String pageName = pages.get(i).getPageAddress();
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + pageName);
                for (int j = page.getTuples().size()-1 ; j >= 0; j--) {
                    if (page.getTuples().get(j).getClusteringKeyValue().compareTo(key) >= 0)
                        result.add(page.getTuples().get(j));
                    else {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }
        } else if (operand._strOperator.equals("<=")) {
            boolean flag = true;
            for (PageAddress address :pages) {
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + address.getPageAddress());
                for (Tuple tuple : page.getTuples()) {
                    if (tuple.getClusteringKeyValue().compareTo(key) <= 0)
                        result.add(tuple);
                    else {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }
        } else {
            for (PageAddress address : pages) {
                Page page = (Page) Serializer.deserialize("Pages/" + operand._strTableName + "/" + address.getPageAddress());
                for (Tuple tuple : page.getTuples()) {
                    if (tuple.getClusteringKeyValue().compareTo(key) != 0)
                        result.add(tuple);
                }
            }
        }
            return result;
    }
}
