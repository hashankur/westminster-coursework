public class IntSum {
    public static void main(String[] args) {
        int counter = 1;
        int sum = 0;

        while (counter <= 10) {
            sum += counter;
            counter++;
        }

        System.out.println(sum);
    }
}
