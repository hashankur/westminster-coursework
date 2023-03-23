import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Theatre {

    static final String OPTION = "";
    static final String ROW = "Row";
    static final String SEAT = "Seat";

    public static void main(String[] args) {
        boolean[][] rows = { new boolean[12], new boolean[16], new boolean[20] };
        ArrayList<Ticket> tickets = new ArrayList<>();

        boolean runProgram = true;
        while (runProgram) {

            System.out.println("\n\nWelcome to the New Theatre");
            System.out.println(
                    """
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
            int option = validateInputInt(OPTION);
            System.out.println();

            switch (option) {
                case 0 -> runProgram = false;
                case 1 -> buy_ticket(rows, tickets);
                case 2 -> print_seating_area(rows);
                case 3 -> cancel_ticket(rows, tickets);
                case 4 -> show_available(rows);
                case 5 -> save(rows);
                case 6 -> rows = load();
                case 7 -> show_tickets_info(tickets);
                case 8 -> sort_tickets(tickets);
                default -> System.out.println("Invalid option. Please try again.");
            }
            if (option != 0)
                pressEnterToContinue();
        }
    }

    private static void buy_ticket(boolean[][] rows, ArrayList<Ticket> tickets) {
        int rowNum = validateInputInt(ROW);
        rowNum = validateInputRowArray(rowNum, rows);
        boolean[] row = rows[rowNum - 1];

        int seatNum = validateInputInt(SEAT);
        seatNum = validateInputSeatArray(seatNum, row);

        if (row[seatNum - 1]) {
            System.out.println("\nSeat not available");
        } else {
            Scanner input = new Scanner(System.in);
            System.out.print("\nName: ");
            String name = input.next();
            System.out.print("Surname: ");
            String surname = input.next();
            System.out.print("Email: ");
            String email = input.next();

            Person person = new Person(name, surname, email);
            int[] ticketPrice = { 30, 20, 10 };
            tickets.add(new Ticket(rowNum, seatNum, ticketPrice[rowNum - 1], person));

            row[seatNum - 1] = true;
            System.out.println("\nReserved seat " + (seatNum) + " of row " + rowNum);
        }
    }

    private static void print_seating_area(boolean[][] rows) {
        System.out.println("     ***********");
        System.out.println("     *  STAGE  *");
        System.out.println("     ***********");

        int[] spaces = { 4, 2, 0 }; // Left padding for seating area

        // Loop through rows 1-3 arrays
        for (int i = 0; i < rows.length; i++) {
            System.out.print(" ".repeat(spaces[i]));

            // Loop each row for seats
            for (int j = 0; j < rows[i].length; j++) {
                boolean seat = rows[i][j];

                if (seat)
                    System.out.print("X");
                else
                    System.out.print("O");

                // Seating area center spacing
                if (j == (rows[i].length / 2 - 1)) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void cancel_ticket(boolean[][] rows, ArrayList<Ticket> tickets) {
        int rowNum = validateInputInt(ROW);
        rowNum = validateInputRowArray(rowNum, rows);
        boolean[] row = rows[rowNum - 1];

        int seatNum = validateInputInt(SEAT);
        seatNum = validateInputSeatArray(seatNum, row);

        if (row[seatNum - 1]) {
            row[seatNum - 1] = false;

            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                if (ticket.isEqual(rowNum, seatNum))
                    tickets.remove(i);
            }
            System.out.println("\nTicket cancelled.");
        } else {
            System.out.println("\nInvalid request. Seat not occupied.");
        }
    }

    private static void show_available(boolean[][] rows) {
        // Loop through rows 1-3 arrays
        for (int i = 0; i < rows.length; i++) {
            System.out.print("Seats available in row " + (i + 1) + ":");

            for (int j = 0; j < rows[i].length; j++) {
                if (!rows[i][j]) { // Get seat value
                    System.out.print(" " + (j + 1));

                    if (j + 1 != rows[i].length)
                        System.out.print(",");
                    else
                        System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static void save(boolean[][] rows) {
        boolean fileCreated;
        File file;
        try {
            file = new File("seating.txt");
            fileCreated = file.createNewFile();

            if (fileCreated) {
                System.out.println("File created: " + file.getName());
            }

            if (file.exists()) {
                System.out.println("File already exists.");
            }

            FileWriter writeFile = new FileWriter("seating.txt");
            for (boolean[] row : rows) {
                for (boolean seat : row) {
                    writeFile.write(seat + " ");
                }
                writeFile.write("\n");
            }
            writeFile.close();

        } catch (IOException e) {
            System.out.println("Error creating file.");
            e.printStackTrace();
        }
    }

    private static boolean[][] load() {
        boolean[][] fileContents = { new boolean[12], new boolean[16], new boolean[20] };

        try {
            Scanner input = new Scanner(new File("seating.txt"));

            for (int i = 0; i < fileContents.length; i++) {
                for (int j = 0; j < fileContents[i].length; j++) {
                    if (input.hasNextBoolean()) {
                        fileContents[i][j] = input.nextBoolean();
                    }
                }
            }
            input.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
        System.out.println("File loaded.\n");
        return fileContents;
    }

    private static void show_tickets_info(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("No tickets bought.");
        } else {
            int totalPrice = 0;
            for (Ticket ticket : tickets) {
                ticket.print();
                totalPrice += ticket.getPrice();
            }
            System.out.println("\nTotal price of all tickets: £" + totalPrice);
        }
    }

    private static void sort_tickets(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("No tickets bought.");
        } else {
            ArrayList<Ticket> array = new ArrayList<>(tickets);

            // Bubble sort - Optimized
            int bottom = array.size() - 2;
            Ticket temp;
            boolean exchanged = true;
            while (exchanged) {
                exchanged = false;
                for (int i = 0; i <= bottom; i++) {
                    if (array.get(i).getPrice() > array.get(i + 1).getPrice()) {
                        temp = array.get(i);
                        array.set(i, array.get(i + 1));
                        array.set(i + 1, temp);
                        exchanged = true;
                    }
                }
                bottom--;
            }
            for (Ticket ticket : array) {
                ticket.print();
            }
        }
    }

    /**
     * Await user for ENTER keypress
     */
    private static void pressEnterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress ENTER for the menu...");
        scanner.nextLine();
        clearScreen();
    }

    private static void clearScreen() {
        // System.out.print("\033[2J\033[1;1H");
        System.out.print("\n\033[H\033[2J"); // What each esc code does?
        System.out.flush();
    }

    /**
     * Validates if the input is an integer.
     *
     * @param type The type of input to validate (OPTION, ROW or SEAT)
     * @return Input number if type int
     */
    private static int validateInputInt(String type) {
        if (type.equals(ROW))
            System.out.print(type + " number (1, 2 or 3): ");
        else if (type.equals(SEAT))
            System.out.print(type + " number: ");
        else
            System.out.print("Enter option: ");

        Scanner input = new Scanner(System.in);
        int num;
        try {
            num = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nPlease input a number");
            num = validateInputInt(type);
        }
        return num;
    }

    /**
     * Validates if the row number is within the bounds of the array.
     *
     * @param rowNum Validated row number of type int
     * @param rows 2D array of all rows
     * @return Row number
     */
    private static int validateInputRowArray(int rowNum, boolean[][] rows) {
        boolean[] row = {};
        boolean error = true;
        while (error) {

            try {
                row = rows[rowNum - 1];
                error = false;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nInvalid row number. Please try again.");
                rowNum = validateInputInt(ROW);
            }
        }
        return rowNum;
    }

    /**
     * Validates if the seat number is within the bounds of the array.
     *
     * @param seatNum Validated seat number of type int
     * @param row 2D array of all rows
     * @return Seat number
     */
    private static int validateInputSeatArray(int seatNum, boolean[] row) {
        boolean error = true;
        boolean test = false;
        while (error) {

            try {
                test = row[seatNum - 1];
                error = false;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nInvalid seat number. Please try again.");
                seatNum = validateInputInt(SEAT);
            }
        }
        return seatNum;
    }
}
