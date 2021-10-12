package com.example.app_gymfc.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.example.app_gymfc.Utils.Converters;
import com.example.app_gymfc.dao.UserDao;
import com.example.app_gymfc.models.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
@TypeConverters({Converters.class})

public abstract class GymFcDataBase extends RoomDatabase {
    private static final String DB_NAME = "Gym_Fc_DB";
    private static GymFcDataBase instance;

    public static synchronized GymFcDataBase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx.getApplicationContext(), GymFcDataBase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}