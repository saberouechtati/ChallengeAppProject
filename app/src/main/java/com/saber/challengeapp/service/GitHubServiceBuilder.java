package com.saber.challengeapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubServiceBuilder {
    public static final String BASE_URL = "https://api.github.com";
    private Retrofit retrofit;

    public GitHubServiceBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public GitHubService getService() {
        return retrofit.create(GitHubService.class);
    }
}
