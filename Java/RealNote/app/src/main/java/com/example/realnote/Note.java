package com.example.realnote;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int noteId;

    @ColumnInfo(name = "noteTitle")
    private String noteTitle;

    @ColumnInfo(name = "noteContent")
    private String noteContent;

    @ColumnInfo(name = "noteDate")
    private String noteDate;

    public Note (){}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}