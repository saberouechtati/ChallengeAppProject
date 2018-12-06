package com.saber.challengeapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saber.challengeappproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // The GitHub login button
    AppCompatButton gitHubLoginButton;

    // Required empty public constructor
    public LoginFragment() {}

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment UserRepoDetailFragment.
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize the GitHub login button
        gitHubLoginButton = rootView.findViewById(R.id.github_login_button);

        // Sets Retrieve button onClickListener
        gitHubLoginButton.setOnClickListener(view -> {
            String url = new StringBuilder()
                .append(getString(R.string.gitHubOauthUrl))
                .append("?client_id=")
                .append(getString(R.string.githubClientId))
                .append("&scope=repo&redirect_url=")
                .append(getString(R.string.redirectUrl))
                .toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        return rootView;
    }
}
