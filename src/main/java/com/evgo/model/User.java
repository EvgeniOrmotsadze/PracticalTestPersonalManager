package com.evgo.model;

/**
 * Created by root_pc on 11/13/2016.
 */
public class User {

    private int id;
    private String email;
    private String username;
    private String password;

    public User(int id, String username,String email, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
