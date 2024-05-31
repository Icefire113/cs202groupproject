
public class Screen {

    private int width = 100;
    private int height = 100;
    private String clrString;
    private String buffer;
    private final String ESC = "\u001B";

    

    private void createBuffers(){

        
        clrString   = "";
        buffer      = "";
        for(int x =0; x < width; x++) {
            for(int y =0; y < height; y++) {

                clrString   += " ";
                buffer      += ESC + "[38;2;" + 0 + ";"+ 0 + ";"+ 0 + "m ";

            
            }
            clrString   += "\n";
            buffer      += "\n";
            

        }


    };





    Screen(int w, int h){

        if(w < 0 || h < 0) {

            throw new IllegalArgumentException();
        }

        createBuffers();


    }
    
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

        System.out.println("\u001B[=0h   Hello");
        System.out.println();
        //moveToPixel(0,0);
        //System.out.println(buffer);
        

    }


    

}
