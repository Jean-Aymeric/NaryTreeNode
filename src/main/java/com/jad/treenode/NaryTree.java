package com.jad.treenode;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NaryTree<E> implements INaryTree<E> {
    private final INaryTreeNode<E> root;

    public NaryTree() {
        this(null);
    }

    public NaryTree(E root) {
        this.root = new NaryTreeNode<>(root);
    }

    @Override
    public E getRoot() {
        return this.root.getValue();
    }

    @Override
    public boolean add(final E element) {
        return this.root.add(new NaryTreeNode<>(element));
    }

    @Override
    public boolean remove(final Object element) {
        if (this.root.getValue().equals(element)) {
            this.root.setValue(null);
            return true;
        }
        return this.root.remove(element);
    }

    @Override
    public String generateText() {
        return this.root.generateText();
    }

    @Override
    public boolean contains(final Object element) {
        return this.root.contains(element);
    }

    @Override
    public int getHeight() {
        return this.root.getHeight();
    }

    @Override
    public int size() {
        return this.root.size();
    }

    @Override
    public int getNumberOfLeaves() {
        return this.root.getNumberOfLeaves();
    }

    @Override
    public String toJson() {
        return this.root.toJson();
    }

    @Override
    public String toPrettyText() {
        return this.root.toPrettyText();
    }

    @Override
    public List<E> toPostfixList() {
        return this.root.toPostfixList();
    }

    @Override
    public List<E> toPrefixList() {
        return this.root.toPrefixList();
    }

    @Override
    public List<E> toByWidthList() {
        return this.root.toByWidthList();
    }

    @Override
    public INaryTree<E> getNodeFromElement(final E element) {
        return this.root.getNodeFromElement(element);
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> list = this.toByWidthList();
        if (list != null) {
            return list.iterator();
        }
        return Collections.emptyIterator();
    }
}
