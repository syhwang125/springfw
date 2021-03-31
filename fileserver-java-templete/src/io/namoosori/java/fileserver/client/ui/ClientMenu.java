package io.namoosori.java.fileserver.client.ui;

import java.util.List;

public class ClientMenu {
    public static void displayListMenu(List<String> files) {
        StringBuilder sb = new StringBuilder();
        sb.append("..............................\n");
        sb.append(" File List \n");
        sb.append("..............................\n");
        for(int idx=0; idx< files.size(); idx++) {
            sb.append(String.format(" %d. %s\n", idx+1, files.get(idx)));
        }
        sb.append("..............................\n");
        sb.append(" 0. MainMenu\n");
        sb.append("..............................\n");
        System.out.println(sb.toString());
    }

    public static void displayMainMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("..............................\n");
        sb.append(" Main \n");
        sb.append("..............................\n");
        sb.append(" 1. Upload files\n");
        sb.append(" 2. List files\n");
        sb.append(" 3. Download all files\n");
        sb.append("..............................\n");
        sb.append(" 0. Exit\n");
        sb.append("..............................\n");
        System.out.println(sb.toString());
    }

    public static void displayDetailMenu(String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("..............................\n");
        sb.append(" Detail - " + fileName + "\n");
        sb.append("..............................\n");
        sb.append(" 1. Delete file\n");
        sb.append(" 2. Download file\n");
        sb.append("..............................\n");
        sb.append(" 0. List\n");
        sb.append("..............................\n");
        System.out.println(sb.toString());
    }
}
