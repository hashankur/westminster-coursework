import java.util.Scanner;

public class Employees {
	public static void main(String[] args) {
		int manual = 500;
		int skilled = 700;
		int management = 800;

		Scanner input = new Scanner(System.in);

		System.out.print("Enter number of manual skilled employees: ");
		int numManual = input.nextInt();
		System.out.print("Enter number of skilled skilled employees: ");
		int numSkilled = input.nextInt();
		System.out.print("Enter number of management skilled employees: ");
		int numManagement = input.nextInt();

		System.out.println("\n----------------------------------------\n");

		int totManual = manual * numManual;
		int totSkilled = skilled * numSkilled;
		int totManagement = management * numManagement;

		System.out.println("Wage bill:");
		System.out.println("Manual: £" + totManual);
		System.out.println("Skilled: £" + totSkilled);
		System.out.println("Management: £" + totManagement);

		int totalWage = totManual + totSkilled + totManagement;
		System.out.println("\nApproximate tax: £" + (totalWage * 20 / 100));
	}
}
