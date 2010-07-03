package net.slashie.libjcsi.textcomponents;

import java.util.*;

import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 * Displays a list in a box.
 * @author Santiago Zapata
 */
public class ListBox extends TextComponent {
	private List<ListItem> itemsList;

    public ListBox(ConsoleSystemInterface si) {
        super(si);
        itemsList = new ArrayList<ListItem>();
    }

    public void draw() {
        clearBox();
        int length = (itemsList.size() < super.inHeight ? itemsList.size() : super.inHeight);
        for (int i = 0; i < length; i++) {
            ListItem item = itemsList.get(i);
            si.print(inPosition.x, inPosition.y + i, item.getIndex(), item.getIndexColor());
            if (item.getRow().length() > inWidth) {
                si.print(inPosition.x + 2, inPosition.y + i, item.getRow().substring(0, inWidth), foreColor);
            } else {
                si.print(inPosition.x + 2, inPosition.y + i, item.getRow(), foreColor);
            }
        }
    }

    public void clear() {
    	itemsList.clear();
    }

    public void addElements(List<ListItem> elements) {
    	if (elements.size()>0 && !(elements.get(0) instanceof ListItem)){
    		throw new ClassCastException("Invalid list item type: "+elements.get(0).getClass().getCanonicalName());
    	}
    	
        itemsList.addAll(elements);
    }
    
    public void setElements(List<ListItem> elements) {
    	clear();
    	addElements(elements);
    }
    
    public void addElement(ListItem element) {
        itemsList.add(element);
    }
}

