package com.ananth.databasesample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.ananth.databasesample.db.AppDatabase;
import com.ananth.databasesample.model.ContactModel;
import com.ananth.databasesample.utils.Utils;

import java.util.List;

/**
 * Created by Babu on 10/4/2017.
 */

public class ContactViewModel extends AndroidViewModel {

    private final LiveData<List<ContactModel>> mContactList;

    private final LiveData<ContactModel> mSingleContact;

    private String mPhone="";

    private AppDatabase mAppDatabase;

    public ContactViewModel(Application application) {
        super(application);

        mAppDatabase = AppDatabase.getDataBase(this.getApplication());
        mContactList = mAppDatabase.getContactDao().getAllContacts();
        mSingleContact = mAppDatabase.getContactDao().getContactByPhone(Utils.mPhone);
    }

    /**
     *  get All contacts
     * */
    public LiveData<List<ContactModel>> getLiveContactList() {
        return mContactList;
    }

    /**
     * get a single contact
     * */
    public LiveData<ContactModel> getSingleContact() {
        return mSingleContact;
    }

    public void deleteContact(ContactModel contact) {
        new DeleteContact(mAppDatabase).execute(contact);
    }

    private static class DeleteContact extends AsyncTask<ContactModel, Void, Void> {
        AppDatabase mAppDatabase;

        DeleteContact(AppDatabase appDatabase) {
            mAppDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(ContactModel... params) {
            mAppDatabase.getContactDao().deleteContact(params[0]);
            return null;
        }
    }
}
