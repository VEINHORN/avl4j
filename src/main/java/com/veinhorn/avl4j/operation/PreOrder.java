package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public class PreOrder<K extends Comparable<K>, V> implements Traversable<K, V> {
    @Override
    public void traverse(TreeNode<K, V> node, Consumer<TreeNode<K, V>> consumer) {
        if (node == null) return;

        consumer.accept(node);
        traverse(node.left(), consumer);
        traverse(node.right(), consumer);
    }
}
