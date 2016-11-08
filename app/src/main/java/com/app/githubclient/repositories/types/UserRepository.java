package com.app.githubclient.repositories.types;

import com.app.githubclient.values.User;

import java.util.List;

public interface UserRepository {

    interface OnUsersListReceivedListener {
        void onUserReceived(List<User> users);
        void onUsersNotFound();
        void onRequestFail();
    }

    void searchUsers(String text, OnUsersListReceivedListener listener);
}
