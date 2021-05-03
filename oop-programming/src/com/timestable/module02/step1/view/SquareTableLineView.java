/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module02.step1.view;

import com.timestable.module01.domain.Table;
import com.timestable.module01.domain.Table;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Target;
import java.util.LinkedList;

public class SquareTableLineView extends AbstractTableLineView {
	//
	private Table table;

	public SquareTableLineView(TableLineViewOption tableLineViewOption) {
		//
		super(tableLineViewOption);
	}

	@Override
	public void takeUnitTables(LinkedList<Table> sourceTables) {
		//
		table = sourceTables.removeFirst();
	}

	@Override
	public void showTableLine() {
		//
		StringBuilder builder = new StringBuilder();
		for(String formattedEquation : table.requestFormattedEquations()) {
			builder.append(formattedEquation);
			builder.append(" ");
		}
 
		System.out.println(builder.toString());
	}
}