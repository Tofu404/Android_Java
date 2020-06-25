package com.example.shensuo;

import android.widget.ImageView;

public class CountMessage {
    int imageID;
    String userName;
    String userCount;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountMessage(int id,int imageID, String userName, String userCount){
        this.imageID = imageID;
        this.userName = userName;
        this.userCount = userCount;
        this.id = id;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }
}
