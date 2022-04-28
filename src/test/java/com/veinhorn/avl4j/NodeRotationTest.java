package com.veinhorn.avl4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeRotationTest {
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

    /*
     3           3         2
    /   L(1)    /  R(3)   / \
   1    --->   2   --->   1   3
    \         /
     2       1
     */
    @Test
    public void testDoubleRightLeftRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);

        AVLTree.Node expected = new AVLTree.Node(2, new AVLTree.Node(1), new AVLTree.Node(3));

        assertEquals(expected.toString(), tree.toString());
    }

    /*
     1           1             2
      \    R(3)   \    L(1)   / \
       3   --->    2   --->  1   3
      /             \
     2               3
     */
    @Test
    public void testDoubleLeftRightRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);

        AVLTree.Node expected = new AVLTree.Node(2, new AVLTree.Node(1), new AVLTree.Node(3));

        assertEquals(expected.toString(), tree.toString());
    }
}
