import java.util.Scanner;

public class ArrayTest {
    public static void main(String[] args) {
        String[] students = {"A", "B", "C"};
        double[] scores = new double[students.length];

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < students.length; i++) {
            System.out.print("Enter scores: ");
            scores[i] = input.nextDouble();
        }
    }
}
