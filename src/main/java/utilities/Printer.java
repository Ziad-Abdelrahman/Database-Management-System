package utilities;

import table.Table;
import table.page.Page;
import table.page.PageAddress;
import utilities.bplustree.BPlusTree;

public class Printer {
    public static void printTable(String tableName){
        Table table = (Table) Serializer.deserialize("Tables/" + tableName);
        for(PageAddress pa : table.getPages()){
            Page p = (Page) Serializer.deserialize("Pages/" + tableName + "/" + pa.getPageAddress());
            System.out.println(pa.getPageAddress());
            System.out.println(p);
            System.out.println("-------------------------------------------------");

        }

    }

    public static void printBTree(String tableName, String btree) {
        BPlusTree tree = (BPlusTree) Serializer.deserialize("Indices/"+ tableName + "/" + btree);
        tree.print();
    }
}
