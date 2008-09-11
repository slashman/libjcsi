package net.slashie.libjcsi;

import java.util.ArrayList;

public class CSIColor {

    private final static int OPAQUE = 255;// 255 is opaque for the all channels
    private int value;
    public static final CSIColor BLACK = new CSIColor(0x000000),  BLUE = new CSIColor(0x0000ff),  BROWN = new CSIColor(0x808000),  CYAN = new CSIColor(0x00ffff),  DARK_BLUE = new CSIColor(0x0000c8),  DARK_GRAY = new CSIColor(0X404040),  DARK_RED = new CSIColor(0x800000),  GRAY = new CSIColor(0x808080),  GREEN = new CSIColor(0x008000),  LEMON = new CSIColor(0x00FF00),// duplicated as LIGHT_GREEN for useability
         LIGHT_GRAY = new CSIColor(0xc0c0c0),  LIGHT_GREEN = new CSIColor(0x00FF00),  MAGENTA = new CSIColor(0xff00ff),  ORANGE = new CSIColor(0xffc800),  PINK = new CSIColor(0xffafaf),  PURPLE = new CSIColor(0x800080),  TEAL = new CSIColor(0x008080),  RED = new CSIColor(0xff0000),  WHITE = new CSIColor(0xffffff),  YELLOW = new CSIColor(0xffff00);
    /*
     *colorArray holds the default palette.  Any new items need to be added to the end
     * in order to maintain backwards compatability 
     */
    public static CSIColor[] COLOR_ARRAY = {BLACK, DARK_BLUE, GREEN, TEAL, DARK_RED, PURPLE, BROWN, LIGHT_GRAY, GRAY, BLUE, LEMON, CYAN, RED, MAGENTA, YELLOW, WHITE};

    public CSIColor(int pr, int pg, int pb, int pa) {
        this((pa << 24) | (pr << 16) | (pg << 8) | pb);
    }

    public CSIColor(int pr, int pg, int pb) {
        this((OPAQUE << 24) | (pr << 16) | (pg << 8) | pb);
    }

    public CSIColor() { //builds white
        this((OPAQUE << 24) | (OPAQUE << 16) | (OPAQUE << 8) | OPAQUE);
    }
    
    public CSIColor(CSIColor color){
        value = color.value;
    }

    public CSIColor(int hex) {
        value = hex;
    }

    public boolean equals(CSIColor b) {
        return (value == b.value);
    }

    public void setColor(int color) {
        value = color;
    }

    public int getColor() {
        return value;
    }

    public int getR() {
        return (value >> 16) & 0xff;
    }

    public void setR(int r) {
       value = (getA() << 24) | (r << 16) | (getG() << 8) | getB();
    }

    public int getG() {
        return (value >> 8) & 0xff;
    }

    public void setG(int g) {
       value = (getA() << 24) | (getR() << 16) | (g << 8) | getB();
    }

    public int getB() {
        return value & 0xff;
    }

    public void setB(int b) {
       value = (getA() << 24) | (getR() << 16) | (getG() << 8) | b;
    }

    public int getA() {
        return (value >> 24) & 0xff;
    }

    public void setA(int a) {
       value = (a << 24) | (getR() << 16) | (getG() << 8) | getB();
    }

    public int getColor(String colorName) {
        ArrayList<CSIColor> tempList = new ArrayList<CSIColor>();
        for (int i = 0; i < COLOR_ARRAY.length; i++) {
            tempList.add(COLOR_ARRAY[i]);
        }
        if (colorName == null) {
            return -1;
        }
        if (colorName.equals("BLACK")) {
            return tempList.indexOf(BLACK);
        }
        if (colorName.equals("DARK_BLUE")) {
            return tempList.indexOf(DARK_BLUE);
        }
        if (colorName.equals("GREEN")) {
            return tempList.indexOf(GREEN);
        }
        if (colorName.equals("TEAL")) {
            return tempList.indexOf(TEAL);
        }
        if (colorName.equals("DARK_RED")) {
            return tempList.indexOf(DARK_RED);
        }
        if (colorName.equals("PURPLE")) {
            return tempList.indexOf(PURPLE);
        }
        if (colorName.equals("BROWN")) {
            return tempList.indexOf(BROWN);
        }
        if (colorName.equals("LIGHT_GRAY")) {
            return tempList.indexOf(LIGHT_GRAY);
        }
        if (colorName.equals("GRAY")) {
            return tempList.indexOf(GRAY);
        }
        if (colorName.equals("BLUE")) {
            return tempList.indexOf(BLUE);
        }
        if (colorName.equals("LEMON")) {
            return tempList.indexOf(LEMON);
        }
        if (colorName.equals("CYAN")) {
            return tempList.indexOf(CYAN);
        }
        if (colorName.equals("RED")) {
            return tempList.indexOf(RED);
        }
        if (colorName.equals("MAGENTA")) {
            return tempList.indexOf(MAGENTA);
        }
        if (colorName.equals("YELLOW")) {
            return tempList.indexOf(YELLOW);
        }
        if (colorName.equals("WHITE")) {
            return tempList.indexOf(WHITE);
        }
        return -1;
    }

    public CSIColor getColorFromCode(int code) {
        if ((code < 0) || (code > COLOR_ARRAY.length - 1)) {
            return BLACK;
        }
        return COLOR_ARRAY[code];
    }

    public int getCodeFromColor(CSIColor color) {
        ArrayList<CSIColor> tempList = new ArrayList<CSIColor>();
        for (int i = 0; i < COLOR_ARRAY.length; i++) {
            tempList.add(COLOR_ARRAY[i]);
        }
        return tempList.indexOf(color);
    }
}

