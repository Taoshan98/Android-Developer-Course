package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView timerText;
    private MediaPlayer mediaPlayer;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        timerText = findViewById(R.id.timerText);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimerText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private int updateTimerText(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = String.valueOf((seconds < 10 ? "0" + seconds : seconds));
        String minutesString = String.valueOf((minutes < 10 ? "0" + minutes : minutes));

        timerText.setText(minutesString + ":" + secondsString);

        return seconds;
    }

    public void startTimer(View view) {

        seekBar.setEnabled(false);

        countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = updateTimerText((int) millisUntilFinished / 1000);
                seekBar.setProgress(secondsLeft);
            }

            @Override
            public void onFinish() {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sveglia);
                mediaPlayer.start();
                resetTimer();
            }
        };

        countDownTimer.start();
    }

    @SuppressLint("SetTextI18n")
    private void resetTimer() {
        timerText.setText("00:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        seekBar.setEnabled(true);
    }
}
