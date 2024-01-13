package org.westminsterShopper.data;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private static ArrayList<Product> cartProducts = new ArrayList<Product>();
    private static HashMap<String, Integer> cartProductsQuantity = new HashMap<>();
    private static HashMap<String, Integer> itemsPerCategory = new HashMap<>();

    public static void addProduct(Product product, int quantity) {
        if (ShoppingCart.cartProducts.contains(product)) {
            int currentQuantity = cartProductsQuantity.get(product.getProductID());
            cartProductsQuantity.put(product.getProductID(), currentQuantity + quantity);
        } else {
            ShoppingCart.cartProducts.add(product);
            cartProductsQuantity.put(product.getProductID(), quantity);
            itemsPerCategory.put(product.getClass().getSimpleName(),
                    itemsPerCategory.getOrDefault(product.getClass().getSimpleName(), 0) + quantity);
        }
    }

    public static void removeProduct(Product product) {
        ShoppingCart.cartProducts.remove(product);
    }

    public static double getTotalCost() {
        double totalCost = 0;
        for (Product product : ShoppingCart.cartProducts) {
            totalCost += product.getPrice() * cartProductsQuantity.get(product.getProductID());
        }
        return totalCost;
    }

    public static boolean categoryDiscount() {
        for (String category : itemsPerCategory.keySet()) {
            if (itemsPerCategory.get(category) >= 3) {
                return true;
            }
        }
        return false;
    }

    public static int cartSize() {
        return ShoppingCart.cartProducts.size();
    }

    public static Product getProductAtIndex(int index) {
        return ShoppingCart.cartProducts.get(index);
    }

    public static int getProductQuantityInCart(Product product) {
        return cartProductsQuantity.get(product.getProductID());
    }
}
