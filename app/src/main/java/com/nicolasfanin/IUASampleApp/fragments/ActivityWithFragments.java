package com.nicolasfanin.IUASampleApp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.nicolasfanin.IUASampleApp.R;

public class ActivityWithFragments extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mail_list);
    }
}
