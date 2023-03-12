import java.util.Scanner;

public class Exercise05 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Input month: ");
    int month = input.nextInt();

    switch (month) {
      case 2:
        System.out.println("28 days");
        break;
      case 1, 3, 5, 7, 8, 10, 12:
        System.out.println("31 days");
        break;
      case 4, 6, 9, 11:
        System.out.println("30 days");
        break;
    }
  }
}
