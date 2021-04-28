package oop;


import javax.swing.SortOrder;

public class TimeTableApp {

	public static void main(String[] args) {
		printTimeTable(4); 

	}

	public static void printTimeTable(int columnCount) {
		int loopCount = ( 8 / columnCount) + 1; 
		
		int leftNumberOffset = 2;
		
		for(int i =0; i < loopCount ; i++) {
//			for (int leftNum = 2; leftNum <= 9; leftNum++) {
				for(int rightNum = 1; rightNum<= 9; rightNum++) {
					for(int count = 0; count < columnCount; count++) {
						int leftNum = leftNumberOffset + count;
						if(leftNum > 9) {
							break;
						}
						printUnit(leftNum, rightNum); 
					}
					System.out.println(" ");
			}
			leftNumberOffset += columnCount;
			System.out.println(" ");
		}
	}

	private static void printUnit(int leftNum, int rightNum) {
		System.out.print("\t " + leftNum + " x " + rightNum + " = " + (leftNum * rightNum) ); 
//		System.out.format( " %2d x %d = %2d%n ",
//				leftNum, 
//				rightNum, 
//				(leftNum*rightNum) );
	}
	
	/*
	public void showLineTable(SortOrder sortOrder) {
		int leftNum = 1;
		int addNum = 1;
		
		if(SortOrder.DESCENDING.equals(sortOrder)) {
			leftNum = MaxTimes;
			addNum = -1;
		}
		System.out.println();
		
		while(true) {
			System.out.println(" ");
			for(int rightNum = 1; rightNum <= MaxTimes; rightNum++) {
				System.out.print(getColumn(leftNum, rightNum)) ;
			}
			System.out.println());
			
			leftNum += addNum;
			if(leftNum < 2 || leftNum > MaxTimes) {
				break;
			}
		}
		System.out.println();
	}
	*/
}
