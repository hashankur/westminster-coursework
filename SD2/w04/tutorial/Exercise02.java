import java.util.Arrays;
import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {
        int count = 0;
        double[] dnums = new double[10];

        while (count < 10) {
            dnums[count] = Math.random() * 100.0d; // Wat?
            count++;
        }
        System.out.println(Arrays.toString(dnums));

        Scanner scn = new Scanner(System.in);
        System.out.print("Num 1: ");
        int i = scn.nextInt();
        System.out.print("Num 2: ");
        int j = scn.nextInt();

        double temp1 = dnums[i];
        double temp2 = dnums[j];

        dnums[i] = temp2;
        dnums[j] = temp1;

        System.out.println(Arrays.toString(dnums));
    }
}
// INFO: String.format("%.02f", m)
