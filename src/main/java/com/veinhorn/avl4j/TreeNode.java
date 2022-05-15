package com.veinhorn.avl4j;

import com.veinhorn.avl4j.operation.TraverseOrder;

import java.util.Objects;

public class TreeNode<K extends Comparable<K>> {
    private K key;
    private int height;
    private TreeNode<K> left;
    private TreeNode<K> right;

    public TreeNode(K key) {
        this.key = key;
        this.height = 1;
    }

    public TreeNode(K key, TreeNode<K> left, TreeNode<K> right) {
        this.key = key;
        this.left = left;
        this.right = right;
        // TODO: Need to calculate height for all nodes here
    }

    public K key() {
        return key;
    }

    public int height() {
        return height;
    }

    public TreeNode<K> left() {
        return left;
    }

    public TreeNode<K> right() {
        return right;
    }

    public void setLeft(TreeNode<K> left) {
        this.left = left;
    }

    public void setRight(TreeNode<K> right) {
        this.right = right;
    }

    public void setKey(K key) {
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
        AVLTree<K> tree = new AVLTree<>(this);
        StringBuilder accumulator = new StringBuilder();
        tree.traverse(TraverseOrder.PreOrder, (node) -> accumulator.append(node.key));

        return accumulator.toString();
    }
}
