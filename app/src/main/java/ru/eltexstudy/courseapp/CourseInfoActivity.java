package ru.eltexstudy.courseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import ru.eltexstudy.courseapp.YouTubeAPI.YouTubeApiConfig;

public class CourseInfoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private ArrayList<User> userList;
    private String videoId;
    private YouTubePlayer youTubePlayer;
    private String currentCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        ImageView buttonBack = findViewById(R.id.buttonBack);
        Intent mainActivity = new Intent(this, MainActivity.class);
        buttonBack.setOnClickListener(v -> startActivity(mainActivity));

        TextView courseName = findViewById(R.id.courseName);
        TextView countLessons = findViewById(R.id.countLessons);
        TextView courseTime = findViewById(R.id.courseTime);
        TextView courseFullDescription = findViewById(R.id.fullCourseDescription);
        currentCourseName = getIntent().getStringExtra("courseName");
        courseName.setText(currentCourseName);
        courseTime.setText("Время обучения: " + getIntent().getStringExtra("courseTime"));
        courseFullDescription.setText(getIntent().getStringExtra("fullCourseDescription"));

        videoId = getIntent().getStringExtra("videoId");
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubeView.initialize(YouTubeApiConfig.YOUTUBE_API_KEY, this);

        AppCompatButton addCourse = findViewById(R.id.addCourse);
        // Обработка наличие курса у пользователя
        userList = HomeFragment.getUserList();
        if (userList.get(0).getActiveCourses().contains(currentCourseName)) {
            addCourse.setVisibility(View.GONE);
        }

        addCourse.setOnClickListener(v -> {
            userList.get(0).addActiveCourses(courseName.getText().toString());
            HomeFragment.setUserList(userList);
            Toast.makeText(getApplicationContext(), "Курс успешно добавлен", Toast.LENGTH_SHORT).show();
            this.finish();
        });

//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.googleapis.com/youtube/v3/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        YouTubeService youTubeService = retrofit.create(YouTubeService.class);
//        youTubeService.getOneVideoDetails("FOMWgFlmo-8", YouTubeApiConfig.YOUTUBE_API_KEY).enqueue(new Callback<VideoModel>() {
//            @Override
//            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
//                response.body().getItems().forEach(element -> {
//                });
//            }
//
//            @Override
//            public void onFailure(Call<VideoModel> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        this.youTubePlayer = youTubePlayer;
        if (!wasRestored) {
            youTubePlayer.cueVideo(videoId);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(YouTubeApiConfig.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayerView getYouTubePlayerProvider() {

        return youTubeView;
    }

}