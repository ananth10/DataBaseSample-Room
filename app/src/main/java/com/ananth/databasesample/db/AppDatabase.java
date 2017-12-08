package com.ananth.databasesample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ananth.databasesample.model.ContactModel;

/**
 * Created by Babu on 10/4/2017.
 */

@Database(entities = {ContactModel.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static  AppDatabase mInstance;

    public static AppDatabase getDataBase(Context context)
    {
        if(mInstance==null)
        {
            mInstance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"contact_db").build();
        }

        return mInstance;

    }

    public static void destroyInstance() {
        mInstance = null;
    }
    public abstract ContactDao getContactDao();
}
