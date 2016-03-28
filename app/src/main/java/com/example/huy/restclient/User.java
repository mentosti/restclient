package com.example.huy.restclient;

/**
 * Created by huy on 3/28/2016.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int status;
    private String name;

    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public int getStatus(){
        return status;
    }

    public String getName(){
        return name;
    }
}
