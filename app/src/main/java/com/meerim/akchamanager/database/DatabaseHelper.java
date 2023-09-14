package com.meerim.akchamanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.meerim.akchamanager.models.IncomeModel;

@Database(entities = IncomeModel.class, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String DB_NAME = "notifications";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getDB(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

    public abstract IncomeDAO notificationDAO();
}