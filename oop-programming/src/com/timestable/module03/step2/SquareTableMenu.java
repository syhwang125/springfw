package com.timestable.module03.step2;

import com.timestable.module01.domain.TableOption;
import com.timestable.module01.domain.TimesTable;
import com.timestable.module02.step1.view.ConsoleView;
import com.timestable.module02.step1.view.TableLineType;
import com.timestable.module02.step1.view.TableLineViewOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SquareTableMenu extends AbstractMenu {
    //
    public SquareTableMenu(int sequence, String title) {
        //
        super(sequence, title);
    }

    public SquareTableMenu(int sequence, String title, String exitMessage) {
        //
        super(sequence, title, exitMessage);
    }

    @Override
    public ConsoleView buildConsoleView() {
        //
        TableLineViewOption tableLineViewOption = new TableLineViewOption(TableLineType.Square);
        super.assignTableViewOptionValue(tableLineViewOption);
       ConsoleView consoleView = new ConsoleView(tableLineViewOption);
        return consoleView;
    }

    @Override
    public TimesTable buildTimesTable() {
        //
        // 주석을 풀고 동작하도록 코딩하세요.
        //
        TableOption tableOption = new TableOption();
        super.assignTableOptionValue(tableOption);
        TimesTable timesTable = new TimesTable(1, 9);
        timesTable.setTableOption(tableOption);

        return timesTable;
        
    }

    public static SquareTableMenu buildMenu() {
        //
        SquareTableMenu squareTableMenu = new SquareTableMenu(2, "Square table ");

        // 코드를 채우세요.

        squareTableMenu.add(OptionItem.newInstance(OptionType.TableOrder));
        squareTableMenu.add(OptionItem.newInstance(OptionType.TableFormat));
//        squareTableMenu.add(OptionItem.newInstance(OptionType.EquationOrder));
        
        return squareTableMenu;
    }
}