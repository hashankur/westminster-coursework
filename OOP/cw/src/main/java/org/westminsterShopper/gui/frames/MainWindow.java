package org.westminsterShopper.gui.frames;

import org.westminsterShopper.Main;
import org.westminsterShopper.Util;
import org.westminsterShopper.data.Clothing;
import org.westminsterShopper.data.Electronics;
import org.westminsterShopper.data.Product;
import org.westminsterShopper.gui.components.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Westminster Shopping Manager");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);

        JPanel contentPane = new JPanel(new BorderLayout());

        // Selector
        JLabel comboLabel = new JLabel("Select Product Category");
        JComboBox<String> comboBox = new JComboBox<>(new String[] { "All", "Electronics", "Clothing" });

        // Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartWindow();
            }
        });
        buttonPanel.add(comboLabel);
        buttonPanel.add(comboBox);
        buttonPanel.add(shoppingCartBtn);

        // Table
        String[] columnNames = { "Product ID", "Name", "Category", "Price(£)", "Info" };
        ArrayList<Product> products = Main.products;
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

            JScrollPane scrollPane = new JScrollPane(new ProductTable(data, columnNames, comboBox, textArea));
            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, textArea);
            splitPane.setResizeWeight(0.8);
            contentPane.add(splitPane, BorderLayout.CENTER);
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
        contentPane.add(addToCartPanel, BorderLayout.SOUTH);

        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
