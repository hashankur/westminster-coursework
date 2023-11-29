import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) throws Exception {
        ShoppingManager manager = new WestminsterShoppingManager();
        Scanner input = new Scanner(System.in);
        int option = 0;

        boolean runProgram = true;
        while (runProgram) {

            System.out.println("Welcome to Westminster Shopping Manager");
            System.out.println("Select an option:");
            System.out.println("\t1) Add product");
            System.out.println("\t2) Delete product");
            System.out.println("\t3) Print product list");
            System.out.println("\t4) Save to file");
            System.out.println("\t5) Read from file");
            System.out.println("\t0) Exit");
            System.out.print("Enter your choice: ");

            // if (input.hasNextInt()) {
            option = validate_input_int(input);
            // System.out.println();
            // // rest of your code
            // } else {
            // System.out.println("Invalid input. Please enter an integer.");
            // }

            System.out.println();

            switch (option) {
                case 0 -> runProgram = false;
                case 1 -> manager.addProduct(products);
                case 2 -> {
                    System.out.print("Enter product ID: ");
                    // manager.deleteProduct(validate_input_int(input)); // Need string input
                }
                case 3 -> manager.printProductList(products);
                case 4 -> manager.saveToFile(products);
                case 5 -> products = manager.readFromFile();
                // case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static int validate_input_int(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            input.next(); // Discard the invalid input
        }
        return input.nextInt();
    }

}
