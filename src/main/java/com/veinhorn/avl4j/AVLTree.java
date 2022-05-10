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

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null) return node;

        if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else { // if key is the same as root's key, then this is the node to be deleted
            if (node.left == null || node.right == null) {
                Node tmp = null;

                if (tmp == node.left) tmp = node.right;
                else                  tmp = node.left;

                if (tmp == null) { // No child case
                    tmp = node;
                    node = null;
                } else { // One child case
                    node = tmp;
                }
            } else { // Node with 2 children. Get the inorder successor (smallest in the right subtree)
                Node tmp = minValueNode(node.right);

                node.value = tmp.value; // copy the inorder successor's data to this node

                node.right = delete(node.right, tmp.value); // delete the inorder successor
            }
        }

        if (node == null) return node; // if the tree had only one node then return

        node.height = Math.max(height(node.left), height(node.right)) + 1; // update height of the current node

        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (getBalance(node.right) >= 0) return rotateLeft(node); // right-right case
            else if (getBalance(node.right) < 0) {                    // right left case
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        } else if (balanceFactor < -1) {
            if (getBalance(node.left) <= 0) return rotateRight(node); // left-left case
            else if (getBalance(node.left) > 0) {                     // left right case
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        return node;
    }

    // Given a non-empty binary search tree, return the node with minimum key value found in that tree
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    public static Node rotateLeft(Node pivot) {
        Node q = pivot.right;
        Node qLeft = q.left;

        q.left = pivot;
        pivot.right = qLeft;

        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        q.height = Math.max(height(q.left), height(q.right)) + 1;

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

    // Traverse in pre-order
    private void traverseUsing(Consumer<Integer> consumer, Node node) {
        if (node == null) return;

        consumer.accept(node.value);
        traverseUsing(consumer, node.left);
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
        private Integer value; // TODO: Rename this field to "key"
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
