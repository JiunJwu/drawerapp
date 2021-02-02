package com.example.drawer;

import android.net.wifi.aware.PublishConfig;

import java.io.Serializable;

public class User implements Serializable {
    private String userName,userEmail;
    public User(String userName, String userEmail){
        this.userEmail=userEmail;
        this.userName=userName;
    }
    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName=userName;}
    public String getUserEmail(){return userEmail;}
    public void setUserEmail(String userEmail){this.userEmail=userEmail;}
}
