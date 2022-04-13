package com.rivvana.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView tvQuestion, tvScore, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2;
    private Button next;

    int totalQuestion;
    int qCounter = 0;
    private QuestionModel currentQuestion;

    private List<QuestionModel> questionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionModelList = new ArrayList<>();
        tvQuestion = findViewById(R.id.question);
        tvScore = findViewById(R.id.score);
        tvTimer = findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.rbGrup);
        rb1 = findViewById(R.id.opsi1);
        rb2 = findViewById(R.id.opsi2);
        next = findViewById(R.id.buttonPilih);

        addQuestion();
        totalQuestion = questionModelList.size();
        showNextQuestions();
    }

    private void showNextQuestions() {
        if (qCounter < totalQuestion){
            currentQuestion = questionModelList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());

            qCounter++;
        }else {
            finish();
        }

    }

    private void addQuestion() {

        questionModelList.add(new QuestionModel("A is a correct", "A", "B", 1));
        questionModelList.add(new QuestionModel("B is a correct", "A", "B", 2));

    }
}