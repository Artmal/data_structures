package com.artmal.trees.tree;

/**
 * Class for testing workability of Tree data structure.
 * @author Artem Malchenko
 * @version 1.0
 */
public final class Demo {
    private Demo() { }

    /**
     * Entry point to the program.
     * @param args don't affect
     */
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.insert(3, 4);
        tree.insert(3, 5);
        tree.insert(3, 6);

        tree.insert(6, 7);
        tree.insert(6, 8);
        tree.insert(6, 9);

        tree.insert(7, 11);
        tree.insert(8, 10);
        tree.insert(9, 12);

        tree.prune(6);
        System.out.println(tree);
    }
}
