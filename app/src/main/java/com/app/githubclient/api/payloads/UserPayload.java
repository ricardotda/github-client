package com.app.githubclient.api.payloads;

import com.google.gson.annotations.SerializedName;

public class UserPayload {

    @SerializedName("login") String login;
    @SerializedName("id") long id;
    @SerializedName("avatar_url") String avatarUrl;
    @SerializedName("gravatar_id") String gravatarId;
    @SerializedName("url") String url;
    @SerializedName("html_url") String htmlUrl;
    @SerializedName("followers_url") String followersUrl;
    @SerializedName("following_url") String followingUrl;
    @SerializedName("gists_url") String gistsUrl;
    @SerializedName("starred_url") String starredUrl;
    @SerializedName("subscriptions_url") String subscriptionsUrl;
    @SerializedName("organizations_url") String organizationsUrl;
    @SerializedName("repos_url") String reposUrl;
    @SerializedName("events_url") String eventsUrl;
    @SerializedName("received_events_url") String receivedEventsUrl;
    @SerializedName("type") String type;
    @SerializedName("site_admin") boolean siteAdmin;
    @SerializedName("score") float score;

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public float getScore() {
        return score;
    }
}
