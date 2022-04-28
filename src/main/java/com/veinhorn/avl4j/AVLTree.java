package com.veinhorn.avl4j;

import java.util.Objects;
import java.util.function.Consumer;

public class AVLTree {
    private Node root;

    public AVLTree() {}

    public AVLTree(Node root) {
        this.root = root;
    }

    public void insert(int... values) {
        for (int value : values) insert(value);
    }

    public void insert(int value) {
        if (root == null) root = new Node(value);
        else              root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null)            return new Node(value);

        if (node.value > value)      node.left = insert(node.left, value);
        else if (node.value < value) node.right = insert(node.right, value);
        else                         return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (value > node.right.value) { // right-right case
                return rotateLeft(node);
            } else if (value < node.right.value) { // right-left rotation
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        } else if (balanceFactor < -1) {
            if (value < node.left.value) { // left-left rotation
                return rotateRight(node);
            } else if (value > node.left.value) { // left-right rotation
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        return node;
    }

    public static Node rotateLeft(Node pivot) {
        Node q = pivot.right;
        // Node qLeft = q.left;
        Node qLeft = q == null ? null : q.left;

        if (q != null) q.left = pivot;
        pivot.right = qLeft;

        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        // q.height = Math.max(height(q.left), height(q.right)) + 1;
        if (q != null) q.height = Math.max(height(q.left), height(q.right)) + 1;

        return q;
    }

    /*
 pivot  ->  |Q|             |P|
            / \  right      / \
           P   C =>   <=   A   Q
          / \        left     / \
         A   B               B   C
     */
    public static Node rotateRight(Node pivot) {
        Node p = pivot.left;
        Node pRight = p.right;

        p.right = pivot;
        pivot.left = pRight;

        // TODO: Update height here
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        p.height = Math.max(height(p.left), height(p.right)) + 1;

        return p;
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.right) - height(node.left);
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

    // Used only for internal purposes
    public void traverseUsing(Consumer<Integer> consumer) {
        traverseUsing(consumer, root);
    }

    private void traverseUsing(Consumer<Integer> consumer, Node node) {
        if (node == null) return;

        traverseUsing(consumer, node.left);
        consumer.accept(node.value);
        traverseUsing(consumer, node.right);
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

    @Override
    public String toString() {
        return root.toString();
    }

    public static class Node {
        private final Integer value; // TODO: Rename this field to "key"
        private int height;
        private Node left;
        private Node right;

        public Node(Integer value) {
            this.value = value;
            this.height = 1;
        }

        public Node(Integer value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
            // TODO: Need to calculate height for all nodes here
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

        @Override
        public String toString() {
            AVLTree tree = new AVLTree(this);
            StringBuilder builder = new StringBuilder();
            tree.traverseUsing(builder::append);
            return builder.toString();
        }
    }
}
