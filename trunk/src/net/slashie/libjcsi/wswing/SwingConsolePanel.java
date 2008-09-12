package net.slashie.libjcsi.wswing;

import javax.swing.*;
import java.awt.*;

public class SwingConsolePanel extends JPanel {

    private char[][] charBuffer;
    private Color[][] colorBuffer;
    private Color[][] backGroundBuffer;
    private Color backGround,  foreGround;
    private boolean[][] updateBuffer;
    //private boolean autoUpdate;
    private transient Graphics graphicsBuff;
    private transient Image imageBuff;
    private int xpos,  ypos, // Current Cursor Position
         width,  height, // Size of the Panel in points
         xdim,  ydim, // Size of the Panel in characters
         fontSize;
    private int fontWidth;
    private int fontDown;
    private int ascent, descent;
    private Font font;
    private FontMetrics fMetric;

    public void init(Font f, int xdim, int ydim) {
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
        descent = fMetric.getMaxDescent();
        fontSize = f.getSize();
        fontWidth = fMetric.getMaxAdvance();
        fontDown = fMetric.getHeight();
        repaint();
    }

    public void plot(char c, int x, int y) {
        plot(c, x, y, foreGround, backGround);
    }

    public void plot(char c, int x, int y, Color foreColor) {
        plot(c, x, y, foreColor, backGround);
    }

    public void plot(char c, int x, int y, Color foreColor, Color backColor) {
        colorBuffer[x][y] = foreColor;
        backGroundBuffer[x][y] = backColor;
        charBuffer[x][y] = c;
        updateBuffer[x][y] = true;

        if (autoUpdate) {
            repaint();
        }
    }

    public void refresh() {
        repaint();
    }
    private boolean autoUpdate = false;

    public void setAutoUpdate(boolean value) {
        autoUpdate = value;
    //autoUpdate = false;
    }

    @Override
    public void setFont(Font pFont) {
        font = pFont;
    }

    public char peekChar(int x, int y) {
        return charBuffer[x][y];
    }

    public void flash(Color fc) {
    }

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

    @Override
    public synchronized void paintComponent(Graphics g) {
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
    }
}