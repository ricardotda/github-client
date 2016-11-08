package com.app.githubclient.api.payloads;

import com.app.githubclient.values.Repo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FetchedReposPayload {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<RepoPayload> repoPayloadList;

    public List<Repo> toRepos() {
        List<Repo> repoList = new ArrayList<>();
        for (RepoPayload repoPayload : repoPayloadList) {
            repoList.add(new Repo(
                    repoPayload.getFullName(),
                    repoPayload.toUser())
            );
        }
        return repoList;
    }
}
