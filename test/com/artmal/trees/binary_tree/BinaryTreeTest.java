package com.artmal.trees.binary_tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class BinaryTreeTest {
    @Test
    void testIfFailsToDeleteNodeWithTwoChildren() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(3);
        binaryTree.add(4);
        binaryTree.add(5);

        binaryTree.insert(4, 6);
        binaryTree.insert(4, 7);

        assertThrows(UnsupportedOperationException.class, () -> binaryTree.delete(4));
    }
}