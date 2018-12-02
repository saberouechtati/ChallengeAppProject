package com.saber.challengeapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * GitHubUser entity, model
 */
@Entity(tableName = "github_user")
public class GitHubUser {

    // The external id
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    public int id;

    // The username
    @ColumnInfo(name = "login")
    @SerializedName("login")
    @Expose
    public String name;

    // The avatar_url
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    @Expose
    public String avatarURL;

    // Gets the Github user external id
    public int getId() {
        return id;
    }

    // Setes the external id
    public void setId(int id) {
        this.id = id;
    }

    // Gets the Github user username
    public String getName() {
        return name;
    }

    // Sets the Github user username
    public void setName(String name) {
        this.name = name;
    }

    // Gets the Github user  avatar's url
    public String getAvatarURL() {
        return avatarURL;
    }

    // Sets the Github user  avatar's url
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
