package com.nicolasfanin.IUASampleApp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.CreditCard;

import static com.nicolasfanin.IUASampleApp.utils.Constants.CREDIT_CARD;

public class CreditCardActivity extends AppCompatActivity {

    private CreditCard creditCard;

    private TextView creditCardNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_parcelable);

        creditCardNumber = findViewById(R.id.credit_card_text_view);

        savedInstanceState = getIntent().getExtras();
        if(savedInstanceState != null) {
            creditCard = (CreditCard) savedInstanceState.getParcelable(CREDIT_CARD);
            creditCardNumber.setText(creditCard.getNumero());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();
        setResult(RESULT_OK);
    }
}
