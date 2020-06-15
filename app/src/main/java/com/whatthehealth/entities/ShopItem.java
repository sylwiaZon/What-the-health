package com.whatthehealth.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_list_table")
public class ShopItem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item")
    private String item;

    public ShopItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
