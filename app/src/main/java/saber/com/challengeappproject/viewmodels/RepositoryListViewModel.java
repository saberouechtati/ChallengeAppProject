package saber.com.challengeappproject.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import saber.com.challengeappproject.data.GitHubRepository;

public class RepositoryListViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    public List<GitHubRepository> getRepositoryList(int count) {
        List<GitHubRepository> mRepositoryList;
        mRepositoryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int j = i+1;
            GitHubRepository mGitHubRepository = new GitHubRepository();
            mGitHubRepository.setName("This is repository #" + j);
            mGitHubRepository.setBranchesCount(5*j);
            mGitHubRepository.setForksCount(5*j);
            mGitHubRepository.setStarsCount(5*j);
            mRepositoryList.add(mGitHubRepository);
        }
        return mRepositoryList;
    }
}
