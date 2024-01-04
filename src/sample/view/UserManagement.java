/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.view;

import sample.controllers.ProductList;
import sample.utils.Validation;

/**
 *
 * @author LuanTNKSE184059
 */
public class UserManagement {

    public static void main(String[] args) {
        Menu mainMenu = new Menu("welcome to user management");
        Menu subMenu = new Menu("update user ");
        addMainMenuItem(mainMenu);
        addSubMenuItem(subMenu);
        ProductList pl = new ProductList();
        pl.load("user.dat");
        boolean change = false;
        boolean confirm;
        int choice;
        do {
            choice = mainMenu.getUserChoice();
            switch (choice) {
                case 1:
                    pl.add();
                    change = true;
                    break;
                case 2:
                    pl.checkExistedUser();
                    change = true;
                    break;
                case 3:
                    pl.searchUser();
                    change = true;
                    break;
                case 4:
                    updateVehicle(subMenu, pl);
                    change = true;
                    break;
                case 5:
                    pl.save("user.dat");
                    change = true;
                    break;
                case 6:
                    pl.output();
                    change = true;
                    break;
                case 7:
                    if (change) {
                        System.out.println("CHANGES HAVE BEEN MADE TO THE DATA. DO YOU WANT TO SAVE AND EXIT?");
                        confirm = Validation.parseBoolean("TYPE 'Y' TO SAVE AND EXIT, OR 'N' TO EXIT WITHOUT SAVING: ", "YOUR CHOICE MUST BE 'Y' OR 'N' AND CANNOT BE LEAVE EMPTY!");
                        if (confirm) {
                            pl.save("user.dat");
                            System.out.println("YOUR CHANGES HAVE BEEN SAVED! SEE YOU NEXT TIME!");
                            break;
                        } else {
                            System.out.println("SEE YOU NEXT TIME!");
                        }
                    }
                    change = true;
                    break;
            }
        } while (choice != 7);
    }

    private static void updateVehicle(Menu subMenu, ProductList pl) {
        int userChoice;
        do {
            userChoice = subMenu.getUserChoice();
            switch (userChoice) {
                case 1:
                    pl.update();
                    break;
                case 2:
                    pl.remove();
                    break;
                case 3:
                    break;
            }
        } while (userChoice != 3);
    }

    public static void addMainMenuItem(Menu mainMenu) {
        mainMenu.addMenuItem("create user account");
        mainMenu.addMenuItem("check exists user");
        mainMenu.addMenuItem("search user information by name");
        mainMenu.addMenuItem("update user");
        mainMenu.addMenuItem("save account to file");
        mainMenu.addMenuItem("print list user from file");
        mainMenu.addMenuItem("quit");
    }

    public static void addSubMenuItem(Menu subMenu) {
        subMenu.addMenuItem("update user");
        subMenu.addMenuItem("delete user");
        subMenu.addMenuItem("back to the main menu");
    }

}
