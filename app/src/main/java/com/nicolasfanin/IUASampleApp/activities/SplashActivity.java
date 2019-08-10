package com.nicolasfanin.IUASampleApp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.nicolasfanin.IUASampleApp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
}
