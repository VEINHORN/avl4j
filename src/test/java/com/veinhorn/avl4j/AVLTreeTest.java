package com.veinhorn.avl4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: Add more tests to the AVL tree. Maybe separate unit tests for insert and deletion operations
class AVLTreeTest {
    /*
    Tree before deletion:

              9
             / \
            1   10
           / \    \
          0   5    11
         /   / \
       -1   2   6

     Tree after deletion:

              1
             / \
            0   9
           /   /  \
         -1   5    11
             / \
            2   6

      Preorder traversal: 1 0 -1 9 5 2 6 11
     */
    @Test
    public void testDeletion() {
        AVLTree tree = new AVLTree();
        tree.insert(9, 5, 10, 0, 6, 11, -1, 1, 2);

        tree.delete(10);

        assertEquals("10-1952611", tree.toString());
    }

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