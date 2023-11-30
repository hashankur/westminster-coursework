import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class WestminsterShoppingManager implements ShoppingManager {

    static final String FILE_PATH = "products.txt";

    public void addProduct(ArrayList<Product> products) {
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
        int price = App.validate_input_int(input);

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
                int warranty = App.validate_input_int(input);

                products.add(new Electronics(productID, productName, price, brand, warranty));
            }
            default -> System.out.println("Invalid choice!");
        }

        // input.close();
    }

    public void deleteProduct(String productID, ArrayList<Product> products) {
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

    public void printProductList(ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("No products in list");
        } else {
            // sort products alphabetically
            products.sort((product1, product2) -> product1.getProductID().compareTo(product2.getProductID()));
            for (Product product : products) {
                System.out.println("-".repeat(50));
                System.out.println(product);
            }
            System.out.println("-".repeat(50));
        }
    }

    public void saveToFile(ArrayList<Product> products) {
        boolean fileCreated;
        File file;
        try {
            file = new File(FILE_PATH);
            fileCreated = file.createNewFile();

            if (fileCreated) {
                System.out.println("File created: " + file.getName());
            }

            if (file.exists()) {
                System.out.println("File saved.");
            }

            FileWriter writeFile = new FileWriter(FILE_PATH);
            for (Product product : products) {
                if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    writeFile.write(String.format(
                            "%s;%s;%s;%s;%s;%s\n",
                            'C', // 'C' for Clothing
                            clothing.getProductID(),
                            clothing.getProductName(),
                            clothing.getPrice(),
                            clothing.getSize(),
                            clothing.getColour()));
                } else if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    writeFile.write(String.format(
                            "%s;%s;%s;%s;%s;%s\n",
                            'E', // 'E' for Electronics
                            electronics.getProductID(),
                            electronics.getProductName(),
                            electronics.getPrice(),
                            electronics.getBrand(),
                            electronics.getWarranty()));
                }
            }
            writeFile.close();

        } catch (IOException e) {
            System.out.println("Error creating file.");
        }

    }

    public ArrayList<Product> readFromFile() {
        ArrayList<Product> fileContent = new ArrayList<>();

        try {
            Scanner input = new Scanner(new File(FILE_PATH));

            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(";");
                if (line[0].equals("C")) {
                    fileContent.add(new Clothing(line[1], line[2], Integer.parseInt(line[3]), line[4], line[5]));
                } else if (line[0].equals("E")) {
                    fileContent.add(new Electronics(line[1], line[2], Integer.parseInt(line[3]), line[4],
                            Integer.parseInt(line[5])));
                }

            }
            input.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        System.out.println("File loaded.\n");
        return fileContent;
    }
}
