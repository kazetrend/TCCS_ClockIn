package com.example.jirehcordova.tccs_clockin.model;

import java.io.Serializable;

/**
 * Created by Jireh Cordova on 26/02/2017.
 */

public class User implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private boolean hasFirstLogin;
    private String hashedPin;

    public User(int id, String firstname, String lastname, boolean hasFirstLogin, String hashedPin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hasFirstLogin = hasFirstLogin;
        this.hashedPin = hashedPin;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isHasFirstLogin() {
        return hasFirstLogin;
    }

    public void setHasFirstLogin(boolean hasFirstLogin) {
        this.hasFirstLogin = hasFirstLogin;
    }

    public String getHashedPin() {
        return hashedPin;
    }

    public void setHashedPin(String hashedPin) {
        this.hashedPin = hashedPin;
    }
}
