package net.slashie.libjcsi.wswing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.Hashtable;
import net.slashie.libjcsi.*;
import net.slashie.util.*;

public class WSwingConsoleInterface implements ConsoleSystemInterface, Runnable, ComponentListener {

    /** Provides Console IO.
     * Returns keystrokes as CharKeys.
     * Shows the characters in a Frame
     */    //Relations
    private SwingConsoleFrame targetFrame; //To get the keypresses from the AWT Model
    private SwingConsolePanel targetPanel; //To output characters
    private StrokeInformer aStrokeInformer; // Object to which strokes are informed

    // Attributes
    private int xpos,  ypos;
    /** Current printing cursor position */
    //private boolean autorefresh;

    // Static Attributes
    public static Font consoleFont;
    public static int xdim = 80;
    public static int ydim = 25;
    private CSIColor[][] colors;
    private char[][] chars;
    private CSIColor[][] colorsBuffer;
    private char[][] charsBuffer;
    private CSIColor backColor = CSIColor.BLACK;
    private CSIColor frontColor = CSIColor.WHITE;
    private Position caretPosition = new Position(0, 0);
    private boolean sandboxDeploy;
    private Hashtable<CSIColor, Color> colorTable = new Hashtable<CSIColor, Color>();

    public WSwingConsoleInterface(String windowName, boolean sandboxDeploy) {
        this.sandboxDeploy = sandboxDeploy;
        aStrokeInformer = new StrokeInformer();
        targetFrame = new SwingConsoleFrame(windowName);
        java.awt.Dimension initialSize = new java.awt.Dimension(1208, 1024);
        int fontSize = defineFontSize(initialSize.height, initialSize.width);
        String strConsoleFont = loadFont();
        consoleFont = new Font(strConsoleFont, Font.PLAIN, fontSize);
        //targetFrame.setUndecorated(true);
        targetFrame.init(consoleFont, xdim, ydim);


        colors = new CSIColor[xdim][ydim];
        chars = new char[xdim][ydim];
        colorsBuffer = new CSIColor[xdim][ydim];
        charsBuffer = new char[xdim][ydim];

        targetPanel = targetFrame.getSwingConsolePanel();
        targetFrame.addKeyListener(aStrokeInformer);
        targetFrame.addComponentListener(this);


        targetFrame.setSize((int) (fontSize * xdim * 0.7 * 1.05), (int) (fontSize * ydim * 1.15));
        //targetFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//        targetFrame.setLocation(0, 0);
        targetFrame.setLocationRelativeTo(null); // places window in center of screen
        locate(1, 1);

        targetFrame.setVisible(true);
    }

    public void flash(int color) {
        //targetPanel.flash(getColorFromCode(color));
    }
    
    private Color colorPreProcess(CSIColor b){
        if (!colorTable.containsKey(b)){
            colorTable.put(b, new Color(b.getColor()));
        }
            return colorTable.get(b);
    }
    
    /**
     * This flushes the internal Hashtable for the colors.
     * Should be called when changing the map drasticaly, such as during a level change event.
     */
    public void flushColorTable(){
        colorTable.clear();
    }

    public void cls() {
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                chars[x][y] = ' ';
                colors[x][y] = backColor;
            }
        }
        targetPanel.cls();

    }

    public void locate(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void refresh() {
        targetFrame.repaint();

    }

    public void print(int x, int y, String what, int color) {
        locate(x, y);

        print(x, y, what, getColorFromCode(color));
    }

    public void print(int x, int y, String what, CSIColor color) {
        locate(x, y);

        for (int i = 0; i < what.length(); i++) {
            if (xpos >= xdim) {
                xpos = 0;
                ypos++;
            }
            if (ypos >= ydim) {
                break;
            }
            targetPanel.plot(what.charAt(i), xpos, ypos, colorPreProcess(color));
            chars[x + i][y] = what.charAt(i);
            colors[x + i][y] = color;
            xpos++;
        }
    }

    public void print(int x, int y, char what, int color) {
        locate(x, y);
        CSIColor front = getColorFromCode(color);
        print(x, y, what, front);
    }

    public void print(int x, int y, char what, CSIColor color) {
        locate(x, y);
        if (chars[x][y] == what && colors[x][y] == color) {
            return;
        }
        targetPanel.plot(what, xpos, ypos, colorPreProcess(color));
        colors[x][y] = color;
        chars[x][y] = what;
    }

    public void print(int x, int y, String what) {
        print(x, y, what, frontColor);
    }

    public void locateCaret(int x, int y) {
        caretPosition.x = x;
        caretPosition.y = y;
    }

    public String input() {
        return input(9999);
    }

    public String input(int l) {
        String ret = "";
        CharKey read = new CharKey(CharKey.NONE);
        while (true) {
            while (read.code == CharKey.NONE) {
                read = inkey();
            }
            if (read.isMetaKey()) {
                read.code = CharKey.NONE;
                continue;
            }
            if (read.code == CharKey.ENTER) {
                return ret;
            }
            if (read.code == CharKey.BACKSPACE) {
                if (ret.equals("")) {
                    read.code = CharKey.NONE;
                    continue;
                }
                if (ret.length() > 1) {
                    ret = ret.substring(0, ret.length() - 1);
                } else {
                    ret = "";
                }
                caretPosition.x--;
                print(caretPosition.x, caretPosition.y, " ");

            } else {
                if (ret.length() >= l) {
                    read.code = CharKey.NONE;
                    continue;
                }
                String nuevo = read.toString();
                print(caretPosition.x, caretPosition.y, nuevo);
                ret += nuevo;
                caretPosition.x++;
            }
            refresh();
            read.code = CharKey.NONE;

        }
    //return ret;
    }

    public synchronized void refresh(Thread toNotify) {
        refresh();

        toNotify.interrupt();
    }

    public synchronized CharKey inkey() {
        aStrokeInformer.informKey(Thread.currentThread());
        try {
            this.wait();
        } catch (InterruptedException ie) {
        }
        CharKey ret = new CharKey(aStrokeInformer.getInkeyBuffer());
        return ret;
    }

    public int getColor(String colorName) {
        if (colorName == null) {
            return -1;
        }
        if (colorName.equals("BLACK")) {
            return BLACK;
        }
        if (colorName.equals("DARK_BLUE")) {
            return DARK_BLUE;
        }
        if (colorName.equals("GREEN")) {
            return GREEN;
        }
        if (colorName.equals("TEAL")) {
            return TEAL;
        }
        if (colorName.equals("DARK_RED")) {
            return DARK_RED;
        }
        if (colorName.equals("PURPLE")) {
            return PURPLE;
        }
        if (colorName.equals("BROWN")) {
            return BROWN;
        }
        if (colorName.equals("LIGHT_GRAY")) {
            return LIGHT_GRAY;
        }
        if (colorName.equals("GRAY")) {
            return GRAY;
        }
        if (colorName.equals("BLUE")) {
            return BLUE;
        }
        if (colorName.equals("LEMON")) {
            return LEMON;
        }
        if (colorName.equals("CYAN")) {
            return CYAN;
        }
        if (colorName.equals("RED")) {
            return RED;
        }
        if (colorName.equals("MAGENTA")) {
            return MAGENTA;
        }
        if (colorName.equals("YELLOW")) {
            return YELLOW;
        }
        if (colorName.equals("WHITE")) {
            return WHITE;
        }
        return -1;
    }

    private CSIColor getColorFromCode(int code) {
        switch (code) {
            case BLACK:
                return CSIColor.BLACK;
            case DARK_BLUE:
                return CSIColor.DARK_BLUE;
            case GREEN:
                return CSIColor.GREEN;
            case TEAL:
                return CSIColor.TEAL;
            case DARK_RED:
                return CSIColor.DARK_RED;
            case PURPLE:
                return CSIColor.PURPLE;
            case BROWN:
                return CSIColor.BROWN;
            case LIGHT_GRAY:
                return CSIColor.LIGHT_GRAY;
            case GRAY:
                return CSIColor.GRAY;
            case BLUE:
                return CSIColor.BLUE;
            case LEMON:
                return CSIColor.LEMON;
            case CYAN:
                return CSIColor.CYAN;
            case RED:
                return CSIColor.RED;
            case MAGENTA:
                return CSIColor.MAGENTA;
            case YELLOW:
                return CSIColor.YELLOW;
            case WHITE:
                return CSIColor.WHITE;
            default:
                return null;
        }
    }

    private int getCodeFromColor(CSIColor color) {
        if (color.equals(CSIColor.BLACK)) {
            return BLACK;
        } else if (color.equals(CSIColor.DARK_BLUE)) {
            return DARK_BLUE;
        } else if (color.equals(CSIColor.GREEN)) {
            return GREEN;
        } else if (color.equals(CSIColor.TEAL)) {
            return TEAL;
        } else if (color.equals(CSIColor.DARK_RED)) {
            return DARK_RED;
        } else if (color.equals(CSIColor.PURPLE)) {
            return PURPLE;
        } else if (color.equals(CSIColor.BROWN)) {
            return BROWN;
        } else if (color.equals(CSIColor.LIGHT_GRAY)) {
            return LIGHT_GRAY;
        } else if (color.equals(CSIColor.GRAY)) {
            return GRAY;
        } else if (color.equals(CSIColor.BLUE)) {
            return BLUE;
        } else if (color.equals(CSIColor.LEMON)) {
            return LEMON;
        } else if (color.equals(CSIColor.CYAN)) {
            return CYAN;
        } else if (color.equals(CSIColor.RED)) {
            return RED;
        } else if (color.equals(CSIColor.MAGENTA)) {
            return MAGENTA;
        } else if (color.equals(CSIColor.YELLOW)) {
            return YELLOW;
        } else if (color.equals(CSIColor.WHITE)) {
            return WHITE;
        } else {
            return 0;
        }
    }

    public void setAutoRefresh(boolean value) {
        targetPanel.setAutoUpdate(value);
    }

    public char peekChar(int x, int y) {
        return targetPanel.peekChar(x, y);
    }

    public int peekColor(int x, int y) {
        return getCodeFromColor(colors[x][y]);
    }
    
        public CSIColor peekCSIColor(int x, int y) {
        return colors[x][y];
    }

    private String loadFont() {
        if (!sandboxDeploy) {
            BufferedReader br = null;
            try {
                br = FileUtil.getReader("font.scb");
                String fnt = br.readLine();
                br.close();
                if (!fnt.equals("NFE")) {
                    System.out.println("Using font " + fnt);
                    return fnt;
                }
            } catch (IOException ioe) {
                System.out.println("Error reading font file: " + ioe.getMessage());
                try {
                    br.close();
                } catch (IOException ioe2) {
                    System.out.println("Error reading font file: " + ioe2.getMessage());
                }
            }
        } else {
            return "Monospaced";
        }

        String x[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        boolean lucida = false, courier = false;
        for (int i = 0; i < x.length; i++) {
            if (x[i].equals("Lucida Console")) {
                lucida = true;
            } else if (x[i].equals("Courier New")) {
                courier = true;
            }
        }
        if (courier) {
            return "Courier New";
        } else if (lucida) {
            return "Lucida Console";
        }
        return "Monospaced";

    }

    private int defineFontSize(int scrHeight, int scrWidth) {
        int byHeight = (int) (scrHeight / ydim);
        int byWidth = (int) (scrWidth / (xdim * 0.8));

        if (byHeight < byWidth) {
            return byHeight;
        } else {
            return byWidth;
        }
    }

    public void run() {
    }

    public boolean isInsideBounds(Position p) {
        return p.x >= 0 && p.x <= xdim && p.y >= 0 && p.y <= ydim;
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && x <= xdim - 1 && y >= 0 && y <= ydim - 1;
    }

    public void safeprint(int x, int y, char what, int color) {
        if (isInsideBounds(x, y)) {
            print(x, y, what, color);
        }
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        int fontSize = defineFontSize(((Component) e.getSource()).getHeight(), ((Component) e.getSource()).getWidth());
        String strConsoleFont = loadFont();
        consoleFont = new Font(strConsoleFont, Font.PLAIN, fontSize);
        targetFrame.setFont(consoleFont);
        targetPanel.setFont(consoleFont);

    }

    public void componentShown(ComponentEvent e) {
    }

    public void waitKey(int keyCode) {
        CharKey x = new CharKey(CharKey.NONE);
        while (x.code != keyCode) {
            x = inkey();
        }
    }

    public void restore() {
        /*for (int i = 0; i < colors.length; i++){
        System.arraycopy(colorsBuffer[i], 0, colors[i], 0, colors[i].length-1);
        System.arraycopy(charsBuffer[i], 0, chars[i], 0, colors[i].length-1);
        }*/
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                this.print(x, y, charsBuffer[x][y], colorsBuffer[x][y]);
            }
        }
    }

    public void saveBuffer() {
        for (int i = 0; i < colors.length; i++) {
            System.arraycopy(colors[i], 0, colorsBuffer[i], 0, colors[i].length - 1);
            System.arraycopy(chars[i], 0, charsBuffer[i], 0, colors[i].length - 1);
        }
    }
}