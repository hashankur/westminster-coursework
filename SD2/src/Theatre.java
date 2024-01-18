import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Theatre {

    // For validate_input_int()
    static final byte ROW = 1;
    static final byte SEAT = 2;
    static final byte OPTION = 3;
    static final byte PRICE = 4;

    public static void main(String[] args) {
        boolean[][] rows = {new boolean[12], new boolean[16], new boolean[20]};
        ArrayList<Ticket> tickets = new ArrayList<>();

        System.out.println("Welcome to the New Theatre");
        boolean runProgram = true;
        while (runProgram) {
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
            int option = validate_input_int(OPTION);

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
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    option = validate_input_int(OPTION);
                    continue;
                }
            }
            if (option != 0) press_enter_to_continue();
        }
    }

    /**
     * Buy tickets
     *
     * @param rows 2d array including all rows
     * @param tickets Tickets array
     */
    private static void buy_ticket(boolean[][] rows, ArrayList<Ticket> tickets) {
        int rowNum = validate_input_int(ROW);
        rowNum = validate_input_row_array(rowNum, rows);
        boolean[] row = rows[rowNum - 1];

        int seatNum = validate_input_int(SEAT);
        seatNum = validate_input_seat_array(seatNum, row);

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
            int price = validate_input_int(PRICE);

            Person person = new Person(name, surname, email);
            tickets.add(new Ticket(rowNum, seatNum, price, person));

            row[seatNum - 1] = true;
            System.out.println("\nReserved seat " + (seatNum) + " of row " + rowNum);
        }
    }

    /**
     * Print seating area according to the floor plan
     *
     * @param rows 2d array including all rows
     */
    private static void print_seating_area(boolean[][] rows) {
        System.out.println("     ***********");
        System.out.println("     *  STAGE  *");
        System.out.println("     ***********");

        int[] spaces = {4, 2, 0}; // Left padding for seating area

        // Loop through rows 1-3 arrays
        
        for (int i = 0; i < rows.length; i++) {
            System.out.print(" ".repeat(spaces[i]));

            // Loop each row for seats
            for (int j = 0; j < rows[i].length; j++) {
                boolean seat = rows[i][j];

                if (seat) System.out.print("X");
                else System.out.print("O");

                // Seating area center spacing
                if (j == (rows[i].length / 2 - 1)) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Cancel tickets
     *
     * @param rows 2d array including all rows
     * @param tickets Tickets array
     */
    private static void cancel_ticket(boolean[][] rows, ArrayList<Ticket> tickets) {
        int rowNum = validate_input_int(ROW);
        rowNum = validate_input_row_array(rowNum, rows);
        boolean[] row = rows[rowNum - 1];

        int seatNum = validate_input_int(SEAT);
        seatNum = validate_input_seat_array(seatNum, row);

        if (row[seatNum - 1]) {
            row[seatNum - 1] = false;

            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                if (ticket.isEqual(rowNum, seatNum)) tickets.remove(i);
            }
            System.out.println("\nTicket cancelled.");
        } else {
            System.out.println("\nInvalid request. Seat not reserved.");
        }
    }

    /**
     * Show all available tickets
     *
     * @param rows 2d array including all rows
     */
    private static void show_available(boolean[][] rows) {
        // Loop through rows 1-3 arrays
        for (int i = 0; i < rows.length; i++) {
            System.out.print("Seats available in row " + (i + 1) + ":");

            for (int j = 0; j < rows[i].length; j++) {
                if (!rows[i][j]) { // Get seat value
                    System.out.print(" " + (j + 1));

                    if (j + 1 != rows[i].length) System.out.print(",");
                    else System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    /**
     * Save array data to file 'seating.txt'
     *
     * @param rows 2d array including all rows
     */
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
                System.out.println("File saved.");
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
        }
    }

    /**
     * Load array data from file 'seating.txt'
     *
     * @return Load data into main array 'rows'
     */
    private static boolean[][] load() {
        boolean[][] fileContents = {new boolean[12], new boolean[16], new boolean[20]};

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
        }
        System.out.println("File loaded.\n");
        return fileContents;
    }

    /**
     * Show information from tickets array
     *
     * @param tickets Array containing Ticket objects
     */
    private static void show_tickets_info(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("No tickets bought.");
        } else {
            int totalPrice = 0;
            for (Ticket ticket : tickets) {
                ticket.print();
                totalPrice += ticket.getPrice();
            }
            System.out.println("\nTotal price of all tickets: Rs. " + totalPrice);
        }
    }

    /**
     * Show information from tickets array sorted by price
     *
     * @param tickets Array containing Ticket objects
     */
    private static void sort_tickets(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("No tickets bought.");
        } else {
            // Create copy of tickets array
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

    /** Await user for ENTER keypress */
    private static void press_enter_to_continue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress ENTER for the menu...");
        scanner.nextLine();
        System.out.println();
    }

    /**
     * Validates if the input is an integer.
     *
     * @param type The type of input to validate (OPTION, ROW or SEAT)
     * @return Input number if type int
     */
    private static int validate_input_int(byte type) {
        switch (type) {
            case 1 -> System.out.print("Row number (1, 2 or 3): ");
            case 2 -> System.out.print("Seat number: ");
            case 3 -> System.out.print("Enter option: ");
            case 4 -> System.out.print("Price: Rs. ");
        }

        Scanner input = new Scanner(System.in);
        int num;
        try {
            num = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nPlease input a number");
            num = validate_input_int(type);
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
    private static int validate_input_row_array(int rowNum, boolean[][] rows) {
        boolean[] row = {};
        boolean error = true;
        while (error) {

            try {
                row = rows[rowNum - 1];
                error = false;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nInvalid row number. Please try again.");
                rowNum = validate_input_int(ROW);
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
    private static int validate_input_seat_array(int seatNum, boolean[] row) {
        boolean error = true;
        boolean test = false;
        while (error) {

            try {
                test = row[seatNum - 1];
                error = false;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nInvalid seat number. Please try again.");
                seatNum = validate_input_int(SEAT);
            }
        }
        return seatNum;
    }
}
