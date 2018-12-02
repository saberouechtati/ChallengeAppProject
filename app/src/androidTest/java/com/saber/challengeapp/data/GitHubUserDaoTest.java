package com.saber.challengeapp.data;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.saber.challengeapp.utils.LiveDataTestUtil;
import com.saber.challengeapp.utils.TestDataSourceUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GitHubUserDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private GitHubUserDao testGitHubUserDao;
    private AppDatabase testDb;
    private List<GitHubUser> testUserList;

    @Before
    public void createDb() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        testDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
            .allowMainThreadQueries()
            .build();
        testGitHubUserDao = testDb.gitHubUserDao();
    }

    @After
    public void closeDb() throws IOException {
        testDb.close();
    }

    @Test
    public void onInsertingUsers_checkIf_RowCountIsCorrect() throws InterruptedException {
        testUserList = TestDataSourceUtil.getTestUserList(3);
        testGitHubUserDao.insertAll(testUserList);
        assertEquals(3, LiveDataTestUtil.getValue(testGitHubUserDao.getAll()).size());
    }

    @Test
    public void onFetchingUsers_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List <GitHubUser> testUserList = LiveDataTestUtil.getValue(testGitHubUserDao.getAll());
        assertTrue(testUserList.isEmpty());
    }

    @Test
    public void onUpdatingAUser_checkIf_UpdateHappensCorrectly() throws InterruptedException {
        GitHubUser testUser = (TestDataSourceUtil.getTestUserList(1)).get(0);
        testGitHubUserDao.deleteAll();
        assertEquals(0, LiveDataTestUtil.getValue(testGitHubUserDao.getAll()).size());
        testGitHubUserDao.insert(testUser);
        testUser.setName(TestDataSourceUtil.NEW_TEST_USER_NAME);
        testGitHubUserDao.update(testUser);
        //Thread.sleep(5000);
        String updatedTestUserName = (LiveDataTestUtil.getValue(testGitHubUserDao.getAll()).get(0)).getName();
        assertEquals(TestDataSourceUtil.NEW_TEST_USER_NAME, updatedTestUserName);
    }

    @Test
    public void onUserDeletion_CheckIf_UserIsDeletedFromTable() throws InterruptedException {
        List <GitHubUser> testUserList = TestDataSourceUtil.getTestUserList(7);
        testGitHubUserDao.insertAll(testUserList);
        testGitHubUserDao.delete(testUserList.get(2));
        assertNull(LiveDataTestUtil.getValue(testGitHubUserDao.findById(testUserList.get(2).getId())));
    }

    @Test
    public void onAllUsersDeletion_CheckIf_UsersAreDeletedFromTable() throws InterruptedException {
        List <GitHubUser> testUserList = TestDataSourceUtil.getTestUserList(7);
        testGitHubUserDao.insertAll(testUserList);
        testGitHubUserDao.deleteAll();
        assertEquals(0, LiveDataTestUtil.getValue(testGitHubUserDao.getAll()).size());
    }
}
