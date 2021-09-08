package com.example.shoponline.models;

public class UserModel {
    private String name, email, passord;

    public UserModel() {
    }

    public UserModel(String name, String email, String passord) {
        this.name = name;
        this.email = email;
        this.passord = passord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
