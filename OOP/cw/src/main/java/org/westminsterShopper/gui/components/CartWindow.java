package org.westminsterShopper.gui.components;

import org.westminsterShopper.Util;

import javax.swing.*;
import java.awt.*;

public class CartWindow extends JFrame {
    public CartWindow() {
        super("Shopping Cart");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500, 300);
        
        JPanel contentPane = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = { "Product", "Quantity", "Price" };
        Object[][] data = { { "1", "2", "3" }, { "6", "7", "8" } };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
