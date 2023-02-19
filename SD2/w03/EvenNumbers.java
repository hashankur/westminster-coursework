public class EvenNumbers {
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 0; i <= 10; i+=2) {
            System.out.println(i);
            sum += i;
        }

        System.out.println("Sum: " + sum);
    }
}
