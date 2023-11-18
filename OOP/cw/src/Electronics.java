public class Electronics extends Product {
    private String brand;
    private int warranty;

    public Electronics(String productID, String productName, int price, String brand, int warranty) {
        super(productID, productName, price);
        this.brand = brand;
        this.warranty = warranty;
    }

    public String getBrand() {
        return this.brand;
    }

    public int getWarranty() {
        return this.warranty;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
}
