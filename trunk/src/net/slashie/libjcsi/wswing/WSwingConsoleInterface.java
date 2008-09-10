package net.slashie.libjcsi.wswing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.HashMap;
import net.slashie.libjcsi.*;
import net.slashie.libjcsi.textcomponents.DialogBox;
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
    private HashMap<CSIColor, Color> colorMap = new HashMap<CSIColor, Color>();

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
        targetPanel.cls();
        flushColorTable();// might as well clean out the colorTable if the screen is going to be blank

    }

    public void locate(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void refresh() {
        targetFrame.repaint();
    }

    public String askPlayer(int lines, String question, CSIColor color) {
        int x, y;
        String answer;
        saveBuffer();

        DialogBox dialog = new DialogBox(this, lines, question);
        dialog.setForeColor(color);
        x = (xdim / 2) - (dialog.getWidth() / 2);
        y = (ydim / 2) - (dialog.getHeight() / 2);
        dialog.setPosition(x, y);

        dialog.setText(question);
        locateCaret(x + 2, y + lines + 2);
        dialog.draw();
        refresh();

        answer = input();
        restore();
        refresh();
        return answer;
    }

    public String askPlayer(int lines, String question) {
        return askPlayer(lines, question, frontColor);
    }

    public void print(int x, int y, String what, int color) {
        locate(x, y);

        print(x, y, what, frontColor.getColorFromCode(color));
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
        CSIColor front = frontColor.getColorFromCode(color);
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
        return frontColor.getColor(colorName);
    }


    public void setAutoRefresh(boolean value) {
        targetPanel.setAutoUpdate(value);
    }

    public char peekChar(int x, int y) {
        return targetPanel.peekChar(x, y);
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
        boolean lucida = false,  courier = false;
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