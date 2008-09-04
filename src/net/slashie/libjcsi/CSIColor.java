package net.slashie.libjcsi;

public class CSIColor {

    private int r,  g,  b,  a;
    private static int opaque = 255;// 255 is opaque for the alpha channel

    public CSIColor(int pr, int pg, int pb, int pa) {
        this(pr, pg, pb);
        a = pa;
    }

    public CSIColor(int pr, int pg, int pb) {
        r = pr;
        b = pb;
        g = pg;
        a = opaque;
    }

    public CSIColor() { //returns white
        r = 255;
        b = 255;
        g = 255;
        a = opaque;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
