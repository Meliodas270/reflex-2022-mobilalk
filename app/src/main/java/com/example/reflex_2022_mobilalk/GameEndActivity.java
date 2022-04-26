package com.example.reflex_2022_mobilalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GameEndActivity extends AppCompatActivity {

    Button backToProfileButton;
    TextView outcomeTextView, pointsTextView;
    UserDAO userDao;
    PointsDAO pointsDao;
    FirebaseUser user;

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
        pointsDao = new PointsDAO();

        pointsTextView.setText(Integer.toString(getIntent().getIntExtra("points", 0)));
        outcomeTextView.setText("Your points:");

        //TODO eredmény elmentése

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            userDao.update(user.getUid(), userDao.getHash()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    User currentUser = userDao.getCurrentUser();

                    Point p = new Point(FirebaseAuth.getInstance().getUid(), String.valueOf(System.currentTimeMillis()), Integer.toString(getIntent().getIntExtra("points", 0)), currentUser.getUsername());

                    pointsDao.add(p).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(GameEndActivity.this, "Points saved!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }
    }

    public void backToProfile(View view) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("verysecretkey", 42);
        startActivity(profileIntent);
    }
}