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
public class NaryTreeNode<E> implements INaryTreeNode<E> {
    private final LinkedList<INaryTreeNode<E>> children;

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

    private static <E> String toPrettyText(final INaryTreeNode<E> node, final int depth) {
        return MessageFormat.format("{0}{1}{2}\n{3}",
                                    NaryTreeNodeUtils.VALUE_PRETTY_DEPTH.repeat(depth - 1),
                                    NaryTreeNodeUtils.VALUE_PRETTY_CHILDREN_PREFIX,
                                    node.getValue().toString(),
                                    node.getChildren().stream().map(
                                                    child -> NaryTreeNode.toPrettyText(child, depth + 1))
                                            .collect(Collectors.joining("")));
    }

    @Override
    public INaryTreeNode<E> getChild(final int index) {
        return this.children.get(index);
    }

    @Override
    public List<INaryTreeNode<E>> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    @Override
    public boolean add(final INaryTreeNode<E> node) {
        if (this.children.contains(node)) {
            return false;
        }
        return this.children.add(node);
    }

    @Override
    public boolean remove(final INaryTreeNode<E> child) {
        return this.children.remove(child);
    }

    @Override
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    @Override
    public String generateText() {
        if (this.isLeaf()) {
            return NaryTreeNodeUtils.VALUE_PREFIX + (this.value == null ? NaryTreeNodeUtils.VALUE_NULL :
                    this.value.toString()) +
                    NaryTreeNodeUtils.VALUE_SUFFIX;
        }
        return NaryTreeNodeUtils.VALUE_PREFIX + (this.value == null ? NaryTreeNodeUtils.VALUE_NULL :
                this.value.toString()) +
                NaryTreeNodeUtils.VALUE_SUFFIX + NaryTreeNodeUtils.VALUE_SEPARATOR
                + this.children.stream().map(INaryTreeNode::generateText).collect(Collectors.joining(
                NaryTreeNodeUtils.CHILDREN_SEPARATOR,
                NaryTreeNodeUtils.CHILDREN_PREFIX, NaryTreeNodeUtils.CHILDREN_SUFFIX));
    }

    @Override
    public boolean contains(final Object element) {
        if (this.value.equals(element)) {
            return true;
        }
        for (final INaryTreeNode<E> child : this.children) {
            if (child.contains(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getHeight() {
        if (this.children.isEmpty()) return 1;
        return 1 + this.children.stream().mapToInt(INaryTreeNode::getHeight).max().getAsInt();
    }

    @Override
    public int size() {
        return this.isLeaf() ? 1 : 1 + this.children.stream().mapToInt(INaryTreeNode::size).sum();
    }

    @Override
    public int getNumberOfLeaves() {
        return this.isLeaf() ? 1 : this.children.stream().mapToInt(INaryTreeNode::getNumberOfLeaves).sum();
    }

    @Override
    public String toJson() {
        if (this.isLeaf()) {
            return "{\"" + NaryTreeNodeUtils.JSON_VALUE_KEY + "\":"
                    + new Gson().toJson(this.value) + "}";
        }
        return "{\"" + NaryTreeNodeUtils.JSON_VALUE_KEY + "\":"
                + new Gson().toJson(this.value) + ",\"" + NaryTreeNodeUtils.JSON_CHILDREN_KEY + "\":["
                + this.children.stream().map(INaryTreeNode::toJson).collect(Collectors.joining(",")) + "]}";
    }

    @Override
    public String toPrettyText() {
        return MessageFormat.format("{0}\n{1}",
                                    this.value.toString(),
                                    this.children.stream().map(node -> NaryTreeNode.toPrettyText(node, 1))
                                            .collect(Collectors.joining("")));
    }

    @Override
    public List<E> toPostfixList() {
        List<E> list = new LinkedList<>();
        for (INaryTreeNode<E> child : this.children) {
            list.addAll(child.toPostfixList());
        }
        list.add(this.value);
        return list;
    }

    @Override
    public List<E> toPrefixList() {
        List<E> list = new LinkedList<>();
        list.add(this.value);
        for (INaryTreeNode<E> child : this.children) {
            list.addAll(child.toPrefixList());
        }
        return list;
    }

    @Override
    public List<E> toByWidthList() {
        List<E> list = new LinkedList<>();
        LinkedList<INaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            INaryTreeNode<E> node = queue.poll();
            list.add(node.getValue());
            queue.addAll(node.getChildren());
        }
        return list;
    }

    @Override
    public final INaryTreeNode<E> getNodeFromElement(final E element) {
        if (this.value == element) return this;
        INaryTreeNode<E> result = null;
        for (INaryTreeNode<E> child : this.children) {
            INaryTreeNode<E> response = child.getNodeFromElement(element);
            result = (response == null) ? result : response;
        }
        return result;
    }

    @Override
    public int getChildrenCount() {
        return this.children.size();
    }

    @Override
    public boolean add(final E element) {
        return this.add(new NaryTreeNode<>(element));
    }

    @Override
    public String toString() {
        return "NaryTreeNode{" +
                "value=" + this.value.toString() +
                ", children=" + this.children.toString() +
                '}';
    }
}
