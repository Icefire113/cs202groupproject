package local.cs202gp.snake;

public class Menu {


    ////////////////////////////////////////////////
    //  constants and disply info
    ////////////////////////////////////////////
    private final char[] frames = {'/', '-', '\\', '|'};
    private final String[] options = 
    {
        "[Resume]", 
        "[Sound]", 
        "[Music]", 
        "[Credits]", 
        "[Exit Game]"
    };


    private CONTROLS controller;
    private char cursor;
    private boolean returnToGame;
    
    private Color menuColor;
    private Color menuTextColor;

    private int currentFrame = 0;


    ////////////////////////////////////////////////
    //  Monitor time
    ////////////////////////////////////////////
    final long Δt           =  50;
    private int Δframe;

    private long currentTime        = System.currentTimeMillis();
    private long lastTime           = System.currentTimeMillis();
    private long lastMoveTime       = System.currentTimeMillis();


    private int cursorX;
    private int cursorY;


    private int width;
    private int height;

    private int x;
    private int y;


    private char cursorSelection;

    Menu(int x, int y, int width, int height){


        StdAudio.play("Sounds/menuopen.wav");


        this.width = width;
        this.height = height;

        this.x = x;
        this.y = y;

        Δframe = 0;
        cursor = '/';

        cursorSelection = 0;

        menuColor       = new Color((char)20,(char)20,(char)20);
        menuTextColor   = new Color((char)100,(char)200,(char)200);





    }

    private void displayMenu(Screen s){


        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                s.setPixelColor(x + i, y + j, menuColor);
                s.setPixel(x + i, y + j, '█');
                


            }
            
        }
        for(int i = 0; i < height; i++){
            
            s.setPixelColor(x, y + i, menuTextColor);
            s.setPixelColor(x + width - 1, y + i, menuTextColor);



            s.setPixel(x, y + i, '║');
            s.setPixel(x + width - 1, y +i, '║');

            


        }
        for(int i = 0; i < width; i++){
            s.setPixelColor(x + i, y, menuTextColor);
            s.setPixelColor(x + i, y + height - 1, menuTextColor);
            s.setPixel(x + i, y, '═');
            s.setPixel(x + i, y + height - 1, '═');
        }//╚╝╔╗
        s.setPixel(x, y, '╔');
        s.setPixel(x + width - 1, y, '╗');
        s.setPixel(x, y + height - 1, '╚');
        s.setPixel(x + width - 1, y + height - 1, '╝');


        
        for(int i = 0; i < options.length; i++) {

            for(int j = 0; j < options[i].length(); j++) {

                s.setPixelColor( x + j + width/4, y + ((i+1) * height)/(options.length+1), menuTextColor);
                s.setPixel( x + j + width/4, y + ((i+1) * height)/(options.length+1), options[i].charAt(j));


            
            }


        }


        s.setPixelColor( x + width/4 - 2, y + ((cursorSelection+1) * height)/(options.length+1), menuTextColor);
        s.setPixel( x  + width/4 - 2, y + ((cursorSelection+1) * height)/(options.length+1), frames[currentFrame]);



    }


    public void OpenMenu(Screen s, UserInput keylogger){

        
        
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

                lastTime           = System.currentTimeMillis();

                displayMenu(s);
                

                

                currentFrame = (currentFrame + 1)%frames.length;
                    


                
                

                s.display();


                switch(controller) {



                    
                    case UP:
                        if(cursorSelection != 0){
                            cursorSelection = (char)(((int)cursorSelection - 1)%(options.length-1));
                        }
                        else{
                            cursorSelection = (char)(options.length-1);
                        }
                        UserInput.seenControl();

                        StdAudio.play("Sounds/menuclick.wav");

                        
                    break;
                    case DOWN:
                    
                        cursorSelection = (char)(((int)cursorSelection + 1)%(options.length));

                        UserInput.seenControl();

                        StdAudio.play("Sounds/menuclick.wav");

                        
                    break;

                    case LEFT:
                        StdAudio.play("Sounds/menubadkey.wav");
                        UserInput.seenControl();

                    break;
                    case RIGHT:
                        StdAudio.play("Sounds/menubadkey.wav");
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
