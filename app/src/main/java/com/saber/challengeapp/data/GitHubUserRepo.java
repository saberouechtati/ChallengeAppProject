package com.saber.challengeapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "github_repositories")
public class GitHubUserRepo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @Expose
    public int id;

    @ColumnInfo(name = "name")
    @Expose
    public String name;

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    public String fullName;

    @ColumnInfo(name = "description")
    @Expose
    public String description;

    @ColumnInfo(name = "language")
    @Expose
    public String language;

    @ColumnInfo(name = "owner")
    @SerializedName("owner")
    @TypeConverters(Converters.class)
    public GitHubUser user;

    @ColumnInfo(name = "watchers_count")
    @SerializedName("watchers_count")
    public int watchersCount;

    @ColumnInfo(name = "branches_count")
    @SerializedName("branches_count")
    public int branchesCount;

    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    public int forksCount;

    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    public int starsCount;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    public String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    public String updateddAt;


    /*** Getter and setter part ***/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GitHubUser getUser() {
        return user;
    }

    public void setUser(GitHubUser user) {
        this.user = user;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public int getBranchesCount() {
        return branchesCount;
    }

    public void setBranchesCount(int branchesCount) {
        this.branchesCount = branchesCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateddAt() {
        return updateddAt;
    }

    public void setUpdateddAt(String updateddAt) {
        this.updateddAt = updateddAt;
    }
}
