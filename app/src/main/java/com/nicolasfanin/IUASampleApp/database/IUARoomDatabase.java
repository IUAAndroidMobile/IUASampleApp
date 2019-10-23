package com.nicolasfanin.IUASampleApp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nicolasfanin.IUASampleApp.data.Color;

@Database(entities = {Color.class}, version = 1, exportSchema = false)
public abstract class IUARoomDatabase extends RoomDatabase {

    public abstract ColorDao colorDao();

    private static volatile IUARoomDatabase INSTANCE;

    public static IUARoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IUARoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IUARoomDatabase.class, "IUARoomDatabase").build();
                }
            }
        }
        return INSTANCE;
    }
}
