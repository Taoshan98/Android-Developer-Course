package com.example.trisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectTypeMatch extends AppCompatActivity {

    private TextView player1Name, player2Name;
    private Button mCpuButton, mPersonButton, mGoGameButton;
    private String mTypeMatchSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_match);

        player1Name = findViewById(R.id.namePlayer1);
        player2Name = findViewById(R.id.namePlayer2);

        mCpuButton = findViewById(R.id.buttonCpu);
        mPersonButton = findViewById(R.id.buttonPerson);
        mGoGameButton = findViewById(R.id.goGameButton);

        player1Name.setVisibility(View.INVISIBLE);
        player2Name.setVisibility(View.INVISIBLE);
    }

    public void personClick(View view) {
        mTypeMatchSelected = "Person";
        mPersonButton.setBackgroundColor(Color.GRAY);
        mCpuButton.setBackgroundColor(Color.TRANSPARENT);
        player1Name.setVisibility(View.VISIBLE);
        player2Name.setVisibility(View.VISIBLE);
        mGoGameButton.setEnabled(true);
    }

    public void cpuClick(View view) {
        mTypeMatchSelected = "CPU";
        mCpuButton.setBackgroundColor(Color.GRAY);
        mPersonButton.setBackgroundColor(Color.TRANSPARENT);
        player1Name.setVisibility(View.VISIBLE);
        player2Name.setVisibility(View.INVISIBLE);
        mGoGameButton.setEnabled(true);
    }

    public void startGameClick(View view) {

        Bundle bund = new Bundle();

        bund.putString("namePlayer1", player1Name.getText().toString());
        bund.putString("namePlayer2", player2Name.getText().toString());
        bund.putString("typeMatchSelected", mTypeMatchSelected);

        Intent intent = new Intent();

        intent.putExtras(bund);

        intent.setComponent(new ComponentName(SelectTypeMatch.this, MainActivity.class));

        startActivity(intent);
    }
}
