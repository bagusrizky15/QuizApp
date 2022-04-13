package com.rivvana.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView tvQuestion, tvScore, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2;
    private Button next;

    int totalQuestion;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;

    CountDownTimer countDownTimer;


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

        dfRbColor = rb1.getTextColors();

        addQuestion();
        totalQuestion = questionModelList.size();
        showNextQuestions();

        next.setOnClickListener(v -> {
            if (answered == false){
                if (rb1.isChecked() || rb2.isChecked()){
                    checkAnswer();
                    countDownTimer.cancel();
                } else {
                    Toast.makeText(MainActivity.this, "Please select Answer", Toast.LENGTH_SHORT).show();

                }
            }else {
                 showNextQuestions();
            }
        });
    }

    private void checkAnswer() {

        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
            tvScore.setText("Score : "+score);

        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1 :
                rb1.setTextColor(Color.GREEN);
                break;
            case 2 :
                rb2.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestion){
            next.setText("Next");
        }else {
            next.setText("Finish");
        }

    }

    private void showNextQuestions() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);

        if (qCounter < totalQuestion){
             timer();
            currentQuestion = questionModelList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());

            qCounter++;

            next.setText("Submit");
            answered = false;




        }else {
            finish();
        }

    }

    private void timer() {
    countDownTimer = new CountDownTimer(20000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvTimer.setText("00:" + 1/1000);
        }

        @Override
        public void onFinish() {
            showNextQuestions();

        }
    }.start();
    }

    private void addQuestion() {

        questionModelList.add(new QuestionModel("A is a correct", "A", "B", 1));
        questionModelList.add(new QuestionModel("B is a correct", "A", "B", 2));

    }
}