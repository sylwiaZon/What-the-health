package com.whatthehealth.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.whatthehealth.dao.ShopItemDao;
import com.whatthehealth.databases.ShoppingListDatabase;
import com.whatthehealth.entities.ShopItem;

import java.util.List;

public class ShoppingListRepository {
    private ShopItemDao shopItemDao;
    private LiveData<List<ShopItem>> allItems;

    public ShoppingListRepository(Context context) {
        ShoppingListDatabase db = ShoppingListDatabase.getDatabase(context);
        shopItemDao = db.shopItemDao();
        allItems = shopItemDao.getAllItems();
    }

    public LiveData<List<ShopItem>> getAllItems() {
        return allItems;
    }

    public void insert(ShopItem item) {
        ShoppingListDatabase.databaseWriteExecutor.execute(() -> {
            shopItemDao.insert(item);
        });
    }

    public void changeItemState(ShopItem item){
        ShoppingListDatabase.databaseWriteExecutor.execute(() -> {
            shopItemDao.updateItem(item);
        });
    }

    public void deleteCheckedItems(){
        ShoppingListDatabase.databaseWriteExecutor.execute(() -> {
            shopItemDao.deleteItems(true);
        });
    }
}
