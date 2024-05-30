import java.util.Random;

import util.*;

public class BoardGraphics {

    // board sizes
    final static int boardX = 20;
    final static int boardY = 20;

    static int snakeX;
    static int snakeY;

    // should we use arraylists or just nested arrays for the board?

    // handle all board init, making board, picking where the snake should spawn,
    // putting the first apple, etc

    @SuppressWarnings("unused")
    public static void initBoard() {
        // kinda like a static assert that the board is of a playable size
        if (boardX < 1 || boardY < 1) {
            throw new IllegalArgumentException("boardX or boardY must be over 0");
        }

        Logging.debug("Starting board init");
        Logging.debug("Board size (" + boardX + ", " + boardY + ")");

        // pick starting point
        snakeX = new Random().nextInt(boardX);
        snakeY = new Random().nextInt(boardY);
        Logging.debug("Snake start pos: (" + snakeX + ", " + snakeY + ")");

        // default direction is up

    }

    public static void clearAndPrint() {

    }

    // called each 'frame' to update the display
    // we should prolly move the snake around every 3/4 of a second, but should
    // adjust it after playtesting
    public static void update(Direction dir, double deltaTime) {
        if (dir == Direction.NONE)
            return;

    }
}
