package com.nicolasfanin.IUASampleApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.Mail;

import java.util.ArrayList;

import static com.nicolasfanin.IUASampleApp.R.layout.item_email;
import static com.nicolasfanin.IUASampleApp.utils.Constants.MAIL_LIST;

public class MailListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<Mail> mails;
    private ListView listView;

    private MailListFragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail_list, container, false);
        listView = (ListView) view.findViewById(R.id.mail_list);
        mails = getArguments().getParcelableArrayList(MAIL_LIST);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtengo una referencia del listener, que será la activity que implemente MailListFragmentListener.
        listener = (MailListFragmentListener) getActivity();

        String[] entities = new String[mails.size()];
        for (int i = 0; i < mails.size(); i++) {
            entities[i] = "De: " + mails.get(i).getFrom() + " \n " + mails.get(i).getSubject();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), item_email, entities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

        //Llamo al método del listener y envio el mail que quier que se detalle.
        listener.navigateToMailDetailsScreen(mails.get(position));
    }

    //Agrego una interfaz para manejar el listener:
    public interface MailListFragmentListener {

        void navigateToMailDetailsScreen(Mail mail);

    }
}
