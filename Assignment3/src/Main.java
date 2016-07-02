/**
 * Jeff Morin
 * Assignment3
 * 6/19/16
 */

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Specify input file: ");
        XYZProcess process = new XYZProcess(new File(scan.nextLine()));
        process.processCards();
        System.out.println("\n" + process.getInventory().size() +
                " widgets left in stock: ");
        for(Widget w : process.getInventory()) {
            System.out.printf("Quantity: %d \tPrice: $%.2f%n",
                    w.stockQty(), w.price());
        }
    }
}