package com.artmal.trees.tree;

/**
 * Class for testing workability of Tree data structure;
 * @author Artem Malchenko
 * @version 1.0
 */
public class Demo {
    /**
     * Entry point to the program.
     * @param args don't affect
     */
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(4);

        tree.add(4, 5);
        System.out.println(tree);
    }
}
