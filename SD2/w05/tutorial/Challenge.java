public class Challenge {
    public static void main(String[] args) {
        int num = 1;
        rec(num);
    }

    private static int rec(int num) {
        if (num >= 2) {
            num = rec(num - 2) + rec(num - 1);
        }
        return num;
    }
}
