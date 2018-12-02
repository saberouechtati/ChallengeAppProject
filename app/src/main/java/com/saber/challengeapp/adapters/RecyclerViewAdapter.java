package com.saber.challengeapp.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.saber.challengeapp.data.GitHubUserRepo;
import com.saber.challengeapp.utils.Destinations;
import com.saber.challengeapp.viewmodels.SharedViewModel;
import com.saber.challengeappproject.R;

/**
 * A custom Adapter for RecyclerView's views
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // This class tag
    private static final String TAG = "RecyclerViewAdapter";

    // The repository list to display in the RecyclerView
    private static List<GitHubUserRepo> mRepositoryList;

    // The shared ViewModel
    private static SharedViewModel sharedViewModel;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The repository name
        private final TextView repositoryNameTV;

        // The repository branches number
        private final TextView repositoryBranchesTV;

        // The repository forks number
        private final TextView repositoryForksTV;

        // The repository stars number
        private final TextView repositoryStarsTV;

        // This view Context
        private static Context context;

        /**
         * Class constructor specifying View of objects to initialize
         *
         * @param view the View to initialize
         */
        public ViewHolder(View view) {
            super(view);

            // Sets the this context
            setContext(view.getContext());

            // Define click listener for the ViewHolder's View.
            view.setOnClickListener(v1 -> {
                int position = getAdapterPosition();

                // Sets selected user repository.
                sharedViewModel.selectUserRepo(mRepositoryList.get(position));

                // Navigate to detail view view: display an istance of UserRepoDetailFragment.
                sharedViewModel.navigate(Destinations.Master_UserRepoDetail);

                Log.d(TAG, "Element " + position + " clicked.");
                Log.i(TAG, "------>>> user name: " + mRepositoryList.get(position).user.getName());
            });

            // Initialize the repository name TextView
            repositoryNameTV = view.findViewById(R.id.repoNameTextView);

            // Initialize the repository branches number TextView
            repositoryBranchesTV = view.findViewById(R.id.repoBranchesTextView);

            // Initialize the repository forks number TextView
            repositoryForksTV = view.findViewById(R.id.repoForksTextView);

            // Initialize the repository stars number TextView
            repositoryStarsTV = view.findViewById(R.id.repoStarsTextView);
        }

        /**
         * Gets the repositoryName TextView of this ViewHolder object
         *
         * @return the repositoryName TextView of this ViewHolder object
         */
        TextView getRepositoryNameTV() {
            return repositoryNameTV;
        }

        /**
         * Gets the repositoryBranches TextView of this ViewHolder object
         *
         * @return the repositoryBranches TextView of this ViewHolder object
         */
        TextView getRepositoryBranchesTV() {
            return repositoryBranchesTV;
        }

        /**
         * Gets the repositoryForks TextView of this ViewHolder object
         *
         * @return the repositoryForks TextView of this ViewHolder object
         */
        TextView getRepositoryForksTV() {
            return repositoryForksTV;
        }

        /**
         * Gets the repositoryStars TextView of this ViewHolder object
         *
         * @return the repositoryStars TextView of this ViewHolder object
         */
        TextView getRepositoryStarsTV() {
            return repositoryStarsTV;
        }

        /**
         * Gets the wiew context
         *
         * @return the view context
         */
        public Context getContext() {
            return context;
        }

        /**
         * Sets the view context
         *
         * @param context the new context
         */
        public void setContext(Context context) {
            this.context = context;
        }
    }

    /**
     * Initialize the mRepositoryList of the Adapter.
     *
     * @param repositoryList List<GitHubUserRepo> containing the data to populate views to be used by RecyclerView.
     */
    public RecyclerViewAdapter(List<GitHubUserRepo> repositoryList, Context context) {
        mRepositoryList = repositoryList;
        sharedViewModel = ViewModelProviders.of((FragmentActivity) context).get(SharedViewModel.class);
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // White space
        final String WHITE_SPACE = " ";

        // Gets the GitHub repository from the repository list mRepositoryList at this position
        GitHubUserRepo repository = mRepositoryList.get(position);

        // Chek the name length, if longer than 30, the cut the name
        if (repository.name.length() > 30) {
            repository.name = (new StringBuilder().append(repository.name.substring(0, 29))
                    .append("...")).toString();
        }

        // Replace the contents of the view with that element attributes.
        // Replace the content of repositoryNameTV
        viewHolder.getRepositoryNameTV().setText(new StringBuilder()
                .append(viewHolder.getContext().getResources().getString(R.string.repoNameText))
                .append(WHITE_SPACE)
                .append(repository.name)
        );

        // Replace the content of repositoryBranchesTV
        viewHolder.getRepositoryBranchesTV().setText(new StringBuilder()
                .append(viewHolder.getContext().getResources().getString(R.string.repoBranchText))
                .append(WHITE_SPACE)
                .append(repository.branchesCount)
        );

        // Replace the content of repositoryForksTV
        viewHolder.getRepositoryForksTV().setText(new StringBuilder()
                .append(viewHolder.getContext().getResources().getString(R.string.repoForkText))
                .append(WHITE_SPACE)
                .append(repository.forksCount)
        );

        // Replace the content of repositoryStarsTV
        viewHolder.getRepositoryStarsTV().setText(new StringBuilder()
                .append(viewHolder.getContext().getResources().getString(R.string.repoStarText))
                .append(WHITE_SPACE)
                .append(repository.starsCount)
        );
    }

    /**
     * Return the size of mRepositoryList (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        if (mRepositoryList != null) return mRepositoryList.size();
        else return 0;
    }
}
