package com.timestable.module00;

public class TimesTable021 {

	public static void main(String[] args) {
		showTable(4); 

	}

	public static void showTable(int columnCount) {
		int loopCount = ( 8 / columnCount) + 1; 
		
		int leftNumberOffset = 2;
		
		for(int i =0; i < loopCount ; i++) {
				for(int rightNum = 1; rightNum<= 9; rightNum++) {
					for(int count = 0; count < columnCount; count++) {
						int leftNum = leftNumberOffset + count;
						if(leftNum > 9) {
							break;
						}
						System.out.print("\t" + leftNum + "x" + rightNum + "=" + (leftNum * rightNum) ); 
					}
					System.out.println(" ");
			}
			leftNumberOffset += columnCount;
			System.out.println(" ");
		}
	}
	
}
