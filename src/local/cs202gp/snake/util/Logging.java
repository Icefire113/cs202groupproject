package local.cs202gp.snake.util;

public class Logging {
    // change to false for release builds
    static private boolean m_debug = true;

    // prints the string passed to it after the string "[DEBUG]" only if the local
    // varible is true
    public static void debug(String x) {
        if (m_debug)
            System.out.println("[DEBUG] " + x);

    }

    public static void prettyPrintArray(int[][] twoDm) {
        if (m_debug) {
            for (int[] row : twoDm) {
                for (int i : row) {
                    System.out.print(i);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    public static void info(String x) {
        System.out.println("[INFO] " + x);
    }

    public static void error(String x) {
        if (m_debug)
            System.out.println("[ERROR] " + x);
    }
}
