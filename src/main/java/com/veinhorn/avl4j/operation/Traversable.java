package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public interface Traversable<K extends Comparable<K>> {
    void traverse(TreeNode<K> node, Consumer<TreeNode<K>> consumer);
}
