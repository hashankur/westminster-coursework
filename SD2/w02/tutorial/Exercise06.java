import java.util.Scanner;

public class Exercise06 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter charactor: ");
		String entered_char = input.next();
		char c = entered_char.charAt(0);

		if (Character.isLowerCase(c)) {
			System.out.println("Character entered is lowercase");
		} else if (Character.isUpperCase(c)) {
			System.out.println("Character entered is uppercase");
		} else {
			System.out.println("Not a letter");
		}
	}
}
