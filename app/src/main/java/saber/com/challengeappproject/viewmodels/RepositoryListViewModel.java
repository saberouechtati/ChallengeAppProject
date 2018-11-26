package saber.com.challengeappproject.viewmodels;

import android.arch.lifecycle.ViewModel;

public class RepositoryListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public String[] getRepositoryList(int count) {
        String[] mDataset;
        mDataset = new String[count];
        for (int i = 0; i < count; i++) {
            mDataset[i] = "This is element #" + i;
        }
        return mDataset;
    }
}
