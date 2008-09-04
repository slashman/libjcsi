package net.slashie.libjcsi.textcomponents;

import java.io.Serializable;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.util.Position;


public abstract class TextComponent implements Serializable{
	protected Position position = new Position(0,0);
	protected int width;
	protected int height;
	protected String spaces;
	
	protected transient ConsoleSystemInterface si;
	
	protected int foreColor = ConsoleSystemInterface.WHITE;
	private boolean border;
	protected int borderColor = ConsoleSystemInterface.WHITE;
	
	protected Position inPosition;
	protected int inWidth;
	protected int inHeight;
	
	public void setPosition (int x, int y){
		position.x = x;
		position.y = y;
		recalcInnerBounds();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int value) {
		width = value;
		recalcInnerBounds();
		spaces = "";
		for (int i = 0; i <= inWidth; i++)
			spaces += " ";
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int value) {
		height = value;
		recalcInnerBounds();
	}

	public abstract void draw();
	
	public TextComponent (ConsoleSystemInterface si){
		this.si = si;
	}

	public int getForeColor() {
		return foreColor;
	}

	public void setForeColor(int foreColor) {
		this.foreColor = foreColor;
	}

	public void setBorder(boolean value){
		border = value;
		recalcInnerBounds();
		spaces = "";
		for (int i = 0; i <= inWidth; i++)
			spaces += " ";
	}
	
	public void setBounds(int x, int y, int width, int height){
		setPosition(x,y);
		setWidth(width);
		setHeight(height);
	}
	
    public void clearBox(){
    	for (int i = 0; i <= inHeight; i++)
    		si.print(inPosition.x, inPosition.y+i, spaces);
    }

    public boolean hasBorder(){
    	return border;
    }
    
    public void drawBorder(){
    	if (!hasBorder())
    		return;
    	for (int x = position.x; x <= position.x+width; x++){
    		si.print(x,position.y, '-', borderColor);
    		si.print(x,position.y+height, '-', borderColor);
    	}
    	for (int y = position.y; y <= position.y+height; y++){
    		si.print(position.x,y, '|', borderColor);
    		si.print(position.x+width,y, '|', borderColor);
    	}
    	
    	si.print(position.x, position.y, '/', borderColor);
    	si.print(position.x+width, position.y+height, '/', borderColor);
    	si.print(position.x, position.y+height, '\\', borderColor);
    	si.print(position.x+width, position.y, '\\', borderColor);
    }
    
    private void recalcInnerBounds(){
		if (hasBorder()){
			inPosition = new Position(position.x +1, position.y+1);
			inWidth = width - 2;
			inHeight = height - 2;
		} else {
			inPosition = new Position(position.x, position.y);
			inWidth = width;
			inHeight = height;
		}
	}

    public void setBorderColor(int borderColor){
    	this.borderColor = borderColor;
    }
}
