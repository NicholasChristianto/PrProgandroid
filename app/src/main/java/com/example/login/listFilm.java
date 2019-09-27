package com.example.login;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by Herdi_WORK on 15.09.16.
 */
public class listFilm extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_film);
        dataSet = new ArrayList<>();
        initDataset();

        rvView = findViewById(R.id.recycle_view_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(dataSet);
        rvView.setAdapter(adapter);

    }

    private void initDataset(){
        dataSet.add("Pertama");
        dataSet.add("Kedua");
        dataSet.add("Ketiga");
        dataSet.add("Keempat");


    }
}