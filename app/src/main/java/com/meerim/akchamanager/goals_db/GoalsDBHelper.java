package com.meerim.akchamanager.goals_db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.meerim.akchamanager.models.GoalsModel;

@Database(entities = GoalsModel.class, exportSchema = false, version = 1)
public abstract class GoalsDBHelper extends RoomDatabase {

    private static final String DB_NAME = "goals";
    private static com.meerim.akchamanager.goals_db.GoalsDBHelper instance;

    public static synchronized com.meerim.akchamanager.goals_db.GoalsDBHelper getExpense(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, com.meerim.akchamanager.goals_db.GoalsDBHelper.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        }
        return instance;
    }

    public abstract GoalsDAO expenseDAO();
}