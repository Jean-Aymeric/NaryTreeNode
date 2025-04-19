package com.jad.treenode;

import java.util.List;

/**
 * Interface for a node in an n-ary tree.
 * This interface extends the INaryTree interface and provides methods
 * to manage the node's children, add new nodes, remove nodes,
 * and perform various tree operations.
 * It also provides methods to generate a string representation of the tree,
 * convert the tree to JSON format, and traverse the tree in different orders (prefix, postfix, and by width).
 *
 * @param <E> - the type of the value stored in the node
 */
public interface INaryTreeNode<E> extends INaryTree<E> {
    /**
     * Returns the child at the specified position in the children list of this node.
     *
     * @param index - the index of the child
     *
     * @return the child at the specified position in the children list of this node.
     */
    INaryTreeNode<E> getChild(int index);

    /**
     * Ensures that this collection contains the specified node.
     *
     * @param node - node whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    boolean add(INaryTreeNode<E> node);

    /**
     * Returns all children of this node.
     *
     * @return all children of this node.
     */
    List<INaryTreeNode<E>> getChildren();

    /**
     * Returns True if this node is a leaf.
     *
     * @return True if this node is a leaf.
     */
    boolean isLeaf();

    /**
     * Returns the number of children of this node.
     *
     * @return the number of children of this node.
     */
    int getChildrenCount();


    /**
     * Returns the value of the node.
     *
     * @return the value of the node
     */
    E getValue();


    /**
     * Sets the value of the node.
     *
     * @param value - the value of the node
     */
    void setValue(E value);
}
