
public class TimesTable011 {

	public static void main(String[] argst) {
	
		System.out.println(" Times tables. " );
		System.out.println("------------------------");
		for (int leftNum = 2; leftNum <= 9; leftNum++) {
			for (int rightNum = 1; rightNum <= 9; rightNum++) {
				System.out.format(" %d x %d = %2d %n",  leftNum, rightNum , (leftNum * rightNum) ); 
				}
				System.out.println("------------------------- ");
			}
		}
	
}
