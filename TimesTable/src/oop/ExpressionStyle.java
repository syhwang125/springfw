package oop;

public enum ExpressionStyle {

	InEnglish(" %d times %d is %2d"),
	InJavaCode(" %d * %d = %2d"),
	 InMath(" %d x %d = %2d"),
	 InKorean(" %d 곱하기 %d 는 %2d");
	
	 private String formatStr;
	
	
	private ExpressionStyle(String style) {
		this.formatStr = style;
	}
}
