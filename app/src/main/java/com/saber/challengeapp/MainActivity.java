package com.saber.challengeapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.saber.challengeapp.ui.SearchFragment;
import com.saber.challengeapp.ui.UserRepoDetailFragment;
import com.saber.challengeapp.ui.UserRepoListFragment;
import com.saber.challengeapp.ui.SplashFragment;

import com.saber.challengeapp.viewmodels.SharedViewModel;
import com.saber.challengeappproject.R;

import static com.saber.challengeapp.utils.Destinations.Splash_UserRepoList;
import static com.saber.challengeapp.utils.Destinations.Start_SplashScreen;

/**
 * This is the entry point of this application
 */
public class MainActivity extends AppCompatActivity {

    // The class tag
    private static final String TAG = "MainActivity";

    public Context context;

    // The fragment used for navigation
    private static Fragment fragment;

    // The shared ViewModel
    public SharedViewModel sharedViewModel;

    // The RecyclerView's layout manager
    public RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        context = this;
        if (savedInstanceState == null) {

            // Initialize the shared ViewModel
            sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

            // Observes and navigate betwen destinations
            sharedViewModel.getDestination().observe(this, destinations -> {
                switch (destinations) {
                    case Start_SplashScreen:
                        navigateToSplashScreen();
                        break;
                    case Splash_UserRepoList:
                        navigateToUserRepoList();
                        break;
                    case Master_UserRepoDetail:
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            navigateToUserRepoDetail();
                        }, 1000);
                        break;
                    case Master_UserRepoSearch:
                        navigateToSearch();
                        break;
                    case Master_Login:
                        navigateToLogin();
                        break;
                    default:
                        navigateToUserRepoList();
                }
            });

            // Navigate to Splash screen destination.
            sharedViewModel.navigate(Start_SplashScreen);
        }
    }

    /**
     * This sart the application by navigate to the splash screen,
     * and after 2000 milliseconds navigate to the Master destination (user repository list fragment)
     */
    private void navigateToSplashScreen() {
        // Initialize, display, and navigate to the splash screen destination
        fragment = SplashFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
                .replace(R.id.container, fragment)
                .commitNow();

        // Handler: after 2000 milliseconds navigates from the splash screen
        // to the user repository list destination
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Navigate to UserRepoList destination.
            sharedViewModel.navigate(Splash_UserRepoList);
        }, 2000);
    }

    /**
     * Navigats to the login dstination
     */
    private void navigateToLogin() {
        // Initialize, display, and navigate to the login destination
        fragment = UserRepoListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
                .replace(R.id.container, fragment)
                .commitNow();
    }

    /**
     * Navigats to the search dstination
     */
    private void navigateToSearch() {
        // Initialize, display, and navigate the search destination
        fragment = SearchFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
                .replace(R.id.container, fragment)
                .commitNow();
    }

    /**
     * Navigats to the userRepoDetails dstination
     */
    private void navigateToUserRepoDetail() {
        // Initialize, display, and navigate to the user repository detail destination
        fragment = UserRepoDetailFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
                .replace(R.id.container, fragment)
                .commitNow();
    }

    /**
     * Navigats to the master/ userRepoList dstination
     */
    private void navigateToUserRepoList() {
        // Initialize, display, and navigate to the user repository list destination
        fragment = UserRepoListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
                .replace(R.id.container, fragment)
                .commitNow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.fragment_open_scale, R.anim.fragment_close_translate);
    }

    @Override
    public void onBackPressed() {

        switch (sharedViewModel.getDestination().getValue()) {
            case Splash_UserRepoList:
                super.onBackPressed();
                break;
            case Master_UserRepoDetail:
                sharedViewModel.navigate(Splash_UserRepoList);
                break;
            case Master_UserRepoSearch:
                sharedViewModel.navigate(Splash_UserRepoList);
                break;
            default:
                super.onBackPressed();
        }
    }
}
