/**
 * Jeff Morin
 * 6/26/16
 *
 */
import Tree.BinaryTree;

import java.io.File;
import java.util.*;

public class Main {
    public static BinaryTree<Integer> intTree;
    public static CSVModel treeDataModel;

    public static void main(String[] args) {
        System.out.print("Enter a path: ");
        String path = new Scanner(System.in).nextLine();
        treeDataModel = new CSVModel(new File(path));
        Integer[] treeData = createIntTree(treeDataModel.getModel().get(0));
        intTreeInsertion(treeData);
        printTreeOrders();
        printCount();
    }

    // method to parse tree input data into an Integer array
    public static Integer[] createIntTree(Object[] data) {
        Integer[] insertData = new Integer[data.length];
        for(int i = 0 ; i < data.length ; i++)
            insertData[i] = Integer.parseInt(data[i].toString());
        return insertData;
    }

    // TODO: method to insert the tree's initial values
    public static void intTreeInsertion(Integer[] data) {
        intTree = new BinaryTree<>(data[0]);
        intTree.insertValues(data);
    }

    // TODO: method to print the tree's orders
    public static void printTreeOrders() {
        System.out.println("\nPREORDER");
        intTree.printPreOrder();
        System.out.println("\nINORDER");
        intTree.printInOrder();
        System.out.println("\nPOSTORDER:");
        intTree.printPostOrder();
    }

    // TODO: method to print the tree's counts
    public static void printCount() {
        System.out.printf("%nCount: %d", intTree.count());
    }

    // TODO: method to delete the tree's values.
    public static void deleteValue() {
        System.out.print("Enter a deletion value: ");
        int deletionVal = new Scanner(System.in).nextInt();
        intTree.deleteValue(deletionVal);
        printTreeOrders();
        printCount();
    }

    // TODO: method to get the number of node's children
    public static void printChildren() {
        System.out.println("How many children for which value? ");
        int val = new Scanner(System.in).nextInt();
        System.out.printf("%nChild count of node %d: %d",
                val, intTree.getChildren(val));
    }
}
