package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private List<ModelData> usersData;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        usersData = new ArrayList<>();

        usersData.add(new ModelData("Arturo Ride", R.drawable.a1));
        usersData.add(new ModelData("Arturo Si Dispera", R.drawable.a2));
        usersData.add(new ModelData("Paesaggio", R.drawable.a3));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(usersData, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true); //Le card view sono tutte delle stesse dimensioni e ci permette  di risparmiare memoria facendo entrare un tot di card in una schermata

        swipeRefrehLayout();
    }


    private void notifyOnDataChanged(){
        usersData.clear();
        usersData = addElement();
        recyclerView.removeAllViews();
        adapter = new RecyclerViewAdapter(usersData, this);
        recyclerView.setAdapter(adapter);
    }

    private List<ModelData> addElement() {
        List<ModelData> modelData = new ArrayList<>();

        modelData.add(new ModelData("Arturo Ride", R.drawable.a1));
        modelData.add(new ModelData("Arturo Si Dispera", R.drawable.a2));
        modelData.add(new ModelData("Paesaggio", R.drawable.a3));

        return modelData;
    }

    //Consente facendo un swipe refresh di aggiornare i dati del recycler
    private void swipeRefrehLayout(){
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * Viene richiamato al momento dello swipe
     */
    @Override
    public void onRefresh() {
        notifyOnDataChanged();
        refreshLayout.setRefreshing(false);
    }
}
