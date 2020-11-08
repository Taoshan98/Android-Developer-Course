package com.example.intentimplicitoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toSecondActivity(View view) {
        startIntent();
    }

    private void startIntent(){
        Intent intent = new Intent();
        intent.setAction("com.example.intentimplicitoapp.startsecondactivity");
        startActivity(intent);
    }
}
