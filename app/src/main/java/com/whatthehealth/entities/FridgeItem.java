package com.whatthehealth.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fridge_table")
public class FridgeItem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item")
    private String item;

    @ColumnInfo(name = "checked")
    private boolean checked;

    public FridgeItem(String item) {
        this.item = item;
        checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void check() {
        checked = !checked;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}