package com.example.pc.myapplication;

/**
 * Created by PC on 7/30/2018.
 */

public class RegisterData
{

    public String name;
    public String userName;
    public String password;
    public String amount;
    public String role;

    public RegisterData(String name, String userName, String password,String amount ,String role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.role = role;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;


    }

    public RegisterData()
    {

    }
}
