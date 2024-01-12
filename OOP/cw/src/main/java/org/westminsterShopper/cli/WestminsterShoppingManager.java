package org.westminsterShopper.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.westminsterShopper.Util;
import org.westminsterShopper.data.Clothing;
import org.westminsterShopper.data.Electronics;
import org.westminsterShopper.data.Product;

public class WestminsterShoppingManager implements ShoppingManager {

    static final String FILE_PATH_PRODUCTS = "products.txt";
    static Scanner input = new Scanner(System.in);

    public void addProduct(ArrayList<Product> products) {
        System.out.println("Select product type:\n\t1) Clothing\n\t2) Electronics");

        System.out.print("Enter your choice: ");
        int productType = Util.validate_input_int(input);
        System.out.println();

        if (productType == 1 || productType == 2) {
            System.out.print("Enter product ID: ");
            String productID = input.next();
            System.out.print("Enter product name: ");
            String productName = input.next();
            System.out.print("Enter price: ");
            int price = Util.validate_input_int(input);

            if (productType == 1) {
                System.out.print("Enter size: ");
                String size = input.next();
                System.out.print("Enter color: ");
                String color = input.next();
                products.add(new Clothing(productID, productName, price, size, color));
            } else {
                System.out.print("Enter brand: ");
                String brand = input.next();
                System.out.print("Enter warranty: ");
                int warranty = Util.validate_input_int(input);
                products.add(new Electronics(productID, productName, price, brand, warranty));
            }
            Util.coloriseTerminalText("\nProduct added.", false);
        } else {
            Util.coloriseTerminalText("Invalid choice!", true);
        }

        System.out.print("Add product? (Back to menu: q): ");
        String choice = input.next();
        if (!choice.equals("q"))
            addProduct(products);
    }

    public ArrayList<Product> deleteProduct(ArrayList<Product> products) {
        System.out.print("Enter product ID: ");
        String id = input.next();
        boolean found = false;

        for (Product product : products) {
            if (product.getProductID().equals(id)) {
                products.remove(product);
                found = true;
                break;
            }
        }

        if (!found) {
            Util.coloriseTerminalText("Product not found.", true);
        }

        System.out.print("Delete another product? (Back to menu: q): ");
        String choice = input.next();
        if (!choice.equals("q"))
            deleteProduct(products);

        return products;
    }

    public void printProductList(ArrayList<Product> products) {
        if (products.isEmpty()) {
            Util.coloriseTerminalText("No products available.", true);
        } else {
            // sort products alphabetically
            // Collections.sort???
            // products.sort((product1, product2) ->
            // product1.getProductID().compareTo(product2.getProductID()));
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
                String className = product.getClass().getSimpleName();
                String productID = product.getProductID();
                String productName = product.getProductName();
                double price = product.getPrice();
                String additionalInfo = "";

                if (product instanceof Clothing clothing) {
                    additionalInfo = String.format("%s;%s",
                            clothing.getSize(),
                            clothing.getColour());
                } else if (product instanceof Electronics electronics) {
                    additionalInfo = String.format("%s;%s",
                            electronics.getBrand(),
                            electronics.getWarranty());
                }

                writeFile.write(String.format("%s;%s;%s;%s;%s\n",
                        className,
                        productID,
                        productName,
                        price,
                        additionalInfo));
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
                    fileContent.add(new Clothing(
                            line[1],
                            line[2],
                            Double.parseDouble(line[3]),
                            line[4],
                            line[5]));
                } else if (line[0].equals("Electronics")) {
                    fileContent.add(new Electronics(
                            line[1],
                            line[2],
                            Double.parseDouble(line[3]),
                            line[4],
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
