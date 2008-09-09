package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 *
 * @author ehoward
 */
public class DialogBox extends TextBox {

    public DialogBox(ConsoleSystemInterface si, int lines, String args) {

        super(si);

        int maxLineLength = 0, lineLength = 0, tempLineLength, nextIndex;

        lineLength = args.length();

        for (int i = 1; i < lines; i++) {
            tempLineLength = (lineLength * i) / lines;
            nextIndex = args.indexOf(" ", tempLineLength);
            if (nextIndex == -1){
                nextIndex = tempLineLength / i;
            }
            maxLineLength = Math.max(maxLineLength, nextIndex);
        }

        if(maxLineLength == 0){
            maxLineLength = lineLength;
        }

        setWidth(maxLineLength + 3);
        setHeight(lines + 3);
        setBorder(true);
        setText(args);
    }
}