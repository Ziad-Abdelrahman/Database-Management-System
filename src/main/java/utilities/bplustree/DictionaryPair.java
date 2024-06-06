package utilities.bplustree;


import table.page.PageAddress;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a dictionary pair that is to be contained within the
 * leaf nodes of the B+ tree. The class implements the Comparable interface
 * so that the DictionaryPair objects can be sorted later on.
 */

public class DictionaryPair implements Comparable<DictionaryPair>, Serializable {
    Comparable key;
    ArrayList<PageAddress> values = new ArrayList<>();

    /**
     * Constructor
     * @param key: the key of the key-value pair
     * @param values: the value of the key-value pair
     */
    public DictionaryPair(Comparable key, ArrayList<PageAddress> values) {
        this.key = key;
        this.values = values;
    }

    /**
     * This is a method that allows comparisons to take place between
     * DictionaryPair objects in order to sort them later on
     * @param o
     * @return
     */
    @Override
    public int compareTo(DictionaryPair o) {
        return key.compareTo(o.key);
    }

    public String toString() {
        String result= key + "\n" ;
        for (PageAddress pageAddress : values) {
            result += pageAddress.toString() + "\n";
        }
        return result;
    }
}
