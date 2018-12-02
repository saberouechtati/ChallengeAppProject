package com.saber.challengeapp.ui;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.saber.challengeapp.viewmodels.SharedViewModel;
import com.saber.challengeappproject.R;

import java.util.Objects;

import static com.saber.challengeapp.utils.Destinations.Splash_UserRepoList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // The shared ViewModel
    private SharedViewModel sharedViewModel;

    // The Search/ Retrieve button
    AppCompatButton retrieveBtn;

    // The username EditText
    EditText usernameET;

    // Required empty public constructor
    public SearchFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment UserRepoDetailFragment.
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize the Retrieve button
        retrieveBtn = rootView.findViewById(R.id.retrieve_button);

        //Initialize the username EditText
        usernameET = rootView.findViewById(R.id.usernameET);

        // Sets Retrieve button onClickListener
        retrieveBtn.setOnClickListener(view -> {
            hideSoftKeyboard();
            String username = usernameET.getText().toString();
            sharedViewModel.updateUsername(username);
            sharedViewModel.updateUserRepoList();
            sharedViewModel.navigate(Splash_UserRepoList);
        });
        return rootView;
    }

    /**
     * Hides soft keybord if it is open
     */
    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getActivity().getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Initialize sharedViewModel
        sharedViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SharedViewModel.class);
        sharedViewModel.getUsername(this.getContext()).observe(getViewLifecycleOwner(), username -> {
            // Loads the GitHub user repository list
            if (username != null) {
                usernameET.setText(username);
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
