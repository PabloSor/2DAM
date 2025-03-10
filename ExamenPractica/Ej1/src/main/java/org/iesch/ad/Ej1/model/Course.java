package org.iesch.ad.Ej1.model;

import java.util.ArrayList;

public class Course {
    private String courseId;
    private String courseName;

    private ArrayList<Grades> grades;

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Grades> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grades> grades) {
        this.grades = grades;
    }
}
