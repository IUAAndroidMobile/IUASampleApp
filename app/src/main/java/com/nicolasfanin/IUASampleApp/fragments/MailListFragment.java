package com.nicolasfanin.IUASampleApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolasfanin.IUASampleApp.data.Mail;

import java.util.ArrayList;

import static com.nicolasfanin.IUASampleApp.utils.Constants.MAIL_LIST;

public class MailListFragment extends Fragment {

    private ArrayList<Mail> mails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mails = getArguments().getParcelableArrayList(MAIL_LIST);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
