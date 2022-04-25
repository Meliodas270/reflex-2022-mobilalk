package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreenActivity extends AppCompatActivity {

    private static final int KEY = 42;
    int questionNum, layer, points;
    TextView questionNumber, time, question;
    EditText answer;
    List<String> correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        if (getIntent().getIntExtra("verysecretkey", 0) != 42) {
            finish();
        }

        Log.d("time", String.valueOf(getIntent().getIntExtra("time", 0)));

        question = findViewById(R.id.question);
        time = findViewById(R.id.time);
        questionNumber = findViewById(R.id.questionNumber);
        answer = findViewById(R.id.answer);
        questionNum = 1;
        layer = 1;

        new CountDownTimer(getIntent().getIntExtra("time", 0) * 1000L, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(String.format("%d", millisUntilFinished / 1000));
            }

            public void onFinish() {
                time.setText(R.string.done);
            }

        }.start();

        questionNumber.setText(Integer.toString(getIntent().getIntExtra("questionNum", 0)));

        switch (getIntent().getIntExtra("questionNum", 0)) {
            case 1:
                question.setText(R.string.l1q1);
                correctAnswer = Arrays.asList(getString(R.string.l1a1).split(", "));
                break;
            case 2:
                question.setText(R.string.l1q2);
                correctAnswer = Arrays.asList(getString(R.string.l1a2).split(", "));
                break;
            case 3:
                question.setText(R.string.l1q3);
                correctAnswer = Arrays.asList(getString(R.string.l1a3).split(", "));
                break;
            case 4:
                Intent endIntent = new Intent(this, GameEndActivity.class);
                endIntent.putExtra("verysecretkey", KEY);
                endIntent.putExtra("points", getIntent().getIntExtra("time", 0));
                startActivity(endIntent);
        }

        answer.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d("keyboard", "enter_key_called");
                Log.d("time", time.getText().toString());

                finish();
                getIntent().putExtra("questionNum", getIntent().getIntExtra("questionNum", 0) + 1);
                getIntent().putExtra("time", Integer.parseInt(time.getText().toString()));
                startActivity(getIntent());
            }

            return false;
        });
    }

    private void nextQuestion() {
        if (correctAnswer.contains(answer.getText().toString())) {
            points += 10;
        }
    }


}