package com.whatthehealth.repositories;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.whatthehealth.dao.RecipeItemDao;
import com.whatthehealth.databases.RecipeDatabase;
import com.whatthehealth.entities.RecipeItem;

import java.util.List;


public class RecipeRepository {
    private RecipeItemDao recipeItemDao;
    private LiveData<List<RecipeItem>> allItems;

    public RecipeRepository(Context context) {
        RecipeDatabase db = RecipeDatabase.getDatabase(context);
        recipeItemDao = db.recipeItemDao();
        allItems = recipeItemDao.getAllItems();
    }

    public LiveData<List<RecipeItem>> getAllItems() {
        return allItems;
    }

    public LiveData<Integer> getItemsCount(){
        return recipeItemDao.getItemsCount();
    }

    public void insert(RecipeItem item) {
        System.out.println("Inserted !!!!!" + item.getTitle());
        RecipeDatabase.databaseWriteExecutor.execute(() -> {
            recipeItemDao.insert(item);
        });
    }

    public void changeItemState(RecipeItem item){
        RecipeDatabase.databaseWriteExecutor.execute(() -> {
            recipeItemDao.updateItem(item);
        });
    }
}
