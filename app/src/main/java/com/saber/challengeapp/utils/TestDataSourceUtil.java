package com.saber.challengeapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.saber.challengeapp.data.GitHubUser;
import com.saber.challengeapp.data.GitHubUserRepo;

public class TestDataSourceUtil {

    // repo
    public static final int TEST_REPO_EXTERNAL_ID = 1000;
    public static final String TEST_REPO_NAME = "Test Repository #";
    public static final String NEW_TEST_REPO_NAME = "New Test Repository";
    public static final int TEST_REPO_BRANCHES_NUMBER = 25;
    public static final int TEST_REPO_FORKS_NUMBER = 125;
    public static final int TEST_REPO_STARS_NUMBER = 1250;

    // user
    public static final int TEST_USER_EXTERNAL_ID = 1000;
    public static final String TEST_USER_NAME = "Test Repository #";
    public static final String NEW_TEST_USER_NAME = "New Test Repository";
    public static final String TEST_USER_AVATAR_URL = "Avater url here";

    /**
     * This method helps generating a fake repository list for testing
     * @param n the number of elements
     * @return testRepoList The generated test repository list
     */
    public static List<GitHubUserRepo> getTestRepoList(int n) {
        List<GitHubUserRepo> testRepoList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            GitHubUserRepo testRepo = new GitHubUserRepo();
            testRepo.setName(TEST_REPO_NAME + i);
            testRepo.setUser(getTestUserList(1).get(0));
            testRepo.setForksCount(TEST_REPO_FORKS_NUMBER);
            testRepo.setStarsCount(TEST_REPO_STARS_NUMBER);
            testRepoList.add(testRepo);
        }
        return testRepoList;
    }

    /**
     * This method helps generating a fake user list for testing
     * @param n the number of elements
     * @return testUserList The generated test user list test list
     */
    public static List<GitHubUser> getTestUserList(int n) {
        List<GitHubUser> testUserList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            GitHubUser testUser = new GitHubUser();
            testUser.setName(TEST_USER_NAME + i);
            testUser.setAvatarURL(TEST_USER_AVATAR_URL);
            testUserList.add(testUser);
        }
        return testUserList;
    }
}
