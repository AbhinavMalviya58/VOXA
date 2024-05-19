package com.example.myapplication.Models;

public class Users {
    String profilePic , userMame , mail , password , userId , lastMessage;

    public Users(String profilePic, String userMame, String mail, String password, String userId, String lastMessage) {
        this.profilePic = profilePic;
        this.userMame = userMame;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    public Users(){

    }

    // SignUp

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserMame() {
        return userMame;
    }

    public void setUserMame(String userMame) {
        this.userMame = userMame;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
