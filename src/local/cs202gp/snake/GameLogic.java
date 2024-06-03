package local.cs202gp.snake;

import java.util.Random;

import local.cs202gp.snake.util.Logging;

public class GameLogic {

    static int snakeX = 0;
    static int snakeY = 0;
    static int snakeLength = 1;
    // this is the path the snake took, 1 are cells that will be removed on next
    // move, and 0 is where the snake hasnt been, anything else can be followed up
    // to determine where the path the snake has taken
    static int[][] snakePath = new int[BoardGraphics.boardX][BoardGraphics.boardY];

    public static void checkColisions(Direction d) {
        if (d == Direction.NONE)
            return;
    }

    public static void initGameplay() {
        // pick starting point
        snakeX = new Random().nextInt(BoardGraphics.boardX);
        snakeY = new Random().nextInt(BoardGraphics.boardY);
        Logging.debug("Snake start pos: (" + snakeX + ", " + snakeY + ")");

        BoardGraphics.setCords(snakeX, snakeY, BoardGraphics.snakeHead);
    }

    public static void updateSnakeWeights() {
        for (int i = 0; i < BoardGraphics.boardX; i++) {
            for (int j = 0; j < BoardGraphics.boardY; j++) {
                if (snakePath[i][j] > 0)
                    snakePath[i][j] -= 1;
            }
        }
        snakePath[snakeX][snakeY] = snakeLength;
        Logging.prettyPrintArray(snakePath);
    }

}
