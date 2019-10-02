package com.nicolasfanin.IUASampleApp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.preferences.PreferencesUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Inicializar SharedPrefernces.
        PreferencesUtils prefs = new PreferencesUtils(getBaseContext());

        Button initButton = findViewById(R.id.init_button);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SplashActivity.this, MyMainActivity.class);
                startActivity(mainIntent);
            }
        });

        //Trabajar con arrays!
        getResources().getDrawable(R.drawable.logo);
        String[] miArrayString = getResources().getStringArray(R.array.mi_array);
        setTitle(miArrayString[0]);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_item:
                Toast.makeText(this, "Agregar nuevo item seleccionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.user_item:
                Toast.makeText(this, "Usuario item seleccionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
