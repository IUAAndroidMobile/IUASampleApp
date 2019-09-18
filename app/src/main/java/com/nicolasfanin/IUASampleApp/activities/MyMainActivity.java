package com.nicolasfanin.IUASampleApp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.CreditCard;

import static com.nicolasfanin.IUASampleApp.utils.Constants.CREDIT_CARD;

public class MyMainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;  // Request code que me permite hacer multiples startActivityForResult.

    private Button navigatToSplashButton;
    private Button layoutsActivityButton;
    private Button selectContactButton;
    private Button listActivityButton;
    private Button activityWithFragmentsButton;
    private Button sendCreditCardButton;
    private Button mailListButton;

    private TextInputEditText creditCardNumberTextView;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main_activity);

        // Ocultar la barra de acción.
        getSupportActionBar().hide();

        navigatToSplashButton = findViewById(R.id.navigate_to_splash_button);
        layoutsActivityButton = findViewById(R.id.activity_layouts_button);
        selectContactButton = findViewById(R.id.select_contact_button);
        listActivityButton = findViewById(R.id.list_activity_button);
        activityWithFragmentsButton = findViewById(R.id.activity_with_fragments);
        creditCardNumberTextView = (TextInputEditText) findViewById(R.id.credit_card_input_number);
        sendCreditCardButton = findViewById(R.id.send_credit_card_button);
        mailListButton = findViewById(R.id.activity_mail_list);

        navigatToSplashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = findViewById(R.id.my_main_layout);
                Snackbar snackbar = Snackbar
                        .make(linearLayout, getString(R.string.hola) + creditCardNumberTextView.getText().toString(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        layoutsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayoutsActivity();
            }
        });
        selectContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickContact();
            }
        });
        listActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToListActivity();
            }
        });
        activityWithFragmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivityWithFragments();
            }
        });
        sendCreditCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCreditCardActivity(new CreditCard(null, creditCardNumberTextView.getText().toString(), ""));
            }
        });
        mailListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivityWithMailList();
            }
        });


    }

    private void navigateToLayoutsActivity() {
        Intent listIntent = new Intent(MyMainActivity.this, LayoutsActivity.class);
        startActivity(listIntent);
    }

    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(Phone.CONTENT_TYPE); // Mostrar solo los contactos del usuario con sus nombres
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Verificamos que se corresponda con nuestra solicitud mediante
        // el codigo que nosotros mismos creamos
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // el argumento data contiene información del contacto seleccionado
                Uri selectedContact = data.getData();
                String[] projection = {Phone.NUMBER};
                Cursor cursor = getContentResolver().query(selectedContact, projection, null, null, null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(Phone.NUMBER);
                String number = cursor.getString(column);
                Toast.makeText(this, "Telefono Seleccionado: " + number, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void navigateToListActivity() {
        Intent listIntent = new Intent(MyMainActivity.this, RecyclerViewActivity.class);
        startActivity(listIntent);
    }

    private void navigateToActivityWithFragments() {
        startActivity(new Intent(MyMainActivity.this, ActivityWithFragments.class));
    }

    private void navigateToCreditCardActivity(CreditCard creditCard) {
        Intent listIntent = new Intent(MyMainActivity.this, CreditCardActivity.class);
        listIntent.putExtra(CREDIT_CARD, creditCard);
        startActivity(listIntent);
    }

    private void navigateToActivityWithMailList() {
        Intent mailIntent = new Intent(MyMainActivity.this, MailActivity.class);
        startActivity(mailIntent);
    }
}
