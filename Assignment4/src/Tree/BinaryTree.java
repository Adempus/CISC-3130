package Tree;

/**
 * Jeff Morin
 * Assignment4
 * 6/26/16
 *
 */

public class BinaryTree<T extends Comparable<T>>
{
    private Node<T> root;
    public BinaryTree(T rootData) {
        root = new Node<>(rootData);
    }

    @SafeVarargs
    public final void insertValues(T ... data) {
        for(T datum : data)
            insert(root, datum);
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

    public void deleteValue(T datum) {
        root = delete(root, datum);
    }
    protected Node<T> delete(Node<T> node, T datum) {
        if(node == null) return null;
        else if(datum.compareTo(node.datum) < 0)
            node.leftChild = delete(node.leftChild, datum);
        else if (datum.compareTo(node.datum) > 0)
            node.rightChild = delete(node.rightChild, datum);
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
                node.rightChild = delete(node.rightChild, tempNode.datum);
            }
        }
        return node;
    }

    public int count() {
        return count(root);
    }
    protected int count(Node<T> node) {
        if(node == null) return 0;
        else return (1 + count(node.leftChild) +
                count(node.rightChild));
    }

    public Node<T> find(T item) {
        return find(root, item);
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

    public T min() { return findMin(root); }
    protected T findMin(Node<T> node) {
        if(node == null) return null;
        else if (node.leftChild == null)
            return node.datum;
        return findMin(node.leftChild);
    }

    public T max() {
        return findMax(root);
    }
    protected T findMax(Node<T> node) {
        if(node == null) return null;
        else if (node.rightChild == null)
            return node.datum;
        return findMax(node.rightChild);
    }

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

    public boolean isEmpty() {
        return (count() == 0);
    }
}