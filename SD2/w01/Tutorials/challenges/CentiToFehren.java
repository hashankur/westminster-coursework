import java.util.Scanner;

public class CentiToFehren {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter centigrade temperature: ");
		float c = input.nextFloat();
		double f = (9.0 / 5) * c + 32;
		System.out.println(c + " centimeters = " + f + " fahrenheit");
	}
}
