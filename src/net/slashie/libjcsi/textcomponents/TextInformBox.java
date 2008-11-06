package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

/** Represents a text box which can be updated to
 * show a new text along with the old one until
 * it is full, in which case it shows a [More]
 * prompt, waits for a player keypress, erases it
 * selfs and continues exhibiting the same behaviour.
 * @author Santiago Zapata
 * @author Eben Howard
 */
public class TextInformBox extends TextComponent {

    private int curx,  cury;
    private StringBuffer[] lines;

    /**
     * 
     * @param value vertical height desired
     */
    @Override
    public void setHeight(int value) {
        super.setHeight(value);
        lines = new StringBuffer[value];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuffer("");
        }
    }

    /**
     * Outputs the box to the interface.
     */
    public void draw() {
        for (int i = 0; i < lines.length; i++) {
            si.print(position.x, position.y + i, lines[i].toString(), foreColor);
        }
    }

    /**
     * Allows text to be added to the box
     * @param text content to be added
     */
    public void addText(String text) {
        String[] tokens = text.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            int distance = width - curx;
            if (cury >= height - 1) {
                distance -= "[MORE]".length();
            }
            if (distance < tokens[i].length() + 1) {
                if (cury < height - 1) {
                    curx = 0;
                    cury++;
                } else {
                    //i--;
                    lines[cury].append("[MORE]");
                    morePrompt();
                    clear();
                }
            }
            lines[cury].append(tokens[i] + " ");
            curx += tokens[i].length() + 1;
        }
    }

    /**
     * Erases current content and then sets content
     * @param text new content
     */
    public void setText(String text) {
        clear();
        addText(text);
    }

    /**
     * Erases content but leaves box
     */
    public void clear() {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuffer("");
        }
        for (int i = 0; i < height; i++) {
            si.print(position.x, position.y + i, spaces);
        }
        curx = 0;
        cury = 0;
    }

    /**
     * Displays [MORE] prompt and waits for space bar to be pressed.
     */
    private void morePrompt() {
        draw();
        si.refresh();
        CharKey ck = new CharKey(CharKey.NONE);
        while (ck.code != CharKey.SPACE) {
            ck = si.inkey();
        }
    }

    /**
     * Creates new TextInformBox tied to a specific interface.
     * @param si interface to attach to
     */
    public TextInformBox(ConsoleSystemInterface si) {
        super(si);
    }
}
