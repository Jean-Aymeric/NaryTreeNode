package com.jad.treenode;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Nary tree node.
 *
 * @param <E> the type parameter
 */
public class NaryTreeNode<E> {
    private final LinkedList<NaryTreeNode<E>> children;

    @Setter
    @Getter
    private E value;

    /**
     * Instantiates a new NaryTreeNode.
     */
    public NaryTreeNode() {
        this(null);
    }

    /**
     * Instantiates a new NaryTreeNode with a value.
     *
     * @param element - the value of the node
     */
    public NaryTreeNode(final E element) {
        this.value = element;
        this.children = new LinkedList<>();
    }

    /**
     * Returns the child at the specified position in the children list of this node.
     *
     * @param index - the index of the child
     *
     * @return the child at the specified position in the children list of this node.
     */
    public NaryTreeNode<E> getChild(final int index) {
        return this.children.get(index);
    }

    /**
     * Returns all children of this node.
     *
     * @return all children of this node.
     */
    public List<NaryTreeNode<E>> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param element - element whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    public boolean add(final E element) {
        return this.add(new NaryTreeNode<>(element));
    }

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param node - element whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    public boolean add(final NaryTreeNode<E> node) {
        if (this.children.contains(node)) {
            return false;
        }
        return this.children.add(node);
    }

    /**
     * Remove the child from the children list of this node.
     *
     * @param child - the child to be removed
     *
     * @return true if this collection changed as a result of the call
     */
    public boolean remove(final NaryTreeNode<E> child) {
        return this.children.remove(child);
    }

    /**
     * Returns the number of children of this node.
     *
     * @return the number of children of this node.
     */
    public int getChildrenCount() {
        return this.children.size();
    }

    /**
     * Returns True if this node is a leaf.
     *
     * @return True if this node is a leaf.
     */
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

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
    public String generateText() {
        if (this.isLeaf()) {
            return NaryTreeNodeUtils.VALUE_PREFIX + (this.value == null ? NaryTreeNodeUtils.VALUE_NULL :
                    this.value.toString()) +
                    NaryTreeNodeUtils.VALUE_SUFFIX;
        }
        return NaryTreeNodeUtils.VALUE_PREFIX + (this.value == null ? NaryTreeNodeUtils.VALUE_NULL :
                this.value.toString()) +
                NaryTreeNodeUtils.VALUE_SUFFIX + NaryTreeNodeUtils.VALUE_SEPARATOR
                + this.children.stream().map(NaryTreeNode::generateText).collect(Collectors.joining(
                NaryTreeNodeUtils.CHILDREN_SEPARATOR,
                NaryTreeNodeUtils.CHILDREN_PREFIX, NaryTreeNodeUtils.CHILDREN_SUFFIX));
    }

    /**
     * Returns True if the tree contains the specified value.
     *
     * @param element - element whose presence in this tree is to be tested
     *
     * @return true if the tree contains the specified value
     */
    public boolean contains(final Object element) {
        if (this.value.equals(element)) {
            return true;
        }
        for (final NaryTreeNode<E> child : this.children) {
            if (child.contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the height of the tree.
     * A tree with a single node (root) has a height of 1.
     *
     * @return the height of the tree
     */
    public int getHeight() {
        if (this.children.isEmpty()) return 1;
        return 1 + this.children.stream().mapToInt(NaryTreeNode::getHeight).max().getAsInt();
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return this.isLeaf() ? 1 : 1 + this.children.stream().mapToInt(NaryTreeNode::size).sum();
    }

    /**
     * Returns the number of leaves in the tree.
     *
     * @return the number of leaves in the tree
     */
    public int getNumberOfLeaves() {
        return this.isLeaf() ? 1 : this.children.stream().mapToInt(NaryTreeNode::getNumberOfLeaves).sum();
    }

    /**
     * Returns a json representation of the tree.
     * The json representation consists of a list of the node's value and its children.
     * The json's key for the value is {@value com.jad.treenode.NaryTreeNodeUtils#JSON_VALUE_KEY}.
     * The json's key for the children is {@value com.jad.treenode.NaryTreeNodeUtils#JSON_CHILDREN_KEY}.
     *
     * @return a json representation of the tree
     */
    public String toJson() {
        if (this.isLeaf()) {
            return "{\"" + NaryTreeNodeUtils.JSON_VALUE_KEY + "\":"
                    + new Gson().toJson(this.value) + "}";
        }
        return "{\"" + NaryTreeNodeUtils.JSON_VALUE_KEY + "\":"
                + new Gson().toJson(this.value) + ",\"" + NaryTreeNodeUtils.JSON_CHILDREN_KEY + "\":["
                + this.children.stream().map(NaryTreeNode::toJson).collect(Collectors.joining(",")) + "]}";
    }

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
    public String toPrettyText() {
        return MessageFormat.format("{0}\n{1}",
                                    this.value.toString(),
                                    this.children.stream().map(n -> n.toPrettyText(1)).collect(Collectors.joining("")));
    }

    private String toPrettyText(final int depth) {
        return MessageFormat.format("{0}{1}{2}\n{3}",
                                    NaryTreeNodeUtils.VALUE_PRETTY_DEPTH.repeat(depth - 1),
                                    NaryTreeNodeUtils.VALUE_PRETTY_CHILDREN_PREFIX,
                                    this.value.toString(),
                                    this.children.stream().map(n -> n.toPrettyText(depth + 1)).collect(
                                            Collectors.joining("")));
    }

    @Override
    public String toString() {
        return "NaryTreeNode{" +
                "value=" + this.value.toString() +
                ", children=" + this.children.toString() +
                '}';
    }

    /**
     * Returns a postfix list of all values.
     * The postfix list is obtained by traversing the tree in post-order.
     * All children of a node are visited before the node itself.
     *
     * @return a postfix list of all values
     */
    public List<E> toPostfixList() {
        List<E> list = new LinkedList<>();
        for (NaryTreeNode<E> child : this.children) {
            list.addAll(child.toPostfixList());
        }
        list.add(this.value);
        return list;
    }

    /**
     * Returns a prefix list of all values.
     * The prefix list is obtained by traversing the tree in pre-order.
     * The node is visited before its children.
     *
     * @return a prefix list of all values
     */
    public List<E> toPrefixList() {
        List<E> list = new LinkedList<>();
        list.add(this.value);
        for (NaryTreeNode<E> child : this.children) {
            list.addAll(child.toPrefixList());
        }
        return list;
    }

    /**
     * Returns a by width list of all values.
     * The by width list is obtained by traversing the tree in width.
     * Each level of the tree is visited before the next level.
     *
     * @return a by width list of all values
     */
    public List<E> toByWidthList() {
        List<E> list = new LinkedList<>();
        LinkedList<NaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            NaryTreeNode<E> node = queue.poll();
            list.add(node.value);
            queue.addAll(node.children);
        }
        return list;
    }

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
    public final NaryTreeNode<E> getNodeFromElement(final E element) {
        if (this.value == element) return this;
        NaryTreeNode<E> result = null;
        for (NaryTreeNode<E> child : this.children) {
            NaryTreeNode<E> response = child.getNodeFromElement(element);
            result = (response == null) ? result : response;
        }
        return result;
    }
}
