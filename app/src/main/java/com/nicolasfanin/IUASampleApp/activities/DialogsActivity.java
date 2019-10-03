package com.nicolasfanin.IUASampleApp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.preferences.PreferencesUtils;

public class DialogsActivity extends AppCompatActivity {

    private PreferencesUtils preferences;

    private CardView userNameCardView;
    private CardView cardViewAlertDialog;
    private TextView userNameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        preferences = new PreferencesUtils(getBaseContext());

        userNameCardView = findViewById(R.id.user_name_card_view);
        userNameTextView = findViewById(R.id.user_name_text_view);

        cardViewAlertDialog = findViewById(R.id.card_view_alert_dialog);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mi propio titulo!");

        cardViewAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        userNameCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUserName();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preferences.getUserName() != "") {
            userNameTextView.setText(preferences.getUserName());
        }
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("UserName");
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.input_text_dialog_layout, null, false);

        final EditText userEditText = (EditText) viewInflated.findViewById(R.id.user_edit_text);
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String user = userEditText.getText().toString();
                if (!user.equals("")) {
                    if (preferences != null) {
                        preferences.setUserName(user);
                    }
                    userNameTextView.setText(user);
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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
