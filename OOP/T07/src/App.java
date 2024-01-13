import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int[] arr = { 7 };
        loadArray(arr);

        ArrayList<Book> list = new ArrayList<>();
        while (list.size() < 10) {
            System.out.print("Enter book title: ");
            String title = input.next();
            System.out.print("Enter book price: ");
            double price = input.nextDouble();
            System.out.print("Enter book year: ");
            int year = input.nextInt();
            System.out.print("Enter book author: ");
            String author = input.next();

            list.add(new Book(title, price, year, author));

            System.out.print("Add another book? (Enter q to quit): ");
            String exit = input.nextLine();
            if (exit.equals("q")) {
                break;
            }
        }

        Collections.sort(list);
        System.out.println(list);

        HashMap<Book, Integer> map = new HashMap<>();
        for (Book b : list) {
            map.put(b, 1);
        }
    }

    public static void loadArray(int[] list) {
        for (int i = 1; i < list.length; i++) {
            list[i] = list[i] + list[i - 1];
        }
    }

    public static void listDemo(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int element = list.get(i);
            list.remove(i);
            list.add(0, element + 1);
        }
        System.out.println(list);
    }
}
