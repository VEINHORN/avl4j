package com.veinhorn.avl4j;

import com.veinhorn.avl4j.operation.*;

import java.util.function.Consumer;

public class AVLTree<K extends Comparable<K>> {
    private TreeNode<K> root;

    public AVLTree() {}

    public AVLTree(TreeNode<K> root) {
        this.root = root;
    }

    public void insert(K... keys) {
        for (K key : keys) insert(key);
    }

    public void insert(K key) {
        if (root == null) root = new TreeNode<>(key);
        else              root = insert(root, key);
    }

    private TreeNode<K> insert(TreeNode<K> node, K key) {
        if (node == null)          return new TreeNode<>(key);

        if (node.key().compareTo(key) > 0/*node.key() > key*/)      node.setLeft(insert(node.left(), key));
        else if (node.key().compareTo(key) < 0/*node.key() < key*/) node.setRight(insert(node.right(), key));
        else                       return node;

        node.setHeight(1 + Math.max(height(node.left()), height(node.right())));

        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (key.compareTo(node.right().key()) > 0/*key > node.right().key()*/) { // right-right case
                return rotateLeft(node);
            } else if (key.compareTo(node.right().key()) < 0/*key < node.right().key()*/) { // right-left rotation
                node.setRight(rotateRight(node.right()));
                return rotateLeft(node);
            }
        } else if (balanceFactor < -1) {
            if (key.compareTo(node.left().key()) < 0/*key < node.left().key()*/) { // left-left rotation
                return rotateRight(node);
            } else if (key.compareTo(node.left().key()) > 0 /*key > node.left().key()*/) { // left-right rotation
                node.setLeft(rotateLeft(node.left()));
                return rotateRight(node);
            }
        }

        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private TreeNode<K> delete(TreeNode<K> node, K key) {
        if (node == null) return node;

        if (key.compareTo(node.key()) < 0 /*key < node.key()*/) {
            node.setLeft(delete(node.left(), key));
        } else if (key.compareTo(node.key()) > 0 /*key > node.key()*/) {
            node.setRight(delete(node.right(), key));
        } else { // if key is the same as root's key, then this is the node to be deleted
            if (node.left() == null || node.right() == null) {
                TreeNode<K> tmp = null;

                if (tmp == node.left()) tmp = node.right();
                else                    tmp = node.left();

                if (tmp == null) { // No child case
                    tmp = node;
                    node = null;
                } else { // One child case
                    node = tmp;
                }
            } else { // Node with 2 children. Get the inorder successor (smallest in the right subtree)
                TreeNode<K> tmp = minValueNode(node.right());

                node.setKey(tmp.key()); // copy the inorder successor's data to this node

                node.setRight(delete(node.right(), tmp.key())); // delete the inorder successor
            }
        }

        if (node == null) return node; // if the tree had only one node then return

        node.setHeight(Math.max(height(node.left()), height(node.right())) + 1); // update height of the current node

        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (getBalance(node.right()) >= 0) return rotateLeft(node); // right-right case
            else if (getBalance(node.right()) < 0) {                    // right left case
                node.setRight(rotateRight(node.right()));
                return rotateLeft(node);
            }
        } else if (balanceFactor < -1) {
            if (getBalance(node.left()) <= 0) return rotateRight(node); // left-left case
            else if (getBalance(node.left()) > 0) {                     // left right case
                node.setLeft(rotateLeft(node.left()));
                return rotateRight(node);
            }
        }

        return node;
    }

    // Given a non-empty binary search tree, return the node with minimum key value found in that tree
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left() != null) current = current.left();
        return current;
    }

    public static TreeNode rotateLeft(TreeNode pivot) {
        TreeNode q = pivot.right();
        TreeNode qLeft = q.left();

        q.setLeft(pivot);
        pivot.setRight(qLeft);

        pivot.setHeight(Math.max(height(pivot.left()), height(pivot.right())) + 1);
        q.setHeight(Math.max(height(q.left()), height(q.right())) + 1);

        return q;
    }

    /*
 pivot  ->  |Q|             |P|
            / \  right      / \
           P   C =>   <=   A   Q
          / \        left     / \
         A   B               B   C
     */
    public static TreeNode rotateRight(TreeNode pivot) {
        TreeNode p = pivot.left();
        TreeNode pRight = p.right();

        p.setRight(pivot);
        pivot.setLeft(pRight);

        pivot.setHeight(Math.max(height(pivot.left()), height(pivot.right())) + 1);
        p.setHeight(Math.max(height(p.left()), height(p.right())) + 1);

        return p;
    }

    private static int height(TreeNode node) {
        return node == null ? 0 : node.height();
    }

    private int getBalance(TreeNode<K> node) {
        return node == null ? 0 : height(node.right()) - height(node.left());
    }

    public void traverse() {
        Consumer<TreeNode<K>> defaultConsumer = (node) -> System.out.print(" " + node.key());
        getTraversable(TraverseOrder.InOrder).traverse(root, defaultConsumer);
    }

    /**
     * Traverse tree with custom consumer
     * @param consumer is using for applying action to the tree node during traversal
     */
    public void traverse(Consumer<TreeNode<K>> consumer) {
        getTraversable(TraverseOrder.InOrder).traverse(root, consumer);
    }

    public void traverse(TraverseOrder order, Consumer<TreeNode<K>> consumer) {
        getTraversable(order).traverse(root, consumer);
    }

    /**
     * Provides traverse implementation based on traverse order
     * @param order of traverse
     * @return traverse implementation
     */
    private Traversable<K> getTraversable(TraverseOrder order) {
        if (order.equals(TraverseOrder.PreOrder)) return new PreOrder<>();
        else if (order.equals(TraverseOrder.PostOrder)) return new PostOrder<>();

        return new InOrder<>();
    }

    public TreeNode<K> search(int key) {
        return search(root, key);
    }

    private TreeNode<K> search(TreeNode node, int key) {
        if (node == null) return null;

        if (node.key().compareTo(key) > 0/* node.key() > key*/) {
            return search(node.left(), key);
        } else if (node.key().compareTo(key) < 0/* node.key() < key*/) {
            return search(node.right(), key);
        }

        return node;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
