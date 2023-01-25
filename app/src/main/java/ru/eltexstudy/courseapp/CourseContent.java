package ru.eltexstudy.courseapp;

public class CourseContent {
    private String duration;
    private String views;
    private String likes;
    private String title;
    public CourseContent(String duration, String views, String likes, String title) {
        this.duration = duration;
        this.views = views;
        this.likes = likes;
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CourseContent{" +
                "duration='" + duration + '\'' +
                ", views='" + views + '\'' +
                ", likes='" + likes + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
