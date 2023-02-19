import java.util.Scanner;

public class Exercise04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a integer: ");
        int pInt = input.nextInt();
        System.out.print("Enter a letter: ");
        char letter = input.next().charAt(0);

        System.out.print("Output: ");
        int i = 1;
        while (i <= pInt) {
            System.out.print(letter);
            i++;
        }
    }
}
