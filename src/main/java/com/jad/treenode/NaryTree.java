package com.jad.treenode;

import lombok.Getter;

public class NaryTree<E> {
    @Getter
    private final NaryTreeNode<E> root;

    public NaryTree() {
        this(null);
    }

    public NaryTree(NaryTreeNode<E> root) {
        this.root = root;
    }
}
