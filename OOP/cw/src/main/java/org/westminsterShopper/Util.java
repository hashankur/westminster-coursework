package org.westminsterShopper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Scanner;

public class Util {
    /**
     * Validates user input to ensure it is an integer.
     * If the input is not an integer, prompts the user to enter a number until a
     * valid input is provided.
     *
     * @param input the Scanner object used to read user input
     * @return the validated integer input
     */
    public static int validateInputInt(Scanner input) {
        while (!input.hasNextInt()) {
            coloriseTerminalText("Invalid input.", true);
            System.out.print("Please enter a number: ");
            input.next(); // Discard the invalid input
        }
        return input.nextInt();
    }

    /**
     * Centers the specified window on the screen.
     *
     * @param frame the window to be centered
     */
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * Colorizes the terminal text and prints it to the console.
     * Enable only if the program is running on Linux.
     * 
     * @param text The text to be colorized and printed.
     * @param warn Indicates whether the text should be colorized as a warning
     *             (true) or success (false).
     */
    public static void coloriseTerminalText(String text, boolean warn) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux") || os.contains("mac")) { // Unix-specific code
            String colour = "";
            if (warn)
                colour = "31"; // Red
            else
                colour = "32"; // Green
            System.out.println("\033[" + colour + "m" + text + "\033[0m");
        } else {
            System.out.println(text);
        }
    }
}
