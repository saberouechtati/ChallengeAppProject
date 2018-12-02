package com.saber.challengeapp.service;

import com.saber.challengeapp.data.GitHubUserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This is a RestApi call interface (Retrofit implementation)
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<GitHubUserRepo>> listRepos(@Path("user") String user);
}
