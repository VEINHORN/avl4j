package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public class PreOrder implements Traversable {
    @Override
    public void traverse(TreeNode node, Consumer<TreeNode> consumer) {
        if (node == null) return;

        consumer.accept(node);
        traverse(node.left(), consumer);
        traverse(node.right(), consumer);
    }
}
