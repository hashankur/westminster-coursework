public class Toy {
    private String name;
    private double price;
    private String category;
    private int stock;
    private int code;

    public Toy(String name, double price, String category, int stock, int code) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.code = code;
    }

    private void CreateToy(int no) {
        this.stock += no;
    }

    private void SellToy(int no) {
        this.stock -= no;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
