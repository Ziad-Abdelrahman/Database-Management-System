package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class CSVManager {
    public static Hashtable<String,String[]> readCSVFile(String tableName){
        File file = new File("metadata.csv");
        Hashtable<String,String[]> colInfo = new Hashtable<>();
        String line;
        try {
            BufferedReader rdr = new BufferedReader(new FileReader(file));
            while ((line = rdr.readLine()) != null) {
                String[] st = line.split(",");
                if (st[0].equalsIgnoreCase(tableName)) {
                    colInfo.put( st[1], new String[]{st[2], st[3], st[4]});
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return colInfo;
    }
    public static String getClusteringColumn (Hashtable<String,String[]> colInfo){
        for (String colName : colInfo.keySet()){
            if (colInfo.get(colName)[1].equalsIgnoreCase("True")){
                return colName;
            }
        }
        return null;
    }
}
