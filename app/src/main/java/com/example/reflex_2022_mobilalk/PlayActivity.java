package com.example.reflex_2022_mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PlayActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        if (getIntent().getIntExtra("verysecretkey", 0) != 42) {
            finish();
        }

        mAuth = FirebaseAuth.getInstance();
        usernameTextView = findViewById(R.id.usernameTextView);

        usernameTextView.setText(mAuth.getCurrentUser().getEmail());
    }

    public void onBack(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}