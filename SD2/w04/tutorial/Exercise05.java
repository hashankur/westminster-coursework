import java.util.Scanner;

public class Exercise05 {
    public static void main(String[] args) {
        double[][] marks = new double[5][3];
        int[] total = new double[5];

        Scanner input = new Scanner(System.in);
        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                System.out.print("Student " + (i + 1) + ", Mark " + (j + 1) + ": ");
                marks[i][j] = input.nextInt();
                total[i] += marks[i][j];
            }
        }

        System.out.println();

        for (int i = 0; i < marks.length; i++) {
            System.out.println("Student " + (i + 1) + " average: " + (total[i] / marks[i].length));
        }
    }
}
