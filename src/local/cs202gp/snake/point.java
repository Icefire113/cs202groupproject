package local.cs202gp.snake;

////////////////////////////////////////////////
//  Point class mostly used to chech to see
//      if the point in game is snake or
//      apple
////////////////////////////////////////////

public class point {

    ////////////////////////////////////////////////
    //  data
    ////////////////////////////////////////////
    int x, y, z;

    ////////////////////////////////////////////////
    //  constructors
    ////////////////////////////////////////////

    point(){

        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    point(int x, int y){

        this.x = x;
        this.y = y;
        this.z = 0;
    }

    point(int x, int y, int z){

        this.x = x;
        this.y = y;
        this.z = z;
    }

    ////////////////////////////////////////////////
    //  genral methods
    ////////////////////////////////////////////
    public void move(Direction d){

        switch(d){

            case UP:
                y++;
            break;
            case DOWN:
                y--;
            break;
            case RIGHT:
                x++;
            break;
            case LEFT:
                x--;
            break;



        }
    };

    ////////////////////////////////////////////////
    //  mutators and accessors
    ////////////////////////////////////////////
    public void move(int x, int y){

        this.x = x;
        this.y = y;
    };

    public void move(int x, int y, int z){

        this.x = x;
        this.y = y;
        this.z = z;
    };


    public int getX(){

        return x;
    }

    public int getY(){

        return y;
    }


    public int getZ(){

        return z;
    }
   
    




    
}
