package ru.vysokov.coursesapp.model;

public class Course {
    int id;
    String img, coursename, courseinfo, countcourses, timecourses, bgcolor, cardinfobg;

    public Course(int id, String img, String coursename, String courseinfo, String countcourses, String timecourses, String bgcolor) {
        this.id = id;
        this.img = img;
        this.coursename = coursename;
        this.courseinfo = courseinfo;
        this.countcourses = countcourses;
        this.timecourses = timecourses;
        this.bgcolor = bgcolor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseinfo() {
        return courseinfo;
    }

    public void setCourseinfo(String courseinfo) {
        this.courseinfo = courseinfo;
    }

    public String getCountcourses() {
        return countcourses;
    }

    public void setCountcourses(String countcourses) {
        this.countcourses = countcourses;
    }

    public String getTimecourses() {
        return timecourses;
    }

    public void setTimecourses(String timecourses) {
        this.timecourses = timecourses;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

}
