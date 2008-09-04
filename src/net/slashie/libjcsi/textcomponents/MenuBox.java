package net.slashie.libjcsi.textcomponents;

import java.util.*;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.util.*;

public class MenuBox extends TextComponent {
	
	private Vector items;
	private int promptSize;
	private String title = "";

	//State Attributes
	private int currentPage;
	private int pages;
	
	//Components
	private TextBox promptBox;
	
	
	public MenuBox(ConsoleSystemInterface si){
		super(si);
		promptBox = new TextBox(si);
	}
	
	public void setPosition(int x, int y){
		super.setPosition(x,y);
		promptBox.setPosition(inPosition.x,inPosition.y+1);
	}
	
	public void setPromptSize(int size){
		promptSize = size;
		promptBox.setHeight(size);
	}
	
	public void setWidth(int width){
		super.setWidth(width);
		promptBox.setWidth(inWidth);
		promptBox.setPosition(inPosition.x,inPosition.y);
	}
	
	public void setBorder(boolean val){
		super.setBorder(val);
		promptBox.setWidth(inWidth);
		promptBox.setPosition(inPosition.x,inPosition.y);
	}
	
	public void setPrompt(String prompt){
		promptBox.clear();
		promptBox.setText(prompt);
	}
	
	public void setMenuItems(Vector items){
		this.items = items;
	}

	public void draw(){
		//pages = (int)(Math.floor((items.size()-1) / inHeight) +1);
		pages = (int)(Math.floor((items.size()-1) / (inHeight-promptSize)) +1);
		/*System.out.println("items.size() "+items.size());
		System.out.println("inHeight "+inHeight);*/
		clearBox();
		drawBorder();
		if (hasBorder())
			si.print(position.x+2, position.y, title);
		//promptBox.clear();
		promptBox.draw();
		
		int pageElements = inHeight-promptSize;
		/*si.print(inPosition.x, inPosition.y-1+promptSize, "items.len"+items.size() + " PE "+pageElements+"CP"+currentPage+"pages"+pages);
		si.refresh();
		si.waitKey(CharKey.SPACE);*/
		Vector shownItems = Util.page(items, pageElements, currentPage);
		
		int i = 0;
		for (; i < shownItems.size(); i++){
			MenuItem item = (MenuItem) shownItems.elementAt(i);
			si.print(inPosition.x, inPosition.y+i+promptSize, ((char) (97 + i))+"." );
			si.print(inPosition.x+2, inPosition.y+i+promptSize, item.getMenuChar(), item.getMenuColor());
			String description = item.getMenuDescription();
			if (description.length() > getWidth()-5){
				description = description.substring(0,getWidth()-6);
			}
			si.print(inPosition.x+4, inPosition.y+i+promptSize, description);
		}
		//si.print(inPosition.x, inPosition.y, inHeight+" "+pageElements+" "+pages);
		/*for (; i < inHeight-promptSize; i++){
			si.print(inPosition.x, inPosition.y+i+promptSize+1, spaces);
		}*/
		si.refresh();
	}

	public Object getSelection (){
		int pageElements = inHeight - promptSize;
		while (true){
			clearBox();
			draw();
			Vector shownItems = Util.page(items, pageElements, currentPage);
			CharKey key = new CharKey(CharKey.NONE);
			while (key.code != CharKey.SPACE &&
				   key.code != CharKey.UARROW &&
				   key.code != CharKey.DARROW &&
				   key.code != CharKey.N8 &&
				   key.code != CharKey.N2 &&
				   (key.code < CharKey.A || key.code > CharKey.A + pageElements-1) &&
				   (key.code < CharKey.a || key.code > CharKey.a + pageElements-1)
				   )
			   key = si.inkey();
			if (key.code == CharKey.SPACE)
				return null;
			if (key.code == CharKey.UARROW || key.code == CharKey.N8)
				if (currentPage > 0)
					currentPage --;
			if (key.code == CharKey.DARROW || key.code == CharKey.N2)
				if (currentPage < pages-1)
					currentPage ++;
			
			if (key.code >= CharKey.A && key.code <= CharKey.A + shownItems.size()-1)
				return shownItems.elementAt(key.code - CharKey.A);
			else
			if (key.code >= CharKey.a && key.code <= CharKey.a + shownItems.size()-1)
				return shownItems.elementAt(key.code - CharKey.a);

		}
	}
	
	public void setTitle(String s){
		title = s;
	}
}