package org.westminsterShopper.data;

public abstract class Product {
    private String productID;
    private String productName;
    private int availableItems;
    private double price;

    public Product(String productID, String productName, int availableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + this.productID + "\n" +
                "Category: " + this.getClass().getSimpleName() + "\n" +
                "Name: " + this.productName + "\n" +
                "Price: " + this.price;
    }
}
