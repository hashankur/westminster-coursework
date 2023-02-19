import java.util.Scanner;

public class Theatre {
    public static void main(String[] args) {
        clearScreen();
        System.out.println("Welcome to the New Theatre");

        boolean[] row1 = new boolean[12];
        boolean[] row2 = new boolean[16];
        boolean[] row3 = new boolean[20];

        boolean runProgram = true;
        while (runProgram) {

        Scanner input = new Scanner(System.in);
        System.out.println("""
                -------------------------------------------------
                Please select an option:
                1) Buy a ticket
                2) Print seating area
                3) Cancel ticket
                4) List available seats
                5) Save to file
                6) Load from file
                7) Print ticket information and total price
                8) Sort tickets by price
                0) Quit
                -------------------------------------------------
                """);
        System.out.print("Enter option: ");
        int opt = input.nextInt();
            clearScreen();

            switch (opt) {
                case 0:
                    runProgram = false;
                    break;

                case 1:
                    System.out.print("Row number: ");
                    int rowNumC1 = input.nextInt();
                    switch (rowNumC1) {
                        case 1:
                            buy_ticket(input, row1, rowNumC1);
                            break;
                        case 2:
                            buy_ticket(input, row2, rowNumC1);
                            break;
                        case 3:
                            buy_ticket(input, row3, rowNumC1);
                            break;
                        default:
                            System.out.println("Invalid row number. Please try again.");
                    }
                    pressEnterToContinue();
                    break;

                case 2:
                    System.out.println("""
                     ***********
                     *  STAGE  *
                     ***********
                """);
                    print_seating_area(row1, row2, row3);
                    pressEnterToContinue();
                    break;
                case 3:
                    System.out.print("Row number (1-3): ");
                    int rowNumC3 = input.nextInt();
                    switch (rowNumC3) {
                        case 1:
                            cancel_ticket(input, row1);
                            break;
                        case 2:
                            cancel_ticket(input, row2);
                            break;
                        case 3:
                            cancel_ticket(input, row3);
                            break;
                        default:
                            System.out.println("Invalid row number. Please try again.");
                    }
                    pressEnterToContinue();
                    break;
                case 4:
                    show_available(row1, row2, row3);
                    pressEnterToContinue();
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                default:
                    System.out.println("Invalid option. Please try again.");
                    pressEnterToContinue();
            }
        }
    }

    private static void buy_ticket(Scanner input, boolean[] row, int rowNum) {
        System.out.print("Seat number: ");
        int seatNum = input.nextInt() - 1;

        if (row[seatNum]) {
            System.out.println("\nSeat not available");
        }
        else {
            row[seatNum] = true;
            System.out.println("\nReserved seat " + seatNum + " of row " + rowNum);
        }
    }

    private static void print_seating_area(boolean[] row1, boolean[] row2, boolean[] row3) {
        boolean[][] rows = {row1, row2, row3};
        int[] spaces = {4, 2, 0};  // Left padding for seating area

        // Loop through rows 1-3 arrays
        for (int j = 0; j < rows.length; j++) {
            boolean[] row = rows[j];
            System.out.print(" ".repeat(spaces[j]));

            // Loop each row for seats
            for (int i = 0; i < row.length; i++) {
                boolean seat = row[i];

                if (seat) System.out.print("X");
                else System.out.print("O");

                // Seating area center spacing
                if (i == (row.length / 2 - 1)) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void cancel_ticket(Scanner input, boolean[] row) {
        System.out.print("Seat number: ");
        int seatNum = input.nextInt() - 1;

        if (row[seatNum]) {
            row[seatNum] = false;
            System.out.println("\nSeat ticket cancelled.");
        }
        else {
            System.out.println("\nSeat not occupied.");
        }
    }

    private static void show_available(boolean[] row1, boolean[] row2, boolean[] row3) {
        boolean[][] rows = {row1, row2, row3};
        // Loop through rows 1-3 arrays
        for (int i = 0; i < rows.length; i++) {
            boolean[] row = rows[i];

            System.out.print("Seats available in row " + (i + 1) + ":");
            for (int j = 0; j < row.length; j++) {
                boolean seat = row[j];
                if (!seat) {
                    System.out.print(" " + (j + 1));
                    if (j + 1 != row.length) System.out.print(",");
                    else System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static void pressEnterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress ENTER to continue...");
        scanner.nextLine();
        clearScreen();
    }

    private static void clearScreen() {
        // TODO: Check support for Windows !!! [CLS]
//         System.out.print("\033[2J\033[1;1H");
        System.out.print("\033[H\033[2J"); // What each esc code does?
        System.out.flush();
    }
}