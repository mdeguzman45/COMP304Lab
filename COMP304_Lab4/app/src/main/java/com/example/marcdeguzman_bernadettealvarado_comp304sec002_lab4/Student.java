package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Room entity class to describe the Professor table structure
@Entity
public class Student {

    @PrimaryKey
    @NonNull
    private int studentId;
    private String firstName;
    private String lastName;
    private String department;
    private int professorId;
    public String classroom;

    public Student() {}
    public Student(int sId, String fName, String lName, String dept, int pId, String classroom) {
        this.studentId = sId;
        this.firstName = fName;
        this.lastName = lName;
        this.department = dept;
        this.professorId = pId;
        this.classroom = classroom;
    }

    public int getStudentId() { return studentId; }

    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getProfessorId() { return professorId; }

    public void setProfessorId(int professorId) { this.professorId = professorId; }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}
