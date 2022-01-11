package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Room database class
@Database(entities = {Professor.class, Student.class, Classroom.class}, version = 7)
public abstract class SchoolDatabase extends RoomDatabase {
    private static volatile SchoolDatabase INSTANCE;
    private static final String DATABASE_NAME = "SchoolDB";
    public abstract ProfessorDao professorDao();
    public abstract StudentDao studentDao();
    public abstract ClassroomDao classroomDao();

    public static synchronized SchoolDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    SchoolDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

    // create callback to populate data of professors
    private static RoomDatabase.Callback populateProfessorRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override

        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // run on separate thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ProfessorDao professorDao = INSTANCE.professorDao();
                        professorDao.deleteAll(); // clear all first

                        // insert
                        professorDao.insert(new Professor(1, "Marc", "De Guzman", "Software Engineering", "12345"));
                        professorDao.insert(new Professor(2, "Bernadette", "Alvarado", "Software Engineering", "12345"));

                    } catch (Exception e) {
                        Log.d("ProfessorRoomDBCallback", "exception populating professor" + e.getMessage());
                    }
                }
            }).start();
        }
    };
}
