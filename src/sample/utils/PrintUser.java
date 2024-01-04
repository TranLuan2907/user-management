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
public class PrintUser {
  public static void printUserCell(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text.toUpperCase());
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

    public static void printUserBoxed(String text, int width) {
        printABar(119);
        System.out.println();
        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text.toUpperCase());
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print("|");
        System.out.println();
    }

    public static void printUserHeader() {
        printUserBar();
        printUserCell("USERNAME", 25);
        printUserCell("FIRST NAME", 25);
        printUserCell("LAST NAME", 25);
        printUserCell("PASSWORD", 25);
        printUserCell("PHONE", 10);
        printUserCell("EMAIL", 40);
        printSpaceAndDash();
        printUserBar();
    }

    public static void printUserBar() {
        System.out.println("+------------------------+------------------------+-------------------------+------------------------+---------+-------------------------------------------+");
    }

    public static void printABar(int length) {
        String dashes = new String(new char[length]).replace("\0", "-");
        System.out.print(dashes);
    }

    public static void printSpaceAndDash() {
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }
}
