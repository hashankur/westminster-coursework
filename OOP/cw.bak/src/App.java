import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Product> products = new ArrayList<Product>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ShoppingManager manager = new WestminsterShoppingManager();
        int option = 0;

        while (true) {

            System.out.println("Welcome to Westminster Shopping Manager");
            System.out.println("Select an option:");
            System.out.println("\t1) Add product");
            System.out.println("\t2) Delete product");
            System.out.println("\t3) Print product list");
            System.out.println("\t4) Save to file");
            System.out.println("\t5) Read from file");
            System.out.println("\t6) Open GUI");
            System.out.println("\t0) Exit");

            System.out.print("Enter your choice: ");
            option = validate_input_int(input);

            System.out.println();

            switch (option) {
                case 0 -> System.exit(0);
                case 1 -> manager.addProduct(products);
                case 2 -> products = manager.deleteProduct(products); // Need string input
                case 3 -> manager.printProductList(products);
                case 4 -> manager.saveToFile(products);
                case 5 -> products = manager.readFromFile();
                case 6 -> manager.spawnMainWindow();
                default -> System.out.println("Invalid choice!");
            }
        }
        // input.close();
    }

    public static int validate_input_int(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            input.next(); // Discard the invalid input
        }
        return input.nextInt();
    }
}
