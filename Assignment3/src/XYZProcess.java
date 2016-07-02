/**
 * Jeff Morin
 * 6/21/16
 * Assignment3
 */

import java.text.NumberFormat;
import java.io.File;
import java.util.*;

public class XYZProcess {
    private List<Widget> inventory;
    private List<Widget> soldWidgets;
    private List<Integer> discounts;
    private int counter;
    private double discount;
    private CSVModel records;
    private NumberFormat USD;

    public XYZProcess(File inputFile) {
        counter = 0;
        discount = 0;
        inventory = new LinkedList<>();
        discounts = new LinkedList<>();
        soldWidgets = new LinkedList<>();
        records = new CSVModel(inputFile);
        USD = NumberFormat.getCurrencyInstance(Locale.US);
    }

    protected void processCards() {
        for(Object[] tr : records.getModel()) {
            switch(tr[0].toString()) {
                case "R" :
                    inventory.add(
                            new Widget(Integer.parseInt(tr[1].toString()),
                            Double.parseDouble(tr[2].toString())));
                    break;
                case "S" :
                    checkInventory(Integer.parseInt(tr[1].toString()));
                    break;
                case "P" :
                    discounts.add(Integer.parseInt(tr[1].toString()));
                    break;
                default : System.err.println("Invalid record");
            }
        }
    }

    // searches through the inventory to find an adequate
    // stock of widgets to sell.
    protected void checkInventory(int requestedQty) {
        int adjustedQty = requestedQty;
        Iterator itr = inventory.iterator();

        try {
            for (Widget w : inventory) {
                // if the stock of widgets is more than the requested quantity.
                if (w.stockQty() > requestedQty) {
                    // reduce the stock of the current list item and ship
                    w.deductStock(requestedQty);
                    fillOrder(requestedQty, w.price());
                    break;
                } else {
                    // while the requested quantity is not satisfied...
                    while (itr.hasNext() && adjustedQty != 0) {
                        Widget widget = (Widget) itr.next();
                        if (widget.stockQty() > adjustedQty) {
                            // remove what's available (@ front of list) and
                            widget.deductStock(adjustedQty);
                            soldWidgets.add(
                                    new Widget(adjustedQty, widget.price()));
                            adjustedQty -= adjustedQty;
                        } else {
                            adjustedQty -= widget.stockQty();
                            soldWidgets.add(widget);
                            itr.remove();
                        }
                    }
                    fillOrder(requestedQty, soldWidgets);
                }
            }
        } catch (ConcurrentModificationException cme) {
            // no side effects. so fuck it.
        }
    }

    // overloaded method for a whole quantity of widgets.
    protected void fillOrder(int requestedQty, double price) {
        double total = requestedQty * price;
        System.out.printf("%n%d Widgets sold at %s each \tSales: %s%n",
                requestedQty, USD.format(price), USD.format(total));
        System.out.printf("Total Sales: %s%n",
                USD.format(applyDiscount(total)));
    }

    // overloaded method for a compound quantity of widgets.
    protected void fillOrder(int requestedQty, List<Widget> soldWidgets) {
        double total = 0.00;
        System.out.printf("%n%d Widgets Sold%n", requestedQty);
        // 	∀w ∈ Widget
        for(Widget w : soldWidgets) {
            // display widget qty, price per qty and sales total.
            System.out.printf("%d at %s each\t Sales: %s%n",
                    w.stockQty(), USD.format(w.price()),
                    USD.format(w.stockQty() * w.price()));
            // accumulate total by calculating the stocks and their prices.
            total += (w.stockQty() * w.price());
        }
        System.out.printf("Total sales: %s%n",
                USD.format(applyDiscount(total)));
        soldWidgets.clear();
    }

    private double applyDiscount(double total) {
        counter++;
        discount = 0;
        if(counter > 1) {
            counter = 0;
            if(discounts.size() > 0)
                discounts.remove(0);
        }
        if(discounts.size() > 0) {
            double percentOff = discounts.get(0);
            double rate = (percentOff / 100);
            discount = rate * total;
            System.out.printf("%-22s",  Integer.toString(
                    new Double(percentOff).intValue()) + "% discount");
        } else {
            counter = 0;
        }
        return total - discount;
    }
    public List<Widget> getInventory() {
        return inventory;
    }
}
