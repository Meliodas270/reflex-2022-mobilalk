package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getIntent().getIntExtra("verysecretkey", 0) != 42) {
            finish();
        }
    }

    public void onRegisterButtonPushed(View view) {
        //TODO register megvalósítása
    }

    public void onCancel(View view) {
        finish();
    }
}