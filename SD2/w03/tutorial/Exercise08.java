public class Exercise08 {
    public static void main(String[] args) {
        int limit = 500;
        System.out.println("Armstrong numbers up to " + limit + " are: ");

        for (int i = 0; i <= limit; i++) {
            if (isArmstrong(i)) {
                System.out.print(i + ", ");
            }
        }
    }

    private static boolean isArmstrong(int temp) {
        int n = 0;
        int digits = 0;
        int last = 0;
        int sum = 0;

        n = temp;

        while (n > 0) {
            n = n / 10;
            digits++;
        }

        n = temp;

        while (n > 0) {
            last = n % 10;
            sum += (Math.pow(last, digits));
            n = n / 10;
        }

        if (temp == sum) {
            return true;
        } else {
            return false;
        }
    }
}
