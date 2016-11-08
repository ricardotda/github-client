package com.app.githubclient.api;

import com.app.githubclient.api.payloads.FetchedReposPayload;
import com.app.githubclient.api.payloads.FetchedUsersPayload;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/search/users")
    Call<FetchedUsersPayload> searchUsers(
            @Query("q") String queryWord
    );

    @GET("/search/repositories")
    Call<FetchedReposPayload> searchRepos(
            @Query("q") String queryWord
    );

}
