package com.saber.challengeapp.data;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("access_type")
    private String accessType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessType() {
        return accessType;
    }
}
