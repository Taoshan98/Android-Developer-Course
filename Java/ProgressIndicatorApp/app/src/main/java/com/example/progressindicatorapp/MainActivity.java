package com.example.progressindicatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private ProgressBar progressBarCircular;
    private Handler handler;
    private Runnable runnable;
    private Timer timer;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarHorizontal = findViewById(R.id.progress_horizontal);
        progressBarHorizontal.setProgress(0);
        progressBarHorizontal.setSecondaryProgress(0);
        progressBarHorizontal.setMax(100);

        progressBarCircular = findViewById(R.id.progress_circular);
        progressBarCircular.setProgress(0);
        progressBarCircular.setMax(100);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (progress <= 100){
                    progress += 1;
                    progressBarHorizontal.setProgress(progress);
                    progressBarHorizontal.setSecondaryProgress(progress + 10);

                    progressBarCircular.setProgress(progress);

                }else{
                    timer.cancel();
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1000, 30);



    }



}
