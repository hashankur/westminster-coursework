import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println(
                    """
**********
MENU
**********
1.- Addition
2.- Subtraction
0.- Quit
Please select an option:
                """);
            System.out.print("Enter option: ");
            int option = Integer.parseInt(input.next());

            if (option == 0) {
                break;
            }

            System.out.print("Enter first number: ");
            double a = Double.parseDouble(input.next());

            System.out.print("Enter second number: ");
            double b = Double.parseDouble(input.next());

            double result = 0;
            switch (option) {
                case 1 -> result = add(a, b);
                case 2 -> result = sub(a, b);
            }

            System.out.println("Result: " + result + "\n");
        }
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double sub(double a, double b) {
        return a - b;
    }
}
