package ru.eltexstudy.courseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltexstudy.courseapp.YouTubeAPI.YouTubeApiConfig;
import ru.eltexstudy.courseapp.YouTubeAPI.YouTubeService;
import ru.eltexstudy.courseapp.YouTubeModelApi.Items;
import ru.eltexstudy.courseapp.YouTubeModelApi.VideoModel;

public class CourseInfoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private List<User> userList = HomeFragment.getUserList();

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

        courseName.setText(getIntent().getStringExtra("courseName"));
        courseTime.setText("Время обучения: " + getIntent().getStringExtra("courseTime"));
        courseFullDescription.setText(getIntent().getStringExtra("fullCourseDescription"));

//        AppCompatButton addCourse = findViewById(R.id.addCourse);
//        addCourse.setOnClickListener(v -> {
//            User user1 = new User("Вася Пупкин");
//            user1.addActiveCourses(courseName.getText().toString());
//            userList.add(user1);
//
//        });

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubeView.initialize(YouTubeApiConfig.YOUTUBE_API_KEY, this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YouTubeService youTubeService = retrofit.create(YouTubeService.class);
        youTubeService.getVideosDetails("FOMWgFlmo-8", YouTubeApiConfig.YOUTUBE_API_KEY).enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                System.out.println("DATA123 " + response.code());
                System.out.println("DATA123 " + response);
                //List<Items> apiData = response.body().getItems();
                response.body().getItems().forEach(element -> {
                    System.out.println(element.getStatistics().getLikeCount());
                });
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo("MaeRXppkzdA");
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