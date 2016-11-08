package com.app.githubclient.repositories.remote;

import com.app.githubclient.api.GitHubModule;
import com.app.githubclient.api.payloads.FetchedReposPayload;
import com.app.githubclient.repositories.types.RepoRepository;
import com.app.githubclient.values.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepoRepository implements RepoRepository {

    @Override
    public void searchRepos(String text, final OnReposListReceivedListener listener) {

        Call<FetchedReposPayload> call = GitHubModule.gitHubService().searchRepos(text);
        call.enqueue(new Callback<FetchedReposPayload>() {
            @Override
            public void onResponse(Call<FetchedReposPayload> call, Response<FetchedReposPayload> response) {
                if (response.isSuccessful()) {
                    List<Repo> users = response.body().toRepos();
                    if (!users.isEmpty()) {
                        listener.onReposReceived(users);
                    } else {
                        listener.onReposNotFound();
                    }
                }
            }

            @Override
            public void onFailure(Call<FetchedReposPayload> call, Throwable t) {
                listener.onRequestFail();
            }
        });


    }
}
