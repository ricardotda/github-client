package com.app.githubclient.api;

import com.app.githubclient.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubModule {

    public Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public GitHubApi providesGitHub(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

    public static GitHubApi gitHubService() {
        GitHubModule module = new GitHubModule();
        Retrofit adapter = module.providesRetrofit();
        return module.providesGitHub(adapter);
    }
}
