public class Recursion {
    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
    }

    private static int factorial(int num) {
        if (num == 1) {
            return 1;
        }

        return num * factorial(num - 1);
    }
}
