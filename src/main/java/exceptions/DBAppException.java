package exceptions;

import utilities.CSVManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/** * @author Wael Abouelsaadat */

public class DBAppException extends Exception {


	public DBAppException( String strMessage ){
		super( strMessage );
	}

	public static void checkExistence(String name,int contentIndex,boolean mode) throws DBAppException, IOException {
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
			br.close();
			if (mode) {
				if (!flag) {
					switch (contentIndex) {
						case 0:
							throw new DBAppException("Table does not exist");
						case 1:
							throw new DBAppException("Column does not exist");
						case 4:
							throw new DBAppException("Index does not exist");
					}
				}
			}
			else {
				if (flag) {
					switch (contentIndex) {
						case 0:
							throw new DBAppException("Table already exists");
						case 4:
							throw new DBAppException("Index already exists");
					}
				}
			}


		}
	}
	public static boolean correctEntries(Hashtable<String, Object> colValues, String tableName) {
		Hashtable<String,String[]> colWithDataTypes = CSVManager.readCSVFile(tableName);
		boolean flag = true;
		for(String key : colValues.keySet()){
			if(!colWithDataTypes.containsKey(key)){
				flag = false;
				break;
			}
			String dataType = colWithDataTypes.get(key)[0];
			if(dataType.equalsIgnoreCase("java.lang.Integer")){
				if(colValues.get(key) instanceof Integer){
					continue;
				}else{
					flag = false;
					break;
				}
			}else if(dataType.equalsIgnoreCase("java.lang.Double")){
				if(colValues.get(key) instanceof Double){
					continue;
				}else{
					flag = false;
					break;
				}
			}
			else{
				if(colValues.get(key) instanceof String){
					continue;
				}else{
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

}