package utilities.bplustree;

import java.io.Serializable;
import java.util.Arrays;

import static utilities.bplustree.BPlusTree.linearNullSearch;

/**
 * This class represents the leaf nodes within the B+ tree that hold
 * dictionary pairs. The leaf node has no children. The leaf node has a
 * minimum and maximum number of dictionary pairs it can hold, as specified
 * by m, the max degree of the B+ tree. The leaf nodes form a doubly linked
 * list that, i.e. each leaf node has a left and right sibling*/
public class LeafNode extends Node implements Serializable {
    int maxNumPairs;
    int minNumPairs;
    int numPairs;
    LeafNode leftSibling;
    LeafNode rightSibling;
    DictionaryPair[] dictionary;

    /**
     * Given an index, this method sets the dictionary pair at that index
     * within the dictionary to null.
     * @param index: the location within the dictionary to be set to null
     */
    public void delete(int index) {

        // Delete dictionary pair from leaf
        this.dictionary[index] = null;

        // Decrement numPairs
        numPairs--;
    }

    /**
     * This method attempts to insert a dictionary pair within the dictionary
     * of the LeafNode object. If it succeeds, numPairs increments, the
     * dictionary is sorted, and the boolean true is returned. If the method
     * fails, the boolean false is returned.
     * @param dp: the dictionary pair to be inserted
     * @return a boolean indicating whether or not the insert was successful
     */
    public boolean insert(DictionaryPair dp) {
        if (this.isFull()) {

            /* Flow of execution goes here when numPairs == maxNumPairs */

            return false;
        } else {

            // Insert dictionary pair, increment numPairs, sort dictionary
            this.dictionary[numPairs] = dp;
            numPairs++;
            Arrays.sort(this.dictionary, 0, numPairs);

            return true;
        }
    }

    /**
     * This simple method determines if the LeafNode is deficient, i.e.
     * the numPairs within the LeafNode object is below minNumPairs.
     * @return a boolean indicating whether or not the LeafNode is deficient
     */
    public boolean isDeficient() { return numPairs < minNumPairs; }

    /**
     * This simple method determines if the LeafNode is full, i.e. the
     * numPairs within the LeafNode is equal to the maximum number of pairs.
     * @return a boolean indicating whether or not the LeafNode is full
     */
    public boolean isFull() { return numPairs == maxNumPairs; }

    /**
     * This simple method determines if the LeafNode object is capable of
     * lending a dictionary pair to a deficient leaf node. The LeafNode
     * object can lend a dictionary pair if its numPairs is greater than
     * the minimum number of pairs it can hold.
     * @return a boolean indicating whether or not the LeafNode object can
     * give a dictionary pair to a deficient leaf node
     */
    public boolean isLendable() { return numPairs > minNumPairs; }

    /**
     * This simple method determines if the LeafNode object is capable of
     * being merged with, which occurs when the number of pairs within the
     * LeafNode object is equal to the minimum number of pairs it can hold.
     * @return a boolean indicating whether or not the LeafNode object can
     * be merged with
     */
    public boolean isMergeable() {
        return numPairs == minNumPairs;
    }

    /**
     * Constructor
     * @param m: order of B+ tree that is used to calculate maxNumPairs and
     *           minNumPairs
     * @param dp: first dictionary pair insert into new node
     */
    public LeafNode(int m, DictionaryPair dp) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.dictionary = new DictionaryPair[m];
        this.numPairs = 0;
        this.insert(dp);
    }

    /**
     * Constructor
     * @param dps: list of DictionaryPair objects to be immediately inserted
     *             into new LeafNode object
     * @param m: order of B+ tree that is used to calculate maxNumPairs and
     * 		     minNumPairs
     * @param parent: parent of newly created child LeafNode
     */
    public LeafNode(int m, DictionaryPair[] dps, InternalNode parent) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.dictionary = dps;
        this.numPairs = linearNullSearch(dps);
        this.parent = parent;
    }
}
