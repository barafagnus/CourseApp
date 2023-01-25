package ru.eltexstudy.courseapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltexstudy.courseapp.Adapter.UniversalRecyclerViewAdapter;
import ru.eltexstudy.courseapp.YouTubeAPI.YouTubeApiConfig;
import ru.eltexstudy.courseapp.YouTubeAPI.YouTubeService;
import ru.eltexstudy.courseapp.YouTubeModelApi.VideoModel;

public class CourseActivity extends YouTubeBaseActivity implements RecyclerViewInterface, YouTubePlayer.OnInitializedListener {
    UniversalRecyclerViewAdapter universalRecyclerViewAdapter;
    RecyclerView courseListRecycler;
    LinearLayoutManager linearLayoutManagerCourseList;
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private static List<CourseContent> courseContentList;
    private ArrayList<String> videoIdList;
    YouTubePlayer youTubePlayer;
    String currentVideoId = "YLUyVtHTGqw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubeView.initialize(YouTubeApiConfig.YOUTUBE_API_KEY, this);

        ImageView buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            this.finish();
        });

        TextView courseName = findViewById(R.id.courseName);
        courseName.setText(getIntent().getStringExtra("courseName"));

        videoIdList = new ArrayList<>();
        videoIdList = getIntent().getStringArrayListExtra("courseVideoId");
        courseContentList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // ---- !!! ---
        for (int i = 0; i < videoIdList.size(); i++) {
            YouTubeService youTubeService = retrofit.create(YouTubeService.class);
            youTubeService.getVideosDetails(videoIdList.get(i), YouTubeApiConfig.YOUTUBE_API_KEY).enqueue(new Callback<VideoModel>() {
                @Override
                public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                    response.body().getItems().forEach(element -> {
                        courseContentList.add(new CourseContent(
                                element.getContentDetails().getDuration(),
                                element.getStatistics().getViewCount(),
                                element.getStatistics().getLikeCount(),
                                element.getSnippet().getTitle()));
                    });
                    if (response.isSuccessful()) {
                        System.out.println(courseContentList);
                        courseListRecycler = findViewById(R.id.courseListRecycler);
                        linearLayoutManagerCourseList = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        setUniversalRecyclerViewAdapter(courseContentList);
                    }
                }

                @Override
                public void onFailure(Call<VideoModel> call, Throwable t) {
                    Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }
    }

    @Override
    public void onItemNavigationClick(int position) {

    }

    @Override
    public void onItemCourseClick(int position) {
        TextView countViews = findViewById(R.id.countViews);
        TextView countLikes = findViewById(R.id.countLikes);
        TextView videoDuration = findViewById(R.id.videoDuration);

        countViews.setText(courseContentList.get(position).getViews());
        if (courseContentList.get(position).getLikes() == null) {
            countLikes.setText("0");
        } else {
            countLikes.setText(courseContentList.get(position).getLikes());
        }
        videoDuration.setText(courseContentList.get(position).getDuration());
        currentVideoId = videoIdList.get(position);
        youTubePlayer.pause();
        youTubePlayer.cueVideo(currentVideoId);

    }

    private void setUniversalRecyclerViewAdapter(List<CourseContent> courseContentList) {
        universalRecyclerViewAdapter = new UniversalRecyclerViewAdapter(this, courseContentList, this);
        universalRecyclerViewAdapter.setCustomViewType("CourseActivity");
        courseListRecycler.setLayoutManager(linearLayoutManagerCourseList);
        courseListRecycler.setAdapter(universalRecyclerViewAdapter);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        this.youTubePlayer = youTubePlayer;
        if (!wasRestored) {
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            youTubePlayer.cueVideo(videoIdList.get(0));
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