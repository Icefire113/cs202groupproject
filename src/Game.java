
public class Game {
    static boolean m_shouldExit = false;
    long startTime;
    long currentTime = System.currentTimeMillis();
    long lastTime = System.currentTimeMillis();

    public void runGame() {
        startTime = System.currentTimeMillis();
        BoardGraphics.initBoard();

        // main game loop
        while (!m_shouldExit) {
            currentTime = System.currentTimeMillis();
            Direction moveDir = UserInput.handleUserInput();
            BoardGraphics.update(moveDir, lastTime - currentTime);

            lastTime = currentTime;
        }
    }
}
