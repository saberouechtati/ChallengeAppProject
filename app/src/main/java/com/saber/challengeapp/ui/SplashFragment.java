package com.saber.challengeapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.saber.challengeappproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {

    // The Required empty public constructor
    public SplashFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SplashFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize the root view, inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        // Initialize the UI
        final TextView appName = rootView.findViewById(R.id.app_name);
        final TextView appVersion = rootView.findViewById(R.id.app_version);
        final ImageView appLogo = rootView.findViewById(R.id.app_logo);

        // Initialize the animations
        final Animation appNameBounceAnimation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.text_bounce);
        final Animation appVersionBounceAnimation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.version_bounce);
        final Animation appLogoBounceAnimation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.logo_bounce);

        // Start the animations
        appName.startAnimation(appNameBounceAnimation);
        appVersion.startAnimation(appVersionBounceAnimation);
        appLogo.startAnimation(appLogoBounceAnimation);

        // Returns the rootView
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        //closing transition animations
        getActivity().overridePendingTransition(R.anim.fragment_open_scale, R.anim.fragment_close_translate);
        super.onPause();
    }
}
