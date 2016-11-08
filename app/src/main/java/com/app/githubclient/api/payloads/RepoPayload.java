package com.app.githubclient.api.payloads;

import com.app.githubclient.values.User;
import com.google.gson.annotations.SerializedName;

public class RepoPayload {

    @SerializedName("id") long id;
    @SerializedName("name") String login;
    @SerializedName("full_name") String fullName;
    @SerializedName("owner") UserPayload userPayload;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public UserPayload getUserPayload() {
        return userPayload;
    }

    public User toUser() {
        return new User(userPayload.getLogin(), userPayload.getAvatarUrl());
    }
}
