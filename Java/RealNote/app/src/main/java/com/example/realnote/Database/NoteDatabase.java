package com.example.realnote.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.realnote.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
