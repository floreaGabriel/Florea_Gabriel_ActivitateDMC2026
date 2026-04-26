package com.florea_gabriel.labs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MagazinEntity.class}, version = 1)
public abstract class MagazinDatabase extends RoomDatabase {

    private static MagazinDatabase instance;

    public abstract MagazinDao magazinDao();

    public static synchronized MagazinDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MagazinDatabase.class, "magazin_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
