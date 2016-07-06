/**
 * Jeff Morin
 * Assignment 4
 * CISC 3130
 * 6/26/16
 *
 */

import Tree.BinaryTree;
import Tree.Node;

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

        if(treeDataModel.getModel() == null) {
            System.exit(0);
        }

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
        selectionLoop: while(true)
        {
            System.out.println("\nView data from which set? ");
            int set = new Scanner(System.in).nextInt();

            operationLoop: while(true)
            {
                System.out.println("\nPerform an operation on set " + set + ": " +
                        "\n1. Print orders\t 2. Insert Data" + "\n3. Delete Data\t 4. Count nodes"
                        + "\n5. Get max\t 6. Get min" + "\n7. Free tree\t 8. See children."
                        + "\n9. Choose another set \t 0. Exit.");
                int operation = new Scanner(System.in).nextInt();
                switch (operation) {
                    case 1 : printTreeOrders(forest[set-1]); break;
                    case 2 : insertValue(forest[set-1]); break;
                    case 3 : deleteValue(forest[set-1]); break;
                    case 4 : printCount(forest[set-1]); break;
                    case 5 : findMax(forest[set-1]); break;
                    case 6 : findMin(forest[set-1]); break;
                    case 7 : freeTree(forest[set-1]); break;
                    case 8 : printChildren(forest[set-1]); break;
                    case 9 : break operationLoop;
                    default : break selectionLoop;
                }
            }
        }
    }

    // method to parse tree input data into an Integer array
    public static Integer[] parseData(Object[] data) {
        Integer[] insertData = new Integer[data.length];
        for(int i = 0 ; i < data.length ; i++)
            insertData[i] = Integer.parseInt(data[i].toString());

        return insertData;
    }

    // method to initialize tree with data
    public static BinaryTree<Integer> createTree(Integer[] data) {
        BinaryTree<Integer> tree = new BinaryTree<>(data[0]);
        tree.insert(data);
        return tree;
    }

    // method to insert the tree's initial values
    public static <T> void insertValue(BinaryTree tree) {
        System.out.print("Enter an insertion value: ");
        int value = new Scanner(System.in).nextInt();
        tree.insert(value);
        System.out.println("Insert another value? [y/n]");
        char choice = new Scanner(System.in).nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y') insertValue(tree);
    }

    // method to print the tree's orders
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

    // method to print the tree's counts
    public static void printCount(BinaryTree tree) {
        System.out.printf("Size: %d%n", tree.size());
    }

    // method to remove the tree's values.
    public static void deleteValue(BinaryTree tree) {
        System.out.println("Enter a deletion value: ");
        int value = new Scanner(System.in).nextInt();
        tree.remove(value);
        System.out.println("remove another value? [y/n]");
        char choice = new Scanner(System.in).nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y') deleteValue(tree);
    }

    // method to get the number of children a node contains.
    public static void printChildren(BinaryTree tree) {
        System.out.println("Print children for which value? ");
        int choice = new Scanner(System.in).nextInt();
        System.out.println("Children: " + tree.getChildren(choice));
        Node<Integer> foundNode = tree.find(choice);
        if (foundNode.getLeftChild() != null)
            System.out.println("Left = " + foundNode.getLeftChild().getDatum());
        if (foundNode.getRightChild() != null)
            System.out.println("Right = " + foundNode.getRightChild().getDatum());
        System.out.println("Count children for another node? [y/n]");
        char again = new Scanner(System.in).nextLine().charAt(0);
        if(again == 'y' || again == 'Y') printChildren(tree);
    }

    public static void freeTree(BinaryTree tree) {
        tree.free();
    }
    public static void findMax(BinaryTree tree) {
        System.out.println("Max: " + tree.max());
    }
    public static void findMin(BinaryTree tree) {
        System.out.println("Min: " + tree.min());
    }
}
