package com.app.githubclient.values;

public class Repo {

    private String name;
    private User user;

    public Repo(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
