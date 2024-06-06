package table;

import table.page.Page;
import table.page.PageAddress;
import utilities.Serializer;

import java.io.Serializable;
import java.util.Vector;

public class Table implements Serializable {
    private Vector<PageAddress> pages;
    private String tableName;
    public int pageNumber= 0;
    public Table(String name, String strClusteringKeyColumn,String dataType){
        this.tableName = name;
        pages = new Vector<>();

    }
    public Page createPage(){
        Page page = new Page();
        String pageLoc = tableName + ++pageNumber;
        Serializer.serialize("Pages/"+tableName+"/"+pageLoc,page);
        PageAddress pageAddress = new PageAddress(pageLoc,Integer.MAX_VALUE);
        pages.add(pageAddress);
        Serializer.serialize("Tables/"+tableName,this);
        return page;
    }

    public int getPageNumber() {
        return pageNumber;
    }
    public String getTableName() {
        return tableName;
    }

    public Vector<PageAddress> getPages() {
        return pages;
    }
}
