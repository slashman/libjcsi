/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.slashie.util;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.textcomponents.TextBox;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author ehoward
 */
public class CSIColorComparison {

    WSwingConsoleInterface mainInterface;
    Random rng = new Random();
    CSIColor colorList[] = CSIColor.FULL_PALLET;
    int colorOrder[] = new int[colorList.length];
    int n = 0,
            carrot = 0,
            backColor = 0,
            frontColor = 17,
            ydim = mainInterface.ydim;

    public CSIColorComparison() {
        try {
            mainInterface = new WSwingConsoleInterface("CSIColor Test");
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }

        for (int i = 0; i < colorOrder.length; i++) {
            colorOrder[i] = i;
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
            rotateListUp();
        } else if (thisKey.isUpRightArrow() || (thisKey.code == CharKey.u)) {
        } else if (thisKey.isRightArrow() || (thisKey.code == CharKey.l)) {
        } else if (thisKey.isDownRightArrow() || (thisKey.code == CharKey.n)) {
        } else if (thisKey.isDownArrow() || (thisKey.code == CharKey.j)) {
            rotateListDown();
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
//            case CharKey.r:
//                sortRed();
//                break;
//            case CharKey.b:
//                sortBlue();
//                break;
//            case CharKey.g:
//                sortGreen();
//                break;
//            case CharKey.w:
//                sortGrey();
//                break;
            case CharKey.z:
                selectPrime();
                break;
            case CharKey.x:
                selectSecondary();
                break;
            case CharKey.R:
                resetColors();
                break;
        }
    }

    private void showHelp(){
        String helpText[] = {
          "Commands:",
          "z - Select Primary Color",
          "x - Select Secondary Color",
          "R - Reset color picks to BLACK and WHITE",
          "? - This help window",
          "",
          "",
          "Press Any Key To Exit This Screen"
        };
        mainInterface.saveBuffer();
        mainInterface.cls();
        for(int i=0;i<helpText.length;i++){
            mainInterface.print(0, i, helpText[i]);
        }
        mainInterface.refresh();
        mainInterface.inkey();
        mainInterface.restore();
        mainInterface.refresh();
    }

    private void resetColors(){
        frontColor = 17;
        backColor = 0;
    }

    private void selectPrime() {
        frontColor = carrot;
    }

    private void selectSecondary() {
        backColor = carrot;
    }

    private void sortRed() {
        int t, j;
        for (int i = 1; i < colorOrder.length; i++) {
            t = colorOrder[i];
            j = i - 1;
            while ((j >= 0) && (colorList[colorOrder[j]].getR() < colorList[t].getR())) {
                colorOrder[j + 1] = colorOrder[j];
                j--;
            }
            colorOrder[j + 1] = t;
        }
        n = 0;
        printScreen();
    }

    private void sortBlue() {
        int t, j;
        for (int i = 1; i < colorOrder.length; i++) {
            t = colorOrder[i];
            j = i - 1;
            while ((j >= 0) && (colorList[colorOrder[j]].getB() < colorList[t].getB())) {
                colorOrder[j + 1] = colorOrder[j];
                j--;
            }
            colorOrder[j + 1] = t;
        }
        n = 0;
        printScreen();
    }

    private void sortGreen() {
        int t, j;
        for (int i = 1; i < colorOrder.length; i++) {
            t = colorOrder[i];
            j = i - 1;
            while ((j >= 0) && (colorList[colorOrder[j]].getG() < colorList[t].getG())) {
                colorOrder[j + 1] = colorOrder[j];
                j--;
            }
            colorOrder[j + 1] = t;
        }
        n = 0;
        printScreen();
    }

    private void sortGrey() {
        int t, j;
        for (int i = 1; i < colorOrder.length; i++) {
            t = colorOrder[i];
            j = i - 1;
            while ((j >= 0) && (colorList[colorOrder[j]].getGray() < colorList[t].getGray())) {
                colorOrder[j + 1] = colorOrder[j];
                j--;
            }
            colorOrder[j + 1] = t;
        }
        n = 0;
        printScreen();
    }

    private void rotateListUp() {
        carrot--;
        if (carrot < 0) {
            carrot++;
        }
        if (carrot - 5 < n) {
            n--;
            if (n < 0) {
                n++;
            }
        }
    }

    private void rotateListDown() {
        carrot++;
        if (carrot >= colorList.length) {
            carrot--;
        }
        if (carrot + 5 > n + ydim) {
            n++;
            if ((n + ydim) > colorList.length) {
                n--;
            }
        }
    }

    private void printScreen() {
        CSIColor tempColor;
        mainInterface.cls();
        for (int k = 0; k < ydim; k++) {
            tempColor = colorList[colorOrder[n + k]];
            mainInterface.print(0, k, "    ", CSIColor.BLACK, tempColor);
            mainInterface.print(5, k, "     " + (colorOrder[n + k]) + " " + tempColor.toString(), tempColor);
        }
        mainInterface.print(5, carrot - n, "<-->");

        for (int i = 0; i < 3; i++) {
            mainInterface.print(60, i, "   ", colorList[frontColor], CSIColor.BLACK);
            mainInterface.print(60, i + 3, "   ", colorList[frontColor], CSIColor.GRAY);
            mainInterface.print(60, i + 6, "   ", colorList[frontColor], CSIColor.WHITE);
            mainInterface.print(60, i + 9, "   ", colorList[frontColor], colorList[backColor]);
            mainInterface.print(60, i + 12, "   ", colorList[backColor], colorList[frontColor]);
        }

        for (int i = 0; i < 15; i++) {
            mainInterface.print(65, i, "   ", CSIColor.BLACK, colorList[frontColor]);
            mainInterface.print(68, i, "   ", CSIColor.BLACK, colorList[backColor]);
            mainInterface.print(71, i, "   ", CSIColor.BLACK, colorList[frontColor]);
        }

        mainInterface.print(60, 1, " @ ", colorList[frontColor], CSIColor.BLACK);
        mainInterface.print(60, 4, " @ ", colorList[frontColor], CSIColor.GRAY);
        mainInterface.print(60, 7, " @ ", colorList[frontColor], CSIColor.WHITE);
        mainInterface.print(60, 10, " @ ", colorList[frontColor], colorList[backColor]);
        mainInterface.print(60, 13, " @ ", colorList[backColor], colorList[frontColor]);

        mainInterface.print(50, 20, "Front Color");
        mainInterface.print(50, 21, colorList[colorOrder[frontColor]].toString());

        mainInterface.print(50, 23, "Back Color");
        mainInterface.print(50, 24, colorList[colorOrder[backColor]].toString());

        mainInterface.refresh();
    }

    public static void main(String[] args) {
        new CSIColorComparison();
    }
}
