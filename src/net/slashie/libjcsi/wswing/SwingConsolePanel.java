package net.slashie.libjcsi.wswing;

import javax.swing.*;
import java.awt.*;

/**
 * Display space.
 * @author Santiago Zapata
 * @author Eben Howard
 */
public class SwingConsolePanel extends JPanel {

    private char[][] charBuffer;
    private Color[][] colorBuffer;
    private Color[][] backGroundBuffer;
    private Color backGround,  foreGround;
    private boolean[][] updateBuffer;
    //private boolean autoUpdate; //not currently implemented
    private transient Graphics graphicsBuff;
    private transient Image imageBuff;
    private int width,  height, // Size of the Panel in points
         xdim,  ydim; // Size of the Panel in characters
    private int fontWidth;
    private int fontDown;
    private int ascent;
    private Font font;
    private FontMetrics fMetric;
    private long timing;

    SwingConsolePanel(boolean b) {
        super(b);
    }

    /**
     * Sets the size and font for the display area.
     * @param f desired font
     * @param xdim horizontal width
     * @param ydim vertical height
     */
    public void init(Font f, int xdim, int ydim) {
        timing = System.currentTimeMillis(); //keep inputs from comming too quickly
        setCursor(null);
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds(0, 0, width, height);
        foreGround = Color.WHITE;
        backGround = Color.BLACK;
        setBackground(backGround);
        this.xdim = xdim;
        this.ydim = ydim;
        this.font = f;
        charBuffer = new char[xdim][ydim];
        colorBuffer = new Color[xdim][ydim];
        backGroundBuffer = new Color[xdim][ydim];
        updateBuffer = new boolean[xdim][ydim];

        for (int i = 0; i < xdim; i++) {
            for (int j = 0; j < ydim; j++) {
                charBuffer[i][j] = ' ';
                colorBuffer[i][j] = foreGround;
                backGroundBuffer[i][j] = backGround;
                updateBuffer[i][j] = true;
            }        //Double Buffer
        }
        imageBuff = createImage(width, height);
        graphicsBuff = imageBuff.getGraphics();
        graphicsBuff.setFont(f);
        fMetric = graphicsBuff.getFontMetrics(f);
        ascent = fMetric.getMaxAscent();
        fontWidth = fMetric.charWidth('W');
        fontDown = fMetric.getHeight();

        repaint();
    }

    /**
     * Sets a specific character to a specific point.
     * @param c specified character
     * @param x horizontal position
     * @param y vertical position
     */
    public void plot(char c, int x, int y) {
        plot(c, x, y, foreGround, backGround);
    }

    /**
     * Sets a specific character to a specific point and makes
     * it a specific color.
     * @param c specified character
     * @param x horizontal position
     * @param y vertical position
     * @param foreColor specified color
     */
    public void plot(char c, int x, int y, Color foreColor) {
        plot(c, x, y, foreColor, backGround);
    }

    /**
     * Sets a specific character to a specific point and makes
     * it and the background specific colors.
     * @param c specified character
     * @param x horizontal position
     * @param y vertical position
     * @param foreColor specified character color
     * @param backColor specified background color
     */
    public void plot(char c, int x, int y, Color foreColor, Color backColor) {
        colorBuffer[x][y] = foreColor;
        backGroundBuffer[x][y] = backColor;
        charBuffer[x][y] = c;
        updateBuffer[x][y] = true;

        if (autoUpdate) {
            repaint();
        }
    }

    /**
     * Redraws the output area.
     */
    public void refresh() {
        repaint();
    }
    private boolean autoUpdate = false;

    /**
     * Declares whether output area should update itself when anything changes.
     * @param value true if automatically updating is desired
     */
    public void setAutoUpdate(boolean value) {
        autoUpdate = value;
    }

    /**
     * 
     * @param pFont desired font
     */
    @Override
    public void setFont(Font pFont) {
        font = pFont;
    }

    /**
     * Shows what character is at a specific point.
     * @param x horizontal position
     * @param y vertical position
     * @return character at point
     */
    public char peekChar(int x, int y) {
        return charBuffer[x][y];
    }

    /**
     * Flashes screen a specified color.  Currently inoperable.
     * @param fc specified color to flash
     */
    public void flash(Color fc) {
    }

    /**
     * Erases entire output area.
     */
    public void cls() {
        for (int x = 0; x < charBuffer.length; x++) {
            for (int y = 0; y < charBuffer[0].length; y++) {
                charBuffer[x][y] = ' ';
                colorBuffer[x][y] = foreGround;
                backGroundBuffer[x][y] = backGround;
                updateBuffer[x][y] = true;
            }
        }
    }

    /**
     * Draws the output area.
     * @param g
     */
    @Override
    public synchronized void paintComponent(Graphics g) {
        do {
        } while (System.currentTimeMillis() - timing < 50); // adds 5 millisecond delay in printing
        for (int x = 0; x < charBuffer.length; x++) {
            for (int y = 0; y < charBuffer[0].length; y++) {
                if (updateBuffer[x][y]) {
                    graphicsBuff.setColor(backGroundBuffer[x][y]);
                    graphicsBuff.fillRect(x * fontWidth, y * fontDown, fontWidth, fontDown);
                    graphicsBuff.setColor(colorBuffer[x][y]);
                    graphicsBuff.drawString("" + charBuffer[x][y], x * fontWidth, y * fontDown + ascent);
                    updateBuffer[x][y] = false;
                }
            }
        }
        g.drawImage(imageBuff, 0, 0, null);
        timing = System.currentTimeMillis();
    }
}