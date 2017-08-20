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
    /**
     * Root node of the tree.
     */
    private Node<E> root;

    /**
     * Used for toString().
     */
    private StringBuilder stringRepresentation = new StringBuilder();

    /**
     * Creates tree without nodes and root.
     */
    public Tree() {
    }

    /**
     * @param whereToInsert element which will be converted to node and searched in tree
     * @param whatToInsert element which will be converted to node ana added if possible
     */
    public void insert(E whereToInsert, E whatToInsert) {
        Node<E> nodeInTreeWhereToInsert = findNode(new Node(whereToInsert), root);

        Node<E> nodeToInsert = new Node<>(whatToInsert, nodeInTreeWhereToInsert);
        nodeInTreeWhereToInsert.getChildren().add(nodeToInsert);
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
            Node<E> nodeToAdd = new Node<>(element, root);
            root.getChildren().add(nodeToAdd);
        } else {
            throw new UnsupportedOperationException("You need to specify the position where you want this node to be" +
                    "inserted");
        }
    }

    public void delete(E element) {
        Node<E> nodeInTreeToDelete = findNode(new Node(element), root);
        nodeInTreeToDelete.getParent().getChildren().remove(nodeInTreeToDelete);
    }

    /**
     * Pruning = deleting subtree.
     * @param element this element and its children
     * will be removed from the tree.
     */
    public void prune(E element) {
        Node<E> nodeInTree = findNode(new Node(element), root);

        List<Node<E>> children = nodeInTree.getChildren();

        for(int i = 0; i < children.size();) {
            delete(children.get(i).value);
        }

        delete(element);
    }

    /**
     * @param nodeToFind node that has same value as some
     *                   node in the tree
     * @param root where to start searching for node
     *             in the tree
     * @return node in the tree
     */
    private Node<E> findNode(Node<E> nodeToFind, Node<E> root) {
        Node<E> result = findNodeRecursively(nodeToFind, root);
        if(nodeToFind == null || !nodeToFind.equals(result))  {
            throw new NoSuchElementException();
        }
        return result;
    }

    private Node<E> findNodeRecursively(Node<E> nodeToFind, Node<E> root) {
        if(root.equals(nodeToFind)) {
            return root;
        } else {
            List<Node<E>> children = root.children;
            for(Node<E> childNode : children) {
                Node<E> result = findNodeRecursively(nodeToFind, childNode);
                if(result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        makeStringFromSubtree(root);
        String result = new String(stringRepresentation);
        stringRepresentation.delete(0, stringRepresentation.length());

        return result;
    }

    /**
     * Initialize stringRepresentation(StringBuilder).
     * @param root where to start.
     */
    private void makeStringFromSubtree(Node<E> root) {
        stringRepresentation.append("Children of element ").append(root.value).append(": [");
        if(root.getChildren().size() != 0) {
            for(Node<E> n : root.getChildren()) {
                stringRepresentation.append(n).append(", ");
            }

            stringRepresentation.delete(stringRepresentation.length() - 2, stringRepresentation.length());
            stringRepresentation.append("]\n");
        } else {
            stringRepresentation.append("]\n");
        }

        for(Node<E> n : root.getChildren()) {
            makeStringFromSubtree(n);
        }
    }

    /**
     * Node of Tree data structure.
     * @author Artem Malchenko
     * @version 1.0
     *
     * @param <T> Data type of value which is stored in the node
     */
    private static class Node<T> {
        private T value;
        private Node<T> parent;
        private List<Node<T>> children = new ArrayList<>();

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> parent) {
            this.value = value;
            this.parent = parent;
        }

        public List<Node<T>> getChildren() {
            return children;
        }
        public void setChildren(List<Node<T>> children) {
            this.children = children;
        }
        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public Node<T> getParent() {
            return parent;
        }
        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public boolean isRoot() {
            return this.parent == null;
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