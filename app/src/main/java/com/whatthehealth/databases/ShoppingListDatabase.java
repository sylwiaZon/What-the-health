package com.whatthehealth.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.whatthehealth.dao.ShopItemDao;
import com.whatthehealth.entities.ShopItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ShopItem.class}, version = 1, exportSchema = false)
public abstract class ShoppingListDatabase extends RoomDatabase {

    public abstract ShopItemDao shopItemDao();

    private static volatile ShoppingListDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ShoppingListDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShoppingListDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShoppingListDatabase.class, "shopping_list_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
