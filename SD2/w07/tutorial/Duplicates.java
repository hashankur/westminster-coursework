public class Duplicates {
    public static void main(String[] args) {
        int[] array = {10, 15, 15, 15, 10, 35, 44, 35, 44, 45};

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    System.out.println(array[i]);
                }
            }
        }
    }
}
