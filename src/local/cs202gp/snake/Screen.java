package local.cs202gp.snake;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Screen {
    ////////////////////////////////////////////
    // useful constants
    ////////////////////////////////////////////
    public static final String ESC = "\u001B";

    ////////////////////////////////////////////
    // MetaData
    ////////////////////////////////////////////
    private int width = 100;
    private int height = 100;
    private String clrString;
    private StringBuffer buffer;

    ////////////////////////////////////////////
    // bufferInfo
    ////////////////////////////////////////////
    private int[][] r, g, b;
    private char bkgrndR, bkgrndG, bkgrndB;
    private char bckgrndChar = '@';

    ////////////////////////////////////////////
    // constructors
    ////////////////////////////////////////////
    Screen(int w, int h) {
        width = w;
        height = h;
        if (w < 0 || h < 0) {
            throw new IllegalArgumentException();
        }
        createBuffers();
    }

    Screen() {
        width = 50;
        height = 30;
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
        createBuffers();
    }

    private String getPixelString(int x, int y) {
        return ESC + "[38;2;" + r[x][y] + ";" + g[x][y] + ";" + b[x][y] + "m" + buffer.charAt(y * width + x);
    }

    private String getPixelString(int x, int y, char c) {
        return ESC + "[38;2;" + r[x][y] + ";" + g[x][y] + ";" + b[x][y] + "m" + c;
    }

    private void createBuffers() {
        clrString = "";
        String aux = "";

        r = new int[width][height];
        g = new int[width][height];
        b = new int[width][height];

        bkgrndR = 105;
        bkgrndG = 105;
        bkgrndB = 205;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                clrString += " ";
                aux += bckgrndChar;
                r[x][y] = bkgrndR;
                g[x][y] = bkgrndG;
                b[x][y] = bkgrndB;

                // buffer += ESC + "[38;2;" + 0 + ";"+ 0 + ";"+ 0 + "m ";

            }
            clrString += "\n";

        }

        buffer = new StringBuffer(aux);

    };

    private void moveToPixel(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
        System.out.println("\u001B[" + x + ";" + y + "H");
    }

    public void clear() {

        moveToPixel(0, 0);
        System.out.println(clrString);

    }

    public void display() {
        String disp = "";
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

        moveToPixel(0, 0);
        drawTopLine(log);

        try {
            drawMainScreen(log);
            drawBottomLine(log);
            log.flush();
        } catch (IOException e) {

        }

        // moveToPixel(0,0);
        // System.out.println(buffer);

    }

    public void setPixelColor(int x, int y, char r, char g, char b) {
        this.r[x][y] = r;
        this.g[x][y] = g;
        this.b[x][y] = b;
    }

    public void setPixelColor(int x, int y, Color c) {
        if (x < width && y < height) {
            this.r[x][y] = c.getR();
            this.g[x][y] = c.getG();
            this.b[x][y] = c.getB();
        }
    }

    public int getScreenWidth() {
        return width;
    }

    public int getScreenHeight() {
        return height;
    }

    public void setbackgroundColor(char r, char g, char b) {
        bkgrndR = r;
        bkgrndG = g;
        bkgrndB = b;
    }

    public void setPixel(int x, int y, char val) {
        if (y * (width) + x < buffer.length())
            buffer.setCharAt(y * (width) + x, val);
    }

    public void clearBuffer() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                r[x][y] = bkgrndR;
                g[x][y] = bkgrndG;
                b[x][y] = bkgrndB;

            }
        }
    }

    public void clearBufferAsGradient(
            Color color1,
            Color color2) {

        float dr = ((float) color2.getR() - (float) color1.getR()) / (float) width;
        float dg = ((float) color2.getG() - (float) color1.getG()) / (float) width;
        float db = ((float) color2.getB() - (float) color1.getB()) / (float) height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                r[x][y] = (int) (color1.getR() + x * dr);
                g[x][y] = (int) (color1.getG() + x * dg);
                b[x][y] = (int) (color1.getB() + y * db);
                setPixel(x, y, bckgrndChar);
            }
        }
    }

    private void drawTopLine(BufferedWriter log) {
        try {
            log.write(getPixelString(0, 0, '╔'));

            String txt = "[SNAKE]";
            for (int x = 1; x < width / 2 - 2 - txt.length() / 2; x++) {
                log.write(getPixelString(x, 0, '═'));
            }

            log.write(txt);

            for (int x = width / 2 - 1; x < width - 1 - txt.length() / 2; x++) {
                log.write(getPixelString(x, 0, '═'));
            }

            log.write(getPixelString(width - 1, 0, '╗'));
            log.write('\n');
        } catch (IOException e) {

        }

    }

    private void drawBottomLine(BufferedWriter log) {
        try {
            log.write(getPixelString(0, height - 1, '╚'));
            for (int x = 1; x < width - 1; x++) {
                log.write(getPixelString(x, height - 1, '═'));
            }

            log.write(getPixelString(width - 1, height - 1, '╝'));
            log.write('\n');
        } catch (IOException e) {

        }

    }

    private void drawMainScreen(BufferedWriter log) {
        try {
            for (int y = 1; y < height - 1; y++) {
                log.write(getPixelString(0, y, '║'));

                for (int x = 1; x < width - 1; x++) {
                    log.write(getPixelString(x, y));
                }
                log.write(getPixelString(width - 1, y, '║'));
                log.write("\n");

            }

        } catch (IOException e) {

        }
    }
}
