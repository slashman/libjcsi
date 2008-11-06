package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 *Allows easy creation of a self-sizing box to display information which needs
 * answering input from the user.
 * @author Eben Howard
 */
public class DialogBox extends TextBox {

    /**
     * Creates the box
     * @param si the ConsoleSystemInterface to be attached to
     * @param lines the number of desired lines for the output portion of the box
     * @param args the text to be displayed as output in the box
     */
    public DialogBox(ConsoleSystemInterface si, int lines, String args) {

        super(si);

        int maxLineLength = 0, lineLength = 0, tempLineLength, nextIndex;

        lineLength = args.length();

        for (int i = 1; i < lines; i++) {
            tempLineLength = (lineLength * i) / lines;
            nextIndex = args.indexOf(" ", tempLineLength);
            if (nextIndex == -1) {
                nextIndex = tempLineLength / i;
            }
            maxLineLength = Math.max(maxLineLength, nextIndex);
        }

        if (maxLineLength == 0) {
            maxLineLength = lineLength;
        }

        setWidth(maxLineLength + 3);//this leaves space for the borders and punctuation
        setHeight(lines + 3);//this leaves space for the borders and an input area
        setBorder(true);
        setText(args);
    }
}