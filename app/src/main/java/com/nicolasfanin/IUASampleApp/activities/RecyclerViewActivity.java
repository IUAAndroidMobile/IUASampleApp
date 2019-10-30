package com.nicolasfanin.IUASampleApp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nicolasfanin.IUASampleApp.data.dao.Color;
import com.nicolasfanin.IUASampleApp.database.ColorRepository;
import com.nicolasfanin.IUASampleApp.utils.ListAdapter;
import com.nicolasfanin.IUASampleApp.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements ListAdapter.RecyclerViewOnItemClickListener {

    private ArrayList<Color> colors;

    private ColorRepository colorRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        colorRepository = new ColorRepository(getApplication());

        //Obtengo la información que quiero mostrar en el adapter.
        initColors();

        // Encontrar el RecyclerView en el layout.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //Setear el LayoutManager para el RecyclerView.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Si quiero que el Recycler tenga un scroll horizontal debo reemplazar el Linear layout por el siguiente:
        // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        // El RecyclerView deberá tener su Adapter.
        recyclerView.setAdapter(new ListAdapter(colors, this));

        // Si quisiera de esta forma podemos agregar un elemento visual a cada item del recycler.
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @SuppressLint("ResourceType")
    private void initColors() {

        if (colorRepository.getAllColors().isEmpty()) {
            colorRepository.insert(new Color(getString(R.string.blue), getResources().getString(R.color.blue)));
            colorRepository.insert(new Color(getString(R.string.indigo), getResources().getString(R.color.indigo)));
            colorRepository.insert(new Color(getString(R.string.red), getResources().getString(R.color.red)));
            colorRepository.insert(new Color(getString(R.string.green), getResources().getString(R.color.green)));
            colorRepository.insert(new Color(getString(R.string.orange), getResources().getString(R.color.orange)));
            colorRepository.insert(new Color(getString(R.string.grey), getResources().getString(R.color.bluegrey)));
            colorRepository.insert(new Color(getString(R.string.amber), getResources().getString(R.color.teal)));
            colorRepository.insert(new Color(getString(R.string.deeppurple), getResources().getString(R.color.deeppurple)));
            colorRepository.insert(new Color(getString(R.string.bluegrey), getResources().getString(R.color.bluegrey)));
            colorRepository.insert(new Color(getString(R.string.yellow), getResources().getString(R.color.yellow)));
            colorRepository.insert(new Color(getString(R.string.cyan), getResources().getString(R.color.cyan)));
            colorRepository.insert(new Color(getString(R.string.brown), getResources().getString(R.color.brown)));
            colorRepository.insert(new Color(getString(R.string.teal), getResources().getString(R.color.teal)));
        }

        colors = (ArrayList<Color>) colorRepository.getAllColors();

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Su color es: " + colors.get(position).getName(), Toast.LENGTH_SHORT).show();

    }
}
