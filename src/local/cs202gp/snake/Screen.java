package local.cs202gp.snake;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Screen {


    ////////////////////////////////////////////////
    //  useful constants
    ////////////////////////////////////////////
    public static final String ESC = "\u001B";

    ////////////////////////////////////////////////
    //  MetaData
    ////////////////////////////////////////////
    private int width = 100;
    private int height = 100;
    private String clrString;
    private StringBuffer buffer;


    

    ////////////////////////////////////////////////
    //  bufferInfo
    ////////////////////////////////////////////
    private int[][] r, g, b;
    private char bkgrndR, bkgrndG, bkgrndB;
    private char bckgrndChar = '█';

    ////////////////////////////////////////////////
    //constructors
    ////////////////////////////////////////////
    Screen(int w, int h){

        width = w;
        height = h;

        if(w < 0 || h < 0) {

            throw new IllegalArgumentException();
        }

        createBuffers();


    }

    Screen(){

        width = 50;
        height = 30;

        if(width < 0 || height < 0) {

            throw new IllegalArgumentException();
        }

        createBuffers();


    }


    private void createBuffers(){

        
        clrString   = "";
        String aux = "";
        

        r = new int[width][height];
        g = new int[width][height];
        b = new int[width][height];

        bkgrndR = 105;
        bkgrndG = 105;
        bkgrndB = 205;




        for(int x =0; x < width; x++) {
            for(int y =0; y < height; y++) {

                clrString   += " ";
                aux += bckgrndChar;
                r[x][y] = bkgrndR;
                g[x][y] = bkgrndG;
                b[x][y] = bkgrndB;


                //buffer      += ESC + "[38;2;" + 0 + ";"+ 0 + ";"+ 0 + "m ";

            
            }
            clrString   += "\n";
            
            

        }

        buffer = new StringBuffer(aux);


    };




    
    
    private void moveToPixel(int x, int y){

        if(x < 0 || y < 0) {

            throw new IllegalArgumentException();
        }

        System.out.println("\u001B[" + x + ";" + y + "H");



    }

    
    public void clear() {

        moveToPixel(0,0);
        System.out.println(clrString);
        

    }
    public void display() {



        //ESC[={value}h
        String disp = "";

        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        moveToPixel(0,0);
        for(int y =0; y < height; y++){
            for(int x = 0; x < width; x++){
                //System.out.print("*");

                disp += (ESC + "[38;2;" + r[x][y] + ";"+ g[x][y] + ";"+ b[x][y] + "m" + buffer.charAt(y*width + x));
            }
            disp += "\n";
        }
        try{
            log.write(disp);

            log.flush();
        } catch(IOException e){


        }

        
        //moveToPixel(0,0);
        //System.out.println(buffer);
        

    }

    public void setPixelColor(int x, int y, char r, char g, char b){

        this.r[x][y] = r;
        this.g[x][y] = g;
        this.b[x][y] = b;


    }
    public void setPixelColor(int x, int y, Color c){

        if(x < width && y < height){
            this.r[x][y] = c.getR();
            this.g[x][y] = c.getG();
            this.b[x][y] = c.getB();
        }


    }


    public int getScreenWidth(){

        return width;
    }

    public int getScreenHeight(){

        return height;
    }
    public void setbackgroundColor(char r, char g, char b){

        bkgrndR = r;
        bkgrndG = g;
        bkgrndB = b;


    }

    public void setPixel(int x, int y, char val){

        if(y * (width) + x < buffer.length())
            buffer.setCharAt(y * (width) + x, val);


    }


    public void clearBuffer(){

        for(int x = 0; x < width; x++){

            for(int y = 0; y < height; y++){
                r[x][y] = bkgrndR;
                g[x][y] = bkgrndG;
                b[x][y] = bkgrndB;
            
            }

        }
        


    }


    public void clearBufferAsGradient
    (
        char r1, 
        char g1, 
        char b1, 
        char r2,
        char g2,
        char b2
        
    ){

        float dr = ((float)r2 - (float)r1)/(float)width;
        float dg = ((float)g2 - (float)g1)/(float)width;
        float db = ((float)b2 - (float)b1)/(float)height;

        for(int x = 0; x < width; x++){

            for(int y = 0; y < height; y++){

                
                r[x][y] = (int)(r1 + x * dr);
                g[x][y] = (int)(g1 + x * dg);
                b[x][y] = (int)(b1 + y * db);
            
            }

        }
        


    }
    public void clearBufferAsGradient
    (
        Color color1,
        Color color2
        
    ){

        float dr = ((float)color2.getR() - (float)color1.getR())/(float)width;

        float dg = ((float)color2.getG() - (float)color1.getG())/(float)width;
        float db = ((float)color2.getB() - (float)color1.getB())/(float)height;

        for(int x = 0; x < width; x++){

            for(int y = 0; y < height; y++){

                
                r[x][y] = (int)(color1.getR() + x * dr);
                g[x][y] = (int)(color1.getG() + x * dg);
                b[x][y] = (int)(color1.getB() + y * db);
            
            }

        }
        


    }


    public void clearBufferAsGradientDistance
    (
        Color color1,
        Color color2
        
    ){

        double dMax = Math.sqrt(width * width + height * height);
        double dr = ((double)color2.getR() - (double)color1.getR())/dMax;
        double dg = ((double)color2.getG() - (double)color1.getG())/dMax;
        double db = ((double)color2.getB() - (double)color1.getB())/dMax;

        for(int x = 0; x < width; x++){

            for(int y = 0; y < height; y++){

                double d = Math.sqrt(x * x + y * y);
                r[x][y] = (int)(color1.getR() + d * dr);
                g[x][y] = (int)(color1.getG() + d * dg);
                b[x][y] = (int)(color1.getB() + d * db);
            
            }

        }
        


    }

}
