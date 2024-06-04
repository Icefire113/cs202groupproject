package local.cs202gp.snake;

////////////////////////////////////////////
//  Color class implemented to make code 
//      more readable/mofdifiable
////////////////////////////////////////////
public class Color {

    ////////////////////////////////////////////
    // 8 bit color data
    ////////////////////////////////////////////
    private char r;
    private char g;
    private char b;

    ////////////////////////////////////////////
    // used to calculate delta functions,
    // allows for colors to gradually
    // change over time (should only
    // ever store values of +1 or -1)
    ////////////////////////////////////////////
    private int dr = 1;
    private int dg = 1;
    private int db = 1;

    ////////////////////////////////////////////
    // constructors (default color is white)
    ////////////////////////////////////////////
    Color() {
        r = (char) 255;
        g = (char) 255;
        b = (char) 255;
    }

    Color(char r, char g, char b) {
        this.r = r;
        this.g = g;
        this.b = b;

    }

    Color(int r, int g, int b) {
        this.r = (char) r;
        this.g = (char) g;
        this.b = (char) b;
    }

    ////////////////////////////////////////////
    // mutator
    ////////////////////////////////////////////
    public void set(char r, char g, char b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    ////////////////////////////////////////////
    // mutators for individual colors
    ////////////////////////////////////////////

    public void setR(char r) {
        this.r += dr;
    }

    public void setG(char g) {
        this.g = g;
    }

    public void setB(char b) {
        this.b = b;
    }

    ////////////////////////////////////////////
    // accessor methods
    ////////////////////////////////////////////
    public char getR() {
        return r;
    }

    public char getG() {
        return g;
    }

    public char getB() {
        return b;
    }

    ////////////////////////////////////////////
    // delta fucntions allow you to change
    // color over time this is mostly
    // used for aesthetic purposes in
    // the background
    ////////////////////////////////////////////

    public void deltaR() {
        this.r += dr;

        if (r == 255) {
            dr = -1;
        }

        if (r == 0) {
            dr = 1;
        }
    }

    public void deltaG() {
        this.g += dg;

        if (g == 255) {
            dg = -1;
        }

        if (g == 0) {
            dg = 1;
        }
    }

    public void deltaB() {
        this.b += db;
        if (b == 255) {
            db = -1;
        }

        if (b == 0) {
            db = 1;
        }
    }

}
