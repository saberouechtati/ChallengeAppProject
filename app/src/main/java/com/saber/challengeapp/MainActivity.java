package com.saber.challengeapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.saber.challengeapp.ui.UserRepoDetailFragment;
import com.saber.challengeapp.ui.UserRepoListFragment;
import com.saber.challengeapp.ui.SplashFragment;

import com.saber.challengeapp.utils.Destinations;
import com.saber.challengeapp.viewmodels.SharedViewModel;
import com.saber.challengeappproject.R;

import static com.saber.challengeapp.utils.Destinations.Splash_UserRepoList;
import static com.saber.challengeapp.utils.Destinations.Start_SplashScreen;

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

            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
                .commitNow();

            sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
            Log.i(TAG, "------->>>> sharedViewModel:  " + sharedViewModel);
            sharedViewModel.getNavigation().observe(this, destinations -> {
                switch(destinations) {
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
                        },1000);
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

    private void navigateToSplashScreen() {
        fragment = SplashFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
                .replace(R.id.container, fragment)
                .commitNow();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Navigate to UserRepoList destination.
            sharedViewModel.navigate(Splash_UserRepoList);
        },2000);
    }

    private void navigateToLogin() {
        fragment = UserRepoListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
            .replace(R.id.container, fragment)
            .commitNow();
    }

    private void navigateToSearch() {
        fragment = UserRepoListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
            .replace(R.id.container, fragment)
            .commitNow();
    }

    private void navigateToUserRepoDetail() {
        fragment = UserRepoDetailFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.fragment_open_translate, R.anim.fragment_close_scale)
            .replace(R.id.container, fragment)
            .commitNow();
    }

    private void navigateToUserRepoList() {
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

        switch(sharedViewModel.getNavigation().getValue()) {
            case Splash_UserRepoList:
                super.onBackPressed();
                break;
            case Master_UserRepoDetail:
                sharedViewModel.navigate(Splash_UserRepoList);
                break;
            default:
                super.onBackPressed();
        }
    }
}
