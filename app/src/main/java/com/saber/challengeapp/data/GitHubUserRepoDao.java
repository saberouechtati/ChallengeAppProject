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
 * The DAO of GitHubUserRepo entity, model
 */
@Dao
public interface GitHubUserRepoDao {

    // Gets all the GitHub user repository record
    @Query("SELECT * FROM github_repositories")
    LiveData<List<GitHubUserRepo>> getAll();

    // Gets the GitHub user repository record by id list
    @Query("SELECT * FROM github_repositories WHERE id IN (:gitHubUserRepoIds)")
    LiveData<List<GitHubUserRepo>> loadAllByIds(int[] gitHubUserRepoIds);

    // Gets a GitHub user repository record by id
    @Query("SELECT * FROM github_repositories WHERE id = :id LIMIT 1")
    LiveData<GitHubUserRepo> findById(int id);

    // Gets  a GitHub user repository record by name
    @Query("SELECT * FROM github_repositories WHERE name LIKE :name LIMIT 1")
    LiveData<GitHubUserRepo> findByName(String name);

    // Insert a GitHub user repository record
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GitHubUserRepo gitHubUserRepo);

    // Insert a list of GitHub user repository record
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<GitHubUserRepo> gitHubUserRepos);

    // Update a  GitHub user repository record
    @Update
    int update(GitHubUserRepo repository);

    // Delete all the GitHub user repository record
    @Query("DELETE FROM github_repositories")
    void deleteAll();

    // Delete a GitHub user repository record
    @Delete
    void delete(GitHubUserRepo gitHubUserRepo);
}
