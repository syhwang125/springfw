package com.timestable.module00;
import java.util.Scanner;


public class TimesTable023 {

	private static final int MAX_TIMES = 9; 
	private static final  int START_LEFT_NUMBER = 2;
	private static final String EXIT_MENU = "0";
	
	
	private static final Scanner scanner ;
	private static int columnCount ;
	private static Format format;
	private static SortOrder sortOrder;
	
	static {
		columnCount = 4;
		format = Format.InMath;
		scanner = new Scanner(System.in);
		sortOrder = SortOrder.Ascending;  
	}
	
	public static void main(String[] args) {
		showMenu();
	}

	private static void showMenu() {
		int inputNumber;
		
		while (true) {
			displayMainMenu();
			inputNumber = selectMenu();
			
			switch (inputNumber) {
				case 1: 
					displayColumnTypeSubMenu();
					break;
				case 2:
					displaySquareTypeSubMenu();
					break;
				case 0:
					exitProgram();
					return;
				default:
					System.out.println("Please select again");
			}
		}
		
		
	}

	private static void exitProgram() {
		scanner.close();
		System.out.println("Program exit. Bye...");
		System.exit(0);
	}

	private static int selectMenu() {
		System.out.print(" > Select number : ");
		int menuNumber = scanner.nextInt();
		
		if(menuNumber >= 0 && menuNumber <= 2) {
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
		System.out.println("  TimesTable 023  menu  ");
		System.out.println("..........................................");
		System.out.println(" 1. Column type");
		System.out.println(" 2. Square type");
		System.out.println(" 0. Exit");
		System.out.println("..........................................");
		
	}

	private static void displayColumnTypeSubMenu() {
		//
		// 코드를 채우세요.
		while(true) {
			int selectedColumnCount = selectColumnCount();
			
			if(selectedColumnCount == 0) {
				break;
			}
			
			String formatTypeInitial = selectFormatType();
			if(formatTypeInitial.equals("0")) {
				break;
			}
			
			columnCount = selectedColumnCount;
			format = Format.valueFromInitial(formatTypeInitial);
			
			showOption();
			showColumnTable();
		}
	}

	private static int selectColumnCount() {
		//
		// 코드를 채우세요.
		int columnCount;
		while(true) {
			System.out.print(" >> Input column count(Previous:0)) : " );
			columnCount = scanner.nextInt();
			
			if(columnCount < 0 || columnCount >= 9) {
				System.out.println(" >> Invalid column count, select again!! ");
				continue;
			}
			break;
		}
		return columnCount;
	}

	private static String selectFormatType() {
		//
		String formatTypeInitial = Format.InMath.initial;
		// 코드를 채우세요.
			System.out.println(buildFormatSelectionSubMenu());
			formatTypeInitial = scanner.next();
			
		return formatTypeInitial;
	}

	private static void displaySquareTypeSubMenu() {
		//
		// 코드를 채우세요.
		while(true) {
			String sortOrderInitial = selectSortOrder();
			
			if(sortOrderInitial.equals("0")) {
				break;
			} 
			
			sortOrder = SortOrder.valueFromInitial(sortOrderInitial);
			showOption();
			showSquareTable();
		}
	}

	private static String selectSortOrder() {
		//
		String sortOrderInitial = SortOrder.Ascending.initial;
		// 코드를 채우세요.
		
		while(true) {
			System.out.println(buildSortOrderSelectionSubMenu());
			sortOrderInitial = scanner.next();
			sortOrderInitial = sortOrderInitial.toLowerCase();
			
			if(SortOrder.isValidInitial(sortOrderInitial) || sortOrderInitial.CASE_INSENSITIVE_ORDER.equals("0")) {
				break;
			}
		}

		return sortOrderInitial;
	}

	private static String buildFormatSelectionSubMenu() {
		//
		return String.format(
				" >> Select format(Math type:%s, English type:%s, Previous:%s)): ",
				Format.InMath.initial(),
				Format.InEnglish.initial(),
				EXIT_MENU);
	}

	private static String buildSortOrderSelectionSubMenu() {
		//
		return String.format(
				" >> Sort order(Ascending:%s, Descending:%s, Exit:%s)): ",
				SortOrder.Ascending.initial(),
				SortOrder.Descending.initial(),
				EXIT_MENU);
	}

	public static void showOption() {
		//
		System.out.printf("\n columnCount: %d, format type: %s, sort order: %s%n",
				columnCount,
				format.name(),
				sortOrder);

		System.out.println("...........................................");
	}

	public static void showColumnTable() {
		//
		// 코드를 채우세요.
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

	private static String buildTimesItem(int leftOffset, int rightNumber) {
		// TODO Auto-generated method stub
		return String.format(format.formatString(),
								leftOffset,
								rightNumber,
								(leftOffset * rightNumber));
	}

	public static void showSquareTable () {
		//
		// 코드를 채우세요.
		
		switch (sortOrder) {
			case Ascending : 
				showAscendingSquareTable();
				break;
			case Descending:
				showDescendingSquareTable();
				break;
		} 
		showMenu();
	}

	private static void showAscendingSquareTable() {
		for(int leftNumber = 1; leftNumber <= 9; leftNumber++) {
			System.out.println(buildLine(leftNumber));
		}
	}
	
	private static void showDescendingSquareTable() {
		for(int leftNumber = 9; leftNumber >=1; leftNumber--) {
			System.out.println(buildLine(leftNumber));
		}
	}

	private static String buildLine(int leftNumber) {
		StringBuilder builder = new StringBuilder();
		
		for(int rightNumber = 1; rightNumber <= 9; rightNumber++) {
			builder.append(String.format("%2d ", leftNumber * rightNumber));
		}
		return builder.toString();
	}



	enum Format {
		//
		InEnglish("e", " %d times %d = %2d "),
		InMath("m", " %d x %d = %2d "),
		InJava("j", " %d * %d = %2d ");

		private final String initial;
		private final String formatString;

		Format(String initial, String formatString) {
			//
			this.initial = initial;
			this.formatString = formatString;
		}

		public String formatString() {
			//
			return formatString;
		}

		public String initial() {
			//
			return initial;
		}

		public static Format valueFromInitial(String initial) {
			//
			Format format;
			switch(initial) {
				case "e":
					format = InEnglish;
					break;
				case "m":
					format = InMath;
					break;
				case "j":
					format = InJava;
					break;
				default:
					throw new IllegalArgumentException("Invalid Format initial: " + initial);
			}

			return format;
		}

		public static boolean isValidInitial(String initial) {
			//
			String initialString = "emj";
			String searchInitial = ".*" + initial + ".*";

			return initialString.matches(searchInitial);
		}
	}

	enum SortOrder {
		//
		Ascending("a"),
		Descending("d");

		private final String initial;

		SortOrder(String initial) {
			//
			this.initial = initial;
		}

		public String initial() {
			//
			return initial;
		}

		public static SortOrder getDefault() {
			//
			return Ascending;
		}

		public static SortOrder valueFromInitial(String initial) {
			//
			SortOrder sortOrder;
			switch (initial) {
				case "a":
					sortOrder = Ascending;
					break;
				case "d":
					sortOrder = Descending;
					break;

				default:
					throw new IllegalArgumentException("Invalid SortOrder initial: " + initial);
			}

			return sortOrder;
		}

		public static boolean isValidInitial(String initial) {
			//
			if (initial.equals(Ascending.initial()) || initial.equals(Descending.initial())) {
				return true;
			}

			return false;
		}
	}
}
