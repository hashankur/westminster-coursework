package org.westminsterShopper.gui.frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.westminsterShopper.Util;
import org.westminsterShopper.data.Product;
import org.westminsterShopper.data.ShoppingCart;
import org.westminsterShopper.gui.components.ProductTable;

public class CartWindow extends JFrame {
    public CartWindow() {
        super("Shopping Cart");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500, 300);

        JPanel contentPane = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = { "Product", "Quantity", "Price" };
        String[][] data = new String[ShoppingCart.cartSize()][3];
        for (int i = 0; i < ShoppingCart.cartSize(); i++) {
            Product product = ShoppingCart.getProductAtIndex(i);
            data[i][0] = String.format(
                    "%s\n%s\n%s",
                    product.getProductID(),
                    product.getProductName(),
                    product.getClass().getSimpleName());
            data[i][1] = Integer.toString(ShoppingCart.getProductQuantityInCart(product));
            data[i][2] = product.getPrice() + " £";
        }
        JScrollPane scrollPane = new JScrollPane(new ProductTable(data, columnNames));

        JTextField total = new JTextField("Total");
        JTextField firstPurchaseDiscount = new JTextField("First Purchase Discount (10%)");
        JTextField threeItemDiscount = new JTextField("Three Items in same Category Discount (20%)");
        total.setHorizontalAlignment(JTextField.RIGHT);
        // priceCalculation.setEditable(false);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(total, BorderLayout.SOUTH);
        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
