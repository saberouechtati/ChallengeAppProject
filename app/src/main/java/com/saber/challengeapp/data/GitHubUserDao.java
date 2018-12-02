package com.saber.challengeapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * The DAO of GitHubUser entity, model
 */
@Dao
public interface GitHubUserDao {

    // Gets all Github user record
    @Query("SELECT * FROM github_user")
    LiveData<List<GitHubUser>> getAll();

    // Gets all Github user record by id list
    @Query("SELECT * FROM github_user WHERE id IN (:gitHubUserIds)")
    LiveData<List<GitHubUser>> loadAllByIds(int[] gitHubUserIds);

    // Gets a Github user record by id
    @Query("SELECT * FROM github_user WHERE id = :id LIMIT 1")
    LiveData<GitHubUser> findById(int id);

    // Gets a Github user record by name
    @Query("SELECT * FROM github_user WHERE login LIKE :name LIMIT 1")
    LiveData<GitHubUser> findByName(String name);

    // Insert a Github user object
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GitHubUser gitHubUser);

    // Insert a Github user list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<GitHubUser> gitHubUsers);

    // Update a Github user record
    @Update
    int update(GitHubUser gitHubUser);

    // Delete all Github user record
    @Query("DELETE FROM github_user")
    void deleteAll();

    // Delete a Github user record
    @Delete
    void delete(GitHubUser gitHubUser);
}
