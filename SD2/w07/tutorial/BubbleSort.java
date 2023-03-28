import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {23, 67, 45, 34, 90, 1, 4, 78, 45};
        System.out.println(Arrays.toString(array));

        BubbleSort(array);
        System.out.println(Arrays.toString(array));
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
}
