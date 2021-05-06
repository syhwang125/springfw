package com.timestable.module03.step2;

public class MenuConsole {
    //
    private MenuBar menuBar;

    public MenuConsole() {
        //
        this.menuBar = buildMenuBar();
    }

    public void showMenuBar() {
        //
        menuBar.show();
    }

    private MenuBar buildMenuBar() {
        //
        MenuBar menuBar = new MenuBar("Timestable menu");
        
        // 코드를 채우세요.
        menuBar.addMenu(ColumnTableMenu.buildMenu());
        menuBar.addMenu(SquareTableMenu.buildMenu());
        menuBar.addMenu(TriangleTableMenu.buildMenu());
        menuBar.addMenu(ExitMenu.buildMenu());
        return menuBar;
    }

    public static void main(String[] args) {
        //
        MenuConsole menuConsole = new MenuConsole();
        menuConsole.showMenuBar();
    }
}