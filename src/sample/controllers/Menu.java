package sample.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
import java.util.ArrayList;
import java.util.Scanner;
import sample.dto.I_Menu;
import sample.utils.MenuUtils;
import sample.utils.Validation;

public class Menu extends ArrayList<String> implements I_Menu {

    private static final Scanner in = new Scanner(System.in);

    private String title;

    public Menu(String title) {
        this.title = title;
    }

    // must implement all abstract method of I_Menu interface
    @Override
    public void addItem(String s) {
        this.add(s.toUpperCase());
    }

    @Override
    public void showMenu() {
        if (this.isEmpty()) {
            System.out.println("NOTHING TO PRINT OUT");
        }
        MenuUtils.printBoxed(title, 50);
        for (int i = 0; i < this.size(); i++) {
            System.out.print("| " + (i + 1) + " | " + get(i));
            MenuUtils.printSpace(get(i));
        }
        MenuUtils.printBarAndSpace();
    }

    @Override
    public boolean confirmYesNo(String welcome, String errorMessage) {
        while (true) {
            System.out.print(welcome.toUpperCase() + "? ");
            String input = in.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                return (input.equals("Y"));
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    @Override
    public int getChoice() {
        showMenu();
        int maxOptions = this.size();
        String inputMessage = "CHOOSE FROM [1..." + maxOptions + "]";
        String errorMessage = "YOU ARE REQUIRED TO CHOOSE FROM [1..." + (maxOptions) + "]";
        return Validation.getAnInteger(1, maxOptions, inputMessage, errorMessage);
    }

}
