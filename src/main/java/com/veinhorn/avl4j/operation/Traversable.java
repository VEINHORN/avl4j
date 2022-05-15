package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public interface Traversable<K extends Comparable<K>, V> {
    void traverse(TreeNode<K, V> node, Consumer<TreeNode<K, V>> consumer);
}
