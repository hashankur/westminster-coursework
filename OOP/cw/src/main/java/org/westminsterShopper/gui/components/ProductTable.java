package org.westminsterShopper.gui.components;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.westminsterShopper.Main;
import org.westminsterShopper.data.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductTable extends JTable {
    public ProductTable(String[][] data, String[] columns, JComboBox<String> comboBox, JTextArea textArea) {
        super(new DefaultTableModel(data, columns));
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.getModel());
        this.setRowSorter(sorter);
        this.setFillsViewportHeight(true);
        ListSelectionModel selectionModel = this.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    System.out.println(getValueAt(getSelectedRow(), 0));
                    if (getSelectedRow() != -1) {
                        textArea.setText("Selected Product - Details\n" + getSelectedProductID((String) getValueAt(getSelectedRow(), 0)).toString());
                    } else {
                        textArea.setText(""); // ???
                    }
                }
            }
        });

        System.out.println(this.getSelectedRow());

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
    }

    public ProductTable(String[][] data, String[] columns) {
        super(data, columns);
    }

    public Product getSelectedProductID(String productID) {
        return Main.products.stream().filter(product -> product.getProductID().equals(productID)).findFirst()
                .orElse(null);
    }
}
