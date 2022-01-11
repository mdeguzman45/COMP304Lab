package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudent();

    @Insert
    void insertStudent(Student student);

    @Update
    void updateStudent(Student student);
}
