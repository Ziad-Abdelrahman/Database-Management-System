package table.page.tuple;

import utilities.CSVManager;

import java.io.Serializable;
import java.util.Hashtable;

public class Tuple implements Serializable,Comparable{
    private Hashtable<String, Object> values;
    //Type: Object until typecasting it to the specified DataType
    private Comparable clusteringKeyValue;

    public Tuple(Hashtable<String, Object> ht, String clusteringColumn,String strTableName) {
        values = new Hashtable<String, Object>();
        Hashtable<String, String[]> tableInfo = CSVManager.readCSVFile(strTableName);
        for (String key : ht.keySet()) {
            String dataType = tableInfo.get(key)[0];
            Object value = ht.get(key);
            if (dataType.equalsIgnoreCase("java.lang.Double"))
                values.put(key, Double.parseDouble(value.toString()));
            else
                values.put(key, value);

        }
        clusteringKeyValue=(Comparable) values.get(clusteringColumn);
    }

    @Override
    public String toString() {
        String str = "";
        for (String key : values.keySet()) {
            String value = values.get(key).toString();
            str += value + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    public Comparable getClusteringKeyValue() {
        return clusteringKeyValue;
    }

    public Hashtable<String, Object> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object tu) {
        Tuple t = (Tuple) tu;
        if(values.size() != t.getValues().size())
            return false;

        for (String key : values.keySet()) {
            Object value = values.get(key);
            if (!value.equals(t.getValues().get(key)))
                return false;
        }
        return true;
    }

    @Override
    public int  hashCode() {
        return clusteringKeyValue.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Tuple) {
            Tuple t = (Tuple) o;
            return this.clusteringKeyValue.compareTo(t.getClusteringKeyValue());
        }
        return this.clusteringKeyValue.compareTo(o);
    }
}