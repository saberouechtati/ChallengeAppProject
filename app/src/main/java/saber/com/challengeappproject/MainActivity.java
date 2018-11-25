package saber.com.challengeappproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import saber.com.challengeappproject.ui.repositorylist.RepositoryListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RepositoryListFragment.newInstance())
                    .commitNow();
        }
    }
}
