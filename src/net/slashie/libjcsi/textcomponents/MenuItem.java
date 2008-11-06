package net.slashie.libjcsi.textcomponents;

/**
 * Interface to show what must be included in menu items.
 * @author Santiago Zapata
 */
public interface MenuItem extends java.io.Serializable{
	public char getMenuChar();
	public int getMenuColor();
	public String getMenuDescription();

}