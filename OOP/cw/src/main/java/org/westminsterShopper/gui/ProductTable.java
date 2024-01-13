package org.westminsterShopper.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.westminsterShopper.cli.WestminsterShoppingManager;
import org.westminsterShopper.data.Product;

public class ProductTable extends JTable {
    public ProductTable(String[][] data, String[] columns) {
        super(data, columns);
    }

    public ProductTable(String[][] data, String[] columns, JComboBox<String> comboBox, JTextArea textArea) {
        super(new DefaultTableModel(data, columns));
        TableModel model = this.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        this.setRowSorter(sorter);
        this.setFillsViewportHeight(true);

        ListSelectionModel selectionModel = this.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() { // Listener for product selection
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (getSelectedRow() != -1) {
                        String productID = (String) getValueAt(getSelectedRow(), 0);
                        Product selectedProduct = getSelectedProduct(productID);
                        textArea.setText("Selected Product - Details\n" +
                                selectedProduct.toString() +
                                "\nAvailable Items: " +
                                selectedProduct.getAvailableItems());
                    } else {
                        textArea.setText(""); // Clear selected product details on product type change
                    }
                }
            }
        });

        comboBox.addActionListener(new ActionListener() { // Listener for product type change
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
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Product getSelectedProduct(String productID) {
        for (Product product : WestminsterShoppingManager.products) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }
}
