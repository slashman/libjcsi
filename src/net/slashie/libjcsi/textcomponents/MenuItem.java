package net.slashie.libjcsi.textcomponents;

import java.io.Serializable;

/**
 * Interface to show what must be included in menu items.
 * @author Santiago Zapata
 */
public interface MenuItem extends Serializable{
	public char getMenuChar();
	public int getMenuColor();
	public String getMenuDescription();

}