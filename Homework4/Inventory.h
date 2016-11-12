/**
 * Jeff Morin
 * CISC 3130
 * Homework #4
 * Fall 2016
 *
 */

#ifndef HOMEWORK4_INVENTORY_H
#define HOMEWORK4_INVENTORY_H

#include <vector>
#include <stack>
#include <string>
#include "Item.h"


using namespace std;

class Inventory {
public :

    Inventory() { }

    template <size_t length>
    Inventory(const Item (&itemsArray)[length] ) :
            stockedItems(itemsArray, &itemsArray[length] ) { }

private :
    vector<stack<Item>> stockedItems;

};

#endif //HOMEWORK4_INVENTORY_H
