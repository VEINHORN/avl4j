package com.veinhorn.avl4j;

import com.veinhorn.avl4j.operation.TraverseOrder;

import java.util.Objects;

public class TreeNode {
    private Integer key;
    private int height;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Integer key) {
        this.key = key;
        this.height = 1;
    }

    public TreeNode(Integer key, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        // TODO: Need to calculate height for all nodes here
    }

    public Integer key() {
        return key;
    }

    public int height() {
        return height;
    }

    public TreeNode left() {
        return left;
    }

    public TreeNode right() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode node = (TreeNode) o;
        return Objects.equals(key, node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        AVLTree tree = new AVLTree(this);
        StringBuilder accumulator = new StringBuilder();
        tree.traverse(TraverseOrder.PreOrder, (node) -> accumulator.append(node.key));

        return accumulator.toString();
    }
}
