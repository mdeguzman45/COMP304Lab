package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class SchoolRepository {
    // professor variables
    private final ProfessorDao professorDao;
    private final StudentDao studentDao;
    private final ClassroomDao classroomDao;
    private MutableLiveData<Integer> insertProfessorResult = new MutableLiveData<>();
    private LiveData<List<Professor>> professorList;
    private MutableLiveData<Professor> authenticatedProfessor = new MutableLiveData<>();
    private MutableLiveData<Integer> insertStudentResult = new MutableLiveData<>();
    private MutableLiveData<Integer> updateStudentResult = new MutableLiveData<>();
    private LiveData<List<Student>> studentList;
    private MutableLiveData<Integer> insertClassroomResult = new MutableLiveData<>();

    private MutableLiveData<List<Classroom>> classroomTestList = new MutableLiveData<>();

    public SchoolRepository(Context context) {
        // create a database object
        SchoolDatabase db = SchoolDatabase.getInstance(context);

        // create interface for professors
        professorDao = db.professorDao();

        // call interface method
        professorList = professorDao.getAllProfessor();

        // add student and classroom here

        // create interface for students
        studentDao = db.studentDao();

        // call interface method
        studentList = studentDao.getAllStudent();

        // create interface for classroom
        classroomDao = db.classroomDao();
    }

    /* Professor methods */
    // returns query results of professors as LiveData object
    LiveData<List<Professor>> getAllProfessor() {
        return professorList;
    }

    // inserts a professor asynchronously
    public void insertProfessor(Professor professor) { insertProfessorAsync(professor); }

    // returns insert result of professors as LiveData object
    public LiveData<Integer> getInsertProfessorResult() {
        return insertProfessorResult;
    }

    // returns the authenticated professor
    public LiveData<Professor> getCurrentAuthenticatedProfessor() {
        return authenticatedProfessor;
    }

    private void insertProfessorAsync(final Professor professor) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    professorDao.insert(professor);
                    insertProfessorResult.postValue(1);
                } catch (Exception e) {
                    insertProfessorResult.postValue(0);
                }
            }
        }).start();
    }

    public void deleteAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    professorDao.deleteAll();
                } catch (Exception e) {
                    Log.d("SchoolRepo.deleteAll", "exception deleting professors" + e.getMessage());
                }
            }
        }).start();
    }

    public void authenticateProfessor(int id, String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    authenticatedProfessor.postValue(professorDao.authenticateProfessor(id, password));
                } catch (Exception e) {
                    Log.d("SchoolRepo.authProf", "exception authenticating professor" + e.getMessage());
                    authenticatedProfessor.postValue(null);
                }
            }
        }).start();
    }

    /* end professor methods */

    /* Student methods */
    // returns query results of students as LiveData object
    LiveData<List<Student>> getAllStudent() {
        return studentList;
    }

    // inserts a student asynchronously
    public void insertStudent(Student student) { insertStudentAsync(student); }

    // returns insert result of student as LiveData object
    public LiveData<Integer> getInsertStudentResult() {
        return insertStudentResult;
    }

    private void insertStudentAsync(final Student student) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDao.insertStudent(student);
                    insertStudentResult.postValue(1);
                } catch (Exception e) {
                    insertStudentResult.postValue(0);
                }
            }
        }).start();
    }

    // updates a student asynchronously
    public void updateStudent(Student student) { updateStudentAsync(student); }

    // returns update result of student as LiveData object
    public LiveData<Integer> getUpdateStudentResult() {
        return updateStudentResult;
    }

    private void updateStudentAsync(final Student student) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDao.updateStudent(student);
                    updateStudentResult.postValue(1);
                } catch (Exception e) {
                    updateStudentResult.postValue(0);
                }
            }
        }).start();
    }

    /* end student methods */

    /* classroom methods */
    // insert classroom asynchronously
    public void insertClassroom(Classroom classroom) { insertClassroomAsync(classroom); }

    // returns insert result of classroom  as LiveData object
    public LiveData<Integer> getInsertClassroomResult() { return insertClassroomResult; }

    private void insertClassroomAsync(Classroom classroom) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    classroomDao.insertClassroom(classroom);
                    insertClassroomResult.postValue(1);
                } catch (Exception e) {
                    insertClassroomResult.postValue(0);
                }
            }
        }).start();
    }

    // returns the classroom testList
    public LiveData<List<Classroom>> getStudentClassroomTestList() {
        return classroomTestList;
    }

    public void queryClassroomTestList(int studentId, int professorId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    classroomTestList.postValue(classroomDao.getStudentTest(studentId, professorId));
                } catch (Exception e) {
                    Log.d("SchoolRepo.getTestList", "exception getting test info: " + e.getMessage());
                    classroomTestList.postValue(null);
                }
            }
        }).start();
    }

    /* end classroom methods */
}
