package com.example.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.song);
        mediaPlayer.start();
    }

    public void playBtn(View view) {
        mediaPlayer.start();
    }
    public void pauseBtn(View view) {
        mediaPlayer.pause();
    }


}
