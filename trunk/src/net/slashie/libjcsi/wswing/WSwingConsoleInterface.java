package net.slashie.libjcsi.wswing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.HashMap;
import net.slashie.libjcsi.*;
import net.slashie.util.*;

/**
 * Swing interface for input and output.
 * Returns keystrokes as CharKeys.
 * Shows the characters in a Frame
 * @author Santiago Zapata
 * @author Eben Howard
 */
public class WSwingConsoleInterface implements ConsoleSystemInterface, Runnable, ComponentListener {

    private SwingConsoleFrame targetFrame; //To get the keypresses from the AWT Model
    private StrokeInformer aStrokeInformer; // Object to which strokes are informed

    // Attributes
    private int xpos,  ypos;
    /** Current printing cursor position */
    //private boolean autorefresh; //not currently implemented

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
    private HashMap<CSIColor, Color> colorMap = new HashMap<CSIColor, Color>();
    private FontMetrics fMetric;

    /**
     * Allows for setting the window's name and deploying as a
     * Java WebStart application.
     * @param windowName
     * @param sandboxDeploy true if intended at a Java Webstart application
     */
    public WSwingConsoleInterface(String windowName, boolean sandboxDeploy) {
        this.sandboxDeploy = sandboxDeploy;
        aStrokeInformer = new StrokeInformer();
        targetFrame = new SwingConsoleFrame(windowName);
        java.awt.Dimension initialSize = new java.awt.Dimension(1280, 1024);
        int fontSize = defineFontSize(initialSize.height, initialSize.width);
        String strConsoleFont = loadFont();
        consoleFont = new Font(strConsoleFont, Font.PLAIN, fontSize);
        targetFrame.init(consoleFont, xdim, ydim);

        colors = new CSIColor[xdim][ydim];
        chars = new char[xdim][ydim];
        colorsBuffer = new CSIColor[xdim][ydim];
        charsBuffer = new char[xdim][ydim];

        targetFrame.addKeyListener(aStrokeInformer);
        targetFrame.addComponentListener(this);
        fMetric = targetFrame.getFontMetrics(consoleFont);

        int x, y;
        x = fMetric.getMaxAdvance();
        y = fMetric.getHeight();
        if (!this.sandboxDeploy) {
            targetFrame.setSize((xdim * x) + x, (ydim * y) + y + y);
        } else {
            x = fMetric.charWidth('W');
            targetFrame.setSize(((xdim * x) + x), (ydim * y) + y + y);
        }
        targetFrame.setLocationRelativeTo(null); // places window in center of screen
        targetFrame.setResizable(false);
        locate(1, 1);

        targetFrame.setVisible(true);
    }

    /**
     * Flashes the output area a specified color.  Currently inoperable.
     * @param color
     */
    public void flash(int color) {
        //targetPanel.flash(getColorFromCode(color));
    }

    private Color colorPreProcess(CSIColor b) {
        if (!colorMap.containsKey(b)) {
            colorMap.put(b, new Color(b.getColor()));
        }
        return colorMap.get(b);
    }

    public void flushColorTable() {
        colorMap.clear();
    }

    public void cls() {
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                chars[x][y] = ' ';
                colors[x][y] = backColor;
            }
        }
        targetFrame.cls();
        flushColorTable();// might as well clean out the colorTable if the screen is going to be blank

    }

    public void locate(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void refresh() {
        targetFrame.repaint();
    }

    public void print(int x, int y, String what, int color) {
        print(x, y, what, frontColor.getColorFromCode(color), CSIColor.BLACK);
    }

    public void print(int x, int y, String what, CSIColor color) {
        print(x, y, what, color, CSIColor.BLACK);
    }

    public void print(int x, int y, String what, CSIColor color, CSIColor background) {
        locate(x, y);

        for (int i = 0; i < what.length(); i++) {
            if (xpos >= xdim) {
                xpos = 0;
                ypos++;
            }
            if (ypos >= ydim) {
                break;
            }
            targetFrame.plot(what.charAt(i), xpos, ypos, colorPreProcess(color), colorPreProcess(background));
            chars[x + i][y] = what.charAt(i);
            colors[x + i][y] = color;
            xpos++;
        }
    }

    public void print(int x, int y, char what, int color) {
        CSIColor front = frontColor.getColorFromCode(color);
        print(x, y, what, front);
    }

    public void print(int x, int y, char what, CSIColor color) {
        print(x, y, what, color, CSIColor.BLACK);
    }

    public void print(int x, int y, char what, CSIColor color, CSIColor back) {
        locate(x, y);
        if (chars[x][y] == what && colors[x][y] == color) {
            return;
        }
        targetFrame.plot(what, xpos, ypos, colorPreProcess(color), colorPreProcess(back));
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
        return frontColor.getColor(colorName);
    }

    public void setAutoRefresh(boolean value) {
    }

    public char peekChar(int x, int y) {
        return targetFrame.peekChar(x, y);
    }

    public int peekColor(int x, int y) {
        return frontColor.getCodeFromColor(colors[x][y]);
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
        int byWidth = (int) (scrWidth / (xdim));

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
        colors = colorsBuffer.clone();
        chars = charsBuffer.clone();
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                this.print(x, y, chars[x][y], colors[x][y]);
            }
        }
    }

    public void saveBuffer() {
        colorsBuffer = colors.clone();
        charsBuffer = chars.clone();
    }
}