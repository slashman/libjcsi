package net.slashie.libjcsi.wswing;

import java.awt.*;

public class SwingConsoleFrame extends javax.swing.JFrame {

    /**
     * Shows the console
     * Gets keyboard input
     */
    private SwingConsolePanel swingConsolePanel;

    public SwingConsoleFrame(String windowName) {
        setTitle(windowName);
    }

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

    public void plot(char c, int x, int y) {
        swingConsolePanel.plot(c, x, y);
    }

    public void plot(char c, int x, int y, Color foreColor) {
        swingConsolePanel.plot(c, x, y, foreColor);
    }

    public void plot(char c, int x, int y, Color foreColor, Color backColor) {
        swingConsolePanel.plot(c, x, y, foreColor, backColor);

    }

    public void refresh() {
        swingConsolePanel.repaint();
    }

    @Override
    public void setFont(Font pFont) {
        swingConsolePanel.setFont(pFont);
    }

    public char peekChar(int x, int y) {
        return swingConsolePanel.peekChar(x, y);
    }

    public void flash(Color fc) {
    }

    public void cls() {
        swingConsolePanel.cls();
    }

    public synchronized void paintComponent(Graphics g) {
        swingConsolePanel.paintComponent(g);
    }
}