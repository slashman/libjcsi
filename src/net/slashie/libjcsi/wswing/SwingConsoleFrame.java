package net.slashie.libjcsi.wswing;

import java.awt.*;

public class SwingConsoleFrame extends javax.swing.JFrame {

    /**
     * Shows the console and gets keyboard input
     * using a Swing interface.
     * 
     * @author Santiago Zapata
     * @author Eben Howard
     */
    private SwingConsolePanel swingConsolePanel;

    /**
     * Allows setting of the window name when creating the frame.
     * @param windowName
     */
    public SwingConsoleFrame(String windowName) {
        setTitle(windowName);
    }

    /**
     * Allows setting of font, width, and height.
     * @param f
     * @param xdim horizontal width
     * @param ydim vertical height
     */
    public void init(Font f, int xdim, int ydim) {
        initComponents();
        swingConsolePanel.init(f, xdim, ydim);
    }

    private void initComponents() {
        swingConsolePanel = new SwingConsolePanel(true);
        getContentPane().setLayout(new BorderLayout(1, 1));
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(swingConsolePanel);
        setCursor(null);
        pack();
    }

    /**
     * Places a specific character at a specific point for
     * later printing.
     * @param c character to be printed
     * @param x horizontal position
     * @param y vertical position
     */
    public void plot(char c, int x, int y) {
        swingConsolePanel.plot(c, x, y);
    }

    /**
     * Places a specific character at a specific point for
     * later printing and sets the color it's to be printed in.
     * @param c character to be printed
     * @param x horizontal position
     * @param y vertical position
     * @param foreColor color to be printed in
     */
    public void plot(char c, int x, int y, Color foreColor) {
        swingConsolePanel.plot(c, x, y, foreColor);
    }

    /**
     * Places a specific character at a specific point for
     * later printing and sets both the color for the character
     * to be printed and the background color.
     * @param c character to be printed
     * @param x horizontal position
     * @param y vertical position
     * @param foreColor color for character to be printed in
     * @param backColor color for background to be printed in
     */
    public void plot(char c, int x, int y, Color foreColor, Color backColor) {
        swingConsolePanel.plot(c, x, y, foreColor, backColor);

    }

    /**
     * Repaints the frame.
     */
    public void refresh() {
        swingConsolePanel.repaint();
    }

    /**
     * 
     * @param pFont desired font
     */
    @Override
    public void setFont(Font pFont) {
        swingConsolePanel.setFont(pFont);
    }

    /**
     * Tells what character is at specific coordinates.
     * @param x horizontal position
     * @param y vertical position
     * @return contents of location
     */
    public char peekChar(int x, int y) {
        return swingConsolePanel.peekChar(x, y);
    }

    /**
     * Flashes entire screen specified color.  Currently inoperable.
     * @param fc color to flash
     */
    public void flash(Color fc) {
    }

    /**
     * Erases entire frame.
     */
    public void cls() {
        swingConsolePanel.cls();
    }

    /**
     * Outputs entire frame.
     * @param g
     */
    public synchronized void paintComponent(Graphics g) {
        swingConsolePanel.paintComponent(g);
    }
}