/**
 * Jeff Morin
 * Assignment 4
 * CISC 3130
 * 6/26/16
 *
 */
 
import Tree.BinaryTree;
import java.io.File;
import java.util.*;

public class Main {
    public static BinaryTree[] forest;
    public static CSVModel treeDataModel;

    public static void main(String[] args)
    {
        System.out.print("Enter file path: ");
        treeDataModel = new CSVModel(
                new File(new Scanner(System.in).nextLine())
        );

        int i = 0;
        forest = new BinaryTree[100];
        for(Object[] o : treeDataModel.getModel()) {
            forest[i] = createTree(parseData(o));
            i++;
        }
        System.out.println(i + " sets of trees created from data.");
        menu();
    }

    public static void menu() {
        boolean exit = false;
        while(!exit) {
            System.out.println("\nView data from which set? ");
            int set = new Scanner(System.in).nextInt();

            operationLoop: do {
                System.out.println("\nPerform an operation on set " + set + ": " +
                        "\n1. Print orders\t 2. Insert Data" + "\n3. Delete Data\t 4. Count nodes"
                        + "\n5. Get max\t\t 6. Get min" + "\n7. Free tree\t 8. Choose another set."
                        + "\n9. Exit.");
                int operation = new Scanner(System.in).nextInt();
                switch (operation) {
                    case 1 : printTreeOrders(forest[set]); break;
                    case 2 : insertValue(forest[set]); break;
                    case 3 : deleteValue(forest[set]); break;
                    case 4 : printCount(forest[set]); break;
                    case 5 : System.out.println("Max: " + findMax(forest[set])); break;
                    case 6 : System.out.println("Min: " + findMin(forest[set])); break;
                    case 7 : freeTree(forest[set]); break;
                    case 9 : exit = true;
                    default : break operationLoop;
                }
            } while(true);
        }
    }

    // TODO: method to parse tree input data into an Integer array
    public static Integer[] parseData(Object[] data) {
        Integer[] insertData = new Integer[data.length];
        for(int i = 0 ; i < data.length ; i++)
            insertData[i] = Integer.parseInt(data[i].toString());
        return insertData;
    }

    public static BinaryTree<Integer> createTree(Integer[] data) {
        BinaryTree<Integer> tree = new BinaryTree<>(data[0]);
        tree.insert(data);
        return tree;
    }

    // TODO: method to insert the tree's initial values
    public static void insertValue(BinaryTree<Integer> tree) {
        System.out.print("Enter an insertion value: ");
        int value = new Scanner(System.in).nextInt();
        tree.insert(value);
        System.out.println("Insert another value? [y/n]");
        char choice = new Scanner(System.in).nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y') insertValue(tree);
    }

    // TODO: method to print the tree's orders
    public static void printTreeOrders(BinaryTree tree) {
        if(!tree.isEmpty()) {
            System.out.println("\nPREORDER");
            tree.printPreOrder();
            System.out.println("\nINORDER");
            tree.printInOrder();
            System.out.println("\nPOSTORDER:");
            tree.printPostOrder();
            System.out.println();
        } else {
            System.out.println("The tree is empty.");
        }
    }

    // TODO: method to print the tree's counts
    public static void printCount(BinaryTree tree) {
        System.out.printf("Size: %d%n", tree.size());
    }

    // TODO: method to remove the tree's values.
    public static void deleteValue(BinaryTree<Integer> tree) {
        System.out.println("Enter a deletion value: ");
        int value = new Scanner(System.in).nextInt();
        tree.remove(value);
        System.out.println("remove another value? [y/n]");
        char choice = new Scanner(System.in).nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y') deleteValue(tree);
    }

    // TODO: method to get the number of node's children
    public static void printChildren() {

    }

    public static void freeTree(BinaryTree tree) {
        tree.free();
    }

    public static Comparable findMax(BinaryTree tree) {
        return tree.max();
    }

    public static Comparable findMin(BinaryTree tree) {
        return tree.min();
    }
}
