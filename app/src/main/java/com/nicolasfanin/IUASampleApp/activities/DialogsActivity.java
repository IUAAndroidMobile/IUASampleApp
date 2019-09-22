package com.nicolasfanin.IUASampleApp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;

import com.nicolasfanin.IUASampleApp.R;

public class DialogsActivity extends AppCompatActivity {

    private CardView cardViewAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        cardViewAlertDialog = findViewById(R.id.card_view_alert_dialog);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mi propio titulo!");

        //actionBar.setHideOnContentScrollEnabled(true);

        cardViewAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Agregar que queremos que occurra si se presiona el Positive Button.
                    }
                })
                .setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Agregar que queremos que ocurra si se presiona el Negative Button.
                    }
                })
                .setTitle("Atención!")
                .create();
        builder.show();
    }
}
