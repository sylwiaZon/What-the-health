package com.whatthehealth.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.whatthehealth.dao.FridgeItemDao;
import com.whatthehealth.databases.FridgeDatabase;
import com.whatthehealth.entities.FridgeItem;

import java.util.List;

public class FridgeRepository {
    private FridgeItemDao fridgeItemDao;
    private LiveData<List<FridgeItem>> allItems;

    public FridgeRepository(Context context) {
        FridgeDatabase db = FridgeDatabase.getDatabase(context);
        fridgeItemDao = db.fridgeItemDao();
        allItems = fridgeItemDao.getAllItems();
    }

    public LiveData<List<FridgeItem>> getAllItems() {
        return allItems;
    }

    public LiveData<Integer> getItemsCount(){
        return fridgeItemDao.getItemsCount();
    }

    public void insert(FridgeItem item) {
        FridgeDatabase.databaseWriteExecutor.execute(() -> {
            fridgeItemDao.insert(item);
        });
    }

    public void changeItemState(FridgeItem item){
        FridgeDatabase.databaseWriteExecutor.execute(() -> {
            fridgeItemDao.updateItem(item);
        });
    }

    public void deleteCheckedItems(){
        FridgeDatabase.databaseWriteExecutor.execute(() -> {
            fridgeItemDao.deleteItems(true);
        });
    }
}
