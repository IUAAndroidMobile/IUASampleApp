package com.nicolasfanin.IUASampleApp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.nicolasfanin.IUASampleApp.R;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GuitHubRepoActivity extends AppCompatActivity {

    public GuitHubRepoActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guit_hub_repo);

    }

    private void handleHttpConnection() {
        URL url = null;
        try {
            url = new URL("https://api.github.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); //1
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo(); //2
        return networkInfo != null && networkInfo.isConnected(); //3
    }

}
