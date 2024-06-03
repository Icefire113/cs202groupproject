package local.cs202gp.snake;

import org.jline.utils.InfoCmp.Capability;

import local.cs202gp.snake.util.*;

public class BoardGraphics {

    // board sizes
    final public static int boardX = 20;
    final public static int boardY = 20;

    final public static char boardEmpty = ' ';
    final public static char apple = '@';
    final public static char snakeHead = 'O';
    final public static char snakeBody = '*';
    final public static char boardWall = '#';

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
                setCords(i, j, boardEmpty);
            }
        }

    }

    public static void clearAndPrint() {
        try {
            UserInput.term.puts(Capability.clear_screen);
            UserInput.term.flush();
        } catch (Exception e) {
            Logging.error("Error clearing screen:  " + e);
        }

        String toPrint = "";
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                toPrint += board[i][j] + " ";
            }
            toPrint += "\n";
        }
        System.out.print(toPrint);

    }

    public static void updateBoard() {
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                if (GameLogic.snakePath[i][j] < 1) {
                    setCords(i, j, boardEmpty);
                } else {
                    setCords(i, j, snakeBody);
                }
            }
        }
        setCords(GameLogic.snakeX, GameLogic.snakeY, snakeHead);
    }

    // called each 'frame' to update the display
    // we should prolly move the snake around every 3/4 of a second, but should
    // adjust it after playtesting
    public static void update(Direction dir) {
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
        Logging.debug("New pos: (" + GameLogic.snakeX + ", " + GameLogic.snakeY + ")");

        updateBoard();
    }

    // move by x, y relative to current snake position
    private static void move(int x, int y) {
        if (!checkCords(GameLogic.snakeX + x, GameLogic.snakeY + y))
            return;
        GameLogic.snakeX += x;
        GameLogic.snakeY += y;
    }

    private static boolean checkCords(int x, int y) {
        if (x > boardX - 1 || x < 0 || y > boardY - 1 || y < 0) {
            Logging.debug("cords: (" + x + ", " + y + ") are outside of the board!");
            return false;
        }
        return true;
    }

    public static void setCords(int x, int y, char toSet) {
        if (!checkCords(x, y))
            return;
        board[x][y] = toSet;
    }
}
