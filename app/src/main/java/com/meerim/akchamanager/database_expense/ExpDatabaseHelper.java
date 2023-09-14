package com.meerim.akchamanager.database_expense;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.meerim.akchamanager.models.ExpenseModel;

@Database(entities = ExpenseModel.class, exportSchema = false, version = 1)
public abstract class ExpDatabaseHelper extends RoomDatabase {

    private static final String DB_NAME = "expense";
    private static com.meerim.akchamanager.database_expense.ExpDatabaseHelper instance;

    public static synchronized com.meerim.akchamanager.database_expense.ExpDatabaseHelper getExpense(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, com.meerim.akchamanager.database_expense.ExpDatabaseHelper.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        }
        return instance;
    }

    public abstract ExpenseDAO expenseDAO();
}