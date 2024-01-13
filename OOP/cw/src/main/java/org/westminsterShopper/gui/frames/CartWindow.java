package org.westminsterShopper.gui.frames;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.westminsterShopper.Util;
import org.westminsterShopper.data.Clothing;
import org.westminsterShopper.data.Product;
import org.westminsterShopper.data.ShoppingCart;
import org.westminsterShopper.gui.components.ProductTable;

public class CartWindow extends JFrame {
    public CartWindow() {
        super("Shopping Cart");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600, 300);

        JPanel contentPane = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = { "Product", "Quantity", "Price" };
        String[][] data = new String[ShoppingCart.cartSize()][3];
        for (int i = 0; i < ShoppingCart.cartSize(); i++) {
            Product product = ShoppingCart.getProductAtIndex(i);
            String additionalInfo = "";
            if (product instanceof Clothing clothing) {
                additionalInfo = clothing.getSize() + ", " + clothing.getColour();
            }
            data[i][0] = String.format(
                    "%s,%s,%s",
                    product.getProductID(),
                    product.getProductName(),
                    additionalInfo);
            data[i][1] = Integer.toString(ShoppingCart.getProductQuantityInCart(product));
            data[i][2] = product.getPrice() + " £";
        }
        JScrollPane scrollPane = new JScrollPane(new ProductTable(data, columnNames));

        // Total
        String[] priceDescriptions = {
                "Total", "First Purchase Discount (10%)",
                "Three Items in same Category Discount (20%)",
                "Final Total" };
        double totalCost = ShoppingCart.getTotalCost();
        double categoryDiscount = ShoppingCart.categoryDiscount() ? totalCost * 0.2 : 0;
        double firstPurchaseDiscount = 0;
        double finalPrice = totalCost - (categoryDiscount + firstPurchaseDiscount);
        String priceBreakdown = String.format(
                "\t\t\t\t%s\n\t\t%s\n\t%s\n\t\t\t\t%s",
                priceDescriptions[0] + "\t" + String.format("%.2f", totalCost) + " £",
                priceDescriptions[1] + "\t" + String.format("%.2f", firstPurchaseDiscount) + " £",
                priceDescriptions[2] + "\t" + String.format("%.2f", categoryDiscount) + " £",
                priceDescriptions[3] + "\t" + String.format("%.2f", finalPrice) + " £");

        JTextArea priceCalculation = new JTextArea(priceBreakdown);
        priceCalculation.setEditable(false);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(priceCalculation, BorderLayout.SOUTH);
        Util.centreWindow(this);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }
}
