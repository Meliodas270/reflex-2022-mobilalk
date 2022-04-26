package com.example.reflex_2022_mobilalk;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class Point {
    private String uid, when, point, username;
    private UserDAO userDAO;

    public Point() {
        userDAO = new UserDAO();
    }

    public Point(String uid, String when, String point, String username) {
        userDAO = new UserDAO();

        this.uid = uid;
        this.when = when;
        this.point = point;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return getUsername() + ": " + getPoint();
    }
}
