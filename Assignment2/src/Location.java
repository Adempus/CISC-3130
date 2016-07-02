/**
 * Jeff Morin
 * Assignment2
 * 6/11/16
 */


 /** Location interface contains an enum of cities to be applied for an
     implementing facility; i.e. Warehouse. Also contains an abstract method to
     retrieve the location.
  */
public interface Location {
    enum City { NY, LA, CHI, HOU, MIA }
    Location.City getLocation();
}