package ru.vysokov.coursesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnToSecondActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnToSecondActivity = (Button) findViewById(R.id.btnToSecondActivity);
//        btnToSecondActivity.setOnClickListener(view -> {
//            Intent intent = new Intent(this, ActivityTwo.class);
//            startActivity(intent);
//        });
    }
}