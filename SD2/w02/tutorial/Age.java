import java.util.Scanner;

public class Age {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int age = input.nextInt();

		if (age >= 18) {
			System.out.println("Over 18");
		}
		else if (age >= 0) {
			System.out.println("Under 18");
		}
		else {
			System.out.println("The age entered is incorrect");
		}
	}
}
