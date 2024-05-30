import java.util.Scanner;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);

    public static Direction handleUserInput() {
        String sDir = scanner.nextLine();
        Direction dir = Direction.NONE;
        if (sDir.length() > 0) {
            switch (sDir.charAt(0)) {
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
        }
        if (dir == Direction.EXIT)
            Game.m_shouldExit = true;

        return dir;
    }
}
