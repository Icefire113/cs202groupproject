package local.cs202gp.snake;

import java.util.Random;

import org.jline.utils.Log;

import local.cs202gp.snake.util.*;

public class BoardGraphics {

    // board sizes
    final static int boardX = 20;
    final static int boardY = 20;

    final static char boardEmpty = ' ';
    final static char apple = '@';
    final static char snakeHead = 'O';
    final static char snakeBody = '*';
    final static char boardWall = '#';

    static int snakeX = 0;
    static int snakeY = 0;
    static int snakeLength = 1;
    // this is the path the snake took, 1 are cells that will be removed on next
    // move, and 0 is where the snake hasnt been, anything else can be followed up
    // to determine where the path the snake has taken
    static int[][] snakePath = new int[boardX][boardY];

    private static char[][] board = new char[boardX][boardY];

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

        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                board[i][j] = boardEmpty;
            }
        }

        // pick starting point
        snakeX = new Random().nextInt(boardX);
        snakeY = new Random().nextInt(boardY);
        Logging.debug("Snake start pos: (" + snakeX + ", " + snakeY + ")");

        board[snakeX][snakeY] = snakeHead;

        // default direction is up

    }

    public static void clearAndPrint() {
        try {
            // UserInput.term.puts(Capability.clear_screen);
            // UserInput.term.flush();
        } catch (Exception e) {
            Logging.error("Error clearing screen:  " + e);
        }

        String toPrint = "";
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                toPrint += board[i][j];
            }
            toPrint += "\n";
        }
        System.out.print(toPrint);

    }

    public static void updateSnakeWeights() {
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                if (snakePath[i][j] > 0)
                    snakePath[i][j] -= 1;
            }
        }
        snakePath[snakeX][snakeY] = snakeLength;
        Logging.prettyPrintArray(snakePath);
    }

    public static void updateBoard() {
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                if (snakePath[i][j] < 1) {
                    board[i][j] = boardEmpty;
                }
            }
        }
        board[snakeX][snakeY] = snakeHead;
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

        updateSnakeWeights();
        updateBoard();
        clearAndPrint();
    }

    // move by x, y relative to current snake position
    private static void move(int x, int y) {
        if (!checkCords(snakeX + x, snakeY + y))
            return;
        snakeX += x;
        snakeY += y;
    }

    private static boolean checkCords(int x, int y) {
        if (x > boardX - 1 || x < 0 || y > boardY - 1 || y < 0) {
            Logging.debug("cords: (" + x + ", " + y + ") are outside of the board!");
            return false;
        }
        return true;
    }
}
