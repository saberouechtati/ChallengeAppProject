package com.saber.challengeapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.saber.challengeapp.adapters.RecyclerViewAdapter;
import com.saber.challengeapp.data.GitHubUserRepo;
import com.saber.challengeapp.viewmodels.SharedViewModel;
import com.saber.challengeappproject.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Implements the distination of displaying a GitHub user repository list.
 * Use the {@link UserRepoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRepoListFragment extends Fragment {

    // The class tag
    private static final String TAG = "UserRepoListFragment";

    // The ShimmerFrameLayout
    private ShimmerFrameLayout mShimmerViewContainer;

    // The RecyclerView used to display GitHub user repository list
    public RecyclerView mRecyclerView;

    // The RecyclerView's Adapter
    public RecyclerViewAdapter mAdapter;

    // The GitHub user repository list to display
    public List<GitHubUserRepo> mRepositoryList;

    // The layout animation controller used for animate the GitHub user repository list
    private static LayoutAnimationController layoutAnimationController;

    // The shared ViewModel
    private SharedViewModel sharedViewModel;

    /**
     * Create and gets a new instance of this fragment
     *
     * @return the created new instance of this fragment
     */
    public static UserRepoListFragment newInstance() {
        return new UserRepoListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Opening transition animations
        // getActivity().overridePendingTransition(R.anim.fragment_open_translate, R.anim.fragment_close_scale);

        // Initialize the root view
        View rootView = inflater.inflate(R.layout.repository_list_fragment, container, false);

        // Sets the tag of the root view
        rootView.setTag(TAG);

        // Initialize the ShimmerFrameLayout
        mShimmerViewContainer = rootView.findViewById(R.id.shimmer_view_container);

        // Initialize RecyclerView
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        // Initialize and display the data loading animation
        displayLodingAnimation();

        // Add FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        // Sets the FloatingActionButton's OnClick event listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Rturn the root view
        return rootView;
    }

    // Initialize and display the data loading animation
    private void displayLodingAnimation() {
        mAdapter = new RecyclerViewAdapter(new ArrayList<>(), getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Prepare the GitHub user repository list to display
        // Initialize the ViewModel
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        Log.i(TAG, "------->>>> sharedViewModel:  " + sharedViewModel);
        sharedViewModel.getUserRepoList(this.getContext()).observe(getViewLifecycleOwner(), userRepoList -> {
            // Update UI
            if (userRepoList != null) {
                mRepositoryList = userRepoList;
                displayUserRepoList(this.getContext());
            } else {
                displayLodingAnimation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        //closing transition animations
        getActivity().overridePendingTransition(R.anim.fragment_open_scale, R.anim.fragment_close_translate);
        super.onPause();
    }

    // Display the GitHub user repository list
    public void displayUserRepoList(Context context) {

        // refreshing recycler view
        mAdapter.notifyDataSetChanged();

        // stop animating Shimmer and hide the layout
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

        // Initialize the mAdapter adapter
        mAdapter = new RecyclerViewAdapter(mRepositoryList, context);

        // Sets mAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Declare and initialize with null the layoutAnimationController
        layoutAnimationController = null;

        // Update the layoutAnimationController with the fall_down_ bottom animation layout
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_bottom);

        // Set the mRecyclerView animation
        mRecyclerView.setLayoutAnimation(layoutAnimationController);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }
}
