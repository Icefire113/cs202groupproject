package local.cs202gp.snake;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class UserInput extends Thread{

    

    ////////////////////////////////////////////////
    //  global info
    ////////////////////////////////////////////
    public static Terminal term;
    static private CONTROLS controller = CONTROLS.NONE;


    ////////////////////////////////////////////////
    //  data
    ////////////////////////////////////////////
    static LineReader reader;



    
    public static boolean kill = false;


    public static void seenControl(){
        controller = CONTROLS.NONE;
    }
    public static CONTROLS getUserInput(){
        return controller;
    }
    public static void destroy() {
        try {
            
            term.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void initUserInput() {
        
        try {
            term = TerminalBuilder.terminal();
            term.enterRawMode();
        } catch (Exception e) {
            
            //Game.g_shouldExit = true;
        }
        reader = LineReaderBuilder.builder().terminal(term).build();
    }

    private static CONTROLS handleUserInput() {
        int character = -1;
        try {
            
            character = term.reader().read();
        } catch (Exception e) {
            
            //Game.g_shouldExit = true;
        }


        char cDir = (char) character;
        
        switch (cDir) {
            case 'w':
                controller = CONTROLS.UP;
                break;
            case 'a':
                controller = CONTROLS.LEFT;
                break;
            case 's':
                controller = CONTROLS.DOWN;
                break;
            case 'd':
                controller = CONTROLS.RIGHT;
                break;
            case '\u001B':
                controller = CONTROLS.EXIT;
                break;
            case (char)13:
                controller = CONTROLS.ENTER;
            break;
           
            default:
            //System.out.println("\u001B[" + 20 + ";" + 20 + "H");

                //System.out.println("\u001B[" + 0 + ";" + 0 + "H");
                break;

                
                
        }

        
        //System.out.println("KeyPressed:\t" + character);
        return controller;
    }
    public static void kill(){

        kill = true;
        try{
        term.reader().close();
        } catch(Exception e) {

            System.out.println("Error killing userInput thread:");
            System.out.println("\tCould not close term reader");
            System.out.println();
            System.out.println();
            System.out.println(e);

        } 
    }

    public void run(){

        
        while(!kill) {

            handleUserInput();


        }


    }
}
