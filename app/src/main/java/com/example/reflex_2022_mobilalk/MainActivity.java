package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int KEY = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayButtonPushed(View view) {
    }

    public void onLoginButtonPushed(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);

        loginIntent.putExtra("verysecretkey", KEY);
        startActivity(loginIntent);
    }

    public void onRegisterButtonPushed(View view) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        registerIntent.putExtra("verysecretkey", KEY);
        startActivity(registerIntent);
    }
}