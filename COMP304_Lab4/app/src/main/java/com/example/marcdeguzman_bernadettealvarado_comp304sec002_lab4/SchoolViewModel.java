package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SchoolViewModel extends AndroidViewModel {

    // professor variables
    private SchoolRepository schoolRepository;
    private LiveData<Integer> insertProfessorResult;
    private LiveData<List<Professor>> professorList;
    private LiveData<Professor> authenticatedProfessor;

    // student variables
    private LiveData<Integer> insertStudentResult;
    private LiveData<List<Student>> studentList;

    // classroom variables
    private LiveData<Integer> insertClassroomResult;
    private LiveData<List<Classroom>> classroomTestList;

    public SchoolViewModel(@NonNull Application application) {
        super(application);
        schoolRepository = new SchoolRepository(application);
        insertProfessorResult = schoolRepository.getInsertProfessorResult();
        authenticatedProfessor = schoolRepository.getCurrentAuthenticatedProfessor();
        professorList = schoolRepository.getAllProfessor();
        studentList = schoolRepository.getAllStudent();
        insertStudentResult = schoolRepository.getInsertStudentResult();
        insertClassroomResult = schoolRepository.getInsertClassroomResult();
        classroomTestList = schoolRepository.getStudentClassroomTestList();
    }

    /* professor methods */
    // calls repository to insert a professor
    public void insert(Professor professor) {
        schoolRepository.insertProfessor(professor);
    }

    // calls repository to delete all professors
    public void deleteAll() {
        schoolRepository.deleteAll();
    }

    // gets the authenticated current professor
    public LiveData<Professor> getCurrentAuthenticatedProfessor() {
        return authenticatedProfessor;
    }

    // gets the insert result of professor as LiveData object
    public LiveData<Integer> getInsertProfessorResult() {
        return insertProfessorResult;
    }

    // returns list of professors as live data object
    LiveData<List<Professor>> getAllProfessor() {
        return professorList;
    }

    // checks for the occurrence of the professor
    public void authenticateProfessor(int id, String password) {
        schoolRepository.authenticateProfessor(id, password);
    }
    /* end professor methods */

    /* student methods */
    // calls repository to insert a student
    public void insert(Student student) {
        schoolRepository.insertStudent(student);
    }

    // gets the insert result of student as LiveData object
    public LiveData<Integer> getInsertStudentResult() {
        return insertStudentResult;
    }

    // returns list of professors as live data object
    LiveData<List<Student>> getAllStudent() {
        return studentList;
    }

    // calls repository to update a student
    public void update(Student student) {
        schoolRepository.updateStudent(student);
    }

    /* end student methods */

    /* classroom methods */
    // calls repository to insert classroom
    public void insert(Classroom classroom) {
        schoolRepository.insertClassroom(classroom);
    }

    // gets the insert result of student as LiveData object
    public LiveData<Integer> getInsertClassroomResult() {
        return insertClassroomResult;
    }

    // checks for the list of student test
    public void callSelectClassroomTestList(int id, String password) {
        schoolRepository.authenticateProfessor(id, password);
    }

    // returns the list of classroom for a student, classroom and professor combination
    public LiveData<List<Classroom>> getClassroomTestList() {
        return classroomTestList;
    }

    // checks for ist of classroom for a student, classroom and professor combination
    public void queryClassroomTestList(int studentId, int professorId) {
        schoolRepository.queryClassroomTestList(studentId, professorId);
    }

    /* end classroom methods */
}
