package com.nicolasfanin.IUASampleApp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.dao.Mail;
import com.nicolasfanin.IUASampleApp.database.MyDatabase;
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
        MyDatabase database = MyDatabase.getInstance(this);

        if (database.getMails().isEmpty()) {
            database.addMail(new Mail("a@b.com", "cc@dd.com", "Mail 1", "Contendio del correo 1", "18/9/2019"));
            database.addMail(new Mail("a@b.com", "ee@ff.com", "Mail 2", "Contendio del correo 2", "18/9/2019"));
            database.addMail(new Mail("a@b.com", "gg@hh.com", "Mail 3", "Contendio del correo 3", "18/9/2019"));
            database.addMail(new Mail("a@b.com", "ii@jj.com", "Mail 4", "Contendio del correo 4", "18/9/2019"));
        }
        mails = new ArrayList<Mail>(database.getMails());
    }

    //Implemento el/los métodos de la interfaz del listener.
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
