package org.westminsterShopper.gui.components;

import org.westminsterShopper.Main;
import org.westminsterShopper.Util;
import org.westminsterShopper.data.Clothing;
import org.westminsterShopper.data.Electronics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Product category
        // JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // JLabel categoryLabel = new JLabel("Select Product Category:");
        // categoryPanel.add(categoryLabel);

        // Table
        String[] columnNames = { "Product ID", "Name", "Category", "Price(£)", "Info" };
        if (!Main.products.isEmpty()) {
            Object[][] data = new Object[Main.products.size()][5];
            for (int i = 0; i < Main.products.size(); i++) {
                if (Main.products.get(i) instanceof Clothing clothing) {
                    data[i][0] = clothing.getProductID();
                    data[i][1] = clothing.getProductName();
                    data[i][2] = clothing.getClass().getSimpleName();
                    data[i][3] = clothing.getPrice();
                    data[i][4] = clothing.getSize() + ", " + clothing.getColour();
                } else if (Main.products.get(i) instanceof Electronics electronics) {
                    data[i][0] = electronics.getProductID();
                    data[i][1] = electronics.getProductName();
                    data[i][2] = electronics.getClass().getSimpleName();
                    data[i][3] = electronics.getPrice();
                    data[i][4] = electronics.getBrand() + ", " + electronics.getWarranty() + " weeks warranty";
                }
            }

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(tableModel);
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCategory = (String) comboBox.getSelectedItem();
                    RowFilter<TableModel, Object> rowFilter = null;
                    if (!selectedCategory.equals("All")) {
                        rowFilter = RowFilter.regexFilter("(?i)" + selectedCategory, 2); // Filter by category column
                    }
                    sorter.setRowFilter(rowFilter);
                }
            });
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
                    Main.products.get(0).toString());
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
        // contentPane.add(categoryPanel, BorderLayout.CENTER);
        contentPane.add(addToCartPanel, BorderLayout.SOUTH);
        
        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
