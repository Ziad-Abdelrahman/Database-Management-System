package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileConfigManager {
    public static void filesInit(){
        File page = new File("Pages");
        File table = new File("Tables");
        File metadata = new File("metadata.csv");
        File index = new File("Indices");
        if(!page.exists())
            page.mkdir();
        if(!table.exists())
            table.mkdir();
        if(!index.exists())
            index.mkdir();
        if(!metadata.exists()) {
            try {
                metadata.createNewFile();
                FileWriter outfile = new FileWriter(("metadata.csv"), true);
                outfile.append("Table Name, Column Name, Column Type, ClusteringKey, IndexName,IndexType\n");
                outfile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void clear(){
        // Clear all the files and directories

        //Get the directories
        File page = new File("Pages");
        File table = new File("Tables");
        File index = new File("Indices");
        File metadata = new File("metadata.csv");

        //Delete tables and their contents
        deleteDir(table);
        table.delete();

        //Delete pages and their contents
        if(page.list() != null)
            for(String f : page.list()){
                File file = new File(page.getName() + "/" + f);
                if(file.isDirectory()) {
                    deleteDir(file);
                    file.delete();
                }
                else {
                    file.delete();
                }
            }
        page.delete();

        //Delete indices and their contents
        if(index.list() != null)
            for(String f : index.list()){
                File file = new File(index.getName() + "/" + f);
                if(file.isDirectory()) {
                    deleteDir(file);
                    file.delete();
                }
                else {
                    file.delete();
                }
            }
        index.delete();

        //Delete metadata
        metadata.delete();
    }
    static void deleteDir(File f){
        if(f.listFiles() != null)
            for(File file : f.listFiles())
                file.delete();
    }
    public static int getMaximumRowsCountinPage(){
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("Resources/DBApp.config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(p.get("MaximumRowsCountinPage").toString());
    }

    public static int  getFanOut(){
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("Resources/DBApp.config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(p.get("FanOut").toString());
    }

}
