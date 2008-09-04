package net.slashie.libjcsi.wswing;

import javax.swing.*;
import java.awt.*;

public class SwingConsolePanel extends JPanel{
    private char [][] charBuffer;
    private Color [][] colorBuffer;
    private Color backGround, foreGround;
    private boolean [][] updateBuffer;
    //private boolean autoUpdate;

    private transient Graphics graphicsBuff;
	private transient Image imageBuff;

    private int xpos, ypos, // Current Cursor Position
                width, height, // Size of the Panel in points
                xdim, ydim, // Size of the Panel in characters
                fontSize;

	private int fontWidth;
	private int fontDown;
    private Font f;

    public void init(Font f, int xdim, int ydim){
    	setCursor(null);
        width =  Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds(0,0, width, height);
        foreGround = Color.WHITE;
        backGround = Color.BLACK;
        setBackground(backGround);
        this.xdim = xdim;
		this.ydim = ydim;
		this.f = f;
        charBuffer = new char [xdim] [ydim];
        colorBuffer = new Color [xdim][ydim];
        updateBuffer = new boolean [xdim][ydim];

        for (int i = 0; i<xdim; i++)
            for(int j=0; j<ydim; j++){
                charBuffer [i][j] = ' ';
                colorBuffer [i][j] = foreGround;
                updateBuffer [i][j] = true;
            }

        //Double Buffer
        imageBuff = createImage(width, height);
        graphicsBuff = imageBuff.getGraphics();
        graphicsBuff.setFont(f);
        fontSize = f.getSize();
        fontWidth = (int) (fontSize * 0.7);
		fontDown = (int)(fontSize * 1.3);
		repaint();
    }

	public void plot (char c, int x, int y){
		colorBuffer [x][y] = foreGround;
		charBuffer [x][y] = c;
		updateBuffer [x][y] = true;
		if (autoUpdate) {
			repaint();
        }
	}

    public void plot (char c, int x, int y, Color foreColor) {
        colorBuffer [x][y] = foreColor;
		charBuffer [x][y] = c;
		updateBuffer [x][y] = true;
		
		if (autoUpdate) {
			repaint();
        }
    }

    public void refresh(){
        repaint();
    }

    private boolean autoUpdate = false;
    public void setAutoUpdate(boolean value){
    	autoUpdate = value;
    	//autoUpdate = false;
    }
    
    public void setFont(Font pFont){
    	f = pFont;
    }

	public char peekChar(int x, int y){
		return charBuffer[x][y];
	}

	public void flash(Color fc){
	}
	
	public void cls(){
		for (int x = 0; x < charBuffer.length; x++)
			for (int y = 0; y < charBuffer[0].length; y++){
				charBuffer[x][y] = ' ';
				updateBuffer[x][y] = true;
			}
	}


	public synchronized void paintComponent(Graphics g){
        for (int x = 0; x < charBuffer.length; x++) {
        	for (int y = 0; y < charBuffer[0].length; y++){
        		if (updateBuffer[x][y]) {
        			graphicsBuff.setColor (backGround);
        			graphicsBuff.fillRect (x*fontWidth,y*fontSize,fontWidth,fontDown);
                   // Fix upper and lower positions if possible
        			if (y-1 >= 0){
        				graphicsBuff.setColor (colorBuffer[x][y-1]);
        				graphicsBuff.drawString(""+charBuffer[x][y-1], x * fontWidth, y * fontSize);
        			}
        			if (y+1 < ydim){
        				graphicsBuff.setColor(colorBuffer[x][y+1]);
        				graphicsBuff.drawString(""+charBuffer[x][y+1], x * fontWidth, (y+2) * fontSize);
        			}
                   graphicsBuff.setColor (colorBuffer[x][y]);
                   graphicsBuff.drawString(""+charBuffer[x][y], x * fontWidth, (y+1) * fontSize);
                   //graphicsBuff.drawChars(charBuffer[x], 0,1,x * fontWidth, y * fontSize);
                   updateBuffer[x][y] = false;
               }
           }
	    }
        g.drawImage(imageBuff, 0, 0, null);
    }
}