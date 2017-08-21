package com.artmal.trees.binary_tree;

import java.util.NoSuchElementException;

/**
 * Implementation of Binary Tree data structure.
 * @param <E> data type elements of which are stored in
 *           the tree.
 * @author Artem Malchenko
 * @version 1.0
 */
public class BinaryTree<E> {
    private Node<E> root;

    /**
     * Used in toString().
     */
    private StringBuilder stringRepresentation = new StringBuilder();

    /**
     * If the tree doesn't have root element - add parameter as one.
     * If the root doesn't have one of his children - add parameter as one.
     * @param element will be converted to Node and added if possible.
     */
    public void add(E element) {
        if(root == null) {
            root = new Node<>(element);
        } else if(root.getLeftChild() == null) {
            Node<E> nodeToAdd = new Node<>(element, root);
            root.setLeftChild(nodeToAdd);
        } else if(root.getRightChild() == null) {
            Node<E> nodeToAdd = new Node<>(element, root);
            root.setRightChild(nodeToAdd);
        } else {
            throw new UnsupportedOperationException("There are already root and his children");
        }
    }

    /**
     * @param whereToInsert value will be converted to a node and searched
     * @param whatToInsert value will be converted to a node and inserted if possible.
     */
    public void insert(E whereToInsert, E whatToInsert) {
        Node<E> nodeInTreeWhereToInsert = findNode(whereToInsert);
        Node<E> nodeToInsert = new Node<>(whatToInsert, nodeInTreeWhereToInsert);

        if(nodeInTreeWhereToInsert.getLeftChild() == null) {
            nodeInTreeWhereToInsert.setLeftChild(nodeToInsert);
        } else if(nodeInTreeWhereToInsert.getRightChild() == null) {
            nodeInTreeWhereToInsert.setRightChild(nodeToInsert);
        }
    }

    private Node<E> findNode(E element) {
        Node<E> nodeToFind = new Node<>(element);

        Node<E> result = findNodeRecursively(nodeToFind, root);
        if(nodeToFind == null && nodeToFind.value != element) {
            throw new NoSuchElementException();
        }

        return result;
    }

    private Node<E> findNodeRecursively(Node<E> nodeToFind, Node<E> root) {
        if(root.equals(nodeToFind)) {
            return root;
        } else {
            Node<E> result = findNodeRecursively(nodeToFind, root.getLeftChild());
            if(result != null) {
                return result;
            }

            result = findNodeRecursively(nodeToFind, root.getRightChild());
            if(result != null) {
                return result;
            }
        }

        return null;
    }

    public void delete(E elementToDelete) {
        Node<E> nodeInTreeToDelete = findNode(elementToDelete);

        if(!nodeInTreeToDelete.isHavingChildren()) {
            nodeInTreeToDelete.getParent().deleteChild(new Node(elementToDelete));
        } else if(nodeInTreeToDelete.isHavingOneChild()){
            if(nodeInTreeToDelete.getLeftChild() != null) {
                Node<E> leftChildOfNodeToDelete = nodeInTreeToDelete.getLeftChild();

                Node<E> parentOfNodeToDelete = nodeInTreeToDelete.getParent();
                parentOfNodeToDelete.deleteChild(nodeInTreeToDelete);
                parentOfNodeToDelete.setLeftChild(leftChildOfNodeToDelete);
            } else {
                Node<E> rightChildOfNodeToDelete = nodeInTreeToDelete.getRightChild();

                Node<E> parentOfNodeToDelete = nodeInTreeToDelete.getParent();
                parentOfNodeToDelete.deleteChild(nodeInTreeToDelete);
                parentOfNodeToDelete.setLeftChild(rightChildOfNodeToDelete);
            }
        } else {
            throw new UnsupportedOperationException("Can't delete node with two children");
        }
    }

    @Override
    public String toString() {
        String result = makeStringFromSubtree(root);

        StringBuilder resultWithoutExtraNewLine = new StringBuilder(result);
        resultWithoutExtraNewLine.setLength(resultWithoutExtraNewLine.length() - System.lineSeparator().length());

        stringRepresentation.delete(0, stringRepresentation.length());
        return new String(resultWithoutExtraNewLine);
    }

    private String makeStringFromSubtree(Node<E> root) {
        if(root != null) {
            stringRepresentation.append("Children of ").append(root).append(":[");

            if(root.getLeftChild() != null) {
                stringRepresentation.append(root.getLeftChild());
                stringRepresentation.append(", ");
            } else {
                stringRepresentation.append("(), ");
            }

            if(root.getRightChild() != null) {
                stringRepresentation.append(root.getRightChild());
                stringRepresentation.append("]");
            } else {
                stringRepresentation.append("()]");
            }

            stringRepresentation.append(System.lineSeparator());

            makeStringFromSubtree(root.getLeftChild());
            makeStringFromSubtree(root.getRightChild());
        }

        return new String(stringRepresentation);
    }

    /**
     * Node of the tree.
     * @param <E> data type elements of which are stored in the tree.
     */
    private class Node<E> {
        private E value;

        private Node<E> parent;
        private Node<E> leftChild, rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        public E getValue() {
            return value;
        }
        public void setValue(E value) {
            this.value = value;
        }
        public Node<E> getParent() {
            return parent;
        }
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
        public Node<E> getLeftChild() {
            return leftChild;
        }
        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }
        public Node<E> getRightChild() {
            return rightChild;
        }
        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        public boolean isHavingChildren() {
            return (leftChild != null || rightChild != null);
        }
        public void deleteChild(Node<E> childNode) {
            if(leftChild.equals(childNode)) {
                leftChild = null;
            } else if(rightChild.equals(childNode)) {
                rightChild = null;
            } else {
                throw new NoSuchElementException("There is no such child in the node");
            }
        }
        public boolean isHavingOneChild() {
            return (leftChild == null && rightChild != null) || (leftChild != null && rightChild == null);
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