package com.nicolasfanin.IUASampleApp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nicolasfanin.IUASampleApp.R;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GitHubRepoActivity extends AppCompatActivity {

    private EditText githubEditText;
    private Button searchButton;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                        repos = new DownloadInfoTask().execute(githubEditText.getText().toString()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contentTextView.setText(repos);
                }
            }
        });
    }

    private InputStream handleHttpConnection(String user) throws IOException {
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

    // Método que chequea si hay conexión a Internet.
    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); //1
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo(); //2
        return networkInfo != null && networkInfo.isConnected(); //3
    }

    private String convertStreamToString(InputStream inputStream) {

        // Agregamos la respuesta en el buffer para leerla
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // Creamos un constructor de String.
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) { // Leemos cada línea hasta la última.
                stringBuilder.append(line).append('\n');        // Agregamos cada línea y un espacio nuevo.
            }
        } catch (IOException e) { // Capturamos una posible excepción que pueda ocurrir.
            e.printStackTrace();    // Imprimimos la excepción en consola para saber qué pasó.
        }

        return stringBuilder.toString();
    }

    //AsyncTask
    private class DownloadInfoTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return convertStreamToString(handleHttpConnection(urls[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
