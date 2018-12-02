package com.saber.challengeapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is an implementention of GitHubService Interface
 */
public class GitHubServiceBuilder {
    public static final String BASE_URL = "https://api.github.com";
    private Retrofit retrofit;

    // Initialize Retrofit
    public GitHubServiceBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Gets the service (the Retrofit interface implementation)
    public GitHubService getService() {
        return retrofit.create(GitHubService.class);
    }
}
