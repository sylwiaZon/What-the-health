package com.whatthehealth.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.whatthehealth.dao.FridgeItemDao;
import com.whatthehealth.entities.FridgeItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FridgeItem.class}, version = 1, exportSchema = false)
public abstract class FridgeDatabase extends RoomDatabase {

    public abstract FridgeItemDao fridgeItemDao();

    private static volatile FridgeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FridgeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FridgeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FridgeDatabase.class, "fridge_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}