package oop;

import java.util.LinkedList;

public interface LineView {

	public void takeTables( LinkedList<UnitTable> unitTableList );
	
	public void setStyle(ExpressionStyle style);
	
	public void showLine();
	
	public int getLineLength();
	 
}
