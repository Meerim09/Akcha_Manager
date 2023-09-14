package com.meerim.akchamanager.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.meerim.akchamanager.adapters.GoalsAdapter;
import com.meerim.akchamanager.database.DatabaseHelper;
import com.meerim.akchamanager.database_expense.ExpDatabaseHelper;
import com.meerim.akchamanager.databinding.ActivityGoalsBinding;
import com.meerim.akchamanager.goals_db.GoalsDBHelper;
import com.meerim.akchamanager.models.ExpenseModel;
import com.meerim.akchamanager.models.GoalsModel;
import com.meerim.akchamanager.models.IncomeModel;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {
    ActivityGoalsBinding binding;
    ArrayList<GoalsModel> models = new ArrayList();
    GoalsAdapter adapter;
    int incomeD = 0;
    int expensesD = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoalsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GoalsDBHelper goalsDBHelper = GoalsDBHelper.getExpense(getApplicationContext());
        DatabaseHelper income = DatabaseHelper.getDB(getApplicationContext());
        ExpDatabaseHelper expense = ExpDatabaseHelper.getExpense(getApplicationContext());

        List<IncomeModel> incomeList = income.notificationDAO().getAllIncome();
        List<ExpenseModel> expenseList = expense.expenseDAO().getAllExpense();

        for (int i = 0; i < incomeList.size(); i++) {
            incomeD += incomeList.get(i).getAmount();
        }
        for (int i = 0; i < expenseList.size(); i++) {
            expensesD += expenseList.get(i).getAmount();
        }

        binding.txtAmount.setText("KGS "+String.valueOf(incomeD-expensesD));

        binding.btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityAdd.class).putExtra("ctx", "goals"));
        });

        models = new ArrayList<>(goalsDBHelper.expenseDAO().getAllGoals());
        for (int i = 0; i < models.size(); i++) {
            Log.d("TAG", "budgetList: " + models.get(i));
        }
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoalsAdapter(models, this,incomeD-expensesD);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}