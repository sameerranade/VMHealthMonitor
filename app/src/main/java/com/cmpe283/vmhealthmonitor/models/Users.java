package com.cmpe283.vmhealthmonitor.models;

/**
 * Created by Varun on 5/1/2015.
 */
public class Users {
    private String userName;
    private String password;
    private String Role;

    public Users(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        Role = role;
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
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
