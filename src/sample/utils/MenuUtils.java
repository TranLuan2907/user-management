/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.utils;

/**
 *
 * @author 
 */
public class MenuUtils {
     public static void printDashedLine(int length) {
        String dashes = new String(new char[length]).replace("\0", "-");
        System.out.print(dashes);
    }

    public static void printSpace(String options) {
        int padding = 45 - options.length();
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print("|");
        System.out.println();
    }

    public static void printBoxed(String text, int width) {

        printBarAndSpace();
        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text.toUpperCase());
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        printBarAndSpace();
    }

    public static void printBar() {
        System.out.print("+");
        printDashedLine(50);
        System.out.print("+");
    }

    public static void printBarAndSpace() {
        printBar();
        System.out.println();
    }

    public static void printCell(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print("|");
    }

    public static void printPaddingMessage(String text, int width) {
        int padding = (width - text.length()) / 2;
        String formattedMessage = String.format("%" + padding + "s%s%" + padding + "s", "", text, "");
        System.out.println(formattedMessage.toUpperCase());
    }
}
