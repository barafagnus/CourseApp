package ru.eltexstudy.courseapp;

import java.util.LinkedList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private LinkedList<String> activeCourses = new LinkedList<>();

    public User(String name) {
        ENV.USER_ID++;
        this.userId = ENV.USER_ID;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(LinkedList<String> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public void addActiveCourses(String course) {
        this.activeCourses.add(course);
    }
}
