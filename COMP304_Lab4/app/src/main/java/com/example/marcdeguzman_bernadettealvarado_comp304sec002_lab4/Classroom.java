package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"classroomId", "studentId", "professorId"})
public class Classroom {
    @NonNull
    private String classroomId;
    @NonNull
    private int studentId;
    @NonNull
    private int professorId;
    private String floor;
    private String airConditioned;
//    @NonNull
//    private String testName;
//    private int testGrade;

    public Classroom() {}

    public Classroom(String classrmID, int studId, int profId, String flr, String airCond) {
        classroomId = classrmID;
        studentId = studId;
        professorId = profId;
        floor = flr;
        airConditioned = airCond;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAirConditioned() {
        return airConditioned;
    }

    public void setAirConditioned(String airConditioned) {
        this.airConditioned = airConditioned;
    }

//    public String getTestName() {
//        return testName;
//    }
//
//    public void setTestName(String testName) {
//        this.testName = testName;
//    }
//
//    public int getTestGrade() {
//        return testGrade;
//    }
//
//    public void setTestGrade(int testGrade) {
//        this.testGrade = testGrade;
//    }
}
