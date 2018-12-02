package com.saber.challengeapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GitHubUserDao {

    @Query("SELECT * FROM github_user")
    LiveData<List<GitHubUser>> getAll();

    @Query("SELECT * FROM github_user WHERE id IN (:gitHubUserIds)")
    LiveData<List<GitHubUser>> loadAllByIds(int[] gitHubUserIds);

    @Query("SELECT * FROM github_user WHERE id = :id LIMIT 1")
    LiveData<GitHubUser> findById(int id);

    @Query("SELECT * FROM github_user WHERE login LIKE :name LIMIT 1")
    LiveData<GitHubUser> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GitHubUser gitHubUser);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<GitHubUser> gitHubUsers);

    @Update
    int update(GitHubUser gitHubUser);

    @Query("DELETE FROM github_user")
    void deleteAll();

    @Delete
    void delete(GitHubUser gitHubUser);
}
