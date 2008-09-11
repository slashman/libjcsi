package net.slashie.libjcsi;

import java.util.ArrayList;

public class CSIColor {

    private final static int OPAQUE = 255;// 255 is opaque for the all channels
    private int value;
    public static final CSIColor BLACK = new CSIColor(0x000000),  
        BLUE = new CSIColor(0x0000ff),  BROWN = new CSIColor(0x808000),  
        CYAN = new CSIColor(0x00ffff),  DARK_BLUE = new CSIColor(0x0000c8), 
        DARK_GRAY = new CSIColor(0X404040),  DARK_RED = new CSIColor(0x800000), 
        GRAY = new CSIColor(0x808080),  GREEN = new CSIColor(0x008000), 
        LEMON = new CSIColor(0x00FF00),// duplicated as LIGHT_GREEN for useability
         LIGHT_GRAY = new CSIColor(0xc0c0c0),  
         LIGHT_GREEN = new CSIColor(0x00FF00),  MAGENTA = new CSIColor(0xff00ff),  
         ORANGE = new CSIColor(0xffc800),  PINK = new CSIColor(0xffafaf),  
         PURPLE = new CSIColor(0x800080),  TEAL = new CSIColor(0x008080),  
         RED = new CSIColor(0xff0000),  WHITE = new CSIColor(0xffffff),  
         YELLOW = new CSIColor(0xffff00),
         
         ALICE_BLUE = new CSIColor(0xf0f8ff),
         ALIZARIN = new CSIColor(0xE32636),
         AMARANTH = new CSIColor(0xE52B50),
         AMBER = new CSIColor(0xFFBF00),
         AMETHYST = new CSIColor(0x9966CC),
         APRICOT = new CSIColor(0xFBCEB1),
         AQUA = new CSIColor(0x00FFFF), //same as cyan
         AQUAMARINE = new CSIColor(0x7FFFD4),
         ARMY_GREEN = new CSIColor(0x4B5320),
         ASPARAGUS = new CSIColor(0x7BA05B),
         ATOMIC_TANGERINE = new CSIColor(0xFF9966),
         AUBURN = new CSIColor(0x6D351A),
         AZUL = new CSIColor(0X007FFF),
         AZURE = new CSIColor(0xF0FFFF),
         BABY_BLUE = new CSIColor(0xE0FFFF),
         BEIGE = new CSIColor(0xF5F5DC),
         BISTRE = new CSIColor(0x3D2B1F),
         PIGMENT_BLUE = new CSIColor(0x333399),
         BLUE_GREEN = new CSIColor(0x00DDDD),
         BLUE_VIOLET = new CSIColor(0x8A2BE2),
         BONDI_BLUE = new CSIColor(0x0095B6),
         BRASS = new CSIColor(0xB5A642),
         BRIGHT_GREEN = new CSIColor(0x66FF00),
         BRIGHT_PINK = new CSIColor(0xFF0080),
         BRIGHT_TURQUOISE = new CSIColor(0x08E8DE),
         BRILLIANT_ROSE = new CSIColor(0xFF55A3),
         BRONZE = new CSIColor(0xCD7F32),
         BROWNER = new CSIColor(0x964B00),
         BUFF = new CSIColor(0xF0DC82),
         BURGUNDY = new CSIColor(0x900020),
         BURNT_ORANGE = new CSIColor(0xCC5500),
         BURNT_SIENNA = new CSIColor(0xE97451),
         BURNT_UMBER = new CSIColor(0x8A3324),
         CAMO_GREEN = new CSIColor(0x78866B),
         CAPUT_MORTUUM = new CSIColor(0x592720),
         CARDINAL = new CSIColor(0xC41E3A),
         CARMINE = new CSIColor(0x960018),
         CARNATION_PINK = new CSIColor(0xFFA6C9),
         CAROLINA_BLUE = new CSIColor(0x99BADD),
         CARROT_ORANGE = new CSIColor(0xED9121),
         CELADON = new CSIColor(0xACE1AF),
         CERISE = new CSIColor(0xDE3163),
         CERULEAN = new CSIColor(0x007BA7),
         CERULEAN_BLUE = new CSIColor(0x2A52BE),
         CHARTREUSE = new CSIColor(0xDFFF00),
         CHARTREUSE_GREEN = new CSIColor(0x7FFF00),
         CHERRY_BLOSSOM = new CSIColor(0xFFB7C5),
         CHESTNUT = new CSIColor(0xCD5C5C),
         CHOCOLATE = new CSIColor(0x7B3F00),
         CINNABAR = new CSIColor(0xE34234),
         CINNAMON = new CSIColor(0xD2691E),
         COBALT = new CSIColor(0x0047AB),
         COLUMBIA_BLUE = new CSIColor(0x9BDDFF),
         COPPER = new CSIColor(0xB87333),
         COPPER_ROSE = new CSIColor(0x996666),
         CORAL = new CSIColor(0xFF7F50),
         CORAL_RED = new CSIColor(0xFF4040),
         CORN = new CSIColor(0xFBEC5D),
         CORNFLOWER_BLUE = new CSIColor(0x6495ED),
         COSMIC_LATTE = new CSIColor(0xFFF8E7),
         CREAM = new CSIColor(0xFFFDD0),
         CRIMSON = new CSIColor(0xDC143C),
         DARK_BROWN = new CSIColor(0x654321),
         DARK_CERULEAN = new CSIColor(0x08457E),
         DARK_CHESTNUT = new CSIColor(0x986960),
         DARK_CORAL = new CSIColor(0xCD5B45),
         DARK_GOLDENROD = new CSIColor(0xB8860B),
         DARK_GREEN = new CSIColor(0x013220),
         DARK_KHAKI = new CSIColor(0xBDB76B),
         DARK_PASTEL_GREEN = new CSIColor(0x03C03C),
         DARK_PINK = new CSIColor(0xE75480),
         DARK_SCARLET = new CSIColor(0x560319),
         DARK_SALMON = new CSIColor(0xE9967A),
         DARK_SLATE_GRAY = new CSIColor(0x2F4F4F),
         DARK_SPRING_GREEN = new CSIColor(0x177245),
         DARK_TAN = new CSIColor(0x918151),
         DARK_TURQUOISE = new CSIColor(0x00CED1),
         DARK_VIOLET = new CSIColor(0x9400D3),
         DEEP_CERISE = new CSIColor(0xDA3287),
         DEEP_CHESTNUT = new CSIColor(0xB94E48),
         DEEP_FUCHSIA = new CSIColor(0xC154C1),
         DEEP_LILAC = new CSIColor(0x9955BB),
         DEEP_MAGENTA = new CSIColor(0xCD00CC),
         DEEP_PEACH = new CSIColor(0xFFCBA4),
         DEEP_PINK = new CSIColor(0xFF1493),
         DENIM = new CSIColor(0x1560BD),
         DODGER_BLUE = new CSIColor(0x1E90FF),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
//         END = new CSIColor(0x000000),
         END = new CSIColor(0x000000);
    /*
     *colorArray holds the default palette.  Any new items need to be added to the end
     * in order to maintain backwards compatability 
     */
    public static CSIColor[] DEFAULT_PALLET = {BLACK, DARK_BLUE, GREEN, TEAL, DARK_RED, PURPLE, BROWN, LIGHT_GRAY, GRAY, BLUE, LEMON, CYAN, RED, MAGENTA, YELLOW, WHITE};

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
        for (int i = 0; i < DEFAULT_PALLET.length; i++) {
            tempList.add(DEFAULT_PALLET[i]);
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
        if ((code < 0) || (code > DEFAULT_PALLET.length - 1)) {
            return BLACK;
        }
        return DEFAULT_PALLET[code];
    }

    public int getCodeFromColor(CSIColor color) {
        ArrayList<CSIColor> tempList = new ArrayList<CSIColor>();
        for (int i = 0; i < DEFAULT_PALLET.length; i++) {
            tempList.add(DEFAULT_PALLET[i]);
        }
        return tempList.indexOf(color);
    }
}

