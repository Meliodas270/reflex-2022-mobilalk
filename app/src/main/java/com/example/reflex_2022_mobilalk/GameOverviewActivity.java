package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class GameOverviewActivity extends AppCompatActivity {

    private static final int KEY = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);
    }

    public void onBack(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onLayerOnePushed(View view) {
        Intent gameScreenIntent = new Intent(this, GameScreenActivity.class);
        gameScreenIntent.putExtra("verysecretkey", KEY);
        gameScreenIntent.putExtra("questionNum", 1);
        gameScreenIntent.putExtra("time", 120);
        finish();
        startActivity(gameScreenIntent);
    }
}