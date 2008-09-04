package net.slashie.libjcsi.textcomponents;

import net.slashie.libjcsi.ConsoleSystemInterface;

public class TextBox extends TextComponent{
	private StringBuffer [] lines;
	private String title = "";
	
	public TextBox(ConsoleSystemInterface si){
		super(si);
		lines = new StringBuffer[]{new StringBuffer("")};
	}
	
	public void setHeight(int value){
		super.setHeight(value);
		if (hasBorder())
			value -=2;
		
		lines = new StringBuffer[value];
		
		for (int i = 0; i < value; i++){
			lines[i] = new StringBuffer("");
		}
	}

	public void setBorder(boolean value){
		super.setBorder(value);
		if (hasBorder()){
			lines = new StringBuffer[getHeight()-2];
		} else {
			lines = new StringBuffer[getHeight()];
		}
		
		for (int i = 0; i < lines.length; i++){
			lines[i] = new StringBuffer("");
		}
	}
		
	
	public void draw (){
		if (height == 0)
			return;
		clearBox();
		if (hasBorder()){
			drawBorder();
			si.print(position.x+2, position.y, title);
		}
		for (int i = 0; i < lines.length; i++){
			si.print(inPosition.x, inPosition.y+i, lines[i].toString(), foreColor);
		}
	}

	public void setText(String text){
		String [] tokens = text.split(" ");
		int curx = 0; 
		int cury = 0;
		out: 
			for (int i = 0; i < tokens.length; i++){
				int distance = inWidth - curx;
				if (distance < tokens[i].length() + 1){
					if (cury < inHeight -1){
						curx = 0;
						cury++;
					} else {
						break out;
					}
				}
				if (tokens[i].equals("XXX")){
					cury ++;
					curx = 0;
				} else {
					lines[cury].append(tokens[i]+" ");
					curx += tokens[i].length()+1;
				}
			}
	}
	
	public void setTitle(String pTitle){
		title = pTitle;
	}

	public void clear (){
		for (int i = 0; i < lines.length; i++)
			lines[i] = new StringBuffer("");
	}

}