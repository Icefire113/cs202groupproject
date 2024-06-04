package local.cs202gp.snake;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;

public class UserInput extends Thread {
    ////////////////////////////////////////////
    // global info
    ////////////////////////////////////////////
    public static Terminal term;
    static private Controls controller = Controls.NONE;

    ////////////////////////////////////////////
    // data
    ////////////////////////////////////////////
    static LineReader reader;

    public static boolean kill = false;

    public static void seenControl() {
        controller = Controls.NONE;
    }

    public static Controls getUserInput() {
        return controller;
    }

    public static void destroy() {
        try {
            term.puts(Capability.cursor_normal);
            term.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void initUserInput() {

        try {
            term = TerminalBuilder.terminal();
            term.enterRawMode();
            term.puts(Capability.cursor_invisible);
        } catch (Exception e) {

            // Game.g_shouldExit = true;
        }
        reader = LineReaderBuilder.builder().terminal(term).build();
    }

    private static Controls handleUserInput() {
        int character = -1;
        try {
            character = term.reader().read();
        } catch (Exception e) {

            // Game.g_shouldExit = true;
        }

        char cDir = (char) character;

        switch (cDir) {
            case 'w':
                controller = Controls.UP;
                break;
            case 'a':
                controller = Controls.LEFT;
                break;
            case 's':
                controller = Controls.DOWN;
                break;
            case 'd':
                controller = Controls.RIGHT;
                break;
            case '\u001B':
                controller = Controls.EXIT;
                break;
            case (char) 13:
                controller = Controls.ENTER;
                break;

            default:
                // System.out.println("\u001B[" + 20 + ";" + 20 + "H");

                // System.out.println("\u001B[" + 0 + ";" + 0 + "H");
                break;

        }

        // System.out.println("KeyPressed:\t" + character);
        return controller;
    }

    public static void kill() {
        kill = true;
        try {
            term.reader().close();
        } catch (Exception e) {
            System.out.println("Error killing userInput thread:");
            System.out.println("\tCould not close term reader");
            System.out.println();
            System.out.println();
            System.out.println(e);
        }
    }

    public void run() {
        while (!kill) {
            handleUserInput();
        }
    }
}
