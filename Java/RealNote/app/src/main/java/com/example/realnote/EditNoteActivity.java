package com.example.realnote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import com.example.realnote.Database.NoteDatabase;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class EditNoteActivity extends AppCompatActivity {

    private static final String TAG = EditNoteActivity.class.getName();
    private static final String ACTION_KEY = TAG + ".action.key";
    private static final String NOTE_KEY = TAG + ".note.key";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private EditText noteTitle;
    private LinedEditText noteContent;
    private NoteDatabase db;
    private char typeAction;
    private Note noteInfo;

    /**
     * A custom EditText that draws lines between each line of text that is displayed.
     */
    public static class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
        private Rect mRect;
        private Paint mPaint;
        // we need this constructor for LayoutInflater
        @SuppressLint("ResourceAsColor")
        public LinedEditText(Context context, AttributeSet attrs) {
            super(context, attrs);

            mRect = new Rect();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(R.color.edit_note_line);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int count = getLineCount();
            Rect r = mRect;
            Paint paint = mPaint;
            for (int i = 0; i < count; i++) {
                int baseline = getLineBounds(i, r);
                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
            }
            super.onDraw(canvas);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"CutPasteId", "ResourceAsColor", "SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteInfo = (Note) getIntent().getSerializableExtra(NOTE_KEY);

        db = Room.databaseBuilder(this, NoteDatabase.class, "realNote_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        noteTitle = findViewById(R.id.noteTitle);

        noteContent = findViewById(R.id.noteContent);
        TextView noteLastEdit = findViewById(R.id.noteLastEdit);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        //Imposto il focus sulla edittext della nota
        noteContent.requestFocus();

        typeAction = getIntent().getCharExtra(ACTION_KEY, 'x');

        assert noteInfo != null;
        //Imposto il titolo in rasparente in modo che quando si accorcia la toolbar diventa bianco
        updateToolbarTitle();
        if (typeAction == 'e') {
            noteTitle.setText(noteInfo.getNoteTitle());
            noteContent.setText(noteInfo.getNoteContent());
            noteLastEdit.setText(noteInfo.getNoteDate());
        } else {
            noteLastEdit.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveClick(View view) {

        String noteTitleString =  (noteTitle.getText().toString().trim().equals("") ? "No Title" : noteTitle.getText().toString().trim());
        String noteContentString = noteContent.getText().toString().trim();

        if (!noteContentString.isEmpty()) {

            if (typeAction == 'a') {
                //Aggiungi al DB
                db.noteDao().insertNote(new Note(noteTitleString, noteContentString));
            } else {
                //Modifica DB
                db.noteDao().deleteNote(noteInfo);
                db.noteDao().insertNote(new Note(noteTitleString, noteContentString));
            }

            Toast.makeText(EditNoteActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
            goToHome();

        } else {
            Toast.makeText(EditNoteActivity.this, "Note can't be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToHome(){
        MainActivity.adapterNotifyChanges();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateToolbarTitle() {
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        noteTitle.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                collapsingToolbarLayout.setTitle(noteTitle.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_note, menu);
        return true;
    }

    public static Intent getEditIntent(Context context, char typeAction, Note note) {
        Intent intent = new Intent(context, EditNoteActivity.class);
        intent.putExtra(ACTION_KEY, typeAction);
        intent.putExtra(NOTE_KEY, note);
        return intent;
    }

    public static Intent getEditIntent(Context context, char typeAction) {
        Intent intent = new Intent(context, EditNoteActivity.class);
        intent.putExtra(ACTION_KEY, typeAction);
        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        View view = findViewById(android.R.id.content).getRootView();

        switch (itemId) {
            case R.id.saveAction:
                //faccio un trigger sul tasto salva
                saveClick(view);
                break;
            case R.id.deleteAction:
                db.noteDao().deleteNote(noteInfo);
                Toast.makeText(EditNoteActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                goToHome();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}