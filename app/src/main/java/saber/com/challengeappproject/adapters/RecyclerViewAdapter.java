package saber.com.challengeappproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import saber.com.challengeappproject.R;
import saber.com.challengeappproject.data.GitHubRepository;

/**
 *  A custom Adapter for RecyclerView's views
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // This class tag
    private static final String TAG = "RecyclerViewAdapter";

    // The repository list to display in the RecyclerView
    private List<GitHubRepository> mRepositoryList;

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
        private Context context;

        /**
         * Class constructor specifying View of objects to initialize
         *
         * @param v the View to initialize
         */
        public ViewHolder(View v) {
            super(v);

            // update the context
            setContext(v.getContext());

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });

            // Initialize the repository name TextView
            repositoryNameTV = (TextView) v.findViewById(R.id.repoNameTextView);

            // Initialize the repository branches number TextView
            repositoryBranchesTV = (TextView) v.findViewById(R.id.repoBranchesTextView);

            // Initialize the repository forks number TextView
            repositoryForksTV = (TextView) v.findViewById(R.id.repoForksTextView);

            // Initialize the repository stars number TextView
            repositoryStarsTV = (TextView) v.findViewById(R.id.repoStarsTextView);
        }

        /**
         * Gets the repositoryName TextView of this ViewHolder object
         *
         * @return the repositoryName TextView of this ViewHolder object
         */
        public TextView getRepositoryNameTV() {
            return repositoryNameTV;
        }

        /**
         * Gets the repositoryBranches TextView of this ViewHolder object
         *
         * @return the repositoryBranches TextView of this ViewHolder object
         */
        public TextView getRepositoryBranchesTV() {
            return repositoryBranchesTV;
        }

        /**
         * Gets the repositoryForks TextView of this ViewHolder object
         *
         * @return the repositoryForks TextView of this ViewHolder object
         */
        public TextView getRepositoryForksTV() {
            return repositoryForksTV;
        }

        /**
         * Gets the repositoryStars TextView of this ViewHolder object
         *
         * @return the repositoryStars TextView of this ViewHolder object
         */
        public TextView getRepositoryStarsTV() {
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
         * @param context the new context
         */
        public void setContext(Context context) {
            this.context = context;
        }
    }

    /**
     * Initialize the mRepositoryList of the Adapter.
     *
     * @param repositoryList List<GitHubRepository> containing the data to populate views to be used by RecyclerView.
     */
    public RecyclerViewAdapter(List<GitHubRepository> repositoryList) {
        mRepositoryList = repositoryList;
    }

    // Create new views (invoked by the layout manager)
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

        final String WHITE_SPACE=" ";

        // Gets the GitHub repository from the repository list mRepositoryList at this position
        GitHubRepository repository = mRepositoryList.get(position);

        // Replace the contents of the view with that element attributes.
        // Replace the content of repositoryNameTV
        viewHolder.getRepositoryNameTV().setText(new StringBuilder()
            .append(viewHolder.getContext().getString(R.string.repoNameText))
            .append(WHITE_SPACE)
            .append(repository.name)
        );

        // Replace the content of repositoryBranchesTV
        viewHolder.getRepositoryBranchesTV().setText(new StringBuilder()
            .append(viewHolder.getContext().getString(R.string.repoBranchText))
            .append(WHITE_SPACE)
            .append(repository.branchesCount)
        );

        // Replace the content of repositoryForksTV
        viewHolder.getRepositoryForksTV().setText(new StringBuilder()
            .append(viewHolder.getContext().getString(R.string.repoForkText))
            .append(WHITE_SPACE)
            .append(repository.forksCount)
        );

        // Replace the content of repositoryStarsTV
        viewHolder.getRepositoryStarsTV().setText(new StringBuilder()
            .append(viewHolder.getContext().getString(R.string.repoStarText))
            .append(WHITE_SPACE)
            .append(repository.starsCount)
        );
    }

    // Return the size of mRepositoryList (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mRepositoryList.size();
    }
}
