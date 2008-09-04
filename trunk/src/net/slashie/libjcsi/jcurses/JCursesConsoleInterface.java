package net.slashie.libjcsi.jcurses;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.util.Position;
import jcurses.system.*;


public class JCursesConsoleInterface implements ConsoleSystemInterface{
	private int[][] colors;
	private char[][] chars;

	private int[][] colorsBuffer;
	private char[][] charsBuffer;
	
	public JCursesConsoleInterface(){
            Toolkit.startPainting();
            colors = new int[Toolkit.getScreenWidth()+1][Toolkit.getScreenHeight()+1];
            chars = new char[Toolkit.getScreenWidth()+1][Toolkit.getScreenHeight()+1];
            colorsBuffer = new int[Toolkit.getScreenWidth()+1][Toolkit.getScreenHeight()+1];
            charsBuffer = new char[Toolkit.getScreenWidth()+1][Toolkit.getScreenHeight()+1];
	}

	public void print (int x, int y, char what, int color){
            //if (isInsideBounds(x,y))
            if (chars[x][y] == what && colors[x][y] == color)
                return;
            Toolkit.printString(what+"", x, y, getJCurseColor(color));
            colors[x][y] = color;
            chars[x][y] = what;
	}

	public void print (int x, int y, String what, int color){
		for (int i = 0; i < what.length(); i++){
			if (! isInsideBounds(x+i,y))
				break;
			chars[x+i][y] = what.charAt(i);
			colors[x+i][y] = color;
		}
		Toolkit.printString(what, x, y, getJCurseColor(color));
	}
	public void print (int x, int y, String what){
		for (int i = 0; i < what.length(); i++){
			if (!isInsideBounds(x+i,y))
				break;
			chars[x+i][y] = what.charAt(i);
			colors[x+i][y] = ConsoleSystemInterface.WHITE;
		}
		Toolkit.printString(what, x, y, WHITE);
	}

	public char peekChar(int x, int y){
		return chars[x][y];
	}

	public int peekColor(int x, int y){
		return colors[x][y];
	}

	public CharKey inkey(){
		InputChar c = Toolkit.readCharacter();
		return new CharKey(ASCtoCharKeyCode(c.getCode()));
	}

    /**  Waits until a key is pressed and returns it */
    public void locateCaret (int x, int y){
    	caretPosition.x = x;
    	caretPosition.y = y;
    }

    private Position caretPosition = new Position(0,0);

    public String input(){
    	return input(999);
    }

    public String input(int l){
	    String ret = "";
		CharKey read = new CharKey(CharKey.NONE);
		while (true){
			while (read.code == CharKey.NONE)
				read = inkey();
			if (read.code == CharKey.ENTER)
				break;
			if (read.code == CharKey.BACKSPACE){
				if (ret.equals("")){
					read.code = CharKey.NONE;
					continue;
				}
				if (ret.length() > 1)
					ret = ret.substring(0, ret.length() -1);
				else
					ret = "";
                caretPosition.x--;
				print(caretPosition.x, caretPosition.y, " ");

            }
			else {
				if (ret.length() >= l){
					read.code = CharKey.NONE;
					continue;
				}
				if (!read.isAlphaNumeric()){
					read.code = CharKey.NONE;
					continue;
				}
					
				String nuevo = read.toString();
				print(caretPosition.x, caretPosition.y, nuevo+"_");
				ret +=nuevo;
				caretPosition.x++;
			}
			refresh();
			read.code = CharKey.NONE;

		}
		return ret;
    }

	public boolean isInsideBounds(Position p){
		return p.x>=0 && p.x <= Toolkit.getScreenWidth() && p.y >=0 && p.y <=Toolkit.getScreenHeight();
	}

	public boolean isInsideBounds(int x, int y){
		return x>=0 && x <= Toolkit.getScreenWidth() && y >=0 && y <=Toolkit.getScreenHeight();
	}



    public void cls(){
    	Toolkit.clearScreen(BLACK);
    	for (int x = 0; x < chars.length; x++)
    		for (int y = 0; y < chars[0].length; y++) {
				chars[x][y] = '\u0000';
				colors[x][y] = ConsoleSystemInterface.BLACK;
			}
	}


    public void refresh(){
		Toolkit.endPainting();
		Toolkit.startPainting();
		Toolkit.printString("", 79,24,BLACK);
	}
    public void refresh(Thread t){
		refresh();
	}
    public void flash(int color){
/*		Toolkit.clearScreen(new CharColor(getJCurseColor(color).getForeground(), getJCurseColor(color).getForeground()));
		try {
			Thread.sleep(10);
		}
		 catch (InterruptedException ie){
		}
		Toolkit.clearScreen(BLACK);   */
		//Toolkit.changeColors(new Rectangle(Toolkit.UL_CORNER, Toolkit.LR_CORNER), new CharColor(getJCurseColor(color).getForeground(), CharColor.BLACK));
	}

    public void setAutoRefresh(boolean value){}

        private final CharColor BLACK = new CharColor(CharColor.BLACK, CharColor.BLACK);
		private final CharColor DARK_BLUE = new CharColor(CharColor.BLACK, CharColor.BLUE);
		private final CharColor GREEN = new CharColor(CharColor.BLACK, CharColor.GREEN);
		private final CharColor TEAL = new CharColor(CharColor.BLACK, CharColor.CYAN);
		private final CharColor DARK_RED = new CharColor(CharColor.BLACK, CharColor.RED);
		private final CharColor PURPLE = new CharColor(CharColor.BLACK, CharColor.MAGENTA);
		private final CharColor BROWN = new CharColor(CharColor.BLACK, CharColor.YELLOW);
		private final CharColor LIGHT_GRAY  = new CharColor(CharColor.BLACK, CharColor.WHITE);
        private final CharColor GRAY = new CharColor(CharColor.BLACK, CharColor.BLACK, CharColor.BOLD, CharColor.BOLD);
		private final CharColor BLUE = new CharColor(CharColor.BLACK, CharColor.BLUE, CharColor.BOLD, CharColor.BOLD);
		private final CharColor LEMON = new CharColor(CharColor.BLACK, CharColor.GREEN, CharColor.BOLD, CharColor.BOLD);
		private final CharColor CYAN = new CharColor(CharColor.BLACK, CharColor.CYAN, CharColor.BOLD, CharColor.BOLD);
		private final CharColor RED = new CharColor(CharColor.BLACK, CharColor.RED, CharColor.BOLD, CharColor.BOLD);
		private final CharColor MAGENTA = new CharColor(CharColor.BLACK, CharColor.MAGENTA, CharColor.BOLD, CharColor.BOLD);
		private final CharColor YELLOW = new CharColor(CharColor.BLACK, CharColor.YELLOW, CharColor.BOLD, CharColor.BOLD);
		private final CharColor WHITE = new CharColor(CharColor.BLACK, CharColor.WHITE, CharColor.BOLD, CharColor.BOLD);


    private CharColor getJCurseColor(int crlColor){
    	switch (crlColor){
   	        case ConsoleSystemInterface.BLACK: return BLACK;
			case ConsoleSystemInterface.DARK_BLUE: return DARK_BLUE;
			case ConsoleSystemInterface.GREEN: return GREEN;
			case ConsoleSystemInterface.TEAL: return TEAL;
			case ConsoleSystemInterface.DARK_RED: return DARK_RED;
			case ConsoleSystemInterface.PURPLE: return PURPLE;
			case ConsoleSystemInterface.BROWN: return BROWN;
			case ConsoleSystemInterface.LIGHT_GRAY: return LIGHT_GRAY;
    	    case ConsoleSystemInterface.GRAY: return GRAY;
			case ConsoleSystemInterface.BLUE: return BLUE;
			case ConsoleSystemInterface.LEMON: return LEMON;
			case ConsoleSystemInterface.CYAN: return CYAN;
			case ConsoleSystemInterface.RED: return RED;
			case ConsoleSystemInterface.MAGENTA: return MAGENTA;
			case ConsoleSystemInterface.YELLOW: return YELLOW;
			case ConsoleSystemInterface.WHITE: return WHITE;
		}
		return BLACK;
	}

	private final static int KEY_BACKSPACE = InputChar.KEY_BACKSPACE;
	private final static int KEY_UP = InputChar.KEY_UP;
	private final static int KEY_DOWN = InputChar.KEY_DOWN;
	private final static int KEY_F1 = InputChar.KEY_F1;
	private final static int KEY_LEFT = InputChar.KEY_LEFT;
	private final static int KEY_RIGHT = InputChar.KEY_RIGHT;

	private int ASCtoCharKeyCode(int code){
    	if (code >= 65 && code <= 90)
	    	return code - (65 - CharKey.A);
	    else
    	if (code >= 97 && code <= 122)
	    	return code- (97 - CharKey.a);

		switch (code){
		
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
		
		if (code == KEY_F1) return CharKey.F1;
		else if (code == InputChar.KEY_F2) return CharKey.F2;
		else if (code == InputChar.KEY_F3) return CharKey.F3;
		else if (code == InputChar.KEY_F4) return CharKey.F4;
		else if (code == InputChar.KEY_F5) return CharKey.F5;
		else if (code == InputChar.KEY_F6) return CharKey.F6;
		else if (code == InputChar.KEY_F7) return CharKey.F7;
		else if (code == InputChar.KEY_F8) return CharKey.F8;
		else if (code == InputChar.KEY_F9) return CharKey.F9;
		else if (code == InputChar.KEY_F10) return CharKey.F10;
		else if (code == InputChar.KEY_F11) return CharKey.F11;
		else if (code == InputChar.KEY_F12) return CharKey.F12;
		else if (code == KEY_BACKSPACE)	return CharKey.BACKSPACE;
		else if (code == KEY_UP)return CharKey.UARROW;
		else if (code == KEY_DOWN) return CharKey.DARROW;
		else if (code == KEY_LEFT) return CharKey.LARROW;
		else if (code == KEY_RIGHT) return CharKey.RARROW;
		return -1;
	}

	public void safeprint (int x, int y, char what, int color){
		if (isInsideBounds(x,y))
			print(x,y,what,color);
	}
	
	public void waitKey (int keyCode){
		CharKey x = new CharKey(CharKey.NONE);
		while (x.code != keyCode)
			x = inkey();
	}

	public void restore() {
		/*for (int i = 0; i < colors.length; i++){
			System.arraycopy(colorsBuffer[i], 0, colors[i], 0, colors[i].length-1);
			System.arraycopy(charsBuffer[i], 0, chars[i], 0, colors[i].length-1);
		}*/
		for (int x = 0; x < colors.length; x++)
			for (int y = 0; y < colors[0].length; y++)
				this.print(x,y,charsBuffer[x][y], colorsBuffer[x][y]);
	}

	public void saveBuffer() {
		for (int i = 0; i < colors.length; i++){
			System.arraycopy(colors[i], 0, colorsBuffer[i], 0, colors[i].length-1);
			System.arraycopy(chars[i], 0, charsBuffer[i], 0, colors[i].length-1);
		}
	}

}