package com.veinhorn.avl4j.operation;

import com.veinhorn.avl4j.TreeNode;

import java.util.function.Consumer;

public interface Traversable {
    void traverse(TreeNode node, Consumer<TreeNode> consumer);
}
