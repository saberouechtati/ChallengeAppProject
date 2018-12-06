package com.saber.challengeapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is an implementention of GitHubService Interface
 */
public class GitHubServiceBuilder {

    private Retrofit retrofit;

    // Initialize Retrofit
    public GitHubServiceBuilder(String basicUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(basicUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Gets the service (the Retrofit interface implementation)
    public GitHubService getService() {
        return retrofit.create(GitHubService.class);
    }
}
