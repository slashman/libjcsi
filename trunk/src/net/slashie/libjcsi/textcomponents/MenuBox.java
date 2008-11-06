package net.slashie.libjcsi.textcomponents;

import java.util.*;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.util.*;

/**
 * Box used for various menus.
 * @author Santiago Zapata
 */
public class MenuBox extends TextComponent {

    private Vector items;
    private int promptSize;
    private String title = "";

    /* State Attributes */
    private int currentPage;
    private int pages;

    /* Components */
    private TextBox promptBox;

    /**
     * Creates a new menu.
     * @param si ConsoleSystemInterface menu is attached to.
     */
    public MenuBox(ConsoleSystemInterface si) {
        super(si);
        promptBox = new TextBox(si);
    }

    /**
     * Allows the position of the menu's upper left corner to be specified.
     * @param x horizontal position
     * @param y vertical position
     */
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        promptBox.setPosition(inPosition.x, inPosition.y + 1);
    }

    /**
     * Sets the height of the menu.
     * @param size menu's height
     */
    public void setPromptSize(int size) {
        promptSize = size;
        promptBox.setHeight(size);
    }

    /**
     * Sets the width of the menu and resets the upper left position to reflect
     * the change of width.
     * @param width menu's width
     */
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        promptBox.setWidth(inWidth);
        promptBox.setPosition(inPosition.x, inPosition.y);
    }

    /**
     * Sets whether or not there is a border on the menu.  The border draws inside
     * the menu's dimensions.
     * @param val true for a border
     */
    @Override
    public void setBorder(boolean val) {
        super.setBorder(val);
        promptBox.setWidth(inWidth);
        promptBox.setPosition(inPosition.x, inPosition.y);
    }

    /**
     * Allows the prompt to be specified.
     * @param prompt String to use as prompt.
     */
    public void setPrompt(String prompt) {
        promptBox.clear();
        promptBox.setText(prompt);
    }

    /**
     * Allows the menu items to be set.
     * @param items Vector of the items in the menu.
     */
    public void setMenuItems(Vector items) {
        this.items = items;
    }

    /**
     * Prints the menu to the screen.
     */
    public void draw() {
        pages = (int) (Math.floor((items.size() - 1) / (inHeight - promptSize)) + 1);
        clearBox();
        drawBorder();
        if (hasBorder()) {
            si.print(position.x + 2, position.y, title);
        }
        promptBox.draw();

        int pageElements = inHeight - promptSize;
        Vector shownItems = Util.page(items, pageElements, currentPage);

        int i = 0;
        for (; i < shownItems.size(); i++) {
            MenuItem item = (MenuItem) shownItems.elementAt(i);
            si.print(inPosition.x, inPosition.y + i + promptSize, ((char) (97 + i)) + ".");
            si.print(inPosition.x + 2, inPosition.y + i + promptSize, item.getMenuChar(), item.getMenuColor());
            String description = item.getMenuDescription();
            if (description.length() > getWidth() - 5) {
                description = description.substring(0, getWidth() - 6);
            }
            si.print(inPosition.x + 4, inPosition.y + i + promptSize, description);
        }
        si.refresh();
    }

    /**
     * Allows the user to move the selection bar up and down the menu and then
     * select an item in the menu with the spacebar.
     * @return item selected
     */
    public Object getSelection() {
        int pageElements = inHeight - promptSize;
        while (true) {
            clearBox();
            draw();
            Vector shownItems = Util.page(items, pageElements, currentPage);
            CharKey key = new CharKey(CharKey.NONE);
            while (key.code != CharKey.SPACE &&
                key.code != CharKey.UARROW &&
                key.code != CharKey.DARROW &&
                key.code != CharKey.N8 &&
                key.code != CharKey.N2 &&
                (key.code < CharKey.A || key.code > CharKey.A + pageElements - 1) &&
                (key.code < CharKey.a || key.code > CharKey.a + pageElements - 1)) {
                key = si.inkey();
            }
            if (key.code == CharKey.SPACE) {
                return null;
            }
            if (key.code == CharKey.UARROW || key.code == CharKey.N8) {
                if (currentPage > 0) {
                    currentPage--;
                }
            }
            if (key.code == CharKey.DARROW || key.code == CharKey.N2) {
                if (currentPage < pages - 1) {
                    currentPage++;
                }
            }
            if (key.code >= CharKey.A && key.code <= CharKey.A + shownItems.size() - 1) {
                return shownItems.elementAt(key.code - CharKey.A);
            } else if (key.code >= CharKey.a && key.code <= CharKey.a + shownItems.size() - 1) {
                return shownItems.elementAt(key.code - CharKey.a);
            }
        }
    }

    /**
     * 
     * @param s String that will be the title
     */
    public void setTitle(String s) {
        title = s;
    }
}