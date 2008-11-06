package net.slashie.libjcsi.textcomponents;

import java.io.Serializable;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.util.Position;

/**
 * Basic framework for all text components.
 * @author Santiago Zapata
 * @author Eben Howard
 */
public abstract class TextComponent implements Serializable {

    protected Position position = new Position(0, 0);
    protected int width;
    protected int height;
    protected String spaces;
    protected transient ConsoleSystemInterface si;
    protected CSIColor foreColor = CSIColor.WHITE;
    private boolean border;
    protected CSIColor borderColor = CSIColor.WHITE;
    protected Position inPosition;
    protected int inWidth;
    protected int inHeight;

    /**
     * Sets the upper left corner of text component within its interface.
     * @param x horizontal position
     * @param y vertical position
     */
    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
        recalcInnerBounds();
    }

    /**
     *
     * @return horizontal width of the component
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param value how wide the componenet should be.
     */
    public void setWidth(int value) {
        width = value;
        recalcInnerBounds();
        spaces = "";
        for (int i = 0; i <= inWidth; i++) {
            spaces += " ";
        }
    }

    /**
     *
     * @return vertical height of the component
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param value the desired height of the component
     */
    public void setHeight(int value) {
        height = value;
        recalcInnerBounds();
    }

    /**
     * Prints the componenet to the interface.
     */
    public abstract void draw();

    /**
     * Creates a new TextComponent within the specified interface.
     * @param si interface to be created within
     */
    public TextComponent(ConsoleSystemInterface si) {
        this.si = si;
    }

    /**
     *
     * @return the code for the color in the foreground
     */
    public int getForeColor() {
        return foreColor.getCodeFromColor(foreColor);
    }

    /**
     * Allows for setting of the color using int codes.
     * @param color int code for color desired
     */
    public void setForeColor(int color) {
        this.foreColor = this.foreColor.getColorFromCode(color);
    }

    /**
     * Allows for setting of the color using CSIColor.
     * @param color color desired
     */
    public void setForeColor(CSIColor color) {
        foreColor = color;
    }

    /**
     * Allows for setting whether there should be a border.
     * @param value true if border desired
     */
    public void setBorder(boolean value) {
        border = value;
        recalcInnerBounds();
        spaces = "";
        for (int i = 0; i <= inWidth; i++) {
            spaces += " ";
        }
    }

    /**
     * Allows for setting the upper left corner and width and height in one method.
     * @param x horizontal starting point
     * @param y vertical starting point
     * @param width horizontal width
     * @param height vertical height
     */
    public void setBounds(int x, int y, int width, int height) {
        setPosition(x, y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Erases content of the component, but leaves component.
     */
    public void clearBox() {
        for (int i = 0; i <= inHeight; i++) {
            si.print(inPosition.x, inPosition.y + i, spaces);
        }
    }

    /**
     * 
     * @return true if there is a border
     */
    public boolean hasBorder() {
        return border;
    }

    /**
     * If there is supposed to be a border then draws a border, otherwise
     * returns without doing anything.
     */        
    public void drawBorder() {
        if (!hasBorder()) {
            return;
        }
        for (int x = position.x; x <= position.x + width; x++) {
            si.print(x, position.y, '-', borderColor);
            si.print(x, position.y + height, '-', borderColor);
        }
        for (int y = position.y; y <= position.y + height; y++) {
            si.print(position.x, y, '|', borderColor);
            si.print(position.x + width, y, '|', borderColor);
        }

        si.print(position.x, position.y, '/', borderColor);
        si.print(position.x + width, position.y + height, '/', borderColor);
        si.print(position.x, position.y + height, '\\', borderColor);
        si.print(position.x + width, position.y, '\\', borderColor);
    }

    private void recalcInnerBounds() {
        if (hasBorder()) {
            inPosition = new Position(position.x + 1, position.y + 1);
            inWidth = width - 2;
            inHeight = height - 2;
        } else {
            inPosition = new Position(position.x, position.y);
            inWidth = width;
            inHeight = height;
        }
    }

    /**
     * Allows for the setting of the border's color independently of the content's color.
     * @param color the int code for the color desired
     */
    public void setBorderColor(int color) {
        this.borderColor = borderColor.getColorFromCode(color);
    }

    /**
     * Allows for the setting of the border's color independently of the content's color.
     * @param color color desired
     */
    public void setBorderColor(CSIColor color) {
        borderColor = color;
    }
}
