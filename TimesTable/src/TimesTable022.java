import java.text.Format;
import java.util.Scanner;

public class TimesTable022 {

	private static final int MAX_TIMES = 9; 
	private static final  int START_LEFT_NUMBER = 2;
	
	private static final Scanner scanner ;
	private static int columnCount ;
	private static final Format format;
	
	static {
		columnCount = 4;
		format = Format.InMath;
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		showMenu();
	}

	public static void showMenu() {
		int inputNumber = 0;
		
		while (true) {
			displayMainMenu();
			inputNumber = selectMenu();
			
			switch (inputNumber) {
				case 1: 
					selectColumnCount();
					break;
				case 0:
					exitProgram();
					return;
				default:
					System.out.println("Please select again ! ");
			}
		}
	}

	private static void selectColumnCount() {
		while(true) {
			int selectedColumnCount = displayColumnCountSelectionAndGetKey();
			
			if(selectedColumnCount == 0) {
				break;
			}
			
			if(selectedColumnCount < 0 || selectedColumnCount >= 9) {
				System.out.println(" >> Invalid column count, select again !! ");
				continue;
			}
			
			columnCount = selectedColumnCount;
			showOption();
			showTable();
		}
	}
	
	private static void showTable() {
		int leftNumber = START_LEFT_NUMBER;
		
		do {
			for (int rightNumber = 1; rightNumber <= MAX_TIMES; rightNumber++) {
				for(int offset = 0; offset < columnCount; offset++) {
					int leftOffset = leftNumber + offset;
					if( leftOffset > MAX_TIMES ) {
						break;
					}
					System.out.print( buildTimesItem(leftOffset, rightNumber));
					System.out.print("  ");
				}
				System.out.println("  ");
			}
			System.out.println("  ");
			
			leftNumber += columnCount;
		} while ( leftNumber <= MAX_TIMES );
		
	}

	private static String  buildTimesItem(int leftOffset, int rightNumber) {
		/** @formatter off */
		return String.format(format.format, 
											leftOffset, 
											rightNumber,
											(leftOffset * rightNumber));
		/** @formatter on */
	}

	private static void showOption() {
		System.out.printf("\n column count : %d, format type : %s%n", columnCount, format.name());
		System.out.println(".......................................");
	}

	private static int displayColumnCountSelectionAndGetKey() {
		System.out.print(" >> Input column count (Exit:0)) : " );
		int columnCount = scanner.nextInt();
		return columnCount;
	}
	

	private static void exitProgram() {
		scanner.close();
		System.out.println("Program exit, Bye..");
		System.exit(0);
	}

	private static int selectMenu() {
		System.out.print(" > Select number : ");
		int menuNumber = scanner.nextInt();
		
		if(menuNumber >= 0 && menuNumber <= 1) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println(" > Invalid number -> " + menuNumber);
			return -1;
		}
	}

	private static void displayMainMenu() {
		System.out.println("");
		System.out.println("..........................................");
		System.out.println(" TimesTable menu ");
		System.out.println("..........................................");
		System.out.println(" 1. Colimn selection");
		System.out.println(" 0. Exit");
		System.out.println("..........................................");
	}

	
	public enum Format {
		InEnglish(" %d times %d is %2d"),
		 InMath(" %d x %d = %2d");

		private final String format;

		Format(String format) {
			this.format = format;
		}
	}
}
