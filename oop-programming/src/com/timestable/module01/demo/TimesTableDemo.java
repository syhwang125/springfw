package com.timestable.module01.demo;

import com.timestable.module01.domain.Format;
import com.timestable.module01.domain.SortOrder;
import com.timestable.module01.domain.Table;
import com.timestable.module01.domain.TableOption;
import com.timestable.module01.domain.TimesTable;

public class TimesTableDemo {
    //
    public static void main(String[] args) {
        //
        TimesTable timesTable = new TimesTable();

        TableOption tableOption = timesTable.getTableOption();
        tableOption.setTableFormat(Format.InEnglish);
        tableOption.setTableOrder(SortOrder.Ascending);
        tableOption.setEquationOrder(SortOrder.Descending);
        timesTable.setTableOption(tableOption);

        showTimesTable(timesTable);  
    }

    public static void showTimesTable(TimesTable timesTable) {
        //
        System.out.println("Times table ");
        System.out.println("............");
        for(Table table : timesTable.requestTables()) {
            showUnitTable(table);
            System.out.println("............");
        }
    }

    public static void showUnitTable(Table table) {
        //
        for(String formattedEquation  : table.requestFormattedEquations()) {
            System.out.println(formattedEquation);
        }
    }
}