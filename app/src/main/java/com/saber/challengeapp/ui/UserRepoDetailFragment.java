package com.saber.challengeapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saber.challengeapp.viewmodels.SharedViewModel;
import com.saber.challengeappproject.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Implements the distination of displaying a GitHub user repository details.
 * Use the {@link UserRepoDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRepoDetailFragment extends Fragment {

    // The class tag
    private static final String TAG = "UserRepoDetailFragment";

    // The shared ViewModel
    private SharedViewModel sharedViewModel;

    // UI, TextViews
    private TextView nameTV;
    private TextView fullNameTV;
    private TextView descriptionTV;
    private TextView languageTV;
    private TextView ownerTV;
    private TextView watchersTV;
    private TextView starsTV;
    private TextView branchesTV;
    private TextView forksTV;
    private TextView createdAtTV;
    private TextView updatedAtTV;

    // Required empty public constructor
    public UserRepoDetailFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment UserRepoDetailFragment.
     */
    public static UserRepoDetailFragment newInstance() {
        return new UserRepoDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Initialize the root view, inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // Sets the tag of the root view
        rootView.setTag(TAG);

        //Initialize UI
        nameTV = rootView.findViewById(R.id.nameTV);
        fullNameTV = rootView.findViewById(R.id.fullNameTV);
        descriptionTV = rootView.findViewById(R.id.descriptionTV);
        languageTV = rootView.findViewById(R.id.languageTV);
        ownerTV = rootView.findViewById(R.id.ownerTV);
        watchersTV = rootView.findViewById(R.id.watchersTV);
        starsTV = rootView.findViewById(R.id.starsTV);
        branchesTV = rootView.findViewById(R.id.branchesTV);
        forksTV = rootView.findViewById(R.id.forksTV);
        createdAtTV = rootView.findViewById(R.id.createdAtTV);
        updatedAtTV = rootView.findViewById(R.id.updatedAtTV);

        // Rturn the root view
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Initialize sharedViewModel
        sharedViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SharedViewModel.class);

        // Observe the selected user repository
        sharedViewModel.getSelectedUserRepo().observe(getViewLifecycleOwner(), userRepo -> {
            // White space
            final String WHITE_SPACE = " ";

            // Update UI
            if (userRepo != null) {

                // Update the nameTV's text
                nameTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.name_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getName()));

                // Update the fullNameTV's text
                fullNameTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.full_name_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getFullName()));

                // Update the descriptionTV's text
                descriptionTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.description_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getDescription()));

                // Update the languageTV's text
                languageTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.language_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getLanguage()));

                // Update the ownerTV's text
                ownerTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.owner_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getUser().getName()));

                // Update the watchersTV's text
                watchersTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.watchers_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getWatchersCount()));

                // Update the starsTV's text
                starsTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.star_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getStarsCount()));

                // Update the branchesTV's text
                branchesTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.branches_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getBranchesCount()));

                // Update the forksTV's text
                forksTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.forks_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getForksCount()));

                // Update the createdAtTV's text
                createdAtTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.created_on_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getCreatedAt().substring(0, 10)));

                // Update the updatedAtTV's text
                updatedAtTV.setText(new StringBuilder()
                        .append(getResources().getString(R.string.last_update_on_text))
                        .append(WHITE_SPACE)
                        .append(userRepo.getUpdatedAt().substring(0, 10)));
            }
        });

    }

    @Override
    public void onPause() {
        //closing transition animations
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.fragment_open_scale, R.anim.fragment_close_translate);
        super.onPause();
    }
}
