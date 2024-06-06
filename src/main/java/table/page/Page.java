package table.page;

import table.page.tuple.Tuple;
import utilities.Miscellaneous;

import java.io.Serializable;
import java.util.Vector;

public class Page implements Serializable {
    private Vector<Tuple> tuples;

    public Page(){
        tuples = new Vector<>();
    }
    public Vector<Tuple> getTuples() {
        return tuples;
    }
    public void insertTuple(Tuple t){
        int index = Miscellaneous.binarySearch(tuples,t);
        if (index<0)
            index = -(index+1);
        tuples.add(index,t);

    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Tuple t : tuples){
            sb.append(t.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
