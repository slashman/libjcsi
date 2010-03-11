package net.slashie.libjcsi.examples;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author ehoward
 */
public class SwingTutorial {

    WSwingConsoleInterface mainInterface;
    Random rng = new Random();
    String mainText[] = {
      ""
    };

    public SwingTutorial() {
        try {
            mainInterface = new WSwingConsoleInterface("Swing Tutorial");
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }

        printScreen();
        showHelp();
        idleLoop();
    }

    private void idleLoop() {// this will get the player's input and launch the method that checks what was input
        CharKey actionKey = new CharKey();
        do {
            printScreen();
            actionKey = mainInterface.inkey();
            takeAction(actionKey);
        } while (true);
    }

    private void takeAction(CharKey thisKey) {  // this will feed control to the appropriate method based on player input
        if (thisKey.isUpArrow() || (thisKey.code == CharKey.k)) {
        } else if (thisKey.isUpRightArrow() || (thisKey.code == CharKey.u)) {
        } else if (thisKey.isRightArrow() || (thisKey.code == CharKey.l)) {
        } else if (thisKey.isDownRightArrow() || (thisKey.code == CharKey.n)) {
        } else if (thisKey.isDownArrow() || (thisKey.code == CharKey.j)) {
        } else if (thisKey.isDownLeftArrow() || (thisKey.code == CharKey.b)) {
        } else if (thisKey.isLeftArrow() || (thisKey.code == CharKey.h)) {
        } else if (thisKey.isUpLeftArrow() || (thisKey.code == CharKey.y)) {
        } else if (thisKey.isSelfArrow() || (thisKey.code == CharKey.DOT)) {
        }

        switch (thisKey.code) {
            case CharKey.ESC:
            case CharKey.Q:
                break;
            case CharKey.QUESTION:
                showHelp();
                break;
        }
    }

    private void showHelp() {
        String helpText[] = {
            "Welcome to the Swing Tutorial for libjcsi!",
            "",
            "Commands:",
            "7 8 9  y k u  Move cursor in given direction",
            "4   6  h   l  The arrow keys can also be used",
            "1 2 3  b j n",
            "",
            "Enter - Make selection",
            "ESC - Cancel selection",
            "? - Open this screen",
            "",
            "",
            "Press Any Key To Exit This Screen"
        };
        mainInterface.saveBuffer();
        mainInterface.cls();
        for (int i = 0; i < helpText.length; i++) {
            mainInterface.print(0, i, helpText[i]);
        }
        mainInterface.refresh();
        mainInterface.inkey();
        mainInterface.restore();
        mainInterface.refresh();
    }

    private void printScreen() {
        mainInterface.cls();
        mainInterface.refresh();
    }

    public static void main(String[] args) {
        new SwingTutorial();
    }
}
