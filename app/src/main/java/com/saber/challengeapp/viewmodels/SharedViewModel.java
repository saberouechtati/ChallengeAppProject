package com.saber.challengeapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import com.saber.challengeapp.data.AccessToken;
import com.saber.challengeapp.data.GitHubUserRepo;
import com.saber.challengeapp.service.GitHubService;
import com.saber.challengeapp.service.GitHubServiceBuilder;
import com.saber.challengeapp.utils.Destinations;
import com.saber.challengeappproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This helps the communication between the activity and fragments.
 * Helps updating the UI using LiveData.
 */
public class SharedViewModel extends ViewModel {

    private Context context;

    // The GitHub username
    private final MutableLiveData<String> username = new MutableLiveData<>();

    // The selected destination state
    private final MutableLiveData<Destinations> destination = new MutableLiveData<>();

    // The GitHub user repository list to display
    private MutableLiveData<List<GitHubUserRepo>> userRepoList;

    // The selected user repository to display
    private final MutableLiveData<GitHubUserRepo> selectedUserRepo = new MutableLiveData<>();

    // The user GitHub Access Token for login
    private MutableLiveData<AccessToken> gitHubAccessToken = new MutableLiveData<>();



    // Gets the GitHub username
    public LiveData<String> getUsername(Context context) {
        if (this.username.getValue() == null)
            username.setValue(context.getString(R.string.default_username));
        return this.username;
    }

    // Update the GitHub username
    public void updateUsername(String username) {
        this.username.setValue(username);
    }

    // Gets the destination
    public LiveData<Destinations> getDestination() {
        return destination;
    }

    // Change the destination
    public void navigate(Destinations destinations) {
        this.destination.setValue(destinations);
    }

    // Gets the GitHub user repository list. if null, loads the list
    public LiveData<List<GitHubUserRepo>> getUserRepoList(final Context context) {
        if (userRepoList == null) {
            userRepoList = new MutableLiveData<>();
            this.context = context;
            loadUserRepoList(getUsername(context).getValue());
        }
        return userRepoList;
    }

    // Gets the GitHub user Access token for login
    public LiveData<AccessToken> getGitHubAccessToken(final Context context, String code) {
        if (gitHubAccessToken == null) {
            gitHubAccessToken = new MutableLiveData<>();
            this.context = context;
            loadGitHubAccessToken(code);
        }
        return gitHubAccessToken;
    }

    // Update the GitHub user repository list
    public void updateUserRepoList() {
        this.userRepoList = new MutableLiveData<>();
        loadUserRepoList(getUsername(context).getValue());
    }

    // Gets the selected GitHub user repository
    public LiveData<GitHubUserRepo> getSelectedUserRepo() {
        return selectedUserRepo;
    }

    // Update the selected GitHub user repository
    public void selectUserRepo(GitHubUserRepo userRepo) {
        if (userRepo != null) {
            this.selectedUserRepo.setValue(userRepo);
        }
    }

    // Loads the selected GitHub user repository list
    public void loadUserRepoList(String username) {
        // Loads the list using Retrofit
        final String BASIC_URL = context.getString(R.string.gitHub_api_basic_url);
        GitHubService gitHubService = new GitHubServiceBuilder(BASIC_URL).getService();
        Call<List<GitHubUserRepo>> userRepoListCall = gitHubService.listRepos(username);
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
                        context.getString(R.string.Load_failed),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Loads the GitHub Access Token for the login
    public void loadGitHubAccessToken(String code) {
        // Loads the Access Token
        final String BASIC_URL = context.getString(R.string.gitHub_basic_url);
        GitHubService gitHubService = new GitHubServiceBuilder(BASIC_URL).getService();
        Call<AccessToken> accessTokenCall = gitHubService.getAccessToken(
                context.getString(R.string.githubClientId),
                context.getString(R.string.githubClientSecret),
                code);
        accessTokenCall.enqueue(new Callback<AccessToken>() {

            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    gitHubAccessToken.setValue(response.body());
                } else {
                    gitHubAccessToken.setValue(null);
                    Toast.makeText(context,
                            context.getString(R.string.request_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                gitHubAccessToken.setValue(null);
                Toast.makeText(context,
                        context.getString(R.string.Load_failed),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
