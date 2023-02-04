import java.util.*;

public class MyFirstJava {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Insert name: ");
        String name = input.next();
        System.out.println("Hello " + name);
    }
}
