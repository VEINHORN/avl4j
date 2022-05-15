package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public class InOrder<K extends Comparable<K>, V> implements Traversable<K, V> {
    @Override
    public void traverse(TreeNode<K, V> node, Consumer<TreeNode<K, V>> consumer) {
        if (node == null) return;

        traverse(node.left(), consumer);
        consumer.accept(node);
        traverse(node.right(), consumer);
    }
}
