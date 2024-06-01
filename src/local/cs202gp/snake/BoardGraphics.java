package local.cs202gp.snake;

import java.util.ArrayList;
import java.util.Random;

import local.cs202gp.snake.util.*;

public class BoardGraphics {

    // board sizes
    final static int boardX = 20;
    final static int boardY = 20;

    static int snakeX;
    static int snakeY;

    private static ArrayList<ArrayList<Character>> board = new ArrayList<>();

    // handle all board init, making board, picking where the snake should spawn,
    // putting the first apple, etc

    private static boolean checkCords(int x, int y) {
        if (x > boardX || x < 0 || y > boardY || y < 0) {
            Logging.error("cords: (" + x + ", " + y + ") are outside of the board!");
            return false;
        }
        return true;
    }

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

        Logging.debug("Moving " + dir);
        switch (dir) {
            case DOWN:
                move(0, -1);
                break;
            case UP:
                move(0, 1);
                break;
            case LEFT:
                move(-1, 0);
                break;
            case RIGHT:
                move(1, 0);
                break;
            default:
                break;
        }
        Logging.debug("New pos: (" + snakeX + ", " + snakeY + ")");
    }

    // move by x, y relative to current snake position
    private static void move(int x, int y) {
        if (!checkCords(snakeX + x, snakeY + y))
            return;
        snakeX += x;
        snakeY += y;
    }
}
