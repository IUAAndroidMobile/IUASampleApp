package com.nicolasfanin.IUASampleApp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.dto.GitHubRepo;
import com.nicolasfanin.IUASampleApp.fragments.GitHubRepoListFragment;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.nicolasfanin.IUASampleApp.utils.Constants.GIT_HUB_REPO;

public class GitHubRepoWithGsonActivity extends AppCompatActivity {

    private EditText githubEditText;
    private Button searchButton;
    private TextView contentTextView;

    private static Gson gson;

    private ArrayList<GitHubRepo> list;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_git_hub_repo);

        githubEditText = findViewById(R.id.github_edit_text);
        searchButton = findViewById(R.id.search_button);
        contentTextView = findViewById(R.id.content_text_view);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String repos = "";
                if (isNetworkConnected()) {
                    try {
                        list = new ArrayList<>();
                        list.addAll(new DownloadInfoTask().execute(githubEditText.getText().toString()).get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    contentTextView.setText(repos);
                }
            }
        });

    }

    private void chargeGitHubRepoFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Fragment gitHubRepoListFragment = new GitHubRepoListFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(GIT_HUB_REPO, list);
        gitHubRepoListFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.result_fragment, gitHubRepoListFragment).commit();
    }

    // Método que chequea si hay conexión a Internet.
    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private List<GitHubRepo> getListFromService(String user) throws IOException {

        InputStream source = retrieveStream(user);

        Reader reader = new InputStreamReader(source);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        List<GitHubRepo> gitList = Arrays.asList(gson.fromJson(reader, GitHubRepo[].class));

        return gitList;
    }

    private InputStream retrieveStream(String user) throws IOException {
        URL url = null;
        try {
            url = new URL("https://api.github.com/users/" + user + "/repos");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return new BufferedInputStream(connection.getInputStream());
    }

    private class DownloadInfoTask extends AsyncTask<String, Integer, List<GitHubRepo>> {

        @Override
        protected List<GitHubRepo> doInBackground(String... strings) {
            try {
                return getListFromService(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<GitHubRepo> gitHubRepoList) {
            super.onPostExecute(gitHubRepoList);
            chargeGitHubRepoFragment();
        }
    }
}
