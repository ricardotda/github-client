package com.app.githubclient.repositories.types;

import com.app.githubclient.values.Repo;

import java.util.List;

public interface RepoRepository {

    interface OnReposListReceivedListener {
        void onReposReceived(List<Repo> repos);
        void onReposNotFound();
        void onRequestFail();
    }

    void searchRepos(String text, OnReposListReceivedListener listener);
}
