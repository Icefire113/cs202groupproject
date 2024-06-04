package local.cs202gp.snake;

import java.util.Random;

public class Game {

    ////////////////////////////////////////////
    // Enums and the such
    ////////////////////////////////////////////

    enum difficulty {

        DONT_HURT_ME,
        KILL_THEM_ALL,
        AZATHOTHS_THRONE,
        BLEEDING_MACHINE,
        GOD_SHALL_WEEP
    }

    enum mode {

        START,
        SELECT,
        PLAY,
        DEATH
    }

    ////////////////////////////////////////////
    // Information related to current
    // game status
    ////////////////////////////////////////////
    static boolean quitGame = false;
    static difficulty gameState;

    UserInput keylogger;
    Screen s;

    Color topLeft;
    Color bottomRight;

    Direction d;

    private SnakeGame snakegame;

    ////////////////////////////////////////////
    // Monitor time
    ////////////////////////////////////////////
    final long deltaT = 100;

    private long startTime;
    private long currentTime = System.currentTimeMillis();
    private long lastTime = System.currentTimeMillis();
    private long lastMoveTime = System.currentTimeMillis();

    ////////////////////////////////////////////
    // Initialize Game
    ////////////////////////////////////////////
    Game() {
        ////////////////////////////////////////////
        // get relavent information for
        // game
        ////////////////////////////////////////////
        startTime = System.currentTimeMillis();
        keylogger = new UserInput();

        ////////////////////////////////////////////
        // Initialize graphics and user controls
        ////////////////////////////////////////////
        UserInput.initUserInput();
        int n = 2;
        s = new Screen(70 * n, 35);

        Random rand = new Random();

        char r, g, b;
        r = (char) rand.nextInt(255);
        g = (char) rand.nextInt(255);
        b = (char) rand.nextInt(255);
        topLeft = new Color(r, g, b);

        r = (char) rand.nextInt(255);
        g = (char) rand.nextInt(255);
        b = (char) rand.nextInt(255);
        bottomRight = new Color(r, g, b);

        s.clearBufferAsGradient(topLeft, bottomRight);

        snakegame = new SnakeGame(s);

        d = Direction.UP;
    }

    private void reset() {

        int n = 2;
        s = new Screen(70 * n, 35);

        Random rand = new Random();

        char r, g, b;
        r = (char) rand.nextInt(255);
        g = (char) rand.nextInt(255);
        b = (char) rand.nextInt(255);
        topLeft = new Color(r, g, b);

        r = (char) rand.nextInt(255);
        g = (char) rand.nextInt(255);
        b = (char) rand.nextInt(255);
        bottomRight = new Color(r, g, b);

        s.clearBufferAsGradient(topLeft, bottomRight);

        ////////////////////////////////////////////
        // Hide cursor
        ////////////////////////////////////////////
        System.out.println("\u001B[?25l");

        snakegame = new SnakeGame(s);

        d = Direction.UP;

    }

    ////////////////////////////////////////////
    // handle game logic
    ////////////////////////////////////////////
    public void runGame() {
        keylogger.start();

        ////////////////////////////////////////////
        // gameLoop
        ////////////////////////////////////////////
        while (!quitGame) {

            ////////////////////////////////////////////
            // update time
            ////////////////////////////////////////////
            currentTime = System.currentTimeMillis();
            long dt = currentTime - lastTime;

            ////////////////////////////////////////////
            // update user input
            ////////////////////////////////////////////
            Controls controller = keylogger.getUserInput();

            switch (controller) {
                // quitGame = true;

                case EXIT:
                    Menu m = new Menu(s.getScreenWidth() / 4, s.getScreenHeight() * 1 / 8, s.getScreenWidth() / 4,
                            s.getScreenHeight() * 3 / 4);
                    m.OpenMenu(s, keylogger);
                    break;

                case LEFT:
                    d = Direction.LEFT;
                    break;
                case RIGHT:
                    d = Direction.RIGHT;
                    break;
                case UP:
                    d = Direction.UP;
                    break;
                case DOWN:
                    d = Direction.DOWN;
                    break;

                default:
                    break;
            }

            ////////////////////////////////////////////
            // check if it is time to update game
            // to next frame
            ////////////////////////////////////////////
            if (dt >= deltaT) {
                lastTime = currentTime;

                topLeft.deltaR();
                topLeft.deltaG();
                topLeft.deltaB();

                bottomRight.deltaR();
                bottomRight.deltaG();
                bottomRight.deltaB();

                s.clearBufferAsGradient(topLeft, bottomRight);

                boolean isDead = snakegame.update(s, d);

                if (isDead) {
                    reset();
                }

                s.display();
            }
        }
        UserInput.destroy();

        try {
            UserInput.kill();
            keylogger.interrupt();
            keylogger.join();
            System.out.println("____________thread joined____________");

        } catch (InterruptedException e) {
            System.out.println(e);// this part is executed when an exception (in this example
                                  // InterruptedException) occurs
        }

    }
}
