/**
 * Jeff Morin
 * CISC 3130
 * Homework #4
 * Fall 2016
 */

#include <sstream>
#include <iomanip>
#include "Shopper.h"

Shopper::Shopper() { }

Shopper::Shopper(const string& firstName, const string& lastName) :
        firstName(firstName), lastName(lastName), receipt("Item\t\tPrice\n") { }

/**
 * Recursively adds an item to the Shopper's cart while quantity > 1,
 * otherwise a single item is added to the cart.
 *  @param item         An 'Item' reference object being added to cart.
 *  @param quantity     The number of times that item is added to cart.
 */
void Shopper::addItemToCart(const Item& item, int quantity) {
    cart.addItem(item);
    if (quantity > 1) addItemToCart(item, quantity-1);
}

/** The amount of money owed in cents by the shopper. */
int Shopper::amountOwed() {
    double taxRate = 8.875 / 100;
    double dollarAmt = round((cart.grandTotal() + (cart.grandTotal() * taxRate)));
    int centAmount = (int) (dollarAmt * 100);

    return centAmount;
}

int Shopper::numItems() {
    return cart.numItems();
}

string Shopper::getReceipt() {
    for (int i = 0 ; i < cart.getItems().size() ; i++) {
       receipt.append("\n"+cart.getItems()[i].getName()+"\t\t$"+
                              to_string(cart.getItems()[i].getPrice()));
    }
    receipt.append("\nTotal:\t$"+to_string(cart.grandTotal()));

    return receipt;
}

ostream& operator<<(ostream& os, const Shopper& shopper) {
    Shopper s = shopper;
    os << shopper.firstName << " " << shopper.lastName
       << " Num items: " << s.numItems();
    return os;
}