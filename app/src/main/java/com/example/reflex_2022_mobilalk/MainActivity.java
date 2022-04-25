package com.example.reflex_2022_mobilalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final int KEY = 42;

    FirebaseAuth mAuth;
    TextView logInfoTextView;
    LinearLayout linearLayout;
    Button registerButton;
    Button loginButton;
    Button logoutButton;
    Button playButton;
    UserDAO userDao;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mAuth = FirebaseAuth.getInstance();
        logInfoTextView = findViewById(R.id.logInfoTextView);
        linearLayout = findViewById(R.id.linearLayout);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);
        logoutButton = findViewById(R.id.logoutButton);
        playButton = findViewById(R.id.playButton);

        logoutButton.setVisibility(View.GONE);
        playButton.setVisibility(View.GONE);

        userDao = new UserDAO();

        user = mAuth.getCurrentUser();

        if (user != null) {
            logInfoTextView.setText(user.getEmail());
            registerButton.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                if (user != null) {
                    goToProfile();
                } else {
                    Toast.makeText(MainActivity.this, "You have to be logged in to access your profile", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.nav_ranglist:
                goToRanglist();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToProfile() {
        Intent loginIntent = new Intent(this, ProfileActivity.class);

        loginIntent.putExtra("verysecretkey", KEY);
        startActivity(loginIntent);
    }

    private void goToRanglist() {
        Intent loginIntent = new Intent(this, RanglistActivity.class);

        loginIntent.putExtra("verysecretkey", KEY);
        startActivity(loginIntent);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            System.exit(0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onLogoutButtonPushed(View view) {
        mAuth.signOut();

        finish();
        startActivity(getIntent());
    }

    public void onPlayButtonPushed(View view) {
        Intent overviewintent = new Intent(this, GameOverviewActivity.class);
        overviewintent.putExtra("verysecretkey", KEY);
        startActivity(overviewintent);
    }
}