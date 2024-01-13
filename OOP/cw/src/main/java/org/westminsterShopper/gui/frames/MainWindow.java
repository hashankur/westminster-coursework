package org.westminsterShopper.gui.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.westminsterShopper.Util;
import org.westminsterShopper.cli.WestminsterShoppingManager;
import org.westminsterShopper.data.Clothing;
import org.westminsterShopper.data.Electronics;
import org.westminsterShopper.data.Product;
import org.westminsterShopper.data.ShoppingCart;
import org.westminsterShopper.gui.components.ProductTable;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Westminster Shopping Manager");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);

        JPanel contentPane = new JPanel(new BorderLayout());

        // Selector
        JLabel comboLabel = new JLabel("Select Product Category");
        JComboBox<String> comboBox = new JComboBox<>(new String[] { "All", "Electronics", "Clothing" });
        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(comboLabel);
        comboPanel.add(comboBox);

        // Shopping Cart Button
        JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ShoppingCart.cartSize() == 0) {
                    JOptionPane.showMessageDialog(
                            MainWindow.this,
                            "Shopping cart is empty!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new CartWindow();
            }
        });
        cartPanel.add(shoppingCartBtn);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(cartPanel);
        topPanel.add(comboPanel);

        // Add to cart Button
        JPanel addToCartPanel = new JPanel(new FlowLayout());
        JButton addToCartBtn = new JButton("Add to Shopping Cart");

        // Table
        String[] columnNames = { "Product ID", "Name", "Category", "Price(£)", "Info" };
        ArrayList<Product> products = WestminsterShoppingManager.products;
        if (!products.isEmpty()) {
            String[][] data = new String[products.size()][5];
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                data[i][0] = product.getProductID();
                data[i][1] = product.getProductName();
                data[i][2] = product.getClass().getSimpleName();
                data[i][3] = Double.toString(product.getPrice());
                if (products.get(i) instanceof Clothing clothing) {
                    data[i][4] = clothing.getSize() + ", " + clothing.getColour();
                } else if (products.get(i) instanceof Electronics electronics) {
                    data[i][4] = electronics.getBrand() + ", " + electronics.getWarranty() + " weeks warranty";
                }
            }

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            ProductTable table = new ProductTable(data, columnNames, comboBox, textArea);
            JScrollPane scrollPane = new JScrollPane(table);
            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, textArea);
            splitPane.setResizeWeight(0.6);
            contentPane.add(splitPane, BorderLayout.CENTER);

            addToCartBtn.addActionListener(new ActionListener() { // Add to cart
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(
                                MainWindow.this,
                                "No product selected!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String productID = (String) table.getValueAt(selectedRow, 0);
                    Product selectedProduct = table.getSelectedProduct(productID);
                    if (selectedProduct == null) {
                        return;
                    }

                    String quantityStr = JOptionPane.showInputDialog(
                            MainWindow.this,
                            "Enter Quantity",
                            "Quantity",
                            JOptionPane.QUESTION_MESSAGE);

                    try {
                        if (quantityStr == null) {
                            return;
                        }
                        int quantity = Integer.parseInt(quantityStr);

                        if (quantity <= 0 || quantity > selectedProduct.getAvailableItems()) {
                            throw new NumberFormatException();
                        }

                        ShoppingCart.addProduct(selectedProduct, quantity);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(
                                MainWindow.this,
                                "Invalid quantity!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            addToCartPanel.add(addToCartBtn);
        } else {
            JLabel isAvailable = new JLabel("No products available.");
            contentPane.add(isAvailable, BorderLayout.CENTER);
        }

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(addToCartPanel, BorderLayout.SOUTH);

        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
