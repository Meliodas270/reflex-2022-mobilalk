package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {

    Button backToProfileButton;
    TextView outcomeTextView, pointsTextView;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        if (getIntent().getIntExtra("verysecretkey", 0) != 42) {
            finish();
        }

        backToProfileButton = findViewById(R.id.backToProfileButton);
        outcomeTextView = findViewById(R.id.outcomeTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        userDao = new UserDAO();

        pointsTextView.setText(Integer.toString(getIntent().getIntExtra("points", 0)));

        //TODO eredmény elmentése


    }

    public void backToProfile(View view) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("verysecretkey", 42);
        startActivity(profileIntent);
    }
}