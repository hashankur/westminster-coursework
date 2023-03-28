public class Exercise03 {
    public static void main(String[] args) {
        nums(1);
    }

    private static void nums(int count) {
        if (count <= 100) {
            System.out.println(count);
            nums(++count);
        }
    }
}
