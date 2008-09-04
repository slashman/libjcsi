package net.slashie.libjcsi;

import java.util.*;

public class CharKey {
	public final static int
		UARROW = 0,
		DARROW = 1,
		LARROW = 2,
		RARROW = 3,
		LCTRL = 4 ,
		RCTRL = 5 ,
		LALT = 6,
		RALT = 7,
		LSHIFT = 8,
		RSHIFT = 9,
		ENTER = 10,
		BACKSPACE = 11,
		F1 = 12,
		F2 = 13,
		F3 = 14,
		F4 = 15,
		F5 = 16,
		F6 = 17,
		F7 = 18,
		F8 = 19,
		F9 = 20,
		F10 = 21,
		F11 = 22,
		F12 = 23,
		INSERT = 24,
		HOME = 25,
		PAGEUP = 26,
		PAGEDOWN = 27,
		DELETE = 28,
		END = 29,
		ESC = 30,
		TAB = 31,
		OPENSHARPBRACETS = 32,
		CLOSESHARPBRACETS = 33,
		SEMICOLON = 34,
		APOSTROPHE = 35,
		COMMA = 36,
		DOT = 37,
		SLASH = 38,
		BACKSLASH = 39,
		SPACE = 40,
		MINUS = 41,
		EQUALS = 42,
		BACKAPOSTROPHE = 43,
		CURLYMINUS = 44,
		EXCLAMATION = 45,
		ARROBE = 46,
		MONEY = 47,
		PERCENTAGE = 48,
		EXPONENCIATION = 49,
		AMPERSAND = 50,
		ASTERISK = 51,
		OPENPARENTHESIS = 52,
		CLOSEPARENTHESIS = 53,
		UNDERLINE = 54,
		PLUS = 55,
		OPENCURLYBRACETS = 56,
		CLOSECURLYBRACETS = 57,
		COLON = 58,
		COMILLAS = 59,
		LESSTHAN = 60,
		MORETHAN = 61,
		QUESTION = 62,
		OR = 63,
		a = 64,
		b = 65,
		c = 66,
		d = 67,
		e = 68,
		f = 69,
		g = 70,
		h = 71,
		i = 72,
		j = 73,
		k = 74,
		l = 75,
		m = 76,
		n = 77,
		o = 78,
		p = 79,
		q = 80,
		r = 81,
		s = 82,
		t = 83,
		u = 84,
		v = 85,
		w = 86,
		x = 87,
		y = 88,
		z = 89,
		A = 90,
		B = 91,
		C = 92,
		D = 93,
		E = 94,
		F = 95,
		G = 96,
		H = 97,
		I = 98,
		J = 99,
		K = 100,
		L = 101,
		M = 102,
		N = 103,
		O = 104,
		P = 105,
		Q = 106,
		R = 107,
		S = 108,
		T = 109,
		U = 110,
		V = 111,
		W = 112,
		X = 113,
		Y = 114,
		Z = 115,
		NONE = 116,
		N0 = 117,
		N1 = 118,
		N2 = 119,
		N3 = 120,
		N4 = 121,
		N5 = 122,
		N6 = 123,
		N7 = 124,
		N8 = 125,
		N9 = 126,
		CTRL = 127
		;

	public int code;
	public static Hashtable mirrors = new Hashtable(20);

	{
		mirrors.put(""+OPENSHARPBRACETS, "[");
		mirrors.put(""+CLOSESHARPBRACETS, "]");
		mirrors.put(""+SEMICOLON, ";");
		mirrors.put(""+APOSTROPHE, "'");
		mirrors.put(""+COMMA, ",");
		mirrors.put(""+DOT, ".");
		mirrors.put(""+SLASH, "/");
		mirrors.put(""+BACKSLASH, "\\");
		mirrors.put(""+SPACE, " ");
		mirrors.put(""+MINUS, "-");
		mirrors.put(""+EQUALS, "=");
		mirrors.put(""+BACKAPOSTROPHE, "`");
		mirrors.put(""+CURLYMINUS, "~");
		mirrors.put(""+EXCLAMATION, "!");
		mirrors.put(""+ARROBE, "@");
		mirrors.put(""+MONEY, "$");
		mirrors.put(""+PERCENTAGE, "%");
		mirrors.put(""+EXPONENCIATION, "^");
		mirrors.put(""+AMPERSAND, "&");
		mirrors.put(""+ASTERISK, "*");
		mirrors.put(""+OPENPARENTHESIS, "(");
		mirrors.put(""+CLOSEPARENTHESIS, ")");
		mirrors.put(""+UNDERLINE, "_");
		mirrors.put(""+PLUS, "+");
		mirrors.put(""+OPENCURLYBRACETS, "{");
		mirrors.put(""+CLOSECURLYBRACETS, "}");
		mirrors.put(""+COLON, ":");
		mirrors.put(""+COMILLAS, "\"");
		mirrors.put(""+LESSTHAN, "<");
		mirrors.put(""+MORETHAN, ">");
		mirrors.put(""+QUESTION, "?");
		mirrors.put(""+OR, "|");
	}

	public CharKey(int code){
		this.code = code;
	}

	public CharKey(){

    }

    public String toString(){
		//return code+"";
		//
//		return ""+((char)(code-2));
		if (code>=90 && code <= 115)
			return ""+((char)(code-25));
		if (code>=64 && code <= 89)
			return ""+((char)(code+33));
		if (code>=117 && code <= 126)
			return ""+((char)(code-69));
		String ret = (String) mirrors.get(""+code);
		if (ret != null)
			return ret;
		else
			return "("+code+")";
	}
    
    public boolean isArrow (){
    	boolean ret = (isRightArrow()|| isUpArrow() || isLeftArrow() || isDownArrow() || isDownLeftArrow() || isDownRightArrow() || isUpLeftArrow() || isUpRightArrow() || isSelfArrow());
    	return ret;
    }
    
    public boolean isDownArrow (){
		return code == CharKey.DARROW || code == CharKey.N2;
	}

    public boolean isDownLeftArrow (){
		return code == CharKey.N1;
	}

    public boolean isDownRightArrow (){
		return code == CharKey.N3;
	}
    
    public boolean isCenterArrow (){
		return code == CharKey.N5;
	}

    public boolean isLeftArrow (){
		return code == CharKey.LARROW || code == CharKey.N4;
	}

    public boolean isRightArrow (){
		return code == CharKey.RARROW || code == CharKey.N6;
	}

    public boolean isUpArrow (){
		return code == CharKey.UARROW || code == CharKey.N8;
	}

    public boolean isUpLeftArrow (){
		return code == CharKey.N7;
	}

    public boolean isUpRightArrow (){
		return code == CharKey.N9;
	}

    public boolean isSelfArrow (){
		return code == CharKey.N5;
	}
    
    public boolean isMetaKey(){
    	return code == CTRL ||
    		   code == RALT ||
    		   code == RCTRL ||
    		   code == RSHIFT ||
    		   code == LALT ||
    		   code == LCTRL ||
    		   code == LSHIFT;  
    }
    
    public boolean isAlphaNumeric(){
    	if (code>=90 && code <= 115)
			return true;
		if (code>=64 && code <= 89)
			return true;
		if (code>=117 && code <= 126)
			return true;
		return false;
    }
}