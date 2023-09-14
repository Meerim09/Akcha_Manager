package com.meerim.akchamanager.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.meerim.akchamanager.models.IncomeModel;

import java.util.List;

@Dao
public interface IncomeDAO {
    @Query("select * FROM notifications")
    List<IncomeModel> getAllIncome();

    @Insert
    void addIncome(IncomeModel model);

    @Delete
    void deleteIncome(IncomeModel model);

}
