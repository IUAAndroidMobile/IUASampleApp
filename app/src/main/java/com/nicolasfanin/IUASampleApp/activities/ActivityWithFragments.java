package com.nicolasfanin.IUASampleApp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.fragments.OneFragment;

public class ActivityWithFragments extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragments);

        //Setear un fragment en forma dinámica

        //1. Obtener una referencia del fragment Manager.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //2. Iniciar un fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //3. Agregar el fragment al contenedor.
        fragmentTransaction.replace(R.id.dinamic_fragment, new OneFragment());

        //fragmentTransaction.addToBackStack(null);

        //4. Hacer un commit de la transacción
        fragmentTransaction.commit();
    }


}
