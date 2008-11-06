package net.slashie.libjcsi.textcomponents;

/**
 * This provides for an easy way to output into a list, useful for inventory.
 * @author Santiago Zapata
 */
public class BasicListItem implements ListItem {

    private String rowData;
    private char index;
    private int indexColor;

    /**
     * Constructor that sets all variables.
     * @param index the char to use as an index
     * @param indexColor the color to display the list item
     * @param data the String displayed
     */
    public BasicListItem(char index, int indexColor, String data) {
        setRow(data);
        this.index = index;
        this.indexColor = indexColor;
    }

    /**
     *
     * @return String of the item
     */
    public String getRow() {
        return rowData;
    }

    /**
     * Sets the String to be contained.
     * @param data String to be contained
     */
    public void setRow(String data) {
        rowData = data;
    }

    /**
     * Sets the char to use as an index in the list.
     * @param index char to be used
     */
    public void setIndex(char index) {
        this.index = index;
    }

    /**
     * Sets the color of the index.
     * @param indexColor int color
     */
    public void setIndexColor(int indexColor) {
        this.indexColor = indexColor;
    }

    /**
     *
     * @return char used as index
     */
    public char getIndex() {
        return index;
    }

    /**
     *
     * @return int representation of color
     */
    public int getIndexColor() {
        return indexColor;
    }
}
