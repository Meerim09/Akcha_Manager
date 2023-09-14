package com.meerim.akchamanager.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "goals")
public class GoalsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int amount;

    public GoalsModel(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    // Getter and Setter methods for the variables

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

