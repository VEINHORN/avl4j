package com.veinhorn.avl4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
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