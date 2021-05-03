/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module01.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.timestable.module01.util.JsonSerializable;
import static java.util.stream.Collectors.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TimesTable implements JsonSerializable {
	//
	private int startLeftNumber;
	private int endLeftNumber;
	private TableOption tableOption;
	private Map<Integer, Table> tableMap;

	// 코드를 채우세요.
	
	public TimesTable() {
		this( Equation.DEFAULT_START_LEFT_NUMBER, 
				 Equation.END_LEFT_NUMBER, 
				 Format.InMath);
	}
	
	public TimesTable(int startLeftNumber, int endLeftNumber, Format format) {
		this.startLeftNumber = startLeftNumber;
		this.endLeftNumber = endLeftNumber;
		this.tableOption = new TableOption();
		
		this.tableOption.setTableFormat(format);   //
		
		this.tableMap = new LinkedHashMap<>();
		this.initialize();
	}
	
	public void initialize() {
		for(int leftNumber = startLeftNumber; leftNumber <= endLeftNumber; leftNumber++) {
			addUnitTable(new Table(leftNumber, tableOption.getTableFormat() ) );
		}
	}
	
	public void addUnitTable(Table table) {

		this.tableMap.put(table.getLeftNumber(), table);
	}
	 
	
	public LinkedList<Table> requestTables() {
		//
		if(!tableOption.getTableOrder().isAscending()) {
			this.startLeftNumber = 9;
			return requestTablesFrom(this.startLeftNumber);
		}
		if(!tableOption.getEquationOrder().isAscending()) {
			this.startLeftNumber = 9;
			return requestReverseTablesFrom(this.startLeftNumber);
		}
		
		return requestTables(this.startLeftNumber);
	}
	
	public LinkedList<Table> requestTables(int startLeftNumber) {
		//
		
		/* @formatter:off */
		LinkedList<Table> tableList = this.tableMap
															  .values()
															  .stream()
															  .collect ( toCollection(LinkedList::new)); 
		/* @formatter:on */
		return tableList;
		
	}
	
	public LinkedList<Table> requestReverseTablesFrom(int startLeftNumber) {
		
//		for(int leftNumber = startLeftNumber;  leftNumber >=1; leftNumber--) {
//			for(int rightNumber = 1; rightNumber <= 9; rightNumber++) {
//				System.out.println(leftNumber + " x = " + rightNumber + " = " + leftNumber*rightNumber);
//			}
//		}
		for(int leftNumber = startLeftNumber; leftNumber > 1; leftNumber --) {
			addUnitTable(new Table(leftNumber, tableOption.getTableFormat() ) );
		}
		
		/* @formatter:off */
		LinkedList<Table> tableList = this.tableMap
															  .values()
															  .stream()
															  .collect ( toCollection(LinkedList::new)); 
		/* @formatter:on */
		return tableList;
		
	}
	
	public LinkedList<Table> requestTablesFrom(int startLeftNumber) {
		
		for(int leftNumber = 9; leftNumber > 1; leftNumber --) {
			addUnitTable(new Table(leftNumber, tableOption.getTableFormat() ) );
		}
		
		/* @formatter:off */
		LinkedList<Table> tableList = this.tableMap
															  .values()
															  .stream()
															  .collect ( toCollection(LinkedList::new)); 
		/* @formatter:on */
		return tableList;
		
	}

	
 }