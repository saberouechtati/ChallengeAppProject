package com.saber.challengeapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import com.saber.challengeapp.data.GitHubUserRepo;
import com.saber.challengeapp.service.GitHubService;
import com.saber.challengeapp.service.GitHubServiceBuilder;
import com.saber.challengeapp.utils.Destinations;
import com.saber.challengeappproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharedViewModel extends ViewModel {

    private Context context;

    //The selected navigation state
    private final MutableLiveData<Destinations> navigation = new MutableLiveData<>();

    // The GitHub user repository list to display
    private MutableLiveData<List<GitHubUserRepo>> userRepoList;

    //The selected user repository to display
    private final MutableLiveData<GitHubUserRepo> selectedUserRepo = new MutableLiveData<>();


    public LiveData<Destinations> getNavigation() {
        return navigation;
    }

    public void navigate(Destinations destinations) {
        this.navigation.setValue(destinations);
    }

    public LiveData<List<GitHubUserRepo>> getUserRepoList(final Context context) {
        if (userRepoList == null) {
            userRepoList = new MutableLiveData<>();
            this.context = context;
            loadUserRepoList();
        }
        return userRepoList;
    }

    public LiveData<GitHubUserRepo> getSelectedUserRepo() {
        return selectedUserRepo;
    }

    public void selectUserRepo(GitHubUserRepo userRepo) {
        if (userRepo != null) {
            this.selectedUserRepo.setValue(userRepo);
        }
    }

    private void loadUserRepoList() {
        GitHubService gitHubService = new GitHubServiceBuilder().getService();
        Call<List<GitHubUserRepo>> userRepoListCall = gitHubService.listRepos("facebook");
        userRepoListCall.enqueue(new Callback<List<GitHubUserRepo>>() {

            @Override
            public void onResponse(Call<List<GitHubUserRepo>> call, Response<List<GitHubUserRepo>> response) {
                if (response.isSuccessful()) {
                    userRepoList.setValue(response.body());
                } else {
                    userRepoList.setValue(null);
                    Toast.makeText(context,
                            context.getString(R.string.request_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GitHubUserRepo>> call, Throwable t) {
                userRepoList.setValue(null);
                Toast.makeText(context,
                        context.getString(R.string.no_network),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
