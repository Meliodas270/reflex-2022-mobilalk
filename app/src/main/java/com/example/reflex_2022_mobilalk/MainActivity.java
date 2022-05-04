package com.example.reflex_2022_mobilalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int KEY = 42;
    private static final String CHANNEL_ID = "értesítések";

    FirebaseAuth mAuth;
    TextView logInfoTextView;
    LinearLayout linearLayout;
    Button registerButton;
    Button loginButton;
    Button logoutButton;
    Button playButton;
    UserDAO userDao;
    FirebaseUser user;
    Animation zoomout, zoomin;

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

        zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);

        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playButton.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playButton.startAnimation(zoomin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        playButton.startAnimation(zoomin);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.notifications);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.reflex_dark_cropped)
                .setContentTitle("HALLO HALLO")
                .setContentText("THIS IS YOUR CAPTAIN SPEAKING")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(new Random().nextInt(), builder.build());
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

    @Override
    protected void onPause() {
        playButton.clearAnimation();
        super.onPause();
    }

    @Override
    protected void onResume() {
        playButton.startAnimation(zoomin);
        super.onResume();
    }
}