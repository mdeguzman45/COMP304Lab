package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

// Room entity class to describe the Professor table structure

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Professor {

    @PrimaryKey
    @NonNull
    private int professorId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public Professor() {}
    public Professor(int id, String fName, String lName, String dept, String pWord) {
        this.professorId = id;
        this.firstName = fName;
        this.lastName = lName;
        this.department = dept;
        this.password = pWord;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
