package saber.com.challengeappproject.ui.repositorylist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import saber.com.challengeappproject.R;
import saber.com.challengeappproject.adapters.RecyclerViewAdapter;
import saber.com.challengeappproject.viewmodels.RepositoryListViewModel;

public class RepositoryListFragment extends Fragment {

    private static final String TAG = "RepositoryListFragment";

    protected RecyclerView mRecyclerView;
    protected RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    private RepositoryListViewModel mViewModel;

    public static RepositoryListFragment newInstance() {
        return new RepositoryListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.repository_list_fragment, container, false);
        rootView.setTag(TAG);

        mViewModel = ViewModelProviders.of(this).get(RepositoryListViewModel.class);
        // TODO: Use the ViewModel
        mDataset = mViewModel.getRepositoryList(50);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new RecyclerViewAdapter(mDataset);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RepositoryListViewModel.class);
        // TODO: Use the ViewModel
        mDataset = mViewModel.getRepositoryList(50);
    }

}
