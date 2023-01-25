package ru.eltexstudy.courseapp;

public class Course {
    private int id;
    private String courseCategory;
    private String courseName;
    private String courseLogo;
    private String courseTime;
    private String courseDescription;
    private String fullCourseDescription;

    public Course(String courseCategory, String courseName, String courseLogo, String courseTime, String courseDescription, String fullCourseDescription) {
        ENV.ID++;
        this.id = ENV.ID;
        this.courseCategory = courseCategory;
        this.courseName = courseName;
        this.courseLogo = courseLogo;
        this.courseTime = courseTime;
        this.courseDescription = courseDescription;
        this.fullCourseDescription = fullCourseDescription;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(String courseLogo) {
        this.courseLogo = courseLogo;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }



    public String getFullCourseDescription() {
        return fullCourseDescription;
    }

    public void setFullCourseDescription(String fullCourseDescription) {
        this.fullCourseDescription = fullCourseDescription;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseCategory='" + courseCategory + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseLogo='" + courseLogo + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }
}
