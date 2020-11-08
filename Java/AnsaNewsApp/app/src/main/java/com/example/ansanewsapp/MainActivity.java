package com.example.ansanewsapp;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ansanewsapp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private static RecyclerViewAdapter adapter;
    private static RecyclerView recyclerView;
    private static View mainActivity;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainActivity = findViewById(R.id.mainActivity);
        context = MainActivity.this;
        recyclerView = findViewById(R.id.recyclerView);

        if (savedInstanceState == null) {
            db = Room.databaseBuilder(this, NoteDatabase.class, "realNote_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

            adapterNotifyChanges();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(noteList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        adapter = new RecyclerViewAdapter(this);
    }

    public static void adapterNotifyChanges() {
       // noteList = db.noteDao().getAll();
        recyclerView = mainActivity.findViewById(R.id.recyclerView);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(context);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}