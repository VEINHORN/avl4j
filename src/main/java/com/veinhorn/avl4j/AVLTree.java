package com.veinhorn.avl4j;

import java.util.Objects;

public class AVLTree {
    private Node root;

    public AVLTree() {

    }

    public AVLTree(Node root) {
        this.root = root;
    }

    public void insert(Integer value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insert(root, value);
        }
    }

    private void insert(Node node, int value) {
        if (node.getValue() > value) {
            if (node.left == null) node.left = new Node(value);
            else insert(node.left, value);
        } else if (node.getValue() < value) {
            if (node.right == null) node.right = new Node(value);
            else insert(node.right, value);
        }
    }

    public void traverse() {
        traverse(root);
    }

    private void traverse(Node node) {
        if (node == null) return;

        traverse(node.left);
        System.out.print(" " + node.value);
        traverse(node.right);
    }

    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null) return null;

        if (node.getValue() > value) {
            return search(node.left, value);
        } else if (node.getValue() < value) {
            return search(node.right, value);
        }

        return node;
    }

    public static class Node {
        private final Integer value;
        private Node left;
        private Node right;

        public Node(Integer value) {
            this.value = value;
        }

        public Node(Integer value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
