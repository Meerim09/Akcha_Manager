package com.meerim.akchamanager.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.meerim.akchamanager.database.DatabaseHelper;
import com.meerim.akchamanager.database_budget.BudgetDBHelper;
import com.meerim.akchamanager.database_expense.ExpDatabaseHelper;
import com.meerim.akchamanager.databinding.ActivityAddBinding;
import com.meerim.akchamanager.goals_db.GoalsDBHelper;
import com.meerim.akchamanager.models.BudgetModel;
import com.meerim.akchamanager.models.ExpenseModel;
import com.meerim.akchamanager.models.GoalsModel;
import com.meerim.akchamanager.models.IncomeModel;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityAddBinding binding;
    private ArrayList<String> paths;
    private int selectedIndex = 0;
    private DatabaseReference mDatabase;
    String cont = "";
    private int totalSpentAmount = 0;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        paths = new ArrayList<>();
        cont = getIntent().getStringExtra("ctx");


        switch (cont) {
            case "budget":
            case "goals":
                binding.edDateLayout.setVisibility(View.GONE);
                binding.imgDatePicker.setVisibility(View.GONE);
                binding.edNameLayout.setHint("Бюджетдин аталышы");
                binding.spinnerDropdown.setVisibility(View.GONE);
                break;

        }


        Log.d(TAG, "totalSpentAmount: " + totalSpentAmount);

        BudgetDBHelper databaseHelper = BudgetDBHelper.getBudget(getApplicationContext());

        List<BudgetModel> models = databaseHelper.budgetDAO().getAllBudgets();
        for (int i = 0; i < models.size(); i++) {
            Log.d(TAG, "budgetList: " + models.get(i));
            paths.add(models.get(i).getName());
        }

        paths.add("Бюджетти тандаңыз");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ActivityAdd.this,
                android.R.layout.simple_spinner_item,
                paths
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerDropdown.setAdapter(adapter);
        binding.spinnerDropdown.setOnItemSelectedListener(this);

        binding.imgClose.setOnClickListener(v -> finish());

        binding.btnSave.setOnClickListener(v2 -> {

            if (!binding.edAmount.getText().toString().equals("")) {

                if (!binding.edName.getText().toString().equals("")) {

                    saveData();
                    Toast.makeText(this, "Сакталды!", Toast.LENGTH_SHORT).show();

                } else binding.edLoginLayout.setError("Enter MAIL");
            } else {
                binding.edNameLayout.setError("Enter Name");
            }
        });

    }

    private void saveData() {
        if (cont.equals("income")) {
            DatabaseHelper databaseHelper = DatabaseHelper.getDB(getApplicationContext());
            databaseHelper.notificationDAO().addIncome(new IncomeModel(binding.edName.getText().toString(), binding.edDate.getText().toString(), Integer.valueOf(binding.edAmount.getText().toString())));

            List<IncomeModel> models = databaseHelper.notificationDAO().getAllIncome();
            for (int i = 0; i < models.size(); i++) {
                Log.d(TAG, "saveData: " + models.get(i));
            }

            startActivity(new Intent(ActivityAdd.this, IncomeActivity.class));
        } else if (cont.equals("expense")) {
            ExpDatabaseHelper databaseHelper = ExpDatabaseHelper.getExpense(getApplicationContext());
            databaseHelper.expenseDAO().addExpense(new ExpenseModel(binding.edName.getText().toString(), binding.edDate.getText().toString(), Integer.valueOf(binding.edAmount.getText().toString()), paths.get(selectedIndex).toString()));

            List<ExpenseModel> models = databaseHelper.expenseDAO().getAllExpense();
            for (int i = 0; i < models.size(); i++) {
                Log.d(TAG, "saveData: " + models.get(i));
            }
            startActivity(new Intent(ActivityAdd.this, ExpenseActivity.class));
        } else if (cont.equals("budget")) {
            BudgetDBHelper databaseHelper = BudgetDBHelper.getBudget(getApplicationContext());
            databaseHelper.budgetDAO().addBudget(new BudgetModel(binding.edName.getText().toString(), Integer.valueOf(binding.edAmount.getText().toString())));

            List<BudgetModel> models = databaseHelper.budgetDAO().getAllBudgets();
            for (int i = 0; i < models.size(); i++) {
                Log.d(TAG, "saveData: " + models.get(i));
            }
            startActivity(new Intent(ActivityAdd.this, BudgetActivity.class));
        } else if (cont.equals("goals")) {
            GoalsDBHelper databaseHelper = GoalsDBHelper.getExpense(getApplicationContext());
            databaseHelper.expenseDAO().addGoals(new GoalsModel(binding.edName.getText().toString(), Integer.valueOf(binding.edAmount.getText().toString())));

            List<GoalsModel> models = databaseHelper.expenseDAO().getAllGoals();
            for (int i = 0; i < models.size(); i++) {
                Log.d(TAG, "saveData: " + models.get(i));
            }
            startActivity(new Intent(ActivityAdd.this, GoalsActivity.class));
        }
        finish();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedIndex = i;
        if (paths.get(i).equals("+Add")) {
            showAddCategoryDialogue();
        }
    }

    private void showAddCategoryDialogue() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ActivityAdd.this);
        builder1.setTitle("Add Category");
        final EditText input = new EditText(ActivityAdd.this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder1.setView(input);
        builder1.setPositiveButton("OK", (dialog, which) -> {
            String text = input.getText().toString();
            paths.add(0, text);
            selectedIndex = 0;
            binding.spinnerDropdown.setSelection(0);

            //make list on firebase
            dialog.dismiss();
            // Handle the OK button press
        });

        builder1.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();

            // Handle the Cancel button press
        });
        builder1.show();


    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}