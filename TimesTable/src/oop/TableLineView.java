package oop;

import java.util.LinkedList;
import java.util.List;

public class TableLineView implements LineView{

	public int  DefaultTableLength = 20;  //read only
	public int columnSize = 0;
	public ExpressionStyle style;
	public List<UnitTable> unitTables;
	public int InetLength;
	
	public TableLineView(int i) {
		
	}
	
	public void showLine() {
		
	}
	
	public void setStyle(ExpressionStyle style) {
		
	}
	
	public void addUnitTable(UnitTable unitTable) {
		
	}
	


	@Override
	public void takeTables(LinkedList<UnitTable> unitTableList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLineLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
