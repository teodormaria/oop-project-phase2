package gifts;

import enums.Category;
import fileio.input.GiftInputData;

/**
 * Class that retains information about a gift
 */
public final class Gift implements Comparable<Gift> {
    /**
     * Name of gift
     */
    private final String productName;
    /**
     * Gift price
     */
    private final double price;
    /**
     * Gift category
     */
    private final Category category;
    /**
     * Quantity of gifts available
     */
    private int quantity;

    public Gift(final GiftInputData input) {
        this.productName = input.getProductName();
        this.price = input.getPrice();
        this.category = input.getCategory();
        this.quantity = input.getQuantity();
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void wasGiven() {
        if (this.quantity != 0) {
            this.quantity--;
        }
    }

    @Override
    public int compareTo(final Gift gift) {
        if (price == gift.getPrice()) {
            return productName.compareTo(gift.getProductName());
        } else {
            return Double.compare(price, gift.getPrice());
        }
    }
}
