public class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(String productID, String productName, int price, String size, String colour) {
        super(productID, productName, price);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return this.size;
    }

    public String getColour() {
        return this.colour;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Size: " + this.size + "\n" +
                "Colour: " + this.colour;
    }
}
