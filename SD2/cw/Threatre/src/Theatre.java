import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Theatre {
    public static void main(String[] args) {
        clearScreen();

        boolean[] row1 = new boolean[12];
        boolean[] row2 = new boolean[16];
        boolean[] row3 = new boolean[20];
        boolean[][] rows = { row1, row2, row3 };
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Ticket> sortedTickets;

        Scanner input = new Scanner(System.in);
        boolean runProgram = true;
        while (runProgram) {

            System.out.println("Welcome to the New Theatre");
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
                        case 1, 2, 3:
                            buy_ticket(rows, rowNumC1, tickets);
                            break;
                        default:
                            System.out.println("Invalid row number. Please try again.");
                    }
                    break;

                case 2:
                    print_seating_area(rows);
                    break;

                case 3:
                    System.out.print("Row number (1-3): ");
                    int rowNumC3 = input.nextInt();
                    switch (rowNumC3) {
                        case 1, 2, 3:
                            cancel_ticket(rows, rowNumC3, tickets);
                            break;
                        default:
                            System.out.println("Invalid row number. Please try again.");
                    }
                    break;

                case 4:
                    show_available(rows);
                    break;

                case 5:
                    save(rows);
                    break;

                case 6:
                    rows = load();
                    break;

                case 7:
                    show_tickets_info(tickets);
                    break;

                case 8:
                     sortedTickets = new ArrayList<>(tickets);
                    sort_tickets(sortedTickets).forEach(Ticket::print);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
            if (opt != 0)
                pressEnterToContinue();
        }
        input.close();
    }

    private static void buy_ticket(boolean[][] rows, int rowNum, ArrayList<Ticket> tickets) {
        Scanner input = new Scanner(System.in);
        boolean[] row = rows[rowNum - 1];
        System.out.print("Seat number: ");
        int seatNum = input.nextInt();

        if (row[seatNum - 1]) {
            System.out.println("\nSeat not available");
        } else {
            System.out.print("\nName: ");
            String name = input.next();
            System.out.print("Surname: ");
            String sname = input.next();
            System.out.print("Email: ");
            String email = input.next();

            Person person = new Person(name, sname, email);
            int[] ticketPrice = { 30, 20, 10 };
            tickets.add(new Ticket(rowNum, seatNum, ticketPrice[rowNum - 1], person));

            row[seatNum - 1] = true;
            System.out.println("\nReserved seat " + (seatNum) + " of row " + rowNum);
        }
        // input.close();
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

    private static void cancel_ticket(boolean[][] rows, int rowNum, ArrayList<Ticket> tickets) {
        Scanner input = new Scanner(System.in);
        boolean[] row = rows[rowNum - 1];
        System.out.print("Seat number: ");
        int seatNum = input.nextInt();

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
        // input.close();
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
        boolean[] row1 = new boolean[12];
        boolean[] row2 = new boolean[16];
        boolean[] row3 = new boolean[20];

        boolean[][] fileContents = { row1, row2, row3 };

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
        System.out.println("File loaded.");
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

    private static ArrayList<Ticket> sort_tickets(ArrayList<Ticket> array) {
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
        return array;
    }

    private static void pressEnterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress ENTER to continue...");
        scanner.nextLine();
        clearScreen();
    }

    private static void clearScreen() {
        // System.out.print("\033[2J\033[1;1H");
        System.out.print("\n\033[H\033[2J"); // What each esc code does?
        System.out.flush();
    }
}
