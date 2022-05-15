package com.veinhorn.avl4j;

import com.veinhorn.avl4j.operation.TraverseOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;

public class AVLTreeTraversalTests extends Trees {
    @Test
    @DisplayName("should traverse AVL tree in-order")
    public void testTreeTraversalInOrder() {
        StringBuilder accumulator = new StringBuilder();
        Consumer<TreeNode> consumer = (node) -> accumulator.append(node.key());
        createTestTree().traverse(TraverseOrder.InOrder, consumer);

        assertThat(accumulator.toString()).isEqualTo("12456");
    }
}
