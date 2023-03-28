import java.util.Scanner;

public class Exercise04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = input.nextInt();
        System.out.print("Enter exponent: ");
        int exp = input.nextInt();
        System.out.println(exp(num, exp));
    }

    private static int exp(int num, int exp) {
        if (exp > 1) {
            num *= exp(num, exp - 1);
        }
        return num;
    }
}
