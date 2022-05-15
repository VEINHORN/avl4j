package com.veinhorn.avl4j;

public class Trees {
    protected AVLTree createTestTree() {
        return new AVLTree(new TreeNode(
                4,
                new TreeNode(2, new TreeNode(1), null),
                new TreeNode(5, null, new TreeNode(6))
        ));
    }
}
