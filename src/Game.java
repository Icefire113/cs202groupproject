
public class Game {
    static boolean m_shouldExit = false;

    public void runGame() {
        BoardGraphics.initBoard();

        // main game loop
        while (!m_shouldExit) {
            UserInput.handleUserInput();
        }
    }
}
