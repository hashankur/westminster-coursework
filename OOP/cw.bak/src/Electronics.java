public class Electronics extends Product {
    private String brand;
    private int warranty;

    public Electronics(String productID, String productName, double d, String brand, int warranty) {
        super(productID, productName, d);
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

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Brand: " + this.brand + "\n" +
                "Warranty: " + this.warranty;
    }
}
