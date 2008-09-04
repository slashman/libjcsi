package net.slashie.libjcsi.wswing;

import java.awt.event.*;

import net.slashie.libjcsi.CharKey;


public class StrokeInformer implements KeyListener, java.io.Serializable{
	private int bufferCode;
	//private transient UserInterface aUI;
	private transient Thread keyListener;

	public StrokeInformer(){
		bufferCode = -1;
	}

	public void informKey (Thread toWho){
		keyListener = toWho;
	}

	public int getInkeyBuffer(){
		return bufferCode;
	}

	/*public void setUI(UserInterface pUI){
		aUI = pUI;
	}*/

	public void keyPressed(KeyEvent e) {
	    bufferCode = charCode(e);
	    //if (!e.isShiftDown())
		    keyListener.interrupt();
    }

    private int charCode(KeyEvent x){
    	int code = x.getKeyCode();
    	if(x.isControlDown()) {
			return CharKey.CTRL;
		}
    	if (code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z){
    		if (x.getKeyChar() >= 'a'){
	    		int diff = KeyEvent.VK_A - CharKey.a;
   		 		return code-diff;
   			} else {
	   			int diff = KeyEvent.VK_A - CharKey.A;
   		 		return code-diff;
   			}
    	}

    	if (x.getKeyChar() == '?')
    		return CharKey.QUESTION;

		switch (x.getKeyCode()){
			case KeyEvent.VK_SPACE:
				return CharKey.SPACE;
			
			case KeyEvent.VK_COMMA:
				return CharKey.COMMA;
			case KeyEvent.VK_PERIOD: case KeyEvent.VK_DECIMAL:
				return CharKey.DOT;
			case KeyEvent.VK_NUMPAD0:
				return CharKey.N0;
			case KeyEvent.VK_NUMPAD1:
				return CharKey.N1;
			case KeyEvent.VK_NUMPAD2:
				return CharKey.N2;
			case KeyEvent.VK_NUMPAD3:
				return CharKey.N3;
			case KeyEvent.VK_NUMPAD4:
				return CharKey.N4;
			case KeyEvent.VK_NUMPAD5:
				return CharKey.N5;
			case KeyEvent.VK_NUMPAD6:
				return CharKey.N6;
			case KeyEvent.VK_NUMPAD7:
				return CharKey.N7;
			case KeyEvent.VK_NUMPAD8:
				return CharKey.N8;
			case KeyEvent.VK_NUMPAD9:
				return CharKey.N9;
			case KeyEvent.VK_F1:
				return CharKey.F1;
			case KeyEvent.VK_ENTER:
				return CharKey.ENTER;
			case KeyEvent.VK_BACK_SPACE:
				return CharKey.BACKSPACE;
			case KeyEvent.VK_ESCAPE:
				return CharKey.ESC;
			case KeyEvent.VK_UP:
				return CharKey.UARROW;
			case KeyEvent.VK_DOWN:
				return CharKey.DARROW;
			case KeyEvent.VK_LEFT:
				return CharKey.LARROW;
			case KeyEvent.VK_RIGHT:
				return CharKey.RARROW;

		}
		return CharKey.NONE;
	}

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}