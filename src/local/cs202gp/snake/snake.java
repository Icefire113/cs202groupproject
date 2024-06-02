package local.cs202gp.snake;
//  Snek, lives in bewt
////////////////////////////////////////////
import java.util.ArrayList;

public class snake{

    ////////////////////////////////////////////////
    //  Queue of points where snake body is
    ////////////////////////////////////////////
    ArrayList<point> points;


    ////////////////////////////////////////////////
    //  initializer
    ////////////////////////////////////////////
    snake(int x, int y) {


        point head = new point(x, y);

        points.add(head);
    }


    ////////////////////////////////////////////////
    //  move snake one in direction specified
    ////////////////////////////////////////////
    public void move(Direction d){

        ////////////////////////////////////////////////
        //  move all points up one except last
        ////////////////////////////////////////////
        for(int i = points.size() - 1; i > 1; i--) {

            point next = points.get(i - 1);
            
            points.set(i, next);
        }


        points.get(0).move(d);



    }









    


    
}
