package com.example.realnote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.realnote.Database.NoteDatabase;
import com.example.realnote.Utils.Utils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class DetailsNoteActivity extends AppCompatActivity {

    private static final String TAG = DetailsNoteActivity.class.getName();
    private static final String NOTE_KEY = TAG + ".key.note";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Note noteInfo;
    private NoteDatabase db;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        db = Room.databaseBuilder(this, NoteDatabase.class, "realNote_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        noteInfo = (Note) getIntent().getSerializableExtra(NOTE_KEY);

        TextView detailsNoteTitle = findViewById(R.id.detailsNoteTitle);
        TextView detailsNoteLastEdit = findViewById(R.id.detailsNoteLastEdit);
        TextView detailsNoteContent = findViewById(R.id.detailsNoteContent);

        detailsNoteTitle.setText(noteInfo.getNoteTitle());
        detailsNoteContent.setText(noteInfo.getNoteContent());
        detailsNoteLastEdit.setText(noteInfo.getNoteDate());
        collapsingToolbarLayout = findViewById(R.id.details_toolbar_layout);

        assert noteInfo != null;
        updateToolbarTitle();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        detailsNoteContent.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(DetailsNoteActivity.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    startActivity(EditNoteActivity.getEditIntent(DetailsNoteActivity.this, 'e', noteInfo));
                    return super.onDoubleTap(e);
                }
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onSingleTapConfirmed(MotionEvent event) {
                    return false;
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    public void editClick(View view) {
        startActivity(EditNoteActivity.getEditIntent(DetailsNoteActivity.this, 'e', noteInfo));
    }

    private void updateToolbarTitle() {
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setTitle(noteInfo.getNoteTitle().trim());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_note, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        View view = findViewById(android.R.id.content).getRootView();

        switch (itemId) {
            case R.id.editAction:
                //faccio un trigger sul tasto salva
                editClick(view);
                break;
            case R.id.deleteAction:
                showAlert("Delete", "do you really want to delete the note?");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.noteDao().deleteNote(noteInfo);
                Toast.makeText(DetailsNoteActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                MainActivity.adapterNotifyChanges();
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

    }

    public static Intent getDetailsIntent(Context context, Note note) {
        Intent intent = new Intent(context, DetailsNoteActivity.class);
        intent.putExtra(NOTE_KEY, note);
        return intent;
    }
}