package com.meerim.akchamanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.meerim.akchamanager.adapters.BudgetAdapter;
import com.meerim.akchamanager.database_budget.BudgetDBHelper;
import com.meerim.akchamanager.databinding.ActivityBudgetBinding;
import com.meerim.akchamanager.models.BudgetModel;

import java.util.ArrayList;

public class BudgetActivity extends AppCompatActivity {

    ActivityBudgetBinding binding;
    ArrayList<BudgetModel> models = new ArrayList();
    BudgetDBHelper databaseHelper;
    BudgetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBudgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityAdd.class).putExtra("ctx", "budget"));
        });

        databaseHelper = BudgetDBHelper.getBudget(getApplicationContext());
        models = new ArrayList<>(databaseHelper.budgetDAO().getAllBudgets());
        for (int i = 0; i < models.size(); i++) {
            Log.d("TAG", "budgetList: " + models.get(i));
        }
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BudgetAdapter(models, this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}