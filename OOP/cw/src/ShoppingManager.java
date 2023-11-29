import java.util.ArrayList;

public interface ShoppingManager {
    public void addProduct(ArrayList<Product> products);

    public void deleteProduct(String productID, ArrayList<Product> products);

    public void printProductList(ArrayList<Product> products);

    public void saveToFile(ArrayList<Product> products);

    public ArrayList<Product> readFromFile();

    public void deleteProduct(int validate_input_int);
}
