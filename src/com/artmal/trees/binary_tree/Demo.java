package com.artmal.trees.binary_tree;

/**
 * Class for testing workability of Binary Tree data structure.
 * @author Artem Malchenko
 * @version 1.0
 */
public final class Demo {
    private Demo() { }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(3);
        binaryTree.add(4);
        binaryTree.add(5);

        binaryTree.insert(4, 6);
//        binaryTree.insert(4, 7);
        binaryTree.delete(4);

        System.out.println(binaryTree);
    }
}
