package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public class PostOrder implements Traversable {
    @Override
    public void traverse(TreeNode node, Consumer<TreeNode> consumer) {
        if (node == null) return;

        traverse(node.left(), consumer);
        traverse(node.right(), consumer);
        consumer.accept(node);
    }
}
