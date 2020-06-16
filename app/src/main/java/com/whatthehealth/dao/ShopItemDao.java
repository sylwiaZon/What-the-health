package com.whatthehealth.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.whatthehealth.entities.ShopItem;

import java.util.List;

@Dao
public interface ShopItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ShopItem item);

    @Query("DELETE FROM shopping_list_table")
    void deleteAll();

    @Query("SELECT * FROM shopping_list_table")
    LiveData<List<ShopItem>> getAllItems();

    @Update
    void updateItem(ShopItem item);

    @Query("DELETE FROM shopping_list_table WHERE checked = :v")
    void deleteItems(boolean v);
}
