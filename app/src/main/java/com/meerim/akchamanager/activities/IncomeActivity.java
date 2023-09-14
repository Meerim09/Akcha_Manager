package com.meerim.akchamanager.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.meerim.akchamanager.adapters.IncomeAdapter;
import com.meerim.akchamanager.database.DatabaseHelper;
import com.meerim.akchamanager.databinding.ActivityIncomeBinding;
import com.meerim.akchamanager.models.IncomeModel;

import java.util.ArrayList;

public class IncomeActivity extends AppCompatActivity {
    ArrayList<IncomeModel> models = new ArrayList();
    DatabaseHelper databaseHelper;
    IncomeAdapter adapter;
    ActivityIncomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityAdd.class).putExtra("ctx","income"));
        });

        databaseHelper = DatabaseHelper.getDB(getApplicationContext());
        models = new ArrayList<>(databaseHelper.notificationDAO().getAllIncome());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IncomeAdapter(models, this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}