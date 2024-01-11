package org.westminsterShopper;

import java.awt.*;

public class Util {
    /**
     * Centers the specified window on the screen.
     *
     * @param frame the window to be centered
     */
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        // int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        int x = 1105;
        int y = 55;
        frame.setLocation(x, y);
    }
}
