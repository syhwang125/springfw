/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module02.step1.view;

import com.timestable.module01.domain.Table;
import com.timestable.module02.step2.view.TriangleTableLineView;
import com.timestable.module01.domain.TimesTable;
import com.timestable.module01.domain.Table;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsoleView {
	//
	private TableLineViewOption tableLineViewOption;
	private List<AbstractTableLineView> tableLineViews;

	public ConsoleView(TableLineViewOption tableLineViewOption) {
		//
		this.tableLineViewOption = tableLineViewOption;
		this.tableLineViews = new ArrayList<>();
	}

	public void setTableViewOption(TableLineViewOption tableLineViewOption) {
		//
		this.tableLineViewOption = tableLineViewOption;
		for(AbstractTableLineView tableLineView : tableLineViews) {
			tableLineView.setTableLineViewOption(tableLineViewOption);
		} 
	}

	public TableLineViewOption getTableViewOption() {
		//
		return tableLineViewOption;
	}

	public void show(TimesTable timesTable) {
		//
		tableLineViews.clear();
		int startLeftNumber = (this.tableLineViewOption.getTableLineType().equals(TableLineType.Square) ? 1 : 2);
		LinkedList<Table> sourceTables = new LinkedList<>(timesTable.requestTables(startLeftNumber));

		while(sourceTables.size() > 0) {
			//
			AbstractTableLineView tableLineView = buildTableLineView();
//			System.out.println( " 0. ConsoleView.show() tableLineView : " + tableLineView + " , sourceTables size : " + sourceTables.size() );
			tableLineView.takeUnitTables(sourceTables);
			tableLineViews.add(tableLineView);
 		}

		showTables();
	}

	private void showTables() {
		//
		System.out.println("");
		for(AbstractTableLineView tableLineView : tableLineViews) {
			tableLineView.showTableLine();
		}
		
	}

	private AbstractTableLineView buildTableLineView() {
		//
		AbstractTableLineView tableLineView;

		switch (tableLineViewOption.getTableLineType()) {
			case Column:
				tableLineView = new ColumnTableLineView(tableLineViewOption);
				break;
			case Square:
				tableLineView = new SquareTableLineView(tableLineViewOption);
				break;
			case Triangle:																	// for extension
				int tableViewCount = tableLineViews.size();				// 0, 1, 2, 3 으로 증가 
				if(tableViewCount == 0) {       // 첫번째 라인 시작 (startIndex = 3, columnCount=1) 
//					System.out.println( " 1.  Consoleview.buildTableLineView() tableViewCount =0 , columnCount = " + tableLineViewOption.getColumnCount() );
					tableLineView = new TriangleTableLineView(tableLineViewOption);   
				} else {
//					System.out.println(" 2. ConsoleView.buildTableLineView() tableViewCount " + tableViewCount + " , columnCount : " + tableLineViewOption.getColumnCount() );
					tableLineView = new TriangleTableLineView((TriangleTableLineView) tableLineViews.get(tableViewCount-1));
				}
				break;

			default:
				throw new IllegalArgumentException("Invalid DisplayType: " + tableLineViewOption.getTableLineType());
		}

		return tableLineView;
	}
}