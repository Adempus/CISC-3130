/**
 * Jeff Morin
 * CISC 3130
 * Homework #4
 * Fall 2016
 */

#ifndef HOMEWORK4_HARDWARESTORE_H
#define HOMEWORK4_HARDWARESTORE_H

#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include "Item.h"
#include "Shopper.h"

class HardwareStore {
public :
    HardwareStore();
    HardwareStore(int);
    void addShopperToLine(const Shopper&);
    void processShopper();
    void checkoutAllShoppers();
    int getAvailEmpty();
    void printShoppingLines();
    double getTotalRevenue();
    void stockInventory(Item&);
    vector<Item> getInventory();

private :
    double                  revenue;
    vector<Item>            inventory;
    vector<queue<Shopper>>  registerVector;

    int emptyLineIndex();
    void adjustRevenue(int);
    int getLongestQueue();
    int getShortestQueue();
};

#endif //HOMEWORK4_HARDWARESTORE_H