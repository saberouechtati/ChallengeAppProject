package com.saber.challengeapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * GitHub user repository entity, model
 */
@Entity(tableName = "github_repositories")
public class GitHubUserRepo {

    // The GitHub user repository id
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @Expose
    public int id;

    // The GitHub user repository name
    @ColumnInfo(name = "name")
    @Expose
    public String name;

    // The GitHub user repository full name
    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    public String fullName;

    // The GitHub user repository description
    @ColumnInfo(name = "description")
    @Expose
    public String description;

    // The GitHub user repository language
    @ColumnInfo(name = "language")
    @Expose
    public String language;

    // The GitHub user repository owner
    @ColumnInfo(name = "owner")
    @SerializedName("owner")
    @TypeConverters(Converters.class)
    public GitHubUser user;

    // The GitHub user repository watchers count
    @ColumnInfo(name = "watchers_count")
    @SerializedName("watchers_count")
    public int watchersCount;

    // The GitHub user repository branches count
    @ColumnInfo(name = "branches_count")
    @SerializedName("branches_count")
    public int branchesCount;

    // The GitHub user repository forks count
    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    public int forksCount;

    // The GitHub user repository stars count
    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    public int starsCount;

    //The GitHub user repository creation date
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    public String createdAt;

    // The GitHub user repository last update date
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    public String updatedAt;


    // Gets the GitHub user repository id
    public int getId() {
        return id;
    }

    // Sets the GitHub user repository id
    public void setId(int id) {
        this.id = id;
    }

    // Gets the GitHub user repository name
    public String getName() {
        return name;
    }

    // Sets the GitHub user repository name
    public void setName(String name) {
        this.name = name;
    }

    // Gets the GitHub user repository owner
    public GitHubUser getUser() {
        return user;
    }

    // Sets the GitHub user repository owner
    public void setUser(GitHubUser user) {
        this.user = user;
    }

    // Gets the GitHub user repository watchers count
    public int getWatchersCount() {
        return watchersCount;
    }

    // Sets the GitHub user repository watcher count
    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    // Gets the GitHub user repository branches count
    public int getBranchesCount() {
        return branchesCount;
    }

    // Sets the GitHub user repository branches count
    public void setBranchesCount(int branchesCount) {
        this.branchesCount = branchesCount;
    }

    // Gets the GitHub user repository forks count
    public int getForksCount() {
        return forksCount;
    }

    // Sets the GitHub user repository forks count
    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    // Gets the GitHub user repository stars count
    public int getStarsCount() {
        return starsCount;
    }

    // Sets the GitHub user repository stars count
    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    // Gets the GitHub user repository full name
    public String getFullName() {
        return fullName;
    }

    // Sets the GitHub user repository full name
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Gets the GitHub user repository description
    public String getDescription() {
        return description;
    }

    // Sets the GitHub user repository description
    public void setDescription(String description) {
        this.description = description;
    }

    // Gets the GitHub user repository language
    public String getLanguage() {
        return language;
    }

    // Sets the GitHub user repository language
    public void setLanguage(String language) {
        this.language = language;
    }

    // Gets the GitHub user repository creation date
    public String getCreatedAt() {
        return createdAt;
    }

    // Sets the GitHub user repository creation date
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Gets the GitHub user repository last update date
    public String getUpdatedAt() {
        return updatedAt;
    }

    // Sets the GitHub user repository last update date
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
