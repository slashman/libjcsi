package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 * Provides a means to put output into a specific box.
 * @author Santiago Zapata
 * @author Eben Howard
 */
public class TextBox extends TextComponent {

    private StringBuffer[] lines;
    private String title = "";
    public static final String BEOL = "XXX";// this is the EndOfLine String for this component

    /**
     * Allows creation of a new TextBox
     * @param si the ConsoleSystemInterface that this box will be attached to
     */
    public TextBox(ConsoleSystemInterface si) {
        super(si);
        lines = new StringBuffer[]{new StringBuffer("")};
    }

    /**
     *
     * @param value The height of the box
     */
    @Override
    public void setHeight(int value) {
        super.setHeight(value);
        if (hasBorder()) {
            value -= 2;
        }
        lines = new StringBuffer[value];

        for (int i = 0; i < value; i++) {
            lines[i] = new StringBuffer("");
        }
    }

    /**
     * Allows for setting a border.
     * @param value true if border desired
     */
    @Override
    public void setBorder(boolean value) {
        super.setBorder(value);
        if (hasBorder()) {
            lines = new StringBuffer[getHeight() - 2];
        } else {
            lines = new StringBuffer[getHeight()];
        }

        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuffer("");
        }
    }

    /**
     * Outputs box to interface.
     */
    public void draw() {
        if (height == 0) {
            return;
        }
        clearBox();
        if (hasBorder()) {
            drawBorder();
            si.print(position.x + 2, position.y, title);
        }
        for (int i = 0; i < lines.length; i++) {
            si.print(inPosition.x, inPosition.y + i, lines[i].toString(), this.foreColor);
        }
    }

    /**
     *
     * @param text String that is the contents desired.
     */
    public void setText(String text) {
        clear();
        String[] tokens = text.split(" ");
        int curx = 0;
        int cury = 0;
        out:
        for (int i = 0; i < tokens.length; i++) {
            int distance = inWidth - curx;
            if (!(tokens[i].equals("XXX")) && (distance < tokens[i].length() + 1)) {
                if (cury < inHeight - 1) {
                    curx = 0;
                    cury++;
                } else {
                    break out;
                }
            }
            if (tokens[i].equals("XXX")) {
                cury++;
                curx = 0;
            } else {
                lines[cury].append(tokens[i] + " ");
                curx += tokens[i].length() + 1;
            }
        }
    }

    /**
     * Sets the title of the box, displayed in the top border.
     *
     * Does not check to see if box is wide enough for the title to
     * appear in the border, so such a check must be done externaly.
     * @param pTitle title for box
     */
    public void setTitle(String pTitle) {
        title = pTitle;
    }

    /**
     * Empties the box but does not erase the box itself.
     */
    public void clear() {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuffer("");
        }
    }
}