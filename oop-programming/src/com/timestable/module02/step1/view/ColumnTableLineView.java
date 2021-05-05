/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module02.step1.view;

import com.timestable.module01.domain.Table;
import com.timestable.module01.domain.Equation;

import static com.timestable.module01.domain.Equation.START_RIGHT_NUMBER;
import static com.timestable.module01.domain.Equation.END_RIGHT_NUMBER;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ColumnTableLineView extends AbstractTableLineView {
	//
	private static final int DefaultTableLength = 16;

	private List<Table> tables;
//	private TableLineViewOption tableLineViewOption ;

	public ColumnTableLineView(TableLineViewOption tableLineViewOption) {
		//
		super(tableLineViewOption);
//		this.tableLineViewOption = tableLineViewOption;
		this.tables = new ArrayList<>();
	}

	@Override
	public void takeUnitTables(LinkedList<Table> sourceTables) {
		for (int i =0; i < getColumnCount(); i++) {
			this.tables.add(sourceTables.removeFirst());
			if(sourceTables.size() == 0 ) {
				break;
			}
		}
	}

	@Override
	public void showTableLine() {
		for ( int rightNumber = START_RIGHT_NUMBER; rightNumber <= END_RIGHT_NUMBER; rightNumber++ ) {
			
			StringBuilder builder = new StringBuilder();
			for(Table table : tables) {
				builder.append(table.requestFormattedEquation(rightNumber));
				builder.append(" ");
			}
	 
			System.out.println(builder.toString());
		}
		System.out.println("  ");
	}

	// 코드를 작성하세요.
}