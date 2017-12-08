package com.ananth.databasesample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ananth.databasesample.model.ContactModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Babu on 10/4/2017.
 */

@Dao
public interface ContactDao {

    @Query("Select * from ContactModel")
    LiveData<List<ContactModel>> getAllContacts();

    @Query("Select * from ContactModel where mPhone = :phone")
    LiveData<ContactModel> getContactByPhone(String phone);

    @Query("Select * from ContactModel where mPhone = :phone")
    ContactModel isContactExit(String phone);

    @Insert(onConflict = REPLACE)
    void addContact(ContactModel contact);

    @Delete
    void deleteContact(ContactModel contact);

    @Update
    void updateContact(ContactModel contact);

//    @Query("UPDATE ContactModel SET endAddress = :end_address  WHERE id = :tid")
//            int updateContact(long tid, String end_address);

}
