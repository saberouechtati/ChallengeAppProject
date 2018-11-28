package saber.com.challengeappproject.ui.repositorylist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.List;

import saber.com.challengeappproject.R;
import saber.com.challengeappproject.adapters.RecyclerViewAdapter;
import saber.com.challengeappproject.data.GitHubRepository;
import saber.com.challengeappproject.viewmodels.RepositoryListViewModel;

/**
 * This fragment implement the distination of displaying a GitHubRepository list.
 */
public class RepositoryListFragment extends Fragment {

    // The class tag
    private static final String TAG = "RepositoryListFragment";

    // The RecyclerView used for displaying a GitHubRepository list
    protected RecyclerView mRecyclerView;

    // The RecyclerView's Adapter
    protected RecyclerViewAdapter mAdapter;

    // The RecyclerView's layout manager
    private RecyclerView.LayoutManager mLayoutManager;

    // The GitHubRepository list to display on the RecyclerView
    protected List<GitHubRepository> mRepositoryList;

    // The ViewModel used for managing the repository list data
    private RepositoryListViewModel mViewModel;

    /**
     * Create and gets a new instance of this fragment
     * @return the created new instance of this fragment
     */
    public static RepositoryListFragment newInstance() {
        return new RepositoryListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Initialize the root view
        View rootView = inflater.inflate(R.layout.repository_list_fragment, container, false);

        // Sets the tag of the root view
        rootView.setTag(TAG);

        // Initialize the ViewModel
        mViewModel = ViewModelProviders.of(this).get(RepositoryListViewModel.class);

        // Gets the repository list using the ViewModel
        mRepositoryList = mViewModel.getRepositoryList(50);

        // Initialize RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Run the RecyclerView items animation
        runAnimation(mRecyclerView);

        // Rturn the root view
        return rootView;
    }

    private void runAnimation(RecyclerView mRecyclerView) {

        // Initialize the context
        Context context = mRecyclerView.getContext();

        // Declare and initialize with null the layoutAnimationController
        LayoutAnimationController layoutAnimationController = null;

        // Update the layoutAnimationController with the fall_down_ bottom animation layout
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_bottom);

        // Initialize the mAdapter adapter
        mAdapter = new RecyclerViewAdapter(mRepositoryList);

        // Sets mAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Set the mRecyclerView animation
        mRecyclerView.setLayoutAnimation(layoutAnimationController);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Update the mViewModel
        mViewModel = ViewModelProviders.of(this).get(RepositoryListViewModel.class);
        // TODO: Use the ViewModel
    }

}
