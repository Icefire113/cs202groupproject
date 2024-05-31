package local.cs202gp.snake;

import java.util.Scanner;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import local.cs202gp.snake.util.Logging;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);
    public static Terminal term;
    static LineReader reader;

    public static void destroy() {
        try {
            Logging.debug("Closing terminal");
            term.close();
        } catch (Exception e) {
            Logging.error(e.getMessage());
        }

    }

    public static void initUserInput() {
        try {
            term = TerminalBuilder.terminal();
            term.enterRawMode();
        } catch (Exception e) {
            Logging.error("Failed to create terminal! " + e);
            Game.g_shouldExit = true;
        }
        reader = LineReaderBuilder.builder().terminal(term).build();
    }

    public static Direction handleUserInput() {
        int character = -1;
        try {
            character = term.reader().read();
        } catch (Exception e) {
            Logging.error(e.getMessage());
            Game.g_shouldExit = true;
        }

        char cDir = (char) character;
        Direction dir = Direction.NONE;

        switch (cDir) {
            case 'w':
                dir = Direction.UP;
                break;
            case 'a':
                dir = Direction.LEFT;
                break;
            case 's':
                dir = Direction.DOWN;
                break;
            case 'd':
                dir = Direction.RIGHT;
                break;
            case 'e':
                dir = Direction.EXIT;
                break;
            default:
                break;
        }

        if (dir == Direction.EXIT)
            Game.g_shouldExit = true;

        return dir;
    }
}
