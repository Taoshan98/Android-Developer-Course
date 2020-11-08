package com.example.quizapp;

import android.widget.Button;

public class Quiz {

    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private Button rightAnswer;

    public Quiz(String questionText, String answer1, String answer2, String answer3, String answer4, Button rightAnswer) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public Button getRightAnswer() {
        return rightAnswer;
    }

    boolean isRightAnswer(Button clickedAnswer){
        return clickedAnswer == rightAnswer;
    }


}
