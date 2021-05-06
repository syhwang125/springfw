package com.timestable.module03.step2;

import com.timestable.module01.domain.TimesTable;
import com.timestable.module02.step1.view.ConsoleView;

public class ExitMenu extends AbstractMenu {
    //
    public ExitMenu(int sequence, String title) {
        //
        super(sequence, title);
    }

    public ExitMenu(int sequence, String title, String exitMessage) {
        //
        super(sequence, title, exitMessage);
    }

    @Override
    public ConsoleView buildConsoleView() {
        return null;
    }

    @Override
    public TimesTable buildTimesTable() {
        return null;
    }

    public static ExitMenu buildMenu() {
        //
        return new ExitMenu(0, "Exit", "Bye...");
    }
}