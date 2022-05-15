package com.veinhorn.avl4j;

// Contains test tree for testing purposes
public class Trees {
    protected AVLTree<Integer> createTestTree() {
        return new AVLTree<>(new TreeNode<>(
                4,
                new TreeNode<>(2, new TreeNode<>(1), null),
                new TreeNode<>(5, null, new TreeNode<>(6))
        ));
    }
}
