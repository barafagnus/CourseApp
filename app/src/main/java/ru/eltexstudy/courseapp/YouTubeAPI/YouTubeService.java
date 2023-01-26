package ru.eltexstudy.courseapp.YouTubeAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.eltexstudy.courseapp.YouTubeModelApi.VideoModel;

public interface YouTubeService {
    @GET("videos?&part=snippet,contentDetails,statistics,status,snippet")
    Call<VideoModel> getVideosDetails(@Query("id") ArrayList<String> id, @Query("key") String key);

    @GET("videos?&part=snippet,contentDetails,statistics,status,snippet")
    Call<VideoModel> getOneVideoDetails(@Query("id") String id, @Query("key") String key);
}
