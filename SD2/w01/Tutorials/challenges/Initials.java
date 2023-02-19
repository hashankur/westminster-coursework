import java.util.Scanner;

public class Initials {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter your name: ");
		String name = input.next();
		System.out.print("Enter your surname: ");
		String sname = input.next();

		System.out.println("Hello " + name.substring(0, 1) + sname.substring(0, 1));
	}
}
