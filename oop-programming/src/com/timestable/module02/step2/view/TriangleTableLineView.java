/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package com.timestable.module02.step2.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static com.timestable.module01.domain.Equation.START_RIGHT_NUMBER;
import static com.timestable.module01.domain.Equation.END_RIGHT_NUMBER;
//import com.timestable.module01.util.StringUtil;
//import org.springframework.util.StringUtils;


import com.timestable.module01.domain.Table;
import com.timestable.module02.step1.view.AbstractTableLineView;
import com.timestable.module02.step1.view.TableLineViewOption;

public class TriangleTableLineView extends AbstractTableLineView {
	//
	private static final int DefaultTableLength = 16;

	private int startIndex;
	private List<Iterator<String>> equationIteratorList;

	public TriangleTableLineView(TriangleTableLineView parentView) {
		// 두번째 라인부터 호출 (2번째 인덱스 부터 2개 칼럼 생성) -> (1번째 인덱스부터 3개 칼럼 생성) -> (0번째 인덱스부터 4개 칼럼 생성) 
		this(parentView.getTableLineViewOption());
		this.startIndex = parentView.getStartIndex() -1;
		super.setColumnCount(parentView.getColumnCount() + 1);
		System.out.println(" 3. TriangleTableLineView constructor' startIndex : " + startIndex + " , columnCount : " + super.getColumnCount() );
	}

	public TriangleTableLineView(TableLineViewOption tableLineViewOption) {
		// 처음 1회 호출 ( 3번째 인덱스에 1개 칼럼 생성)
		super(tableLineViewOption);
		this.startIndex = 3;
		super.setColumnCount(1);
//		System.out.println(" 4. TriangleTableLineView options' startIndex : " + startIndex + " , columnCount :  " + super.getColumnCount() );
	}

	public int getStartIndex() {
		// 
		return startIndex;
	}

	@Override
	public void takeUnitTables(LinkedList<Table> sourceTables) {
		//
		// 코드를 작성하세요.
		List<Table> tables = new ArrayList<>();
		for (int i = 0; i < this.getColumnCount() ; i++) {
			tables.add(sourceTables.removeFirst());
			if(sourceTables.size() == 0) {
				break;
			}
		}
		
		this.equationIteratorList = new ArrayList<>();
		for(Table table : tables ) {
			Iterator<String> equationIterator = table.requestFormattedEquations().iterator();
			equationIteratorList.add(equationIterator);
		}
	}

	@Override
	public void showTableLine() {
		//
		// 코드를 작성하시오.
		for(int i = START_RIGHT_NUMBER ; i <= END_RIGHT_NUMBER ; i++) {
//		for(int i = 1 ; i <= 9 ; i++) {
			System.out.println(this.buildLine());
		}
		
		System.out.println("...");
		
	}
	
	private String buildLine() {
		StringBuilder builder = new StringBuilder();
		
		for(int index=0; index < 7; index++) {		// triangle을 만들기 위해 7개의 index가 필요
			if( index < this.startIndex ) {		// 1행은 3번째에 출력되기 때문에 startIndex=3이므로 0~2까지는 space로 채워짐 
//				builder.append(rightPad( "*", DefaultTableLength));   //16
				builder.append(padSpace( "-", DefaultTableLength)); 
				continue;
			}

//			builder.append(rightPad(buildEquationsLine(), DefaultTableLength));
			builder.append(padSpace(buildEquationsLine(), DefaultTableLength));
			break;
		}
		return builder.toString();
	}

	private String buildEquationsLine() {
		
		StringBuilder builder = new StringBuilder();
		
		for( Iterator<String> equationIterator : equationIteratorList ) {
//			builder.append(rightPad( equationIterator.next() , DefaultTableLength));
//			builder.append(rightPad("+, DefaultTableLength));
			builder.append(padSpace( equationIterator.next() , DefaultTableLength));
			builder.append(padSpace("+", DefaultTableLength));		// 단 테이블을 출력한 후 오른쪽은 공백으로 
		}
		return builder.toString();
	}

	private String padSpace(String str, int size) {
//		return String.format("%" + size + "s",  str);
		return String.format("%16s",  str);
	}

}