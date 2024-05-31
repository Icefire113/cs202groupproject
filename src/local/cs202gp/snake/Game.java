package local.cs202gp.snake;

public class Game {
    static boolean g_shouldExit = false;
    long startTime;
    long currentTime = System.currentTimeMillis();
    long lastTime = System.currentTimeMillis();

    public void runGame() {
        startTime = System.currentTimeMillis();
        BoardGraphics.initBoard();
        UserInput.initUserInput();

        // main game loop
        while (!g_shouldExit) {
            currentTime = System.currentTimeMillis();
            Direction moveDir = UserInput.handleUserInput();
            BoardGraphics.update(moveDir, lastTime - currentTime);

            lastTime = currentTime;
        }
        UserInput.destroy();
    }
}
