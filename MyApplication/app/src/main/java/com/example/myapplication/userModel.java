package com.example.myapplication;

public class userModel {
int userPostProfileImg, userPostImg;
String userName, userProfileBio, userPostText ;

    public userModel(int userPostProfileImg, int userPostImg, String userName, String userProfileBio, String userPostText) {
        this.userPostProfileImg = userPostProfileImg;
        this.userPostImg = userPostImg;
        this.userName = userName;
        this.userProfileBio = userProfileBio;
        this.userPostText = userPostText;
    }
}
