import java.util.Scanner;

public class Search {
    public static void main(String[] args) {
        int[] array = {23, 67, 45, 34, 90, 1, 4, 78, 45};

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number to search for: ");
        int num = input.nextInt();

        int result = LinearSearch(array, num);

        if (result == -1) {
            System.out.println("The number " + num + " is not in the array.");
        } else {
            System.out.println("The number " + num + " is in the array at index " + result);
        }
    }

    public static int LinearSearch(int[] array, int searchValue) {
        int index = 0;
        while (index < array.length && array[index] != searchValue) {
            index++;
        }
        if (index == array.length) {
            return -1;
        } else {
            return index;
        }
    }
}
