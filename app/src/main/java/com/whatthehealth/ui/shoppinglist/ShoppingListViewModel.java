package com.whatthehealth.ui.shoppinglist;

import android.app.Application;
import android.content.ClipData;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whatthehealth.entities.ShopItem;
import com.whatthehealth.repositories.ShoppingListRepository;

import java.util.List;

public class ShoppingListViewModel extends AndroidViewModel {

    private ShoppingListRepository shoppingListRepository;

    private LiveData<List<ShopItem>> allItems;

    public ShoppingListViewModel (Application application) {
        super(application);
        shoppingListRepository = new ShoppingListRepository(application);
        allItems = shoppingListRepository.getAllItems();
    }

    LiveData<List<ShopItem>> getAllItems() { return allItems; }

    public void insert(ShopItem item) { shoppingListRepository.insert(item); }
}