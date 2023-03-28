import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {23, 67, 45, 34, 90, 1, 4, 78, 45};
        System.out.println(Arrays.toString(array));

        BubbleSort(array);
        System.out.println(Arrays.toString(array));

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number to search for: ");
        int num = input.nextInt();

        int result = BinarySearch(array, num);
        if (result == -1) {
            System.out.println("The number " + num + " is not in the array.");
        } else {
            System.out.println("The number " + num + " is in the array at index " + result);
        }
    }

    private static void BubbleSort(int[] array) {
        int bottom = array.length - 2;
        int temp;
        boolean exchanged = true;
        while (exchanged) {
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    exchanged = true;
                }
            }
            bottom--;
        }
    }

    public static int BinarySearch(int[] array, int searchValue) {
        int low = 0, high = array.length - 1, mid = (low + high) / 2;
        while (low <= high && array[mid] != searchValue) {
            if (array[mid] < searchValue) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }
        if (low > high) {
            mid = -1;
        }
        return mid;
    }
}
