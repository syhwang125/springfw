/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module01.domain;

import com.timestable.module01.util.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static java.util.Collections.reverse;
import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;


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
		/* @formatter:off */
		LinkedList<Table> tableList = this.tableMap
															  .values()
															  .stream()
//															  .forEach( table -> { table.setFormat(tableOption.getTableFormat());
//															  								table.setEquationOrder(tableOption.getEquationOrder());
//															                              } )
															  .collect(toCollection(LinkedList::new));

		tableList.forEach(table -> {
			table.setFormat(tableOption.getTableFormat());
			table.setEquationOrder(tableOption.getEquationOrder());
		});
		/* @formatter:on */
		if (!tableOption.getTableOrder().isAscending()) {
			reverse(tableList);
		}
		return tableList;
	}
	
	
	// 이건 언제 호출되지?    $module02/ConsoleView.show()에서 호출됨 - square, linetype으로 구분되어 시작값이 달라짐 
	public List<Table> requestTables(int startLeftNumber) {
		//
		
		return this.requestTables()
				   .stream()
				   .filter( table -> table.getLeftNumber() >= startLeftNumber )
				   .collect( toList() ) ;
		
	}

//	public LinkedList<Table> requestReverseTablesFrom(int startLeftNumber) {
//		/* @formatter:off */
//		LinkedList<Table> tableList = this.tableMap
//										  .values()
//										  .stream()
////										  .sorted(reverseOrder(Map.Entry.comparingByValue()) )
//										  .collect ( toCollection(LinkedList::new));
//		/* @formatter:on */
//		return tableList;
//		
//	}
//
//	public LinkedList<Table> requestTablesFrom(int startLeftNumber) {
//
//		/* @formatter:off */
//		LinkedList<Table> tableList = this.tableMap
//										  .values()
//						    			  .stream()
//										  .collect( toCollection(LinkedList::new) );
//		/* @formatter:on */
//		return tableList;
//		
//	}

	
 }