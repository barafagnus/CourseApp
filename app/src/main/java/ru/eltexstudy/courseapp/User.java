package ru.eltexstudy.courseapp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private ArrayList<String> activeCourses = new ArrayList<>();

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

    public ArrayList<String> getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(ArrayList<String> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public void addActiveCourses(String course) {
        this.activeCourses.add(course);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", activeCourses=" + activeCourses +
                '}';
    }
}
