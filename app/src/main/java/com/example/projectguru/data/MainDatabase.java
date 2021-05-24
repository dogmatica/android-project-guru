package com.example.projectguru.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.projectguru.tools.Converters;

@Database(entities = {Project.class, Phase.class, WorkUnit.class, Resource.class}, exportSchema = false, version = 2)
@TypeConverters({Converters.class})
public abstract class MainDatabase extends RoomDatabase {
    private static final String DB_NAME = "main_db";
    private static MainDatabase instance;

    public static synchronized MainDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract ProjectDao projectDao();
    public abstract PhaseDao phaseDao();
    public abstract WorkUnitDao workUnitDao();
    public abstract ResourceDao resourceDao();



}
