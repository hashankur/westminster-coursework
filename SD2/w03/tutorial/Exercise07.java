import java.util.Random;
import java.util.Scanner;

public class Exercise07 {
    public static void main(String[] args) {
        Random random = new Random();
        int rand = random.nextInt(20) + 1;

        Scanner input = new Scanner(System.in);
        System.out.print("Guess a number between 1 and 20: ");
        int guess = input.nextInt();

        while (guess != rand) {
            if (guess < rand) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
            System.out.print("Guess again: ");
            guess = input.nextInt();
        }
        System.out.println("You guessed correctly!");
    }
}
