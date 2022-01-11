package fileio.input;

import enums.Category;

/**
 * Class that stores input information related to gifts
 */
public final class GiftInputData {
    /**
     * Gift Name
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
    private final int quantity;

    public GiftInputData(final String productName, final double price, final Category category,
                         final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
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
}
