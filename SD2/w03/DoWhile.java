public class DoWhile {
    public static void main(String[] args) {
        int count = 1;
        int sum = 0;

        do {
            double exp =  Math.pow(count, 2);
            System.out.println(exp);
            sum += exp;
            count++;
        }
        while (count <= 10);

        System.out.println("Sum: " + sum);
    }
}
