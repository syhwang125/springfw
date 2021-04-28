package oop;

public class TimesTableDemo {

	private TimesTable timesTable;
	
	TimesTableDemo(){
		this.timesTable = new TimesTable();
		this.timesTable.initailize();
	}
	
	public void showTableLineDemo() {
		ConsoleView consoleView = new ConsoleView(LineType.TableLine);
		consoleView.setColumnSize(4);
		consoleView.setStyle(ExpressionStyle.InMath);
		consoleView.show(this.timesTable);
		consoleView.showLineSeparator();
	}
	
	public static void main(String[] args) {
		TimesTableDemo demo = new TimesTableDemo();
		demo.showTableLineDemo();
		demo.showSimpleLineDemo();

	}

	private void showSimpleLineDemo() {
		ConsoleView consoleView = new ConsoleView(LineType.SimpleLine);
		consoleView.setColumnSize(4);
		consoleView.setStyle(ExpressionStyle.InMath);
		consoleView.show(this.timesTable);
		consoleView.showLineSeparator();
	}

}
