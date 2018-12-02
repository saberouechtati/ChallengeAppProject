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
public interface GitHubUserRepoDao {

    @Query("SELECT * FROM github_repositories")
    LiveData<List<GitHubUserRepo>> getAll();

    @Query("SELECT * FROM github_repositories WHERE id IN (:gitHubUserRepoIds)")
    LiveData<List<GitHubUserRepo>> loadAllByIds(int[] gitHubUserRepoIds);

    @Query("SELECT * FROM github_repositories WHERE id = :id LIMIT 1")
    LiveData<GitHubUserRepo> findById(int id);

    @Query("SELECT * FROM github_repositories WHERE name LIKE :name LIMIT 1")
    LiveData<GitHubUserRepo> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GitHubUserRepo gitHubUserRepo);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<GitHubUserRepo> gitHubUserRepos);

    @Update
    int update(GitHubUserRepo repository);

    @Query("DELETE FROM github_repositories")
    void deleteAll();

    @Delete
    void delete(GitHubUserRepo gitHubUserRepo);
}
