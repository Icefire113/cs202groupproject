package local.cs202gp.snake;

import java.util.Random;



public class SnakeGame {




    private snake snek;

    private int gameWidth, gameHeight;

    private int pixelWidth, pixelHeight;

    private int appleX, appleY;

    Direction last;
    
    
    SnakeGame(Screen s) {

        last = Direction.UP;

        Random rand = new Random();

        int n = 2;
        gameWidth   = (70-2)/n;
        gameHeight  = ((35-2))/n;

        appleX = rand.nextInt(gameWidth);
        appleY = rand.nextInt(gameHeight);

        snek = new snake(10, 10, gameWidth, gameHeight);


        pixelWidth  = s.getScreenWidth()/gameWidth;
        pixelHeight = s.getScreenHeight()/gameHeight;






    }

    private boolean isOpposite(Direction d1, Direction d2){

        switch(d1) {

            case LEFT:
                return d2 == Direction.RIGHT;

            case RIGHT:
                return d2 == Direction.LEFT;
            case UP:
                return d2 == Direction.DOWN;
            case DOWN:
                return d2 == Direction.UP;
            default:
            return false;


        }


    }

    public boolean update(Screen s, Direction dir){

        if(isOpposite(dir, last) && snek.getLength() !=1){
            dir = last;
        }
        else{
            last = dir;
        }

        if(snek.canMove(dir)){

            if(
                snek.getheadX() == appleX &&
                snek.getheadY() == appleY 
                )
                {
                    
                    StdAudio.play("Sounds/apple.wav");

                    Random rand = new Random();

                    while(snek.hasPoint(new point(appleX, appleY))) {

                        appleX = rand.nextInt(gameWidth);
                        appleY = rand.nextInt(gameHeight);

                    }


                    snek.moveOnApple(dir);
                    
                    


                }
            drawApple(s);
            drawSnake(s);
            snek.move(dir);
            
            return false;


        }
        drawApple(s);
        drawSnake(s);
        s.display();
        StdAudio.play("Sounds/death.wav");



        return true;




    }

    private void drawApple(Screen s){


        int x = appleX * pixelWidth;
        int y = appleY * pixelHeight;

        Color red = new Color(220,0,0);

        for(int i =0; i < pixelWidth; i++){


            for(int j = 0; j < pixelHeight; j++){

                s.setPixelColor(x + i + 1, y + j + 1, red);
                //s.setPixel(x + i + 1, y + j + 1, '@');



            }


        }
    }

    private void drawSnake(Screen s){


        for(int a =0; a<snek.getLength(); a++){
            
            int x = snek.getPoint(a).getX() * pixelWidth;
            int y = snek.getPoint(a).getY() * pixelHeight;

            Color green = new Color(0,150 * (a % 2),0);

            for(int i =0; i < pixelWidth; i++){


                for(int j = 0; j < pixelHeight; j++){

                    
                    s.setPixelColor(x + i + 1, y + j + 1, green);
                    s.setPixel(x + i + 1, y + j + 1, '▚');



                }


            }

        }
    }
}
