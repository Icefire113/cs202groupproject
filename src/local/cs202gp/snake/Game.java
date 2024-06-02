package local.cs202gp.snake;

import java.util.Random;

public class Game {

    ////////////////////////////////////////////////
    //  Enums and the such
    ////////////////////////////////////////////

    enum difficulty
    {

        DONT_HURT_ME,
        KILL_THEM_ALL,
        AZATHOTHS_THRONE,
        BLEEDING_MACHINE,
        GOD_SHALL_WEEP
    }
    enum mode
    {

        START,
        SELECT,
        PLAY,
        DEATH
    }

    ////////////////////////////////////////////////
    //  Information related to current 
    //      game status
    ////////////////////////////////////////////
    static boolean quitGame = false;
    static difficulty gameState;

    UserInput keylogger;
    Screen s;

    Color topLeft;
    Color bottomRight;


    ////////////////////////////////////////////////
    //  Monitor time
    ////////////////////////////////////////////
    final long Δt           =  100;
    
    private long startTime;
    private long currentTime        = System.currentTimeMillis();
    private long lastTime           = System.currentTimeMillis();
    private long lastMoveTime       = System.currentTimeMillis();


    ////////////////////////////////////////////////
    //  Initialize Game
    ////////////////////////////////////////////
    Game() {

        ////////////////////////////////////////////////
        //  get relavent information for
        //  game
        ////////////////////////////////////////////
        startTime = System.currentTimeMillis();
        keylogger = new UserInput();
        
        ////////////////////////////////////////////////
        //  Initialize graphics and user controls
        ////////////////////////////////////////////
        UserInput.initUserInput();
        int n = 2;
        s = new Screen(70 * n,35);


        Random rand = new Random();

        char r, g, b;
        r = (char)rand.nextInt(255);
        g = (char)rand.nextInt(255);
        b = (char)rand.nextInt(255);
        topLeft = new Color(r, g, b);

        r = (char)rand.nextInt(255);
        g = (char)rand.nextInt(255);
        b = (char)rand.nextInt(255);
        bottomRight = new Color(r, g, b);




        s.clearBufferAsGradient(topLeft, bottomRight);


        ////////////////////////////////////////////////
        //  Hide cursor
        ////////////////////////////////////////////
        System.out.println("\u001B[?25l");
    }


    ////////////////////////////////////////////////
    //  handle game logic
    ////////////////////////////////////////////
    public void runGame() {


        
        
        
        keylogger.start();



        ////////////////////////////////////////////////
        //  gameLoop
        ////////////////////////////////////////////
        while (!quitGame) {
            


            ////////////////////////////////////////////////
            //  update time
            ////////////////////////////////////////////
            currentTime = System.currentTimeMillis();
            long dt = currentTime - lastTime;

            ////////////////////////////////////////////////
            //  update user input
            ////////////////////////////////////////////
            CONTROLS controller = keylogger.getUserInput();


            if(controller == CONTROLS.EXIT){
                //quitGame = true;


                Menu m = new Menu();
                m.OpenMenu(s, keylogger, s.getScreenWidth()/4, s.getScreenHeight() * 1 / 8, s.getScreenWidth()/ 4, s.getScreenHeight() * 3 /4);
            }

            ////////////////////////////////////////////////
            //  check if it is time to update game
            //  to next frame
            ////////////////////////////////////////////
            if(dt >= Δt){

                
                lastTime = currentTime;

                topLeft.ΔR();
                topLeft.ΔG();
                topLeft.ΔB();

                bottomRight.ΔR();
                bottomRight.ΔG();
                bottomRight.ΔB();


                s.clearBufferAsGradient(topLeft, bottomRight);

                s.display();
                
            }
            
        }
        UserInput.destroy();
        ////////////////////////////////////////////////
        //  Restore cursor
        ////////////////////////////////////////////
        System.out.println("\u001B[?25H");
        System.out.print("\u001B[38;2;255;255;255m");

        try 
        {
            UserInput.kill();
            keylogger.interrupt();
            keylogger.join();
            System.out.println("____________thread joined_________");
            
        } 
        catch(InterruptedException e)
        {
            System.out.println(e);// this part is executed when an exception (in this example InterruptedException) occurs
        }
        
    }
}
