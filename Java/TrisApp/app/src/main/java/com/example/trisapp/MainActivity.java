package com.example.trisapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button mClickedSquare, mButtonRow1_1, mButtonRow1_2, mButtonRow1_3, mButtonRow2_1, mButtonRow2_2, mButtonRow2_3, mButtonRow3_1, mButtonRow3_2, mButtonRow3_3;
    private TextView mEndChallenge, mWinnerIs;
    private List<Button> mArrayClickedButtonsPlayer1 = new ArrayList<>();
    private List<Button> mArrayClickedButtonsPlayer2 = new ArrayList<>();
    private List<Button> mArrayAvailableButtons = new ArrayList<>();
    private String mNamePlayer1, mNamePlayer2, typeMatchSelected;
    private int currentPlayer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNamePlayer1 = getIntent().getStringExtra("namePlayer1");
        mNamePlayer2 = getIntent().getStringExtra("namePlayer2");
        typeMatchSelected = getIntent().getStringExtra("typeMatchSelected");

        mEndChallenge = findViewById(R.id.endChallengeText);
        mWinnerIs = findViewById(R.id.winnerText);

        mButtonRow1_1 = findViewById(R.id.buttonRow1_1);
        mButtonRow1_2 = findViewById(R.id.buttonRow1_2);
        mButtonRow1_3 = findViewById(R.id.buttonRow1_3);
        mButtonRow2_1 = findViewById(R.id.buttonRow2_1);
        mButtonRow2_2 = findViewById(R.id.buttonRow2_2);
        mButtonRow2_3 = findViewById(R.id.buttonRow2_3);
        mButtonRow3_1 = findViewById(R.id.buttonRow3_1);
        mButtonRow3_2 = findViewById(R.id.buttonRow3_2);
        mButtonRow3_3 = findViewById(R.id.buttonRow3_3);

        mArrayAvailableButtons.add(mButtonRow1_1);
        mArrayAvailableButtons.add(mButtonRow1_2);
        mArrayAvailableButtons.add(mButtonRow1_3);
        mArrayAvailableButtons.add(mButtonRow2_1);
        mArrayAvailableButtons.add(mButtonRow2_2);
        mArrayAvailableButtons.add(mButtonRow2_3);
        mArrayAvailableButtons.add(mButtonRow3_1);
        mArrayAvailableButtons.add(mButtonRow3_2);
        mArrayAvailableButtons.add(mButtonRow3_3);
    }

    public void onClickedSquare(View view) {

        /* Prendo l'id della risposta cliccata */
        int buttonId = view.getId();

        /* verifico se la risposta data è corretta o meno */
        mClickedSquare = findViewById(getResources().getIdentifier(String.valueOf(buttonId), "id", getPackageName()));
        startGame(mClickedSquare);
        checkWinner();

        if (currentPlayer == 2 && typeMatchSelected.equals("CPU")) {
            manageCPUMultiplayer();
        }
    }

    private void startGame(Button mClickedButton) {

        mArrayAvailableButtons.remove(mClickedButton);

        if (currentPlayer == 1) {
            mClickedButton.setText("X");
            mArrayClickedButtonsPlayer1.add(mClickedButton);
            mClickedButton.setBackgroundColor(Color.BLUE);
            currentPlayer = 2;
        } else {
            mClickedButton.setText("O");
            mArrayClickedButtonsPlayer2.add(mClickedButton);
            mClickedButton.setBackgroundColor(Color.RED);
            currentPlayer = 1;
        }

        mClickedButton.setTextColor(Color.WHITE);
        mClickedButton.setEnabled(false);
    }

    private void manageCPUMultiplayer() {
        if (mArrayAvailableButtons.size() != 0) {
            Log.d("random button", String.valueOf(mArrayAvailableButtons.size()));
            int randomPositions = new Random().nextInt(mArrayAvailableButtons.size());
            Button mRandomSquareClicked = mArrayAvailableButtons.get(randomPositions);
            mRandomSquareClicked.performClick();
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkWinner() {

        //Controllo vincita orizzontale riga 1
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow1_1) && mArrayClickedButtonsPlayer1.contains(mButtonRow1_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow1_3)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow1_1) && mArrayClickedButtonsPlayer2.contains(mButtonRow1_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow1_3)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita orizzontale riga 2
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow2_1) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_3)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow2_1) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_3)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita orizzontale riga 3
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow3_1) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_3)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow3_1) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_3)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita Verticale riga 1
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow1_1) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_1) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_1)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow1_1) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_1) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_1)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita Verticale riga 2
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow1_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_2)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow1_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_2)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita Verticale riga 3
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow1_3) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_3) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_3)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow1_3) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_3) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_3)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita Diagonale riga 1
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow1_1) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_3)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow1_1) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_3)) {
            setTextWinner(mNamePlayer2);
        }

        //Controllo vincita Diagonale riga 2
        if (mArrayClickedButtonsPlayer1.contains(mButtonRow1_3) && mArrayClickedButtonsPlayer1.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer1.contains(mButtonRow3_1)) {
            setTextWinner(mNamePlayer1);
        } else if (mArrayClickedButtonsPlayer2.contains(mButtonRow1_3) && mArrayClickedButtonsPlayer2.contains(mButtonRow2_2) && mArrayClickedButtonsPlayer2.contains(mButtonRow3_1)) {
            setTextWinner((mNamePlayer2));
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTextWinner(String winnerName) {
        mEndChallenge.setText("Sfida Terminata!");
        mWinnerIs.setText("Il Vincitore è " + (winnerName.equals("") ? "No Name" : winnerName));
        for (Button mItem : mArrayAvailableButtons) {
            mItem.setEnabled(false);
        }
    }
}