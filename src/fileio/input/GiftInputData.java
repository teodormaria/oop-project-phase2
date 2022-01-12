package fileio.input;

import enums.Category;

/**
 * Class that stores input information related to gifts
 */
public record GiftInputData(String productName, double price, Category category,
                            int quantity) {

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
