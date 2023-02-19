import java.util.Scanner;

public class Exercise05 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Numbers to generate: ");
        int count = input.nextInt();

        int i = 1;
        int fib1 = 0;
        int fib2 = 1;
        int fib = 0;

        while (i <= count) {
            System.out.print((fib) + ", ");
            fib1 = fib2;
            fib2 = fib;
            fib = fib2 + fib1;
            i++;
        }
        System.out.print("\b\b");
    }
}
