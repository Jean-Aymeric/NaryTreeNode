package com.jad.treenode;

import java.util.List;

public interface INaryTreeNode<E> {
    /**
     * Returns the child at the specified position in the children list of this node.
     *
     * @param index - the index of the child
     *
     * @return the child at the specified position in the children list of this node.
     */
    INaryTreeNode<E> getChild(int index);

    /**
     * Returns all children of this node.
     *
     * @return all children of this node.
     */
    List<INaryTreeNode<E>> getChildren();

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param node - element whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    boolean add(INaryTreeNode<E> node);

    /**
     * Remove the child from the children list of this node.
     *
     * @param child - the child to be removed
     *
     * @return true if this collection changed as a result of the call
     */
    boolean remove(INaryTreeNode<E> child);

    /**
     * Returns True if this node is a leaf.
     *
     * @return True if this node is a leaf.
     */
    boolean isLeaf();

    /**
     * Returns a string representation of the object.
     * The string representation consists of a list of the node's value and its children.
     * The string representation of the children is enclosed in {@value com.jad.treenode.NaryTreeNodeUtils#CHILDREN_PREFIX} and {@value com.jad.treenode.NaryTreeNodeUtils#CHILDREN_SUFFIX}.
     * Adjacent elements are separated by the characters {@value com.jad.treenode.NaryTreeNodeUtils#CHILDREN_SEPARATOR}.
     * The string representation of the node's value is enclosed in the characters {@value com.jad.treenode.NaryTreeNodeUtils#VALUE_PREFIX} and {@value com.jad.treenode.NaryTreeNodeUtils#VALUE_SUFFIX}.
     * The string representation of the node's value is obtained by calling the toString method.
     * If the node's value is null, then the string representation is {@value com.jad.treenode.NaryTreeNodeUtils#VALUE_NULL}.
     *
     * @return a string representation of the object.
     */
    String generateText();

    /**
     * Returns True if the tree contains the specified value.
     *
     * @param element - element whose presence in this tree is to be tested
     *
     * @return true if the tree contains the specified value
     */
    boolean contains(Object element);

    /**
     * Returns the height of the tree.
     * A tree with a single node (root) has a height of 1.
     *
     * @return the height of the tree
     */
    int getHeight();

    /**
     * Returns the number of nodes in the tree.
     *
     * @return the number of nodes in the tree
     */
    int size();

    /**
     * Returns the number of leaves in the tree.
     *
     * @return the number of leaves in the tree
     */
    int getNumberOfLeaves();

    /**
     * Returns a json representation of the tree.
     * The json representation consists of a list of the node's value and its children.
     * The json's key for the value is {@value com.jad.treenode.NaryTreeNodeUtils#JSON_VALUE_KEY}.
     * The json's key for the children is {@value com.jad.treenode.NaryTreeNodeUtils#JSON_CHILDREN_KEY}.
     *
     * @return a json representation of the tree
     */
    String toJson();

    /**
     * Returns a pretty text representation of the tree.
     * <p>The pretty text representation consists of a list of the node's value and its children.
     * All the nodes are indented according to their depth in the tree.
     * The string representation of the node's value is obtained by calling the toString method.
     * </p>
     * <p>Example:
     * <pre>
     *      root
     *      ├─child1
     *      │ ├─subChild11
     *      │ ├─subChild12
     *      ├─child2
     *      │ ├─subChild21
     *      │ │ ├─subSubChild211
     *      │ ├─subChild22
     *      ├─child3
     *  </pre>
     * </p>
     *
     * @return a pretty text representation of the tree
     */
    String toPrettyText();

    /**
     * Returns a postfix list of all values.
     * The postfix list is obtained by traversing the tree in post-order.
     * All children of a node are visited before the node itself.
     *
     * @return a postfix list of all values
     */
    List<E> toPostfixList();

    /**
     * Returns a prefix list of all values.
     * The prefix list is obtained by traversing the tree in pre-order.
     * The node is visited before its children.
     *
     * @return a prefix list of all values
     */
    List<E> toPrefixList();

    /**
     * Returns a by width list of all values.
     * The by width list is obtained by traversing the tree in width.
     * Each level of the tree is visited before the next level.
     *
     * @return a by width list of all values
     */
    List<E> toByWidthList();

    /**
     * Returns the node that contains the specified element.
     * If the element is found in the tree, the node that contains the element is returned.
     * Otherwise, null is returned.
     * If the element is present multiple times in the tree, the first node that contains the element is returned.
     *
     * @param element the element
     *
     * @return the node that contains the specified element
     */
    INaryTreeNode<E> getNodeFromElement(E element);

    /**
     * Returns the number of children of this node.
     *
     * @return the number of children of this node.
     */
    int getChildrenCount();

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param element - element whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    boolean add(final E element);


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
