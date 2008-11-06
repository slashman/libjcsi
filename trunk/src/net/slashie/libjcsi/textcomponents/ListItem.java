package net.slashie.libjcsi.textcomponents;

/**
 * Interface to show what must be in each type of list item
 * @author Santiago Zapata
 */
public interface ListItem extends java.io.Serializable {
		
	public char getIndex();
	public int getIndexColor();
	public String getRow();
}
