package io.namoosori.java.fileserver.client.ui;

import io.namoosori.java.fileserver.client.transfer.FileServiceStub;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ClientConsole {
    //
    public void start() {
        //
        init();
    }

    private void init() {
        ClientMenu.displayMainMenu();
        String menuNum = Console.scan("Select");
        navigate(menuNum);
    }

    private void navigate(String menuNum) {
        //
        switch (Integer.parseInt(menuNum)){
            case 1:
                upload();
                break;
            case 2:
                list();
                break;
            case 3:
                downloadAll();
                break;
            case 0:
                System.exit(0);
        }
    }

    private void upload() {
        //
        String fileName = Console.scan("File name (full name)");
        new FileServiceStub().upload(new File(fileName));
        init();
    }

    private void list() {
        //
        List<String> fileList = new FileServiceStub().listFiles();
        ClientMenu.displayListMenu(fileList);
        String menuNum = Console.scan("Select File No (0.MainMenu)");
        switch (Integer.parseInt(menuNum)){
            case 0:
                init();
                break;
            default:
                detail(fileList.get(Integer.parseInt(menuNum)-1));
        }
    }

    private void detail(String fileName) {
        //
        ClientMenu.displayDetailMenu(fileName);
        String menuNum = Console.scan("Select (0.List)");
        switch (Integer.parseInt(menuNum)){
            case 0:
                list();
                break;
            case 1:
                new FileServiceStub().delete(fileName);
                init();
                break;
            case 2:
                new FileServiceStub().download(fileName);
                init();
                break;
        }
    }

    private void downloadAll() {
        //
        String multiThreadMode = Console.scan("Multi Thread mode (Y|N)");
        new FileServiceStub().downloadAll(multiThreadMode.equals("Y"));
        init();
    }

    private static class Console {
        static Scanner scanner;

        static String scan(String label) {
            if(scanner == null) {
                scanner = new Scanner(System.in);
            }
            System.out.print(label + " : ");
            return scanner.nextLine();
        }
    }
}
