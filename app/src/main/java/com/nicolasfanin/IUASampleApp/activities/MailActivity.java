package com.nicolasfanin.IUASampleApp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.Mail;
import com.nicolasfanin.IUASampleApp.fragments.MailDetailFragment;
import com.nicolasfanin.IUASampleApp.fragments.MailListFragment;

import java.util.ArrayList;

import static com.nicolasfanin.IUASampleApp.utils.Constants.MAIL_ITEM_DETAIL;
import static com.nicolasfanin.IUASampleApp.utils.Constants.MAIL_LIST;

public class MailActivity extends AppCompatActivity implements MailListFragment.MailListFragmentListener {

    private ArrayList<Mail> mails;

    //Declaro el Fragment Transaction.
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        initMails();

        navigateToMailListFragment();
    }

    private void navigateToMailListFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

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

    @Override
    public void navigateToMailDetailsScreen(Mail mail) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Fragment mailDetailFragment = new MailDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MAIL_ITEM_DETAIL, mail);
        mailDetailFragment.setArguments(bundle);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.mail_container, mailDetailFragment).commit();
    }
}
