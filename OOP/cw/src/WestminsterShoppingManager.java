import java.util.ArrayList;
import java.util.Scanner;

class WestminsterShoppingManager implements ShoppingManager {

    ArrayList<Product> products = new ArrayList<Product>();

    public void addProduct() {
        System.out.println("Select product type:");
        System.out.println("\t1) Clothing");
        System.out.println("\t2) Electronics");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        System.out.println();

        System.out.print("Enter product ID: ");
        String productID = input.next();
        System.out.print("Enter product name: ");
        String productName = input.next();
        System.out.print("Enter price: ");
        int price = input.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter size: ");
                String size = input.next();
                System.out.print("Enter color: ");
                String color = input.next();

                products.add(new Clothing(productID, productName, price, size, color));
            }
            case 2 -> {
                System.out.print("Enter brand: ");
                String brand = input.next();
                System.out.print("Enter warranty: ");
                int warranty = input.nextInt();

                products.add(new Electronics(productID, productName, price, brand, warranty));
            }
            default -> System.out.println("Invalid choice!");
        }

        input.close();
    }

    public void deleteProduct(String productID) {
        System.out.println("Enter product ID: ");
        Scanner input = new Scanner(System.in);
        String id = input.next();

        for (Product product : products) {
            if (product.getProductID().equals(id)) {
                products.remove(product);
                break;
            }
        }

        input.close();
    }

    public void printProductList() {
        // sort products alphabetically
        products.sort((product1, product2) -> product1.getProductName().compareTo(product2.getProductName()));
        for (Product product : products) {
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product name: " + product.getProductName());
            System.out.println("Available items: " + product.getAvailableItems());
            System.out.println("Price: " + product.getPrice());
        }
    }

    public void saveToFile() {

    }

}
