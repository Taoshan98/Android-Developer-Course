package com.example.fragmenttoactivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReciveActivity extends AppCompatActivity {

    private String stringName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);

        stringName = getIntent().getStringExtra("stringName");

        TextView recivedText = findViewById(R.id.reciveText);
        recivedText.setText(stringName);

    }
}
