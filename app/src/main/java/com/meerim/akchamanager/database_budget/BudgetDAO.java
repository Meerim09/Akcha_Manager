package com.meerim.akchamanager.database_budget;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.meerim.akchamanager.models.BudgetModel;

import java.util.List;

@Dao
public interface BudgetDAO {
    @Query("select * FROM budget")
    List<BudgetModel> getAllBudgets();

    @Insert
    void addBudget(BudgetModel model);

    @Delete
    void deleteBudget(BudgetModel model);

}
