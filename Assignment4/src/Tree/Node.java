package Tree;

/**
 * Jeff Morin
 * Assignment 4
 * CISC 3130
 * 6/26/16
 */

public class Node<T extends Comparable<T>>
{
    protected T datum;
    protected Node<T> parent;
    protected Node<T> leftChild;
    protected Node<T> rightChild;
    private enum Position { LEFT, RIGHT }
    private Position pos;

    protected Node(T data) {
        this.datum = data;
        leftChild = null;
        rightChild = null;
    }

    protected boolean isLeaf() {
        return (leftChild == null && rightChild == null);
    }

    protected T getDatum() {
        return datum;
    }
    public Node<T> getLeftChild() {
        return leftChild;
    }
    public Node<T> getRightChild() {
        return rightChild;
    }
    protected void setDatum(T datum) {
        this.datum = datum;
    }

    protected void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
        leftChild.parent = this;
        leftChild.pos = Position.LEFT;
    }

    protected void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
        rightChild.parent = this;
        rightChild.pos = Position.RIGHT;
    }
}
