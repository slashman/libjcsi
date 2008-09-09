package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 *
 * @author ehoward
 */
public class DialogBox extends TextBox {

    public DialogBox(ConsoleSystemInterface si, int lines, String args) {

        super(si);

        int maxLineLength = 0, lineLength = 0, tempLineLength, x, y;

        lineLength = args.length();

        for (int i = 1; i < lines; i++) {
            tempLineLength = (lineLength * i) / lines;
            maxLineLength = Math.max(maxLineLength, args.indexOf(" ", tempLineLength));
        }

        if(maxLineLength == 0){
            maxLineLength = lineLength;
        }

        setWidth(maxLineLength + 2);
        setHeight(lines + 3);
        setBorder(true);
        setText(args);
    }
}