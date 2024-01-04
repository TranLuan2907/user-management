/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.view;

import java.util.ArrayList;
import sample.utils.MenuUtils;
import sample.utils.Validation;

/**
 *
 * @author 
 */
public class Menu extends ArrayList<String>{
    private String title;

    public Menu(String title) {
        this.title = title;
    }
    
    public void addMenuItem(String options) {
        this.add(options.toUpperCase());
    }
    
    public void printMenu() {
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

    public int getUserChoice() {
        printMenu();
        int maxOptions = this.size();
        String inputMessage = "CHOOSE FROM [1..." + maxOptions + "]";
        String errorMessage = "YOU ARE REQUIRED TO CHOOSE FROM [1..." + (maxOptions) + "]";
        return Validation.getAnInteger(1, maxOptions, inputMessage, errorMessage);
    }
}
