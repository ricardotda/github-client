package com.app.githubclient.values;

public class User {

    private String userName;
    private String profilePhotoPath;

    public User(String userName, String profilePhotoPath) {
        this.userName = userName;
        this.profilePhotoPath = profilePhotoPath;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }
}
