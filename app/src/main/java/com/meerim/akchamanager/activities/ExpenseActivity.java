package com.meerim.akchamanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.meerim.akchamanager.adapters.ExpenseAdapter;
import com.meerim.akchamanager.database_budget.BudgetDBHelper;
import com.meerim.akchamanager.database_expense.ExpDatabaseHelper;
import com.meerim.akchamanager.databinding.ActivityExpenseBinding;
import com.meerim.akchamanager.models.BudgetModel;
import com.meerim.akchamanager.models.ExpenseModel;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity {
    ArrayList<ExpenseModel> models = new ArrayList<>();
    ExpDatabaseHelper databaseHelper;
    ExpenseAdapter adapter;
    ActivityExpenseBinding binding;
    private int totalSpentAmount = 0;
    private List<ExpenseModel> spentList;
    private List<BudgetModel> budList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityAdd.class).putExtra("ctx", "expense"));
        });
        ExpDatabaseHelper exp = ExpDatabaseHelper.getExpense(getApplicationContext());
        spentList = exp.expenseDAO().getAllExpense();

        BudgetDBHelper budgetDBHelper = BudgetDBHelper.getBudget(getApplicationContext());
        budList = budgetDBHelper.budgetDAO().getAllBudgets();

//        for (int p = 0; p < budList.size(); p++) {
//
//        }

        for (int x = 0; x < spentList.size(); x++) {
            totalSpentAmount = +spentList.get(x).getAmount();
        }
        Log.d("totalSpentAmount", "totalSpentAmount " + totalSpentAmount);

        databaseHelper = ExpDatabaseHelper.getExpense(getApplicationContext());
        models = new ArrayList<>(databaseHelper.expenseDAO().getAllExpense());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(models, this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}