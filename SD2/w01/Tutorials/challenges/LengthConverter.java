import java.util.Scanner;

public class LengthConverter {
	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter meters: ");
		float meters = input.nextFloat();

		float cm = meters * 100;
		System.out.println(meters + " meters = " + cm + " centimeters");
	}
}


