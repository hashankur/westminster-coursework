import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Westminster Shopping Manager");
            System.out.println("Select an option:");
            System.out.println("\t1) Add product");
            System.out.println("\t2) Delete product");
            System.out.println("\t3) Print product list");
            System.out.println("\t4) Save to file");
            System.out.println("\t5) Exit");
            System.out.print("Enter your choice: ");

            int option = input.nextInt();
            System.out.println();

            switch (option) {
                case 1 -> manager.addProduct();
                case 2 -> {
                    System.out.print("Enter product ID: ");
                    manager.deleteProduct(input.next());
                }
                case 3 -> manager.printProductList();
                case 4 -> manager.saveToFile();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }

        }
    }
}
