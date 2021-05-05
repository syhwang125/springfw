package com.timestable.module02.step1.main;

import com.timestable.module02.step1.view.TableLineType;
import com.timestable.module02.step1.view.ConsoleView;
import com.timestable.module02.step1.view.TableLineViewOption;
import com.timestable.module01.domain.*;

public class ViewDemo211 {
    //
    public static void main(String[] args) {
        //
        int startLeftNumber = 1;
//        TimesTable timesTable = new TimesTable( startLeftNumber, Equation.END_LEFT_NUMBER );
//        showColumnTableLineViewDemo(timesTable);
//        showSquareTableLineViewDemo(timesTable);
    }

    public static void showColumnTableLineViewDemo(TimesTable timesTable) {
        //
        TableLineViewOption tableLineViewOption = new TableLineViewOption(TableLineType.Column, 4);
        ConsoleView consoleView = new ConsoleView(tableLineViewOption);

        consoleView.show(timesTable);
    } 

    public static void showSquareTableLineViewDemo(TimesTable timesTable) {
        //
        TableLineViewOption tableLineViewOption = new TableLineViewOption(TableLineType.Square);
        ConsoleView consoleView = new ConsoleView(tableLineViewOption);

        TableOption tableOption = timesTable.getTableOption();
        tableOption.setTableFormat(Format.InResultValue);
        tableOption.setTableOrder(SortOrder.Descending);
        tableOption.setEquationOrder(SortOrder.Descending);
        timesTable.setTableOption(tableOption);

        consoleView.show(timesTable);
    }
}