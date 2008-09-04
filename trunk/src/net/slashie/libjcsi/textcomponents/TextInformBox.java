package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

/** Represents a text box which can be updated to
 * show a new text along with the old one until
 * it is full, in which case it shows a [More]
 * prompt, waits for a player keypress, erases it
 * selfs and continues exhibiting the same behaviour.
 * @author szapata
 *
 */
public class TextInformBox extends TextComponent{
	private int curx, cury;
	private StringBuffer[] lines;
	private String curLine;
	
	public void setHeight(int value) {
		super.setHeight(value);
		lines = new StringBuffer[value];
		for (int i = 0; i < lines.length; i++)
			lines[i] = new StringBuffer("");
	}
	
	public void draw() {
		for (int i = 0; i < lines.length; i++){
			si.print(position.x, position.y+i, lines[i].toString(), foreColor);
		}
	}
	
	public void addText(String text){
		String [] tokens = text.split(" ");
		for (int i = 0; i < tokens.length; i++){
			int distance = width - curx;
			if (cury>=height-1)
				distance -= "[MORE]".length();
			if (distance < tokens[i].length() + 1){
				if (cury < height -1){
					curx = 0;
					cury++;
				} else {
					//i--;
					lines[cury].append("[MORE]");
					morePrompt();
					clear();
				}
			}
			lines[cury].append(tokens[i]+" ");
			curx += tokens[i].length()+1;
		}
	}
	
	public void setText(String text){
		clear();
		addText(text);
	}
	
	
	public void clear(){
		for (int i = 0; i < lines.length; i++)
			lines[i] = new StringBuffer("");
		for (int i = 0; i < height; i++)
			si.print(position.x, position.y+i, spaces);
		curx=0;
		cury=0;
	}
	
	private void morePrompt(){
		draw();
		si.refresh();
		CharKey ck = new CharKey(CharKey.NONE);
		while (ck.code != CharKey.SPACE)
			ck = si.inkey();
	}

	public TextInformBox(ConsoleSystemInterface si){
		super(si);		
	}
}
