package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PlayActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button bigPlayButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        if (getIntent().getIntExtra("verysecretkey", 0) != 42) {
            finish();
        }

        mAuth = FirebaseAuth.getInstance();
        bigPlayButton = findViewById(R.id.bigPlayButton);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoomin);

        bigPlayButton.startAnimation(animation);
    }

    public void onBack(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void onPlay(View view) {
        Intent gameIntent = new Intent(this, GameOverviewActivity.class);
        startActivity(gameIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}