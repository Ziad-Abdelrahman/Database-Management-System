package utilities;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

public class Miscellaneous {

    public static Comparable DataTypeConverter(String dataType, String value) {
        // Convert the value to the specified data type
        switch (dataType.toLowerCase()) {
            case "java.lang.integer":
                return Integer.parseInt(value);
            case "java.lang.double":
                return Double.parseDouble(value);
            default:
                return value;
        }
    }

    public static int binarySearch(Vector v , Object key){
        return Collections.binarySearch(v,key);
    }

    public static Hashtable<String,Object> caseInsensitive(Hashtable<String,Object> ht,Hashtable<String,String[]> tableInfo){
        Hashtable<String,Object> caseInsensitive = new Hashtable<>();
        for(String key : ht.keySet()){
            caseInsensitive.put(key.toLowerCase(),ht.get(key));
        }
        return caseInsensitive;
    }


}
