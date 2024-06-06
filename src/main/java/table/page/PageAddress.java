package table.page;

import table.page.tuple.Tuple;

import java.io.Serializable;

public class PageAddress implements Comparable, Serializable {
    private String pageAddress;
    private Comparable minValue;

    public PageAddress(String pageAddress , Comparable minValue){
        this.pageAddress = pageAddress;
        this.minValue = minValue;
    }

    public Comparable getMinValue() {
        return minValue;
    }

    public String getPageAddress() {
        return pageAddress;
    }

    @Override
    public String toString() {
        return pageAddress ;
    }

    public void setMinValue(Comparable minValue) {
        this.minValue = minValue;
    }

    @Override
    public boolean equals(Object obj) {
        PageAddress pageAddress = (PageAddress) obj;
        return this.pageAddress.equals(pageAddress.getPageAddress());
    }

    @Override
    public int hashCode() {
        return pageAddress.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof PageAddress) {
            PageAddress pageAddress = (PageAddress) o;
            return this.minValue.compareTo(pageAddress.getMinValue());
        }
        if (o instanceof Tuple) {
            Tuple tuple = (Tuple) o;
            return this.minValue.compareTo(tuple.getClusteringKeyValue());
        }
        return this.minValue.compareTo(o);
    }
}
