package org.westminsterShopper;

import java.util.ArrayList;
import java.util.Scanner;

import org.westminsterShopper.cli.ShoppingManager;
import org.westminsterShopper.cli.WestminsterShoppingManager;
import org.westminsterShopper.data.Product;
import org.westminsterShopper.gui.frames.MainWindow;

public class Main {

    public static ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) {
        ShoppingManager manager = new WestminsterShoppingManager();
        int option = 0;
        String[] options = {
                "Add product",
                "Delete product",
                "Print product list",
                "Save to file",
                "Read from file",
                "Open GUI",
        };

        System.out.println("=".repeat(50));
        System.out.println("Welcome to Westminster Shopping Manager");
        products = manager.readFromFile(); // Load products from file
        System.out.println("=".repeat(50));

        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                // Menu

                System.out.println("Select an option:");
                for (int i = 0; i < options.length; i++) {
                    System.out.println("\t" + (i + 1) + ") " + options[i]);
                }
                System.out.println("\t0) Exit");

                System.out.print("Enter your choice: ");
                option = Util.validate_input_int(input);
                System.out.println();

                switch (option) {
                    case 0 -> System.exit(0);
                    case 1 -> manager.addProduct(products);
                    case 2 -> products = manager.deleteProduct(products);
                    case 3 -> manager.printProductList(products);
                    case 4 -> manager.saveToFile(products);
                    case 5 -> products = manager.readFromFile();
                    case 6 -> new MainWindow();
                    default -> System.out.println("Invalid choice!");
                }
                System.out.println("=".repeat(50));
            }
        }
    }
}