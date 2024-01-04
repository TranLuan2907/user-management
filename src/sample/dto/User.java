package sample.dto;

import java.io.Serializable;
import sample.utils.PrintUser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
public class User implements Serializable {

    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String confirm;
    private String phonenumber;
    private String email;

    public User(String username, String firstname, String lastname, String password,String phonenumber, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public boolean isValidPassowrd() {
        return password.equals(confirm);
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String formmattedString = String.format("|        %-16s|         %-15s|         %-16s|      %-18s|%-9s|         %-34s|", username, firstname, lastname, password, phonenumber, email);
        return formmattedString;

    }

    public void showProfile() {
        PrintUser.printUserHeader();
        System.out.println(toString());
        PrintUser.printUserBar();
    }
}
