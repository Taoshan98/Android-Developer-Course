package com.example.giocomoltiplicazioniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String KEY = MainActivity.class.getName() + "KEY";
    private TextView mQuestion, mTimer, mResult;
    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4, mRestartGame;
    private int rightAnswer = 0, rightAnswerCounter = 0, totalQuestions = 0;
    private long mCurrentTimer = 30000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestion = findViewById(R.id.question);
        mTimer = findViewById(R.id.timer);
        mResult = findViewById(R.id.result);

        mAnswer1 = findViewById(R.id.answer1);
        mAnswer2 = findViewById(R.id.answer2);
        mAnswer3 = findViewById(R.id.answer3);
        mAnswer4 = findViewById(R.id.answer4);
        mRestartGame = findViewById(R.id.restartGame);

        mRestartGame.setVisibility(View.INVISIBLE);

        if (savedInstanceState != null){
            mCurrentTimer = savedInstanceState.getLong(KEY);
        }

        new CountDownTimer(mCurrentTimer, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                //cosa fare ogni secondo
                mCurrentTimer -= 1000;
                mTimer.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                mRestartGame.setVisibility(View.VISIBLE);
                int percentualeDiSuccesso = (100 * rightAnswerCounter) / (totalQuestions == 0 ? 1 : totalQuestions);
                //cosa fare quando è finito il tempo

                mResult.setText("Hai Azzeccato " + rightAnswerCounter + " su " + totalQuestions + "\n Successo " + percentualeDiSuccesso + "%");

                mAnswer1.setEnabled(false);
                mAnswer2.setEnabled(false);
                mAnswer3.setEnabled(false);
                mAnswer4.setEnabled(false);

                mTimer.setText("Tempo Scaduto!");

            }
        }.start();

        /* Genero la lista di risultati random */
        List<Integer> AnswerList = generateRandomNumber();

        /* dispongo i risultati nei tasti */
        setTextToButtons(AnswerList);


    }

    public void AnswerClicked(View view) {

        totalQuestions++;

        /* Prendo l'id della risposta cliccata */
        int buttonId = view.getId();

        /* verifico se la risposta data è corretta o meno */
        Button clickedAnswer = findViewById(getResources().getIdentifier(String.valueOf(buttonId), "id", getPackageName()));
        isRightAnswer(clickedAnswer);

        /* Genero la lista di risultati random */
        List<Integer> AnswerList = generateRandomNumber();

        /* dispongo i risultati nei tasti */
        setTextToButtons(AnswerList);

    }

    @SuppressLint("SetTextI18n")
    private List<Integer> generateRandomNumber() {

        List<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            int firstRandomNumber = new Random().nextInt(10) + 1;
            int SecondRandomNumber = new Random().nextInt(10) + 1;

            if (!numberList.contains(firstRandomNumber * SecondRandomNumber)){
                numberList.add(firstRandomNumber * SecondRandomNumber);
            }else{
                i--;
            }

            if (i == 0) {
                mQuestion.setText("Quanto fa " + firstRandomNumber + " x " + SecondRandomNumber + " ?");
                rightAnswer = firstRandomNumber * SecondRandomNumber;
            }
        }
        return numberList;
    }

    private void isRightAnswer(Button clickedAnswer) {

        if (clickedAnswer.getText().toString().equals(String.valueOf(rightAnswer))) {
            rightAnswerCounter++;
        }
    }

    private void setTextToButtons(List<Integer> numberList) {

        /* Creo questa lista con dei valori da 1 a 4 successivamente li mischio così
         * da rendere imprevedibile dove si trova la risposta esatta
         */
        ArrayList<Integer> randomPosition = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Collections.shuffle(randomPosition);

        mAnswer1.setText(String.valueOf(numberList.get(randomPosition.get(0))));
        mAnswer2.setText(String.valueOf(numberList.get(randomPosition.get(1))));
        mAnswer3.setText(String.valueOf(numberList.get(randomPosition.get(2))));
        mAnswer4.setText(String.valueOf(numberList.get(randomPosition.get(3))));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY, mCurrentTimer);
    }

    public void restartGameClick(View view) {
        Intent intent = getIntent();
        finish();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
