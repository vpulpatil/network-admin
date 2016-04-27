package com.dto;

/**
 * Created by VIPUL on 17-Apr-16.
 */
public class User {
    String userName, password;

    /*User(String userName, String passWord){
        userName= this.userName;
        passWord=this.passWord;
    }*/

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
}
