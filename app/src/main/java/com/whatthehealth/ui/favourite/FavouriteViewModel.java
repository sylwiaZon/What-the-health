package com.whatthehealth.ui.favourite;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.whatthehealth.entities.RecipeItem;
import com.whatthehealth.repositories.RecipeRepository;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;

    private LiveData<List<RecipeItem>> allItems;
    private LiveData<Integer> itemsCount;

    public FavouriteViewModel(Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        allItems = recipeRepository.getAllItems();
        itemsCount = recipeRepository.getItemsCount();
    }

    public LiveData<List<RecipeItem>> getAllItems() { return allItems; }

    public LiveData<Integer> getItemsCount() {
        return itemsCount;
    }

    public void insert(RecipeItem item) { recipeRepository.insert(item); }
}