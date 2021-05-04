/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module01.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import static java.util.stream.Collectors.*;
import java.util.stream.Collectors;

import com.timestable.module01.util.JsonSerializable;
import com.timestable.module01.util.JsonUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Table implements JsonSerializable {
	// 단
	private int leftNumber;
	private Format format;
	private SortOrder equationOrder;
	private Map<Integer, Equation> equationMap;   //  n단별로 우항값을 요청하기 위해 



	// 코드를 작성하세요.
	public Table(int leftNumber) {
		this(leftNumber, Format.InMath);
	}
	
	public Table(int leftNumber, Format format) {
		this.leftNumber = leftNumber;
		this.format = format;
		this.equationOrder = SortOrder.Ascending;
		this.equationMap = new LinkedHashMap<>();
		this.initialize();
	}
	
	
	public String toString() {
		return toJson();
	}
	
	public Table fromJson(String json) {
		return JsonUtil.fromJson(json, Table.class);
	}
	
	public void initialize() {

		for(int rightNumber = Equation.START_RIGHT_NUMBER; rightNumber <=Equation. END_RIGHT_NUMBER; rightNumber++) {
			equationMap.put(rightNumber, new Equation(leftNumber, rightNumber));
		}
	}
	
	public Equation requestEquation(int rightNumber) {
		return equationMap.get(rightNumber);
	}
	
	public List<Equation> requestEquations() {
		/* @formatter:off */
		List<Equation> equationList = equationMap.values()
												 .stream()
												 .collect( Collectors.toList() );
//		List<Equation> equationList = equationMap.values().stream().collect( toCollection(ArrayList::new));
		/* @formatter:on */
		if ( !equationOrder.isAscending() ) {
			Collections.reverse(equationList);
		}
		return equationList;
	}
	
	public String requestFormattedEquation(int rightNumber) {
		Equation equation = equationMap.get(rightNumber);
		return equation.toFormatString(format); 
	}
	
	public List<String> requestFormattedEquations() {
		List<String> formattedEquations = new ArrayList<>();
		for ( Equation equation : requestEquations() ) {
			formattedEquations.add(equation.toFormatString(format));
		}
		return formattedEquations;
	}
	
	
}