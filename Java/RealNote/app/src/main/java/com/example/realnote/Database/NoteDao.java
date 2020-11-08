package com.example.realnote.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.realnote.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note ORDER BY noteId DESC")
    List<Note> getAll();

    @Insert
    void insertNote(Note... notes);

    @Delete
    void deleteNote(Note note);

}
