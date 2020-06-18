package com.whatthehealth.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.whatthehealth.entities.RecipeItem;
import java.util.List;

@Dao
public interface RecipeItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecipeItem item);

    @Query("DELETE FROM recipes_table")
    void deleteAll();

    @Query("SELECT * FROM recipes_table")
    LiveData<List<RecipeItem>> getAllItems();

    @Query("SELECT COUNT(*) FROM recipes_table")
    LiveData<Integer> getItemsCount();

    @Update
    void updateItem(RecipeItem item);

}
