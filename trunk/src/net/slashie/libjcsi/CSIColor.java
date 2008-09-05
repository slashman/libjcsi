package net.slashie.libjcsi;

public class CSIColor {

    private final static int OPAQUE = 255;// 255 is opaque for the all channels
    private int value;

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
        value = hex;
    }
    
    public CSIColor(CSIColorEnum color){
        value = color.color;
    }
    
    public boolean equals(CSIColor b){
        return (value == b.value);
    }
    
    public boolean equals(CSIColorEnum b){
        return (value == b.color);
    }
    
    public void setColor(int color){
        value = color;
    }
    
    public int getColor(){
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
