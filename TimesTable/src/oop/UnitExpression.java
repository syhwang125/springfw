package oop;

public class UnitExpression {

	private int leftNumber;
	private int rightNumber;
	private int resultNumber;
	
	public UnitExpression(int leftNumber, int rightNumber) {
		this.leftNumber = leftNumber;
		this.rightNumber = rightNumber;
		this.resultNumber = leftNumber * rightNumber;
	}
	
	public String toString() {
		return null;
	}
	
	public void show(ExpressionStyle style) {
		System.out.format(style.InEnglish, leftNumber, rightNumber);
	}
	
	public static UnitExpression getSample() {
		
	}
}
