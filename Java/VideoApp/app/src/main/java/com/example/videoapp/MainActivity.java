package com.example.videoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pathVideo = "android.resource://" + MainActivity.this.getPackageName() + "/" + R.raw.video;

        video = findViewById(R.id.videoView);
        video.setVideoPath(pathVideo);
        MediaController mediaController = new MediaController(MainActivity.this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.start();


    }
}
