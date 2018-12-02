package com.saber.challengeapp.data;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import com.saber.challengeapp.utils.LiveDataTestUtil;
import com.saber.challengeapp.utils.TestDataSourceUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GitHubUserRepoDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private GitHubUserRepoDao testGitHubUserRepoDao;
    private AppDatabase testDb;
    private List<GitHubUserRepo> testRepoList;

    @Before
    public void createDb() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        testDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        testGitHubUserRepoDao = testDb.gitHubUserRepoDao();
    }

    @After
    public void closeDb() throws IOException {
        testDb.close();
    }

    @Test
    public void onInsertingRepositories_checkIf_RowCountIsCorrect() throws InterruptedException {
        testRepoList = TestDataSourceUtil.getTestRepoList(3);
        testGitHubUserRepoDao.insertAll(testRepoList);
        assertEquals(3, LiveDataTestUtil.getValue(testGitHubUserRepoDao.getAll()).size());
    }

    @Test
    public void onFetchingRepositories_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List<GitHubUserRepo> testRepoList = LiveDataTestUtil.getValue(testGitHubUserRepoDao.getAll());
        assertTrue(testRepoList.isEmpty());
    }

    @Test
    public void onUpdatingARepositories_checkIf_UpdateHappensCorrectly() throws InterruptedException {
        GitHubUserRepo testRepo = (TestDataSourceUtil.getTestRepoList(1)).get(0);
        testGitHubUserRepoDao.deleteAll();
        assertEquals(0, LiveDataTestUtil.getValue(testGitHubUserRepoDao.getAll()).size());
        testGitHubUserRepoDao.insert(testRepo);
        //
        GitHubUser newTestUser = TestDataSourceUtil.getTestUserList(2).get(1);
        testRepo.setUser(newTestUser);
        testGitHubUserRepoDao.update(testRepo);
        GitHubUser updatedTestUser = (LiveDataTestUtil.getValue(testGitHubUserRepoDao.getAll()).get(0).getUser());
        assertEquals(newTestUser, updatedTestUser);
        //
        testRepo.setName(TestDataSourceUtil.NEW_TEST_REPO_NAME);
        testGitHubUserRepoDao.update(testRepo);
        // Thread.sleep(5000);
        String updatedTestRepoName = (LiveDataTestUtil.getValue(testGitHubUserRepoDao.getAll()).get(0)).getName();
        assertEquals(TestDataSourceUtil.NEW_TEST_REPO_NAME, updatedTestRepoName);
    }

    @Test
    public void onRepositoryDeletion_CheckIf_RepositoryIsDeletedFromTable() throws InterruptedException {
        List<GitHubUserRepo> testRepoList = TestDataSourceUtil.getTestRepoList(7);
        testGitHubUserRepoDao.insertAll(testRepoList);
        testGitHubUserRepoDao.delete(testRepoList.get(2));
        assertNull(LiveDataTestUtil.getValue(testGitHubUserRepoDao.findById(testRepoList.get(2).getId())));
    }

    @Test
    public void onAllRepositoriesDeletion_CheckIf_RepositoriesAreDeletedFromTable() throws InterruptedException {
        List<GitHubUserRepo> testRepoList = TestDataSourceUtil.getTestRepoList(7);
        testGitHubUserRepoDao.insertAll(testRepoList);
        testGitHubUserRepoDao.deleteAll();
        assertEquals(0, LiveDataTestUtil.getValue(testGitHubUserRepoDao.getAll()).size());
    }
}
