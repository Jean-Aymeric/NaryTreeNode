package com.jad.treenode;

import lombok.Getter;

@Getter
public class NaryTree<E> {
    private final INaryTreeNode<E> root;

    public NaryTree() {
        this(null);
    }

    public NaryTree(INaryTreeNode<E> root) {
        this.root = root;
    }
}
