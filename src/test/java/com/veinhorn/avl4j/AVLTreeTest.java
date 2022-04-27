package com.veinhorn.avl4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AVLTreeTest {
    /* Result:
                   30
                  /  \
                20    40
               /  \     \
             10    25    50
         */
    @Test
    @DisplayName("should insert values into AVL tree and balance it when needed")
    public void testInsertingWithBalancing() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        AVLTree.Node expected = new AVLTree.Node(
                30,
                new AVLTree.Node(20, new AVLTree.Node(10), new AVLTree.Node(25)),
                new AVLTree.Node(40, null, new AVLTree.Node(50))
        );

        assertEquals(expected.toString(), tree.toString());
    }

    @Test
    @DisplayName("should do left rotation for complex AVL tree")
    public void testLeftRotationForComplexTree() {
        AVLTree.Node node = new AVLTree.Node(
                2,
                new AVLTree.Node(1),
                new AVLTree.Node(4, new AVLTree.Node(3), new AVLTree.Node(5))
        );

        AVLTree.Node expected = new AVLTree.Node(
                4,
                new AVLTree.Node(2, new AVLTree.Node(1), new AVLTree.Node(3)),
                new AVLTree.Node(5)
        );

        assertEquals(expected.toString(), AVLTree.rotateLeft(node).toString());
    }

    /*
      1             2
       \    left   / \
        2   =>    1   3
         \
          3
     */
    @Test
    @DisplayName("should do left rotation of the node")
    public void testLeftRotation() {
        AVLTree.Node node = new AVLTree.Node(
                1,
                null,
                new AVLTree.Node(2, null, new AVLTree.Node(3))
        );

        AVLTree.Node expected = new AVLTree.Node(
                2,
                new AVLTree.Node(1),
                new AVLTree.Node(3)
        );

        assertEquals(expected.toString(), AVLTree.rotateLeft(node).toString());
    }

    @Test
    @DisplayName("should do right rotation of the node")
    public void testRightRotation() {
        AVLTree.Node node = new AVLTree.Node(
                3,
                new AVLTree.Node(2, new AVLTree.Node(1), null),
                null
        );

        AVLTree.Node expected = new AVLTree.Node(
                2,
                new AVLTree.Node(1),
                new AVLTree.Node(3)
        );

        assertEquals(expected.toString(), AVLTree.rotateRight(node).toString());
    }

    /*
           4              2
          / \     =>     / \
         2   5   right  1   4
        / \       <=       / \
       1   3     left     3   5
     */
    @Test
    @DisplayName("should do right rotation")
    public void testRightRotationForComplexTree() {
        AVLTree.Node node = new AVLTree.Node(
                4,
                new AVLTree.Node(2, new AVLTree.Node(1), new AVLTree.Node(3)),
                new AVLTree.Node(5)
        );

        AVLTree.Node expected = new AVLTree.Node(
                2,
                new AVLTree.Node(1),
                new AVLTree.Node(4, new AVLTree.Node(3), new AVLTree.Node(5))
        );

        assertEquals(expected.toString(), AVLTree.rotateRight(node).toString());
    }

    @Test
    @DisplayName("should search value in AVL tree")
    public void testSearching() {
        assertEquals(new AVLTree.Node(1, null, null), createTestTree().search(1));
    }

    @Test
    @DisplayName("should traverse AVL tree")
    public void testTraversing() {
        createTestTree().traverse();
    }

    private AVLTree createTestTree() {
        return new AVLTree(new AVLTree.Node(
                4,
                new AVLTree.Node(2, new AVLTree.Node(1), null),
                new AVLTree.Node(5, null, new AVLTree.Node(6))
        ));
    }
}