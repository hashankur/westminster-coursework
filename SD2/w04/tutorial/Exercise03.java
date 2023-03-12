public class Exercise03 {
    public static void main(String[] args) {
        int[] A = {2, 3, 4, 5, 6, 7, 8, 9};
        int[] multi = new int[8];

        System.out.print("a)");
        for (int el : A) {
            if (el % 2 == 0) {
                System.out.print(" " + el);
            }
        }

        System.out.print("\nb)");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] * 2 == A[j]) {
                    System.out.print(" " + A[i]);
                }
            }
        }
    }
}
