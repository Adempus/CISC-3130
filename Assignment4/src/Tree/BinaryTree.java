package Tree;

/**
 * Jeff Morin
 * Assignment4
 * CISC 3130
 * 6/26/16
 *
 */

/** Creates a binary tree of comparable generic types. Integers, Doubles,
 *  Floats, Booleans, Characters or any other comparable object can be used
 *  to represent the tree's data. Bin tree data must be comparable and have
 *  precedence.
 *
 */

public class BinaryTree<T extends Comparable<T>>
{
    private Node<T> root;

    public BinaryTree() { }
    public BinaryTree(T rootData) {
        root = new Node<>(rootData);
    }

    @SafeVarargs
    // Accepts a variable number of data to insert into the tree.
    public final void insert(T ... data) {
        if(root == null) root = new Node<>(data[0]);
        for(T datum : data) insert(root, datum);
    }
    protected void insert(Node<T> node, T datum) {
        if(node.datum.compareTo(datum) > 0) {
            if(node.leftChild == null)
                node.setLeftChild(new Node<>(datum));
            else
                insert(node.leftChild, datum);
        } else if (node.datum.compareTo(datum) < 0) {
            if (node.rightChild == null)
                node.setRightChild(new Node<>(datum));
            else
                insert(node.rightChild, datum);
        }
    }

    @SafeVarargs
    // Accepts a variable number of data to remove from the tree.
    public final Node<T> remove(T ... data) {
        for(T datum : data) root = remove(root, datum);
        return root;
    }
    protected Node<T> remove(Node<T> node, T datum) {
        if(node == null) return null;
        else if(datum.compareTo(node.datum) < 0)
            node.leftChild = remove(node.leftChild, datum);
        else if (datum.compareTo(node.datum) > 0)
            node.rightChild = remove(node.rightChild, datum);
        else {
            Node<T> tempNode = new Node<>(null);
            if(node.isLeaf())
                node = null;
            else if (node.leftChild == null)
                node = node.rightChild;
            else if (node.rightChild == null)
                node = node.leftChild;
            else {
                tempNode.datum = findMin(node.rightChild);
                node.datum = tempNode.datum;
                node.rightChild = remove(node.rightChild, tempNode.datum);
            }
        }
        return node;
    }

    // Returns the number of nodes currently in the tree
    public int size() {
        return size(root);
    }
    protected int size(Node<T> node) {
        if(node == null) return 0;
        else return (1 + size(node.leftChild) + size(node.rightChild));
    }

    // Searches for, and returns the node with the specified datum.
    public Node<T> find(T datum) {
        return find(root, datum);
    }
    protected Node<T> find(Node<T> subtree, T item) {
        if (subtree == null)
            return null;
        else if (subtree.datum.compareTo(item) == 0)
            return subtree;
        else if (subtree.datum.compareTo(item) > 0)
            return find(subtree.leftChild, item);
        else
            return find(subtree.rightChild, item);
    }

    // returns the minimum value in the tree.
    public T min() { return findMin(root); }
    protected T findMin(Node<T> node) {
        if(node == null) return null;
        else if (node.leftChild == null)
            return node.datum;
        return findMin(node.leftChild);
    }

    //returns the maximum value in the tree.
    public T max() {
        return findMax(root);
    }
    protected T findMax(Node<T> node) {
        if(node == null) return null;
        else if (node.rightChild == null)
            return node.datum;
        return findMax(node.rightChild);
    }

    // Returns the number of children for a specified node.
    public int getChildren(T datum) {
        Node<T> foundNode = find(datum);
        if(!foundNode.isLeaf()) {
            if(foundNode.rightChild != null && foundNode.leftChild != null)
                return 2;
            else return 1;
        } else return 0;
    }

    public void printPreOrder() {
        printPreOrder(root);
    }
    protected void printPreOrder(Node<T> node) {
        if(node != null) {
            System.out.print(node.datum + ", ");
            printPreOrder(node.leftChild);
            printPreOrder(node.rightChild);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }
    protected void printInOrder(Node<T> node) {
        if(node != null) {
            printInOrder(node.leftChild);
            System.out.print(node.datum + ", ");
            printInOrder(node.rightChild);
        }
    }

    public void printPostOrder() {
        printPostOrder(root);
    }
    protected void printPostOrder(Node<T> node) {
        if(node != null) {
            printPostOrder(node.leftChild);
            printPostOrder(node.rightChild);
            System.out.print(node.datum + ", ");
        }
    }

    // release all the nodes of the tree;
    public void free() {
        root = null;
    }
    // if there are no nodes, the tree is empty.
    public boolean isEmpty() {
        return (size() == 0);
    }
}
