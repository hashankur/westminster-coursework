import java.util.Scanner;

public class Temp {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Input temperature: ");
		int temp = input.nextInt();

		if (temp > 26) {
			System.out.println("Hot");
		}
		else {
			System.out.println("Cold");
		}
	}
}
