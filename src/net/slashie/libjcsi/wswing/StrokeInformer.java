package net.slashie.libjcsi.wswing;

import java.awt.event.*;
import net.slashie.libjcsi.CharKey;

/**
 * Gets keyboard input.
 * @author Santiago Zapata
 * @author Eben Howard
 */
public class StrokeInformer implements KeyListener, java.io.Serializable {

    private int bufferCode;
    private transient Thread keyListener;

    public StrokeInformer() {
        bufferCode = -1;
    }

    /**
     *
     * @param toWho where to add keyListener
     */
    public void informKey(Thread toWho) {
        keyListener = toWho;
    }

    /**
     *
     * @return code shows what was input
     */
    public int getInkeyBuffer() {
        return bufferCode;
    }

    /**
     * Captures input
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        bufferCode = charCode(e);
        keyListener.interrupt();
    }

    /**
     * Takes raw input and turns it into CharKey encoding.
     * @param x pressing of a key
     * @return CharKey encoded value
     */
    private int charCode(KeyEvent x) {
        int code = x.getKeyCode();
        if (x.isControlDown()) {
            return CharKey.CTRL;
        }
        if (code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z) {
            if (x.getKeyChar() >= 'a') {
                int diff = KeyEvent.VK_A - CharKey.a;
                return code - diff;
            } else {
                int diff = KeyEvent.VK_A - CharKey.A;
                return code - diff;
            }
        }

        if (x.getKeyChar() == '?') {
            return CharKey.QUESTION;
        }
        if (x.getKeyChar() == '>') {  // Added 8/19/2008 by Eben
            return CharKey.MORETHAN;
        }
        if (x.getKeyChar() == '<') {  // Added 8/19/2008 by Eben
            return CharKey.LESSTHAN;
        }

        switch (x.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                return CharKey.SPACE;

            case KeyEvent.VK_COMMA:
                return CharKey.COMMA;
            case KeyEvent.VK_PERIOD:
            case KeyEvent.VK_DECIMAL:
                return CharKey.DOT;
            case KeyEvent.VK_NUMPAD0:
                return CharKey.N0;
            case KeyEvent.VK_NUMPAD1:
                return CharKey.N1;
            case KeyEvent.VK_NUMPAD2:
                return CharKey.N2;
            case KeyEvent.VK_NUMPAD3:
                return CharKey.N3;
            case KeyEvent.VK_NUMPAD4:
                return CharKey.N4;
            case KeyEvent.VK_NUMPAD5:
                return CharKey.N5;
            case KeyEvent.VK_NUMPAD6:
                return CharKey.N6;
            case KeyEvent.VK_NUMPAD7:
                return CharKey.N7;
            case KeyEvent.VK_NUMPAD8:
                return CharKey.N8;
            case KeyEvent.VK_NUMPAD9:
                return CharKey.N9;
            case KeyEvent.VK_F1:
                return CharKey.F1;
            case KeyEvent.VK_ENTER:
                return CharKey.ENTER;
            case KeyEvent.VK_BACK_SPACE:
                return CharKey.BACKSPACE;
            case KeyEvent.VK_ESCAPE:
                return CharKey.ESC;
            case KeyEvent.VK_UP:
                return CharKey.UARROW;
            case KeyEvent.VK_DOWN:
                return CharKey.DARROW;
            case KeyEvent.VK_LEFT:
                return CharKey.LARROW;
            case KeyEvent.VK_RIGHT:
                return CharKey.RARROW;

        }
        return CharKey.NONE;
    }

    /**
     * Currently does nothing.
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Currently does nothing.
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }
}