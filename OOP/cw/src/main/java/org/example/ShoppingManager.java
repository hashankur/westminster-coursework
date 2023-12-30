package org.example;

import java.util.ArrayList;

public interface ShoppingManager {
    public void addProduct(ArrayList<Product> products);

    public ArrayList<Product> deleteProduct(ArrayList<Product> products);

    public void printProductList(ArrayList<Product> products);

    public void saveToFile(ArrayList<Product> products);

    public ArrayList<Product> readFromFile();

    public void spawnMainWindow();
}
