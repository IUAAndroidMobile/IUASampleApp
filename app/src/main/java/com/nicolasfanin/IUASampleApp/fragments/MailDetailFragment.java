package com.nicolasfanin.IUASampleApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.dao.Mail;

import static com.nicolasfanin.IUASampleApp.utils.Constants.MAIL_ITEM_DETAIL;

public class MailDetailFragment extends Fragment {

    private Mail mail;

    private TextView textViewFrom;
    private TextView textViewDate;
    private TextView textViewSubject;
    private TextView textViewDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail_detail, container, false);

        mail = getArguments().getParcelable(MAIL_ITEM_DETAIL);

        textViewFrom = view.findViewById(R.id.from_text_view);
        textViewDate = view.findViewById(R.id.date_text_view);
        textViewSubject = view.findViewById(R.id.subject_text_view);
        textViewDescription = view.findViewById(R.id.description_text_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mail != null) {
            textViewFrom.setText(mail.getFrom());
            textViewDate.setText(mail.getDate());
            textViewSubject.setText(mail.getSubject());
            textViewDescription.setText(mail.getContent());
        }

    }
}
