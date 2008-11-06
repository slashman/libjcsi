package net.slashie.libjcsi.jcurses;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.util.Pair;
import net.slashie.util.Position;
import net.slashie.util.ReferenceParam;
import jcurses.system.*;

/**
 * This provides an interface using JCurses
 * @author Santiago Zapata
 */
public class JCursesConsoleInterface implements ConsoleSystemInterface {

    private int[][] colors;
    private char[][] chars;
    private int[][] colorsBuffer;
    private char[][] charsBuffer;
    private HashMap<String, CharColor> colorMap = new HashMap<String, CharColor>();
    private Position caretPosition = new Position(0, 0);

    public JCursesConsoleInterface() {
        Toolkit.startPainting();
        colors = new int[Toolkit.getScreenWidth() + 1][Toolkit.getScreenHeight() + 1];
        chars = new char[Toolkit.getScreenWidth() + 1][Toolkit.getScreenHeight() + 1];
        colorsBuffer = new int[Toolkit.getScreenWidth() + 1][Toolkit.getScreenHeight() + 1];
        charsBuffer = new char[Toolkit.getScreenWidth() + 1][Toolkit.getScreenHeight() + 1];
    }

    public void print(int x, int y, char what, int color) {
        //if (isInsideBounds(x,y))
        if (chars[x][y] == what && colors[x][y] == color) {
            return;
        }
        Toolkit.printString(what + "", x, y, getJCurseColor(color));
        colors[x][y] = color;
        chars[x][y] = what;
    }

    public void print(int x, int y, String what, int color) {
        for (int i = 0; i < what.length(); i++) {
            if (!isInsideBounds(x + i, y)) {
                break;
            }
            chars[x + i][y] = what.charAt(i);
            colors[x + i][y] = color;
        }
        Toolkit.printString(what, x, y, getJCurseColor(color));
    }

    public void print(int x, int y, String what) {
        print(x, y, what, ConsoleSystemInterface.WHITE);
    }

    public char peekChar(int x, int y) {
        return chars[x][y];
    }

    public int peekColor(int x, int y) {
        return colors[x][y];
    }

    public CharKey inkey() {
        InputChar c = Toolkit.readCharacter();
        return new CharKey(ASCtoCharKeyCode(c.getCode()));
    }

    public void locateCaret(int x, int y) {
        caretPosition.x = x;
        caretPosition.y = y;
    }

    public String input() {
        return input(999);
    }

    public String input(int l) {
        String ret = "";
        CharKey read = new CharKey(CharKey.NONE);
        while (true) {
            while (read.code == CharKey.NONE) {
                read = inkey();
            }
            if (read.code == CharKey.ENTER) {
                break;
            }
            if (read.code == CharKey.BACKSPACE) {
                if (ret.equals("")) {
                    read.code = CharKey.NONE;
                    continue;
                }
                if (ret.length() > 1) {
                    ret = ret.substring(0, ret.length() - 1);
                } else {
                    ret = "";
                }
                caretPosition.x--;
                print(caretPosition.x, caretPosition.y, " ");

            } else {
                if (ret.length() >= l) {
                    read.code = CharKey.NONE;
                    continue;
                }
                if (!read.isAlphaNumeric()) {
                    read.code = CharKey.NONE;
                    continue;
                }

                String nuevo = read.toString();
                print(caretPosition.x, caretPosition.y, nuevo + "_");
                ret += nuevo;
                caretPosition.x++;
            }
            refresh();
            read.code = CharKey.NONE;

        }
        return ret;
    }

    public boolean isInsideBounds(Position p) {
        return p.x >= 0 && p.x <= Toolkit.getScreenWidth() && p.y >= 0 && p.y <= Toolkit.getScreenHeight();
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && x <= Toolkit.getScreenWidth() && y >= 0 && y <= Toolkit.getScreenHeight();
    }

    public void cls() {
        Toolkit.clearScreen(getJCurseColor(BLACK));
        for (int x = 0; x < chars.length; x++) {
            for (int y = 0; y < chars[0].length; y++) {
                chars[x][y] = '\u0000';
                colors[x][y] = ConsoleSystemInterface.BLACK;
            }
        }
    }

    public void refresh() {
        Toolkit.endPainting();
        Toolkit.startPainting();
        Toolkit.printString("", 79, 24, getJCurseColor(BLACK));
    }

    public void refresh(Thread t) {
        refresh();
    }

    public void flash(int color) {
        /*		Toolkit.clearScreen(new CharColor(getJCurseColor(color).getForeground(), getJCurseColor(color).getForeground()));
        try {
        Thread.sleep(10);
        }
        catch (InterruptedException ie){
        }
        Toolkit.clearScreen(BLACK);   */
        //Toolkit.changeColors(new Rectangle(Toolkit.UL_CORNER, Toolkit.LR_CORNER), new CharColor(getJCurseColor(color).getForeground(), CharColor.BLACK));
    }

    public void setAutoRefresh(boolean value) {
    }

    private void getBasicColorInfo(ReferenceParam<Short> face, ReferenceParam<Short> boldness, int paletteValue) {
        boldness.setValue(CharColor.NORMAL);
        switch (paletteValue) {
            case ConsoleSystemInterface.BLACK:
                face.setValue(CharColor.BLACK);
                break;
            case ConsoleSystemInterface.DARK_BLUE:
                face.setValue(CharColor.BLUE);
                break;
            case ConsoleSystemInterface.GREEN:
                face.setValue(CharColor.GREEN);
                break;
            case ConsoleSystemInterface.TEAL:
                face.setValue(CharColor.CYAN);
                break;
            case ConsoleSystemInterface.DARK_RED:
                face.setValue(CharColor.RED);
                break;
            case ConsoleSystemInterface.PURPLE:
                face.setValue(CharColor.MAGENTA);
                break;
            case ConsoleSystemInterface.BROWN:
                face.setValue(CharColor.YELLOW);
                break;
            case ConsoleSystemInterface.LIGHT_GRAY:
                face.setValue(CharColor.WHITE);
                break;
            case ConsoleSystemInterface.GRAY:
                face.setValue(CharColor.BLACK);
                break;
            case ConsoleSystemInterface.BLUE:
                face.setValue(CharColor.BLUE);
                boldness.setValue(CharColor.BOLD);
                break;
            case ConsoleSystemInterface.LEMON:
                face.setValue(CharColor.GREEN);
                boldness.setValue(CharColor.BOLD);
                break;
            case ConsoleSystemInterface.CYAN:
                face.setValue(CharColor.CYAN);
                boldness.setValue(CharColor.BOLD);
                break;
            case ConsoleSystemInterface.RED:
                face.setValue(CharColor.RED);
                boldness.setValue(CharColor.BOLD);
                break;
            case ConsoleSystemInterface.MAGENTA:
                face.setValue(CharColor.MAGENTA);
                boldness.setValue(CharColor.BOLD);
                break;
            case ConsoleSystemInterface.YELLOW:
                face.setValue(CharColor.YELLOW);
                boldness.setValue(CharColor.BOLD);
                break;
            case ConsoleSystemInterface.WHITE:
                face.setValue(CharColor.WHITE);
                boldness.setValue(CharColor.BOLD);
                break;
        }
    }

    private CharColor getJCurseColor(int paletteValue, int backgroundValue) {
        String key = paletteValue + "," + backgroundValue;
        CharColor cachedColor = colorMap.get(key);
        if (cachedColor != null) {
            return cachedColor;
        }
        ReferenceParam<Short> frontFace = new ReferenceParam<Short>();
        ReferenceParam<Short> frontBoldness = new ReferenceParam<Short>();
        ReferenceParam<Short> backgroundFace = new ReferenceParam<Short>();
        ReferenceParam<Short> backgroundBoldness = new ReferenceParam<Short>();
        getBasicColorInfo(frontFace, frontBoldness, paletteValue);
        getBasicColorInfo(backgroundFace, backgroundBoldness, backgroundValue);
        cachedColor = new CharColor(backgroundFace.getValue(), frontFace.getValue(), backgroundBoldness.getValue(), frontBoldness.getValue());
        colorMap.put(key, cachedColor);
        return cachedColor;
    }

    private CharColor getJCurseColor(int paletteValue) {
        return getJCurseColor(paletteValue, ConsoleSystemInterface.BLACK);
    }
    private final static int KEY_BACKSPACE = InputChar.KEY_BACKSPACE;
    private final static int KEY_UP = InputChar.KEY_UP;
    private final static int KEY_DOWN = InputChar.KEY_DOWN;
    private final static int KEY_F1 = InputChar.KEY_F1;
    private final static int KEY_LEFT = InputChar.KEY_LEFT;
    private final static int KEY_RIGHT = InputChar.KEY_RIGHT;

    private int ASCtoCharKeyCode(int code) {
        if (code >= 65 && code <= 90) {
            return code - (65 - CharKey.A);
        } else if (code >= 97 && code <= 122) {
            return code - (97 - CharKey.a);
        }
        switch (code) {

            case 32:
                return CharKey.SPACE;
            case 63:
                return CharKey.QUESTION;
            case 44:
                return CharKey.COMMA;
            case 46:
                return CharKey.DOT;
            case 48:
                return CharKey.N0;
            case 49:
                return CharKey.N1;
            case 50:
                return CharKey.N2;
            case 51:
                return CharKey.N3;
            case 52:
                return CharKey.N4;
            case 53:
                return CharKey.N5;
            case 54:
                return CharKey.N6;
            case 55:
                return CharKey.N7;
            case 56:
                return CharKey.N8;
            case 57:
                return CharKey.N9;
            case 10:
                return CharKey.ENTER;
            case 27:
                return CharKey.ESC;
            case 9:
                return CharKey.TAB;
        }

        if (code == KEY_F1) {
            return CharKey.F1;
        } else if (code == InputChar.KEY_F2) {
            return CharKey.F2;
        } else if (code == InputChar.KEY_F3) {
            return CharKey.F3;
        } else if (code == InputChar.KEY_F4) {
            return CharKey.F4;
        } else if (code == InputChar.KEY_F5) {
            return CharKey.F5;
        } else if (code == InputChar.KEY_F6) {
            return CharKey.F6;
        } else if (code == InputChar.KEY_F7) {
            return CharKey.F7;
        } else if (code == InputChar.KEY_F8) {
            return CharKey.F8;
        } else if (code == InputChar.KEY_F9) {
            return CharKey.F9;
        } else if (code == InputChar.KEY_F10) {
            return CharKey.F10;
        } else if (code == InputChar.KEY_F11) {
            return CharKey.F11;
        } else if (code == InputChar.KEY_F12) {
            return CharKey.F12;
        } else if (code == KEY_BACKSPACE) {
            return CharKey.BACKSPACE;
        } else if (code == KEY_UP) {
            return CharKey.UARROW;
        } else if (code == KEY_DOWN) {
            return CharKey.DARROW;
        } else if (code == KEY_LEFT) {
            return CharKey.LARROW;
        } else if (code == KEY_RIGHT) {
            return CharKey.RARROW;
        }
        return -1;
    }

    public void safeprint(int x, int y, char what, int color) {
        if (isInsideBounds(x, y)) {
            print(x, y, what, color);
        }
    }

    public void waitKey(int keyCode) {
        CharKey x = new CharKey(CharKey.NONE);
        while (x.code != keyCode) {
            x = inkey();
        }
    }

    public void restore() {
        /*for (int i = 0; i < colors.length; i++){
        System.arraycopy(colorsBuffer[i], 0, colors[i], 0, colors[i].length-1);
        System.arraycopy(charsBuffer[i], 0, chars[i], 0, colors[i].length-1);
        }*/
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                this.print(x, y, charsBuffer[x][y], colorsBuffer[x][y]);
            }
        }
    }

    public void saveBuffer() {
        for (int i = 0; i < colors.length; i++) {
            System.arraycopy(colors[i], 0, colorsBuffer[i], 0, colors[i].length - 1);
            System.arraycopy(chars[i], 0, charsBuffer[i], 0, colors[i].length - 1);
        }
    }

    // This must be changed to use the interface method
	/*final static CSIColor[] baseCSIColors = new CSIColor[]{
    new CSIColor(0,0,0),
    new CSIColor(0,0,128),
    new CSIColor(0,128,0),
    new CSIColor(0,128,128),
    new CSIColor(128,0,0),
    new CSIColor(128,0,128),
    new CSIColor(128,64,0),
    new CSIColor(128,128,128), //LIGHT_GRAY = 7
    new CSIColor(64,64,64),//GRAY = 8
    new CSIColor(0,0,255),//BLUE = 9
    new CSIColor(0,255,0),//LEMON = 10
    new CSIColor(0,255,255),//CYAN = 11
    new CSIColor(255,0,0),//RED = 12
    new CSIColor(255,0,255),//MAGENTA = 13
    new CSIColor(255,255,0),//YELLOW = 14
    new CSIColor(255,255,255)//WHITE = 15;
    };;*/
    @SuppressWarnings("unchecked")
    private int selectAproximateColor(CSIColor color) {
        int r = color.getR();
        int g = color.getG();
        int b = color.getB();

        int rx = 1, rg = 1;
        if (r > g) {
            if (r > b) {
                rx = 3;
                if (b < g) {
                    rg = 2;
                }
            } else {
                if (g > b) {
                    rg = 3;
                    if (r > b) {
                        rx = 2;
                    }
                }
            }
        } else {
            if (g > b) {
                rg = 3;
                if (r > b) {
                    rx = 2;
                }
            } else {
                rg = 2;
            }
        }
        // Get the 3 colors closer to the dominant color
        CSIColor[] closeColor = new CSIColor[3];
        Pair<CSIColor, Integer>[] colorOffsets = new Pair[CSIColor.DEFAULT_PALLET.length];
        for (int i = 0; i < CSIColor.DEFAULT_PALLET.length; i++) {
            int difference = rx == 3 ? Math.abs(CSIColor.DEFAULT_PALLET[i].getR() - r) : rg == 3 ? Math.abs(CSIColor.DEFAULT_PALLET[i].getG() - g) : Math.abs(CSIColor.DEFAULT_PALLET[i].getB() - b);
            colorOffsets[i] = new Pair<CSIColor, Integer>(CSIColor.DEFAULT_PALLET[i], difference);
        }

        Arrays.sort(colorOffsets, new Comparator<Pair<CSIColor, Integer>>() {

            @Override
            public int compare(Pair<CSIColor, Integer> o1,
                Pair<CSIColor, Integer> o2) {
                return o1.getB() - o2.getB();
            }
        });

        closeColor[0] = colorOffsets[0].getA();
        closeColor[1] = colorOffsets[1].getA();
        closeColor[2] = colorOffsets[2].getA();
        colorOffsets = new Pair[closeColor.length];

        for (int i = 0; i < closeColor.length; i++) {
            int difference = rx == 2 ? Math.abs(closeColor[i].getR() - r) : rg == 2 ? Math.abs(closeColor[i].getG() - g) : Math.abs(closeColor[i].getB() - b);
            colorOffsets[i] = new Pair<CSIColor, Integer>(closeColor[i], difference);
        }

        Arrays.sort(colorOffsets, new Comparator<Pair<CSIColor, Integer>>() {

            @Override
            public int compare(Pair<CSIColor, Integer> o1,
                Pair<CSIColor, Integer> o2) {
                return o1.getB() - o2.getB();
            }
        });

        for (int i = 0; i < CSIColor.DEFAULT_PALLET.length; i++) {
            if (CSIColor.DEFAULT_PALLET[i].equals(colorOffsets[0].getA())) {
                return i;
            }
        }
        return -1;

    }
    
    public void print(int x, int y, char character, CSIColor csiColor) {
        int color = selectAproximateColor(csiColor);
        print(x, y, character, color);
    }

    public void print(int x, int y, String string, CSIColor csiColor) {
        int color = selectAproximateColor(csiColor);
        print(x, y, string, color);
    }

    @Override
    public void flushColorTable() {
        colorMap.clear();
    }
}