package com.example.android_quizapp_sqlite_v3;

import androidx.annotation.NonNull;

public class Category {
    public static final int ADDITIONS = 1;
    public static final int SUBTRACTIONS = 2;
    public static final int MULTIPLICATIONS = 3;
    public static final int DIVISIONS = 4;
    public static final int MIXED = 5;

    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return getName();
    }
}