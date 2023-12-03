import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

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
                            clothing.getClass().getSimpleName(),
                            clothing.getProductID(),
                            clothing.getProductName(),
                            clothing.getPrice(),
                            clothing.getSize(),
                            clothing.getColour()));
                } else if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
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
                if (line[0].equals("Clothing")) {
                    fileContent.add(new Clothing(line[1], line[2], Integer.parseInt(line[3]), line[4], line[5]));
                } else if (line[0].equals("Electronics")) {
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

    public void spawnMainWindow() {
        JFrame frame = new JFrame("Westminster Shopping Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        JPanel contentPane = new JPanel(new BorderLayout());

        // Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnCartWindow();
            }
        });
        buttonPanel.add(shoppingCartBtn);

        // Product category
        // JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // JLabel categoryLabel = new JLabel("Select Product Category:");
        // categoryPanel.add(categoryLabel);

        // Table
        String columnNames[] = { "Product ID", "Name", "Category", "Price(£)", "Info" };
        if (App.products.size() != 0) {
            Object[][] data = new Object[App.products.size()][5];
            for (int i = 0; i < App.products.size(); i++) {
                if (App.products.get(i) instanceof Clothing) {
                    Clothing clothing = (Clothing) App.products.get(i);
                    data[i][0] = clothing.getProductID();
                    data[i][1] = clothing.getProductName();
                    data[i][2] = clothing.getClass().getSimpleName();
                    data[i][3] = clothing.getPrice();
                    data[i][4] = clothing.getSize() + ", " + clothing.getColour();
                } else if (App.products.get(i) instanceof Electronics) {
                    Electronics electronics = (Electronics) App.products.get(i);
                    data[i][0] = electronics.getProductID();
                    data[i][1] = electronics.getProductName();
                    data[i][2] = electronics.getClass().getSimpleName();
                    data[i][3] = electronics.getPrice();
                    data[i][4] = electronics.getBrand() + ", " + electronics.getWarranty();
                }
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            contentPane.add(scrollPane, BorderLayout.CENTER);

            String details = "";
            JTextArea textArea = new JTextArea(details);
            // if (table.getSelectedRow() != -1) {
            // textArea.setText(App.products.get(table.getSelectedRow()).toString());
            // }
            details = String.format(
                    "Selected Product - Details\n%s",
                    App.products.get(0).toString());
            textArea.setEditable(false);
            contentPane.add(textArea, BorderLayout.WEST);
        } else {
            JTextArea textArea = new JTextArea("No products available.");
            textArea.setEditable(false);
            contentPane.add(textArea, BorderLayout.CENTER);
        }

        // Add to cart
        JPanel addToCartPanel = new JPanel(new FlowLayout());
        JButton addToCartBtn = new JButton("Add to Shopping Cart");
        addToCartPanel.add(addToCartBtn);

        contentPane.add(buttonPanel, BorderLayout.NORTH);
        // contentPane.add(categoryPanel, BorderLayout.NORTH);
        contentPane.add(addToCartPanel, BorderLayout.SOUTH);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        centreWindow(frame);
    }

    private void spawnCartWindow() {
        JFrame frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        JPanel contentPane = new JPanel(new BorderLayout());

        // Table
        String columnNames[] = { "Product", "Quantity", "Price" };
        Object[][] data = { { "1", "2", "3" }, { "6", "7", "8" } };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        centreWindow(frame);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
