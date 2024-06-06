package utilities;

import utilities.bplustree.BPlusTree;

public class IndexIdentifier {
    private BPlusTree tree;
    private SQLTerm term;

    public IndexIdentifier(BPlusTree tree, SQLTerm term) {
        this.tree = tree;
        this.term = term;
    }

    public BPlusTree getTree() {
        return tree;
    }

    public SQLTerm getTerm() {
        return term;
    }

}