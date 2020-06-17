package com.whatthehealth.ui.fridge;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.whatthehealth.entities.FridgeItem;
import com.whatthehealth.repositories.FridgeRepository;
import java.util.List;

public class FridgeViewModel extends AndroidViewModel {

    private FridgeRepository fridgeRepository;

    private LiveData<List<FridgeItem>> allItems;
    private LiveData<Integer> itemsCount;

    public FridgeViewModel(Application application) {
        super(application);
        fridgeRepository = new FridgeRepository(application);
        allItems = fridgeRepository.getAllItems();
        itemsCount = fridgeRepository.getItemsCount();
    }

    LiveData<List<FridgeItem>> getAllItems() { return allItems; }

    public LiveData<Integer> getItemsCount() {
        return itemsCount;
    }

    public void insert(FridgeItem item) { fridgeRepository.insert(item); }
}