package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public class InOrder implements Traversable {
    @Override
    public void traverse(TreeNode node, Consumer<TreeNode> consumer) {
        if (node == null) return;

        traverse(node.left(), consumer);
        consumer.accept(node);
        traverse(node.right(), consumer);
    }
}
