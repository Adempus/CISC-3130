/**
 * Jeff Morin
 * CISC 3130
 * Homework #4
 * Fall 2016
 */

#include <iostream>
#include <fstream>
#include <sstream>
#include "Item.h"
#include "HardwareStore.h"

using namespace std;

HardwareStore hardwareStore;
vector<Shopper> shoppers;
void readInventory(string);
void readEvents(string);

int main() {
    cout << "Enter inventory_file.txt file path: " << endl;
    string iPath, ePath;
    cin >> iPath;
    readInventory(iPath);
    cout << "Enter event file path: " << endl;
    cin >> ePath;
    readEvents(ePath);

    /*
    for(int i = 0 ; i < hardwareStore.getInventory().size() ; i++) {
        cout << hardwareStore.getInventory()[i] << endl;
    }
     */

    Item item("A", 60.00);
    Item item2("B", 50.00);
    Item item3("C", 23.49);
    Item item4("D", 5.50);
    Item item5("E", 2.00);

    Shopper shopper0("s1", "xxo");
    shopper0.addItemToCart(item5);

    Shopper shopper1("s2", "xox");
    shopper1.addItemToCart(item, 3);
    shopper1.addItemToCart(item3);

    Shopper shopper2("s3", "oxo");
    shopper2.addItemToCart(item2, 5) ;
    shopper2.addItemToCart(item5);

    Shopper shopper3("s4", "oox");
    shopper3.addItemToCart(item4, 2);
    shopper3.addItemToCart(item, 3);
    shopper3.addItemToCart(item2, 6);

    HardwareStore hs(3);

    hs.addShopperToLine(shopper3);
    hs.addShopperToLine(shopper0);
    hs.addShopperToLine(shopper2);
    hs.addShopperToLine(shopper1);

    hs.printShoppingLines();

    cout << shopper0.getReceipt() << endl;
    cout << shopper1.getReceipt() << endl;
    cout << shopper2.getReceipt() << endl;
    cout << shopper3.getReceipt() << endl;

    return 0;
}

/**
 * This function does the following:
 *      1. Reads text from an event file.
 *      2. Parses items along with their quantity values.
 *      3. Inserts a quantity of items into a shopper's cart.
 *      4. pushes that shopper onto the shopper vector.
 */
void readEvents (string path) {
    string data, parsedDatum;
    ifstream inputFile(path.c_str());
    if (inputFile.fail())  {
        cerr << "File not found. " << endl;
        return;
    }

    while (getline(inputFile, data, '\n')) {
        if (data.at(0) == 'S') {
            stringstream ss;
            ss.str(data);
            vector<pair<string, int>> items;
            /* j "indexes" every 2nd comma separated element (starting from the 3rd), in an 'S' line,
             *  to pair with its following quantity value, indexed by i.
                     S, 3, grill, 1, hammer, 3, wrench, 5
                j-->        |2|       |4|        |6|
               i accesses the the vector to initialize an item with its respective quantity.
                     S, 3, grill, 1, hammer, 3, wrench, 5
                i-->             |3|        |5|        |7|   */
            for (int i = 0, j = 0 ; getline(ss, parsedDatum, ',') ; j++) {
                if (parsedDatum == "S") continue; // skip the 'S'

                if (j % 2 == 0) {
                    items.push_back(pair<string, int>(parsedDatum, 0));
                } else if (j > 1){
                    items[i].second = stoi(parsedDatum);
                    Shopper s("AA", "BB");
                    s.addItemToCart(items[i].first, items[i].second);
                    shoppers.push_back(s);
                    i++;
                }
            }
            items.clear();
        }
    }
}

void readInventory (string path) {
    string data;
    string parsedDatum;
    string dataArr[2];
    ifstream inputFile(path.c_str());
    if (inputFile.fail())  {
        cerr << "File not found. " << endl;
        return;
    }

    while(getline(inputFile, data, '\n')) {
        stringstream ss;
        ss.str(data);
        for(int i = 0 ; getline(ss, parsedDatum, ',') ; i++) {
            dataArr[i] = parsedDatum;
        }
        Item newItem(dataArr[0], atof(dataArr[1].c_str()));
        hardwareStore.stockInventory(newItem);
    }
}