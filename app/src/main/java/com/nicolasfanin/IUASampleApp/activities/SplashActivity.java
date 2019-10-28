package com.nicolasfanin.IUASampleApp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.User;
import com.nicolasfanin.IUASampleApp.database.MyDatabase;
import com.nicolasfanin.IUASampleApp.preferences.PreferencesUtils;

public class SplashActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText userPassEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Inicializar SharedPrefernces.
        PreferencesUtils prefs = new PreferencesUtils(getBaseContext());

        userNameEditText = findViewById(R.id.user_name_edit_text);
        userPassEditText = findViewById(R.id.user_pass_edit_text);

        Button initButton = findViewById(R.id.init_button);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLogin(userNameEditText.getText().toString(), userPassEditText.getText().toString());
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

    private void makeLogin(final String user, final String pass) {
        if (user.isEmpty() || pass.isEmpty()) {
            return;
        }

        final MyDatabase database = MyDatabase.getInstance(this);
        long id = database.checkUser(user, pass);
        final Intent mainIntent = new Intent(SplashActivity.this, MyMainActivity.class);

        if (id == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Atención!");
            builder.setMessage("El usuario no existe, desea crearlo?");
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    long userId = database.addUser(new User(user, "", pass));
                    startActivity(mainIntent);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
        } else {
            Toast.makeText(this, "Login Exitoso, su ID: " + id, Toast.LENGTH_SHORT).show();
            startActivity(mainIntent);
        }
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
            case R.id.settings_item:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
