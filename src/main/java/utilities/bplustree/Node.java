package utilities.bplustree;

import java.io.Serializable;

/**
 * This class represents a general node within the B+ tree and serves as a
 * superclass of InternalNode and LeafNode.
 */
public class Node implements Serializable {
    InternalNode parent;
}
