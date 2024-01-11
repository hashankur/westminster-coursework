package org.westminsterShopper.gui.frames;

import org.westminsterShopper.Util;
import org.westminsterShopper.gui.components.ProductTable;

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
        String[][] data = { { "1", "2", "3" }, { "6", "7", "8" } };
        JScrollPane scrollPane = new JScrollPane(new ProductTable(data, columnNames));

        contentPane.add(scrollPane, BorderLayout.CENTER);
        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
