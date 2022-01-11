package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClassroomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertClassroom(Classroom classroom);

    // return data for the student id with the professor id
    @Query("SELECT * FROM Classroom WHERE studentId = :studentId AND professorId = :professorId")
    public List<Classroom> getStudentTest(int studentId, int professorId);

    @Query("DELETE FROM Classroom")
    void deleteAll();
}
