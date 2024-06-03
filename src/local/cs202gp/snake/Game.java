package local.cs202gp.snake;

public class Game {
    static boolean g_shouldExit = false;

    public void runGame() {
        BoardGraphics.initBoard();
        UserInput.initUserInput();
        GameLogic.initGameplay();

        // main game loop
        while (!g_shouldExit) {
            Direction moveDir = UserInput.handleUserInput();

            BoardGraphics.update(moveDir);
            GameLogic.updateSnakeWeights();
            GameLogic.checkColisions(moveDir);
            BoardGraphics.clearAndPrint();
        }
        UserInput.destroy();
    }
}
