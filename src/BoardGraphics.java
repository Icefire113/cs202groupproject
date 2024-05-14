import util.*;

public class BoardGraphics {

    // board sizes
    final static int boardX = 20;
    final static int boardY = 20;

    // should we use arraylists or just nested arrays for the board?

    // handle all board init, making board, picking where the snake should spawn,
    // putting the first apple, etc
    public static void initBoard() {
        Logging.debug("Starting board init");
        Logging.debug("Board size (" + boardX + ", " + boardY + ")");
    }

    // called each 'frame' to update the display
    // we should prolly move the snake around every 3/4 of a second, but should
    // adjust it after playtesting
    public static void update() {
    }
}
