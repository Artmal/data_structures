package com.artmal.trees.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of Tree data structure.
 * @author Artem Malchenko
 * @version 1.0
 */
public class Tree<E> {
    private Node<E> root;

    public Tree() {
    }

    /**
     *
     * @param whereToInsert element which will be converted to node and searched in tree
     * @param whatToInsert element which will be converted to node ana added if possible
     */
    public void add(E whereToInsert, E whatToInsert) {
        Node<E> nodeInTreeWhereToInsert = findNode(new Node(whereToInsert));
        nodeInTreeWhereToInsert.getChildren().add(new Node(whatToInsert));
    }

    /**
     * If the tree doesn't have root than
     * the parameter will be set as one.
     *
     * If the tree's root doesn't have children
     * then the parameter will be added as one.
     * @param element
     */
    public void add(E element) {
        if(root == null) {
            root = new Node(element);
        } else if(root.getChildren().size() == 0) {
            root.getChildren().add(new Node<>(element));
        } else {
            throw new UnsupportedOperationException("You need to specify the position where you want this node to be" +
                    "inserted");
        }
    }

    private Node<E> findNode(Node<E> nodeToFind) {
        return findNodeRecursively(nodeToFind, root);
    }

    private Node<E> findNodeRecursively(Node<E> nodeToFind, Node<E> root) {
        if(root.equals(nodeToFind)) {
            return root;
        } else {
            List<Node<E>> children = root.children;
            for(Node<E> childNode : children) {
                findNodeRecursively(nodeToFind, childNode);
            }
        }

        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        return subtreeToString(root);
    }

    public String subtreeToString(Node<E> root) {
        StringBuilder sb = new StringBuilder();

        sb.append("Children of element ").append(root.value).append(": [");
        if(root.getChildren().size() != 0) {
            for(Node<E> n : root.getChildren()) {
                sb.append(n).append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());
            sb.append("]\n");
        } else {
            sb.append("]\n");
        }

        for(Node<E> n : root.getChildren()) {
            subtreeToString(n);
        }

        return new String(sb);
    }

    /**
     * Node of Tree data structure.
     * @author Artem Malchenko
     * @version 1.0
     *
     * @param <T> Data type of value which is stored in node
     */
    private static class Node<T> {
        private T value;
        private List<Node<T>> children = new ArrayList<>();

        public Node(T value) {
            this.value = value;
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
