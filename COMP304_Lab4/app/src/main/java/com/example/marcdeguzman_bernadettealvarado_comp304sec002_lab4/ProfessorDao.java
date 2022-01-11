package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// this interface declares database function
// mappings of SQL queries for the Professor table

@Dao
public interface ProfessorDao {
    @Insert
    void insert(Professor professor);

    // return professor object with the professorId and password
    @Query("SELECT * FROM Professor WHERE professorId = :profId AND password = :inputPassword")
    public Professor authenticateProfessor(int profId, String inputPassword);

    @Query("SELECT * FROM Professor ORDER BY professorId ASC")
    LiveData<List<Professor>> getAllProfessor();

    @Query("DELETE FROM Professor")
    void deleteAll();
}
