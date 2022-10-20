package ru.vysokov.coursesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.vysokov.coursesapp.adapter.CourseAdapter;
import ru.vysokov.coursesapp.model.Course;

public class MainActivity extends AppCompatActivity {
    RecyclerView courseRecycler;
    CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "java", "Java разработчик", "Создайте свое приложение", "32 Урока", "30 Мин", "#AECDFF"));
        courseList.add(new Course(2, "graphics", "Векторная графика", "Нарисуйте лучшее будущее", "32 Урока", "30 Мин", "#E38DDD"));
        courseList.add(new Course(3, "web", "Web разработчик", "CSS + HTML + JS", "32 Урока", "30 Мин", "#51C3FE"));
        setCourseRecycler(courseList);

    }

    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);
        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);


    }

//            btnToSecondActivity = (Button) findViewById(R.id.btnToSecondActivity);
//        btnToSecondActivity.setOnClickListener(view -> {
//            Intent intent = new Intent(this, ActivityTwo.class);
//            startActivity(intent);
//        });
}