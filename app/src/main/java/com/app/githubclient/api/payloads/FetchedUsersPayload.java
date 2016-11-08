package com.app.githubclient.api.payloads;

import com.app.githubclient.values.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FetchedUsersPayload {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<UserPayload> userPayloadList;

    public List<User> toUsers() {
        List<User> userList = new ArrayList<>();
        for (UserPayload userPayload : userPayloadList) {
            userList.add(new User(
                    userPayload.getLogin(),
                    userPayload.getAvatarUrl()));
        }
        return userList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<UserPayload> getUserPayloadList() {
        return userPayloadList;
    }
}
