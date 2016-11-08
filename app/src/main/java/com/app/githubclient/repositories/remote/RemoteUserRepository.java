package com.app.githubclient.repositories.remote;

import com.app.githubclient.api.GitHubModule;
import com.app.githubclient.api.payloads.FetchedUsersPayload;
import com.app.githubclient.repositories.types.UserRepository;
import com.app.githubclient.values.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteUserRepository implements UserRepository {

    @Override
    public void searchUsers(String text, final OnUsersListReceivedListener listener) {

        Call<FetchedUsersPayload> call = GitHubModule.gitHubService().searchUsers(text);
        call.enqueue(new Callback<FetchedUsersPayload>() {
            @Override
            public void onResponse(Call<FetchedUsersPayload> call, Response<FetchedUsersPayload> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body().toUsers();
                    if (!users.isEmpty()) {
                        listener.onUserReceived(users);
                    } else {
                        listener.onUsersNotFound();
                    }
                }
            }

            @Override
            public void onFailure(Call<FetchedUsersPayload> call, Throwable t) {
                listener.onRequestFail();
            }
        });
    }
}
