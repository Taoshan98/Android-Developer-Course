package com.example.realnote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.realnote.Database.NoteDatabase;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    private static RecyclerViewAdapter adapter;
    private static NoteDatabase db;
    private static List<Note> noteList;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static View mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    public static void adapterNotifyChanges() {
        noteList = db.noteDao().getAll();
        recyclerView = mainActivity.findViewById(R.id.recyclerView);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(noteList, context);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addNewNote(View view) {
        startActivity(EditNoteActivity.getEditIntent(MainActivity.this, 'a'));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}