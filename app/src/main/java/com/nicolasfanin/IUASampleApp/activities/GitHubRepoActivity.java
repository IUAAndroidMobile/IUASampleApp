package com.nicolasfanin.IUASampleApp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.nicolasfanin.IUASampleApp.R;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GitHubRepoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guit_hub_repo);

    }

    private void handleHttpConnection() throws IOException {
        URL url = null;
        try {
            url = new URL("https://api.github.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

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

}
