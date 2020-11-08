package com.example.imageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.bart);
    }


    public void nextImage(View view){

        if (counter == 0){
            image.setImageResource(R.drawable.krusty);
        }else if (counter == 1){
            image.setImageResource(R.drawable.homer);
        }else {
            image.setImageResource(R.drawable.bart);
            counter = -1;
        }

        counter++;

    }

}
