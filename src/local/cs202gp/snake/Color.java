package local.cs202gp.snake;

public class Color{

    private char r;
    private char g;
    private char b;
    private int dr = 1;
    private int dg = 1;
    private int db = 1;



    Color(){

        r = (char)255;
        g = (char)255;
        b = (char)255;


    }

    Color(char r, char g, char b){

        this.r = r;
        this.g = g;
        this.b = b;



    }


    public void set(char r, char g, char b){

        this.r = r;
        this.g = g;
        this.b = b;



    }

    public char getR(){

        return r;
    }
    public char getG(){

        return g;
    }
    public char getB(){

        return b;
    }

    public void setR(char r){

        this.r += dr;
    }
    public void setG(char g){

        this.g = g;
    }
    public void setB(char b){

        this.b = b;
    }

    public void ΔR(){

        this.r += dr;

        if(r == 255) {

            dr = -1;
        }

        if(r == 0) {

            dr = 1;
        }

    }
    public void ΔG(){

        this.g += dg;

        if(g == 255) {

            dg = -1;
        }

        if(g == 0) {

            dg = 1;
        }
    }
    public void ΔB(){

        this.b += db;
        if(b == 255) {

            db = -1;
        }

        if(b == 0) {

            db = 1;
        }
    }




    
}
