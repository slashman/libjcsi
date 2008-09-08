package net.slashie.libjcsi;

import java.util.Vector;

public class CSIColor {

    private final static int OPAQUE = 255;// 255 is opaque for the all channels
    private int value;
    private static Vector<CSIColor> colorList = new Vector<CSIColor>();
    public static final CSIColor BLACK = new CSIColor(0x000000),  BLUE = new CSIColor(0x0000ff),  BROWN = new CSIColor(0x808000),  CYAN = new CSIColor(0x00ffff),  DARK_BLUE = new CSIColor(0x0000c8),  DARK_GRAY = new CSIColor(0X404040),  DARK_RED = new CSIColor(0x800000),  GRAY = new CSIColor(0x808080),  GREEN = new CSIColor(0x008000),  LEMON = new CSIColor(0x00FF00),// duplicated as LIGHT_GREEN for useability
         LIGHT_GRAY = new CSIColor(0xc0c0c0),  LIGHT_GREEN = new CSIColor(0x00FF00),  MAGENTA = new CSIColor(0xff00ff),  ORANGE = new CSIColor(0xffc800),  PINK = new CSIColor(0xffafaf),  PURPLE = new CSIColor(0x800080),  TEAL = new CSIColor(0x008080),  RED = new CSIColor(0xff0000),  WHITE = new CSIColor(0xffffff),  YELLOW = new CSIColor(0xffff00);

    /**
     * Returns a vector containing all of the constants from this class.
     * Should only be called once and assigned to a variable in your program to keep object creation at a minimum.
     * 
     * @return v Vector<CSIColor>
     */
    static final public Vector<CSIColor> getColorVector() {
        Vector<CSIColor> v = new Vector<CSIColor>(16);
        v.add(BLACK);
        v.add(DARK_BLUE);
        v.add(GREEN);
        v.add(TEAL);
        v.add(DARK_RED);
        v.add(PURPLE);
        v.add(BROWN);
        v.add(LIGHT_GRAY);
        v.add(GRAY);
        v.add(BLUE);
        v.add(LEMON);
        v.add(CYAN);
        v.add(RED);
        v.add(MAGENTA);
        v.add(YELLOW);
        v.add(WHITE);
        return v;
    }

    public CSIColor(int pr, int pg, int pb, int pa) {
        this((pa << 24) | (pr << 16) | (pg << 8) | pb);
    }

    public CSIColor(int pr, int pg, int pb) {
        this((OPAQUE << 24) | (pr << 16) | (pg << 8) | pb);
    }

    public CSIColor() { //builds white
        this((OPAQUE << 24) | (OPAQUE << 16) | (OPAQUE << 8) | OPAQUE);
    }

    public CSIColor(int hex) {
//        if (colorList.contains(hex))
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
        int x = getR();
        value -= x;
        value += r;
    }

    public int getG() {
        return (value >> 8) & 0xff;
    }

    public void setG(int g) {
        int x = getG();
        value -= x;
        value += g;
    }

    public int getB() {
        return value & 0xff;
    }

    public void setB(int b) {
        int x = getB();
        value -= x;
        value += b;
    }

    public int getA() {
        return (value >> 24) & 0xff;
    }

    public void setA(int a) {
        int x = getA();
        value -= x;
        value += a;
    }
}
