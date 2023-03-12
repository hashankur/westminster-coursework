import java.util.Scanner;

public class Exercise06 {
    static void main(String[] args) {
        int password = 486251;
        Scanner input = new Scanner(System.in);

        int count = 0;
        while (count < 4) {

            System.out.print("Enter password: ");
            int attempt = input.nextInt();

            if (attempt == password) {

            } else {
                System.out.println("Wrong password");
            }
            count++;
        }
    }
}
