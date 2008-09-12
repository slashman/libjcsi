package net.slashie.libjcsi.wswing;

import java.awt.*;


public class SwingConsoleFrame extends javax.swing.JFrame{
	/**
	 * Shows the console
	 * Gets keyboard input
	 */

    private SwingConsolePanel swingConsolePanel;
    
    public SwingConsoleFrame(String windowName){
    	setTitle(windowName);
    }

    public void init (Font f, int xdim, int ydim){
	    initComponents();
        //setBounds(0,0, (int)((xdim) * f.getSize() * 0.7), (ydim + 1) * f.getSize());
        swingConsolePanel.init(f, xdim, ydim);
    }

    private void initComponents() {
        swingConsolePanel = new SwingConsolePanel();
        getContentPane().setLayout(new BorderLayout(1,1));
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(swingConsolePanel);
        setCursor(null);
        pack();
    }

	public SwingConsolePanel getSwingConsolePanel() {
		return swingConsolePanel;
	}
}