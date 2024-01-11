package org.westminsterShopper.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.westminsterShopper.Main;
import org.westminsterShopper.data.Clothing;
import org.westminsterShopper.data.Electronics;
import org.westminsterShopper.data.Product;

public class WestminsterShoppingManager implements ShoppingManager {

    static final String FILE_PATH_PRODUCTS = "products.txt";
    static final String FILE_PATH_USERS = "users.txt";

    // static enum FILE_PATH {
    // PRODUCTS("products.txt"), USERS("users.txt");
    // }

    public void addProduct(ArrayList<Product> products) {
        System.out.println("Select product type:");
        System.out.println("\t1) Clothing");
        System.out.println("\t2) Electronics");

        System.out.print("Enter your choice: ");
        int choice = Main.input.nextInt();
        System.out.println();

        System.out.print("Enter product ID: ");
        String productID = Main.input.next();
        System.out.print("Enter product name: ");
        String productName = Main.input.next();
        System.out.print("Enter price: ");
        int price = Main.validate_input_int(Main.input);

        switch (choice) {
            case 1 -> {
                System.out.print("Enter size: ");
                String size = Main.input.next();
                System.out.print("Enter color: ");
                String color = Main.input.next();

                products.add(new Clothing(productID, productName, price, size, color));
            }
            case 2 -> {
                System.out.print("Enter brand: ");
                String brand = Main.input.next();
                System.out.print("Enter warranty: ");
                int warranty = Main.validate_input_int(Main.input);

                products.add(new Electronics(productID, productName, price, brand, warranty));
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    public ArrayList<Product> deleteProduct(ArrayList<Product> products) {
        System.out.print("Enter product ID: ");
        String id = Main.input.next();
        boolean found = false;

        for (Product product : products) {
            if (product.getProductID().equals(id)) {
                products.remove(product);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }

        System.out.print("Delete another product? (Back to menu: q): ");
        String choice = Main.input.next();
        if (!choice.equals("q"))
            deleteProduct(products);

        return products;
    }

    public void printProductList(ArrayList<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            // sort products alphabetically
            // Collections.sort???
            // products.sort((product1, product2) -> product1.getProductID().compareTo(product2.getProductID()));
            Collections.sort(products, Comparator.comparing(Product::getProductID));

            for (Product product : products) {
                System.out.println("-".repeat(50));
                System.out.println(product);
            }
            System.out.println("-".repeat(50));
        }
        System.out.println();
    }

    public void saveToFile(ArrayList<Product> products) {
        boolean fileCreated;
        File file;
        try (FileWriter writeFile = new FileWriter(FILE_PATH_PRODUCTS)) {
            file = new File(FILE_PATH_PRODUCTS);
            fileCreated = file.createNewFile();

            if (fileCreated) {
                System.out.println("File created: " + file.getName());
            }

            if (file.exists()) {
                System.out.println("File saved.");
            }

            for (Product product : products) {
                if (product instanceof Clothing clothing) {
                    writeFile.write(String.format(
                            "%s;%s;%s;%s;%s;%s\n",
                            clothing.getClass().getSimpleName(),
                            clothing.getProductID(),
                            clothing.getProductName(),
                            clothing.getPrice(),
                            clothing.getSize(),
                            clothing.getColour()));
                } else if (product instanceof Electronics electronics) {
                    writeFile.write(String.format(
                            "%s;%s;%s;%s;%s;%s\n",
                            electronics.getClass().getSimpleName(),
                            electronics.getProductID(),
                            electronics.getProductName(),
                            electronics.getPrice(),
                            electronics.getBrand(),
                            electronics.getWarranty()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    public ArrayList<Product> readFromFile() {
        ArrayList<Product> fileContent = new ArrayList<>();
            try (Scanner input = new Scanner(new File(FILE_PATH_PRODUCTS))) {
                while (input.hasNextLine()) {
                    String[] line = input.nextLine().split(";");
                    if (line[0].equals("Clothing")) {
                        fileContent.add(new Clothing(line[1], line[2], Double.parseDouble(line[3]), line[4], line[5]));
                    } else if (line[0].equals("Electronics")) {
                        fileContent.add(new Electronics(line[1], line[2], Double.parseDouble(line[3]), line[4],
                                Integer.parseInt(line[5])));
                    }
                }
            } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        System.out.println("File loaded.\n");
        return fileContent;
    }
}
