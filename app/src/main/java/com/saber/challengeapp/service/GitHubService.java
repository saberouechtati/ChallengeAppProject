package com.saber.challengeapp.service;

import com.saber.challengeapp.data.AccessToken;
import com.saber.challengeapp.data.GitHubUserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * This is a RestApi call interface (Retrofit implementation)
 */
public interface GitHubService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("codee") String code
    );

    @GET("users/{user}/repos")
    Call<List<GitHubUserRepo>> listRepos(@Path("user") String user);
}
