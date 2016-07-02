/**
 * Jeff Morin
 * Assignment3
 * 6/21/16
 */
public class Widget {
    private int quantity;
    private double price;
    protected enum Status { IN_STOCK, OUT_OF_STOCK }
    protected Status status = Status.IN_STOCK;

    public Widget(int amount, Double price) {
        this.quantity = amount;
        this.price = price;
    }

    public void deductStock(int amount) {
        if(status.equals(Status.IN_STOCK)) {
            this.quantity -= amount;
            if(amount <= 0)
                status = Status.OUT_OF_STOCK;
        }
    }

    public int stockQty() { return quantity; }
    public double price() {
        return price;
    }
}