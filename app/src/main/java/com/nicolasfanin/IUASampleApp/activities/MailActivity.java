package com.nicolasfanin.IUASampleApp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.Mail;
import com.nicolasfanin.IUASampleApp.fragments.MailListFragment;

import java.util.ArrayList;

import static com.nicolasfanin.IUASampleApp.utils.Constants.MAIL_LIST;

public class MailActivity extends AppCompatActivity {

    private ArrayList<Mail> mails;

    //Declaro el Fragment Transaction.
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        initMails();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        navigateToMailListFragment();
    }

    private void navigateToMailListFragment() {
        //Crear el fragment y enviar la data.
        Fragment mailListFragment = new MailListFragment();

        Bundle bundle = new Bundle();
        //Agrego el parcelabe como argument al bundle.
        bundle.putParcelableArrayList(MAIL_LIST, mails);
        mailListFragment.setArguments(bundle);

        //Inicio el fragment.
        fragmentTransaction.replace(R.id.mail_container, mailListFragment).commit();
    }

    //Generate data
    private void initMails() {
        mails = new ArrayList<Mail>();

        mails.add(new Mail("a@b.com", "c@d.com", "Mail 1", "Contendio del correo", "18/9/2019"));
        mails.add(new Mail("a@b.com", "c@d.com", "Mail 2", "Contendio del correo", "18/9/2019"));
        mails.add(new Mail("a@b.com", "c@d.com", "Mail 3", "Contendio del correo", "18/9/2019"));
        mails.add(new Mail("a@b.com", "c@d.com", "Mail 4", "Contendio del correo", "18/9/2019"));
    }
}
