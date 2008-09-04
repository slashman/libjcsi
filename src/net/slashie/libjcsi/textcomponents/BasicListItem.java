package net.slashie.libjcsi.textcomponents;

public class BasicListItem implements ListItem{
	private String rowData;
	private char index;
	private int indexColor;
	
	public BasicListItem(char index, int indexColor, String data){
		setRow(data);
		this.index = index;
		this.indexColor = indexColor;
	}
	
	public String getRow(){
		return rowData;
	}
	
	public void setRow(String data){
		rowData = data;
	}
	
	public void setIndex(char index) {
		this.index = index;
	}

	public void setIndexColor(int indexColor) {
		this.indexColor = indexColor;
	}

	public char getIndex(){
		return index;
	}
	
	public int getIndexColor(){
		return indexColor;
	}
}
