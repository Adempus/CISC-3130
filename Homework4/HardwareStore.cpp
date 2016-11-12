/**
 * Jeff Morin
 * CISC 3130
 * Homework #4
 * Fall 2016
 */

#include "HardwareStore.h"
#include <algorithm>

HardwareStore::HardwareStore() { }

/** @param   an integer of registers available at the hardware store. */
HardwareStore::HardwareStore(int numRegisters) :
        registerVector((unsigned) numRegisters) {
}

/** Adds a shopper to the shortest queue of shoppers in line at the available registers.
 *  @param shopper  a shopper object to be added to the shortest line. */
void HardwareStore::addShopperToLine(const Shopper& shopper) {
    // push a shopper to the shortest line in the vector using that index.
    registerVector[getShortestQueue()].push(shopper);
}

void HardwareStore::processShopper() {
    //queue<Shopper> *longestQueue = &registerVector[getLongestQueue()];
    while (!registerVector[getLongestQueue()].empty()) {
        adjustRevenue(registerVector[getLongestQueue()].front().amountOwed());
        registerVector[getLongestQueue()].pop();
    }
}

// TODO : Processes all shoppers still on queue in the vector.
void HardwareStore::checkoutAllShoppers() {
    for (int i = 0 ; i < registerVector.size() ; i++) {
        // check if i is the index of the largest queue in the vector.
        if(i == getLongestQueue()) {
            // if so, call processShopper() to process that queue.
            processShopper();
            continue;   // continue processing smaller queues.
        }
        adjustRevenue(registerVector[i].front().amountOwed());
        registerVector[i].pop();
    }
}
/** @returns the index of an empty queue in the regester vector.*/
int HardwareStore::getAvailEmpty() {
    return emptyLineIndex();
}

/** A visual of the shopping lines. */
void HardwareStore::printShoppingLines() {
    queue<Shopper> tempQueue;
    for (int i = 0 ; i < registerVector.size() ; i++) {
        tempQueue = registerVector[i];

        while (!tempQueue.empty()) {
            cout << tempQueue.front() << " | ";
            tempQueue.pop();
        }
        cout << endl;
    }
}

/** Inserts an item in the store's inventory.
 * @param item - the item to be added to the inventory.*/
void HardwareStore::stockInventory(Item& item) {
    inventory.push_back(item);
}

/** @returns The index to an empty queue in registerVector of shoppers.
 *           The size is returned if there are no empty queues. */
int HardwareStore::emptyLineIndex() {
    for (int i = 0 ; i < registerVector.size() ; i++) {
        if (registerVector[i].empty()) return i;
    }
    return (unsigned) registerVector.size();
}

/** Updates the total amount of money made by the hardware store.
 *  @param amount   the amount of money being added to the store's revenue. */
void HardwareStore::adjustRevenue(int amount) {
    revenue += (amount / 100);
}

/** @returns a vector to the HardwareStore's inventory */
vector<Item> HardwareStore::getInventory() {
    return inventory;
}

/** @returns total amount of money made by the hardware store. */
double HardwareStore::getTotalRevenue() {
    return revenue;
}

/** @returns The index of the largest shopper queue in 'registerVector'. */
int HardwareStore::getLongestQueue() {
    int longestIndex = 0;
    // store the size of the 0th queue in the register to start comparisons.
    int lineSize = (unsigned) registerVector[0].size();
    // Loop through registerVector to find the index of the longest queue.
    for(int i = 1 ; i < registerVector.size() ; i++) {
        if (lineSize < registerVector[i].size()) {
            lineSize = (unsigned) registerVector[i].size();
            longestIndex = i;
        }
    }
    return longestIndex;
}

int HardwareStore::getShortestQueue() {
    // start at the first line of shoppers to get the line with the least amount of shoppers.
    int shortestValue = (unsigned) registerVector[0].size();
    int shortestIndex = 0;
    // Check through each line to find the one with the least amount of shoppers (the smallest sized queue).
    for(int i = 0 ; i < registerVector.size() ; i++) {
        // if the current shortest line's size is greater than the next line's size,
        if(shortestValue > registerVector[i].size()) {
            // reassign the current shortest line with the newest shortest line value.
            shortestValue = (unsigned) registerVector[i].size();
            // store the index of the registerVector containing the shortest line.
            shortestIndex = i;
        }
    }
    return shortestIndex;
}

