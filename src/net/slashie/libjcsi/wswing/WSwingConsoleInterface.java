package net.slashie.libjcsi.wswing;

import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.Properties;

import net.slashie.libjcsi.*;
import net.slashie.libjcsi.util.Position;
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
	private CSIColor[][] backcolors;
    private char[][] chars;
    private CSIColor[][] colorsBuffer;
    private CSIColor[][] backcolorsBuffer;
    private char[][] charsBuffer;
    private CSIColor backColor = CSIColor.BLACK;
    private CSIColor frontColor = CSIColor.WHITE;
    private Position caretPosition = new Position(0, 0);
    private HashMap<CSIColor, Color> colorMap = new HashMap<CSIColor, Color>();
    private FontMetrics fMetric;
    private Properties configuration;

    /**
     * Allows for setting the window's name and deploying as a
     * Java WebStart application.
     * @param windowName
     * @param sandboxDeploy true if intended at a Java Webstart application
     * @deprecated sandboxDeploy is no longer needed to be specified
     */
    public WSwingConsoleInterface(String windowName, boolean sandboxDeploy) {
        this(windowName);
    }

    /**
     * Initializes the SwingConsoleBox, giving its window a distinctive title
     * @param windowName
     */
    public WSwingConsoleInterface(String windowName) {
        this(windowName, new Properties());
    }
    
    /**
     * Initializes the SwingConsoleBox with a custom font
     * @param windowName
     */
    public WSwingConsoleInterface(String windowName, Font font) {
        this(windowName, new Properties());
        consoleFont = font;
        targetFrame.setFont(consoleFont);
    }
    
    

    /**
     * Initializes the SwingConsoleBox, giving its window a distinctive title.
     * Receives the configuration parameters
     * @param windowName
     * @param configuration A Properties object containing key-value configuration pairs
     */
    public WSwingConsoleInterface(String windowName, Properties configuration) {
        this.configuration = configuration;
        aStrokeInformer = new StrokeInformer();
        targetFrame = new SwingConsoleFrame(windowName);
        
        java.awt.Dimension initialSize = new java.awt.Dimension(1024, 768);
        int fontSize = defineFontSize(initialSize.height, initialSize.width);
        
        String fontSizeCnf = configuration.getProperty("fontSize");
        if (fontSizeCnf != null){
        	try {
				fontSize = Integer.parseInt(fontSizeCnf);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        String strConsoleFont = loadFont();
        consoleFont = new Font(strConsoleFont, Font.PLAIN, fontSize);
        targetFrame.init(consoleFont, xdim, ydim);

        colors = new CSIColor[xdim][ydim];
        backcolors = new CSIColor[xdim][ydim];
        chars = new char[xdim][ydim];
        colorsBuffer = new CSIColor[xdim][ydim];
        backcolorsBuffer = new CSIColor[xdim][ydim];
        charsBuffer = new char[xdim][ydim];
		
		

        addKeyListener(aStrokeInformer);
        targetFrame.addComponentListener(this);
        fMetric = targetFrame.getFontMetrics(consoleFont);

        int x, y;
        x = fMetric.getMaxAdvance();
        x = fMetric.charWidth('W'); //TODO: Which one to use?
        y = fMetric.getHeight() - 1;
        targetFrame.setSize((xdim * x) + x, (ydim * y) + y + y);
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
    
    /**
     * Allows external classes to specify a new KeyListener to use
     * @param l the component to add the listener to
     */
    public void addKeyListener(KeyListener l)
    {
    	targetFrame.addKeyListener(l);
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
				backcolors[x][y] = backColor;
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
			backcolors[x + i][y] = background;
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
        targetFrame.plot(what, xpos, ypos, colorPreProcess(color), colorPreProcess(back));
        colors[x][y] = color;
        chars[x][y] = what;
		backcolors[x][y] = back;
    }

    public void print(int x, int y, String what) {
        print(x, y, what, frontColor);
    }

    public void printTransparent(int x, int y, String what, int color) {
	    locate (x,y);
		CSIColor front = CSIColor.getColorFromCode(color);
		for (int i = 0; i < what.length(); i++){
			if (xpos>=xdim){
				xpos = 0;
				ypos++;
			}
			if (ypos>=ydim)
				break;
			if (what.charAt(i) != '�') {
				targetFrame.plot(what.charAt(i), xpos, ypos, colorPreProcess(front));
	            chars[x+i][y] = what.charAt(i);
				colors[x+i][y] = front;
			}
	        xpos ++;
	    }
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
        String font = configuration.getProperty("font");
        if (font != null) {
            return font;
        } else {
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
    
    public void waitKeys(int... keyCodes) {
        CharKey x = new CharKey(CharKey.NONE);
		while (true){
			x = inkey();
			for (int keyCode: keyCodes){
				if (x.code == keyCode)
					return;
			}
			
		}
    }

    public void restore() {
        for (int x = 0; x < colors.length; x++) {
            colors[x] = colorsBuffer[x].clone();
            chars[x] = charsBuffer[x].clone();
			backcolors[x] = backcolorsBuffer[x].clone();
        }
        
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                this.print(x, y, chars[x][y], colors[x][y], backcolors[x][y]);
            }
        }
    }

    public void saveBuffer() {
    	for (int x = 0; x < colors.length; x++) {
    		colorsBuffer[x] = colors[x].clone();
    		charsBuffer[x] = chars[x].clone();
			backcolorsBuffer[x] = backcolors[x].clone();
        }
    }
}