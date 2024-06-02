package local.cs202gp.snake;

public class Menu {


    private CONTROLS controller;
    private char cursor;
    private boolean returnToGame;
    private final char[] frames = {'/', '-', '\\', '|'};
    private String[] options = {"[Resume]", "[Sound]", "[Music]", "[Credits]", "[Exit Game]"};
    private Color menuColor;
    private Color menuTextColor;


    ////////////////////////////////////////////////
    //  Monitor time
    ////////////////////////////////////////////
    final long Δt           =  100;
    private long currentTime        = System.currentTimeMillis();
    private long lastTime           = System.currentTimeMillis();
    private long lastMoveTime       = System.currentTimeMillis();


    private int cursorX;
    private int cursorY;

    private char cursorSelection;

    Menu(){

        cursor = '/';

        cursorSelection = 0;

        menuColor       = new Color((char)200,(char)200,(char)200);
        menuColor       = new Color((char)20,(char)20,(char)20);
        menuTextColor   = new Color((char)100,(char)200,(char)200);



    }


    public void OpenMenu(Screen s, UserInput keylogger, int x, int y, int width, int height){

        CONTROLS controller;
        int n = 0;
        while(!returnToGame){


            ////////////////////////////////////////////////
            //  update time
            ////////////////////////////////////////////
            currentTime = System.currentTimeMillis();
            long dt = currentTime - lastTime;

            ////////////////////////////////////////////////
            //  update user input
            ////////////////////////////////////////////
            controller = keylogger.getUserInput();

            



            ////////////////////////////////////////////////
            //  check if it is time to update game
            //  to next frame
            ////////////////////////////////////////////
            if(dt >= Δt){

                for(int j = 0; j < height; j++){
                    for(int i = 0; i < width; i++){
                        s.setPixelColor(x + i, y + j, menuColor);
                        s.setPixel(x + i, y + j, '█');
                        


                    }
                    
                }
                for(int i = 0; i < height; i++){
                    
                    s.setPixelColor(x, y + i, menuTextColor);
                    s.setPixelColor(x + width - 1, y + i, menuTextColor);



                    s.setPixel(x, y + i, '[');
                    s.setPixel(x + width - 1, y +i, ']');

                    


                }
                for(int i = 0; i < width; i++){
                    s.setPixelColor(x + i, y, menuTextColor);
                    s.setPixelColor(x + i, y + height - 1, menuTextColor);
                    s.setPixel(x + i, y, '=');
                    s.setPixel(x + i, y + height - 1, '=');
                }

                
                for(int i = 0; i < options.length; i++) {

                    for(int j = 0; j < options[i].length(); j++) {

                        s.setPixelColor( x + j + width/4, y + ((i+1) * height)/(options.length+1), menuTextColor);
                        s.setPixel( x + j + width/4, y + ((i+1) * height)/(options.length+1), options[i].charAt(j));


                    
                    }


                }


                s.setPixelColor( x + width/4 - 2, y + ((cursorSelection+1) * height)/(options.length+1), menuTextColor);
                s.setPixel( x  + width/4 - 2, y + ((cursorSelection+1) * height)/(options.length+1), frames[n]);
                n = (n + 1)%frames.length;


                
                

                s.display();


                switch(controller) {

                    case UP:
                        if(cursorSelection != 0)
                        cursorSelection = (char)(((int)cursorSelection - 1)%(options.length-1));

                        UserInput.seenControl();
                        
                    break;
                    case DOWN:
                    
                        cursorSelection = (char)(((int)cursorSelection + 1)%(options.length));

                        UserInput.seenControl();
                        
                    break;

                    case ENTER:
                        switch(cursorSelection){
                            
                            case (char)0:
                                returnToGame = true;
                            break;
                            case (char)4:
                                System.exit(0);
                            break;

                        }

                    break;
                    
                }
                

                
            }
            
        }
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                s.setPixel(x + i, y + j, '█');

                


            }
        }
    }



    
}
