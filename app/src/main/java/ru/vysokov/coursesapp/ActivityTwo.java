package ru.vysokov.coursesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity {
    Button btnToFirstActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnToFirstActivity = (Button) findViewById(R.id.btnToFirstActivity);
        btnToFirstActivity.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}