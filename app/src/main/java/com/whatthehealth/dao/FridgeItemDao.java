package com.whatthehealth.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.whatthehealth.entities.FridgeItem;

import java.util.List;

@Dao
public interface FridgeItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FridgeItem item);

    @Query("DELETE FROM fridge_table")
    void deleteAll();

    @Query("SELECT * FROM fridge_table")
    LiveData<List<FridgeItem>> getAllItems();

    @Query("SELECT COUNT(*) FROM fridge_table")
    LiveData<Integer> getItemsCount();

    @Update
    void updateItem(FridgeItem item);

    @Query("DELETE FROM fridge_table WHERE checked = :v")
    void deleteItems(boolean v);
}