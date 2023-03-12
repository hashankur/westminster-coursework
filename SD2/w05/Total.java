import java.util.Scanner;

public class Total {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Num 1: ");
        int num1 = scn.nextInt();
        System.out.print("Num 2: ");
        int num2 = scn.nextInt();

        System.out.println("\nTotal: " + calcTotal(num1, num2));
    }

    /*
     * Sum of two numbers
     *
     * @param num1
     *
     * @param num2
     *
     * @return the total
     */
    private static int calcTotal(int num1, int num2) {
        return num1 + num2;
    }
}
