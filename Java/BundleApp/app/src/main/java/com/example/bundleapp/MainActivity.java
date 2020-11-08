package com.example.bundleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTime;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            i = 0;
        }else{
            i = savedInstanceState.getInt("KEY");
        }

        mTime = findViewById(R.id.time);

        new Thread(new Run()).start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY", i);
    }

    private class Run implements Runnable{

        @Override
        public void run() {

            while (true){
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTime.setText(String.valueOf(i));
                        i++;
                    }
                });
            }

        }


    }
}
