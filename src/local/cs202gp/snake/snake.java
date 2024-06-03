package local.cs202gp.snake;
import java.util.ArrayList;




////////////////////////////////////////////////
// snek live in boot
////////////////////////////////////////////
public class snake{

    ////////////////////////////////////////////////
    //  Queue of points where snake body is
    ////////////////////////////////////////////
    ArrayList<point> points;

    int width, height;


    ////////////////////////////////////////////////
    //  initializer
    ////////////////////////////////////////////
    snake(int x, int y, int width, int height) {

        this.width = width;
        this.height = height;

        points = new ArrayList<point>();
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
        for(int i = points.size() - 1; i >= 1; i--) {

            point next = new point(points.get(i - 1).getX(), points.get(i - 1).getY());
            
            points.set(i, next);
            
        }


        points.get(0).moveWithWrap(d, width, height);

        

    }

    public  boolean canMove(Direction d){

        ////////////////////////////////////////////////
        //  move all points up one except last
        ////////////////////////////////////////////
        point test = new point (points.get(0).getX(), points.get(0).getY());

        test.moveWithWrap(d, width, height);

        return !hasPoint(test);
        
    }

    public void moveOnApple(Direction d){

        ////////////////////////////////////////////////
        //  ad extra point at the tail
        ////////////////////////////////////////////
        int l = points.size() - 1;
        point firstPoint = new point(points.get(l).getX(), points.get(l).getY());
        firstPoint.moveWithWrap(d, width, height);

        points.add(firstPoint);
        //points.add(new point(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY()));
        

        ////////////////////////////////////////////////
        //  move head in direction
        ////////////////////////////////////////////
        //points.get(0).moveWithWrap(d, width, height);
    }

    public boolean hasPoint(point p){


        for(int i = 0; i < points.size(); i++) {


            if (
                p.getX() == points.get(i).getX()    &&
                p.getY() == points.get(i).getY()
                ) {

                    return true;
                }
        }
        return false;
    }


    ////////////////////////////////////////////////
    // getter methods
    ////////////////////////////////////////////
    public point getPoint(int i){

        if( i >= points.size()) {

            throw new IllegalArgumentException();
        }

        return points.get(i);
    }

    public int getLength(){

        return points.size();
    }


    public int getheadX(){

        return points.get(0).getX();

    }

    public int getheadY(){

        return points.get(0).getY();

    }

}
