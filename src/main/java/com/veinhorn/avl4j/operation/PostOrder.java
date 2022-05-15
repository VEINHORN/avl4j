package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public class PostOrder<K extends Comparable<K>> implements Traversable<K> {
    @Override
    public void traverse(TreeNode<K> node, Consumer<TreeNode<K>> consumer) {
        if (node == null) return;

        traverse(node.left(), consumer);
        traverse(node.right(), consumer);
        consumer.accept(node);
    }
}
