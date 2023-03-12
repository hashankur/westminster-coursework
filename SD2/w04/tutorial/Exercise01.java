import java.util.ArrayList;
import java.util.Scanner;

public class Exercise01 {
    public static void main(String[] args) {
        // int[] marks = {45, 12, 89, 40, 66, 32, 78, 67, 55, 90};
        // int[] marks = new int[10];
        ArrayList<Integer> marks = new ArrayList<Integer>();
        int failed = 0;
        int sum = 0;
        int count = 0;

        while (true) {
            Scanner scn = new Scanner(System.in);
            System.out.print("Input mark: ");
            String input = scn.next();

            try {
                int inputMark = Integer.parseInt(input);
                // marks[count] = inputMark;
                // count++;
                marks.add(inputMark);
            } catch (NumberFormatException e) {
                if ('q' == input.charAt(0)) {
                    break;
                } else {
                    System.err.println("Invalid input");
                }
            }
        }

        for (int mark : marks) {
            sum += mark;
            if (mark < 40) {
                failed++;
            }
        }

        System.out.println();
        // System.out.println(String.parse);
        System.out.println(failed + " number of students failed");
        // System.out.print("Average: " + sum / marks.length());
        System.out.print("Average: " + sum / marks.size());
    }
}
