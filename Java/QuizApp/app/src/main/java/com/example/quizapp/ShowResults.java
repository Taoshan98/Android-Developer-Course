package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ShowResults extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
        TextView resultView = findViewById(R.id.resultView);

        int mRightAnswerCounter = getIntent().getIntExtra("rightAnswerCounter", 0);
        int mTotalAnswerCounter = getIntent().getIntExtra("totalAnswerCounter", 0);
        resultView.setText("Hai risposto correttamente a:\n" + mRightAnswerCounter + " su " + mTotalAnswerCounter);
    }
}



