package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4, mGoNext, clickedAnswer;
    private TextView mQuestionText;
    Quiz[] quizArray;
    private static int currentQuestionIndex = 0, mRightAnswerCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionText = findViewById(R.id.questionText);
        mAnswer1 = findViewById(R.id.answer1);
        mAnswer2 = findViewById(R.id.answer2);
        mAnswer3 = findViewById(R.id.answer3);
        mAnswer4 = findViewById(R.id.answer4);
        mGoNext = findViewById(R.id.goNext);

        mGoNext.setEnabled(false);

        quizArray = new Quiz[]{
                new Quiz(getString(R.string.question_1), getString(R.string.answer_1_1), getString(R.string.answer_2_1), getString(R.string.answer_3_1), getString(R.string.answer_4_1), mAnswer2),
                new Quiz(getString(R.string.question_2), getString(R.string.answer_1_2), getString(R.string.answer_2_2), getString(R.string.answer_3_2), getString(R.string.answer_4_2), mAnswer4),
                new Quiz(getString(R.string.question_3), getString(R.string.answer_1_3), getString(R.string.answer_2_3), getString(R.string.answer_3_3), getString(R.string.answer_4_3), mAnswer3)
        };

        setTextElements();
    }

    public void AnswerClicked(View view) {
        /* Prendo l'id della risposta cliccata */
        int buttonId = view.getId();

        /* verifico se la risposta data è corretta o meno */
        clickedAnswer = findViewById(getResources().getIdentifier(String.valueOf(buttonId), "id", getPackageName()));

        if (quizArray[currentQuestionIndex].isRightAnswer(clickedAnswer)) {
            mRightAnswerCounter++;
            clickedAnswer.setBackgroundColor(Color.GREEN);
        } else {
            quizArray[currentQuestionIndex].getRightAnswer().setBackgroundColor(Color.GREEN);
            clickedAnswer.setBackgroundColor(Color.RED);
        }

        setStateAnswer(false);
        mGoNext.setEnabled(true);
    }

    public void setTextElements() {
        mQuestionText.setText(quizArray[currentQuestionIndex].getQuestionText());
        mAnswer1.setText(quizArray[currentQuestionIndex].getAnswer1());
        mAnswer2.setText(quizArray[currentQuestionIndex].getAnswer2());
        mAnswer3.setText(quizArray[currentQuestionIndex].getAnswer3());
        mAnswer4.setText(quizArray[currentQuestionIndex].getAnswer4());
    }

    @SuppressLint("SetTextI18n")
    public void goToNextClick(View view) {
        if (currentQuestionIndex >= 2) {

            Bundle bund = new Bundle();

            bund.putInt("rightAnswerCounter", mRightAnswerCounter);
            bund.putInt("totalAnswerCounter", quizArray.length);

            Intent intent = new Intent();

            intent.putExtras(bund);

            intent.setComponent(new ComponentName(MainActivity.this, ShowResults.class));

            startActivity(intent);

        } else {
            quizArray[currentQuestionIndex].getRightAnswer().setBackgroundColor(Color.TRANSPARENT);
            clickedAnswer.setBackgroundColor(Color.TRANSPARENT);
            currentQuestionIndex++;
            setTextElements();
            setStateAnswer(true);
            mGoNext.setEnabled(false);
        }
    }

    public void setStateAnswer(boolean state) {
        mAnswer1.setEnabled(state);
        mAnswer2.setEnabled(state);
        mAnswer3.setEnabled(state);
        mAnswer4.setEnabled(state);
    }

}
