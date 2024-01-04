package sample.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sample.dto.I_List;
import sample.dto.User;
import sample.utils.HashCode;
import sample.utils.MenuUtils;
import sample.utils.PrintUser;
import sample.utils.Validation;

public class ProductList extends ArrayList<User> implements I_List {

    private static final Scanner in = new Scanner(System.in);

    @Override
    public void add() {
        String username, firstname, lastname, password, confirm, phone, email;
        User u;
        boolean isContinue;
        do {
            System.out.println("ADD USER #" + (this.size() + 1));
            do {
                username = Validation.getName("input a user's username", "username must be at least five character, no spaces and must not be leave empty");
                u = findUserByName(username);
                if (u != null) {
                    System.err.println("DUPLICATED USERNAME! PLEASE TRY AGAIN!");
                }
            } while (u != null);
            firstname = Validation.getString("input a user's first name", "user's first name must not be leave empty");
            lastname = Validation.getString("input a user's last name", "user's last name must not be leave empty");
            password = Validation.getPassWord("input a user's password", "user's password must not be at least 6 characters, no spaces and must not be leave empty!");
            String hashedPassword = HashCode.hashPassword(password);
            do {
                confirm = Validation.getPassWord("input your password again", "user's password must not be at least 6 characters, no spaces and must not be leave empty!");
                if (!password.equals(confirm)) {
                    System.err.println("PASSWORDS DO NOT MATCH! PLEASE TRY AGAIN!");
                }
            } while (!password.equals(confirm));
            phone = Validation.readStr("input user's phone", "userphone must be 10 numbers and must not be leave empty", "^[0-9]{10}$");
            email = Validation.readEmail("input a user's gmail (...@fpt.edu.vn)", "gmail must be valid and must not be leave empty", "^[a-zA-Z0-9._%+-]+@fpt\\.edu\\.vn$");
            u = new User(username, firstname, lastname, password, phone, email);
            this.add(u);
            System.out.println("ADD USER #" + this.size() + " SUCCESSFULLY!");
            MenuUtils.printPaddingMessage("add users #" + this.size() + " successfully!", 128);
            u.showProfile();
            isContinue = Validation.parseBoolean("do you want to add more (y/n)", "an answer must be either y or n");
        } while (isContinue);

    }

    @Override
    public void remove() {
        String username, password;

        if (this.isEmpty()) {
            System.err.println("NOTHING TO REMOVE!");
            return;
        }

        username = Validation.getName("input a user's username", "username must be at least five character and must not be leave empty");
        password = Validation.getPassWord("input a user's password", "user's password must not be at least 6 characters, no spaces and must not be leave empty!");
        User authenticatedUser = login(username, password);
        if (authenticatedUser == null) {
            System.err.println("USERNAME OR PASSWORD IS WRONG! CANNOT REMOVE ACCOUNT!");
            return;
        }
        MenuUtils.printPaddingMessage("here is the user's account before removing!", 128);
        authenticatedUser.showProfile();
        boolean confirm = Validation.parseBoolean("are you sure that you want to delete this user's account y or n", "an answer must be either y or n");
        if (confirm) {
            this.remove(authenticatedUser);
            System.out.println("USER REMOVED SUCCESSFULLY!");
        } else {
            System.err.println("REMOVED FAILED!");
        }
    }

    @Override
    public void update() {
        String username, password;

        if (this.isEmpty()) {
            System.err.println("NOTHING TO REMOVE!");
            return;
        }

        username = Validation.getName("input a user's username to update", "username must be at least five character and must not be leave empty");
        password = Validation.getPassWord("input a user's password to update", "user's password must not be at least 6 characters, no spaces and must not be leave empty!");
        User authenticatedUser = login(username, password);
        if (authenticatedUser == null) {
            System.err.println("USERNAME OR PASSWORD IS WRONG! CANNOT REMOVE ACCOUNT!");
            return;
        }
        
        System.out.println("FOUND ACCOUNT!");
        authenticatedUser.showProfile();
        
        System.out.print("INPUT A NEW FIRST NAME OR YOU CAN LEAVE IT EMPTY: ");
        String newFirstName = in.nextLine().trim().toUpperCase();
        if (!newFirstName.isEmpty()) {
            authenticatedUser.setFirstname(newFirstName);
            System.out.println("FIRST NAME UPDATED SUCCESSFULLY!");
        } else {
            System.out.println("NO CHANGE IN FIRST NAME");
        }

        System.out.print("INPUT A NEW LAST NAME OR YOU CAN LEAVE IT EMPTY: ");
        String newLastName = in.nextLine().trim().toUpperCase();
        if (!newLastName.isEmpty()) {
            authenticatedUser.setLastname(newLastName);
            System.out.println("LAST NAME UPDATED SUCCESSFULLY!");
        } else {
            System.out.println("NO CHANGE IN LAST NAME");
        }

        System.out.print("INPUT A NEW PASSWORD OR YOU CAN LEAVE IT EMPTY: ");
        String newPassWord = in.nextLine().trim();
        if (!newPassWord.isEmpty()) {
            while (newPassWord.length() < 6 || newPassWord.contains(" ")) {
                System.err.println("PASSWORD MUST BE AT LEAST 6 CHARACTERS AND CONTAIN NO SPACES! PLEASE INPUT AGAIN!");
                newPassWord = in.nextLine().trim();
            }
            authenticatedUser.setPassword(newPassWord);
            System.out.println("PASSWORD UPDATED SUCCESSFULLY!");
        } else {
            System.out.println("NO CHANGE IN PASSWORD");
        }

        System.out.print("INPUT A NEW PHONE OR YOU CAN LEAVE IT EMPTY: ");
        String newPhone = in.nextLine().trim();
        if (!newPhone.isEmpty()) {
            while (!newPhone.matches("\\d{10}")) {
                System.err.println("PHONE MUST BE 10 NUMBERS AND CONTAINS NO SPACES! PLEASE INPUT AGAIN!");
                newPhone = in.nextLine().trim();
            }
            authenticatedUser.setPhonenumber(newPhone);
            System.out.println("PHONE UPDATED SUCCESSFULLY!");
        } else {
            System.out.println("NO CHANGE IN PHONE");
        }

        System.out.print("INPUT A NEW EMAIL OR YOU CAN LEAVE IT EMPTY: ");
        String newEmail = in.nextLine().trim().toUpperCase();
        if (!newEmail.isEmpty()) {
            while (!newEmail.matches(".*@fpt.edu.vn$")) {
                System.err.println("EMAIL MUST BE IN VALID FORMAT (...@fpt.edu.vn) ! PLEASE INPUT AGAIN!");
                newEmail = in.nextLine().trim();
            }
            authenticatedUser.setEmail(newEmail);
            System.out.println("EMAIL UPDATED SUCCESSFULLY!");
        } else {
            System.out.println("NO CHANGE IN EMAIL");
        }

        MenuUtils.printPaddingMessage("updating successfully! here is the new user information  after updating", 118);
        authenticatedUser.showProfile();
    }

    @Override
    public void output() {
        if (this.isEmpty()) {
            System.err.println("NOTHING TO PRINT OUT!");
            return;
        }
        MenuUtils.printPaddingMessage("here is all the user's account!", 128);
        PrintUser.printUserHeader();
        this.stream()
                .map(User::toString)
                .forEach(vehilce -> {
                    System.out.println(vehilce);
                    PrintUser.printUserBar();
                });
    }

    @Override
    public void checkExistedUser() {
        String username = Validation.getName("input a user's username", "username must be at least five character and must not be leave empty");
        User u = findUserByName(username);

        if (u != null) {
            MenuUtils.printPaddingMessage("existed user! here is your user according to user name " + username, 118);
            u.showProfile();
        } else {
            System.err.println("NO USERS FOUND!");
        }
    }

    @Override
    public void searchUser() {
        String name = Validation.getString("input a user's name", "user's name must not be empty!");
        List<User> username = this.stream()
                .filter(u -> u.getFirstname().contains(name) || u.getLastname().contains(name))
                .collect(Collectors.toList());

        if (username.isEmpty()) {
            System.err.println("HAVE NO ANY USER!");
        } else {
            MenuUtils.printPaddingMessage("here is all users contais the name " + name + "!", 128);
            PrintUser.printUserHeader();
            username.forEach(v -> {
                System.out.println(v);
                PrintUser.printUserBar();
            });
        }
    }

    @Override
    public void save(String fName) {
        if (this.isEmpty()) {
            System.err.println("EMPTY LIST!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (User u : this) {
                fo.writeObject(u);
            }
            fo.close();
            f.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void load(String fName) {
        if (this.size() > 0) {
            this.clear();
        }
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            User u;
            while ((u = (User) (fo.readObject())) != null) {
                this.add(u);
            }
            fo.close();
            fi.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public User findUserByName(String userName) {
        return this.stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User login(String username, String password) {
        return this.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
