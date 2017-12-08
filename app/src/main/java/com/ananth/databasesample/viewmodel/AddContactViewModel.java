package com.ananth.databasesample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.ananth.databasesample.db.AppDatabase;
import com.ananth.databasesample.model.ContactModel;

import java.util.concurrent.ExecutionException;

/**
 * Created by Babu on 10/4/2017.
 */

public class AddContactViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddContactViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDataBase(this.getApplication());
    }

    /**
     * Below method is used to add new contact
     * @param contactModel - get info from the user
     * */
    public void addContact(ContactModel contactModel) {
        new AddContact(appDatabase).execute(contactModel);
    }

    private static class AddContact extends AsyncTask<ContactModel, Void, Void> {

        AppDatabase mAppDataBase;

        private AddContact(AppDatabase appDatabase) {
            this.mAppDataBase = appDatabase;
            System.out.println("addContact:" + "123");
        }

        @Override
        protected Void doInBackground(ContactModel... params) {
            mAppDataBase.getContactDao().addContact(params[0]);
            return null;
        }
    }


    /**
     * Below method is used to update the contact by using the phone number
     * @param contactModel - get info from the user
     * */
    public void updateContact(ContactModel contactModel) {
        new UpdateContact(appDatabase).execute(contactModel);
    }

    private static class UpdateContact extends AsyncTask<ContactModel, Void, Void> {

        AppDatabase mAppDataBase;

        private UpdateContact(AppDatabase appDatabase) {
            this.mAppDataBase = appDatabase;
            System.out.println("UpdateContact:" + "123");
        }

        @Override
        protected Void doInBackground(ContactModel... params) {
            mAppDataBase.getContactDao().updateContact(params[0]);
            return null;
        }
    }

    /**
     * Below method is used to check the phone number already exist or not
     * @param phone - Entered phone number by user
     * */

    public Boolean isContactExist(String phone) throws ExecutionException, InterruptedException {

        return  new IsContactExist(appDatabase).execute(phone).get();
    }


    public  class IsContactExist extends AsyncTask<String, Boolean, Boolean> {

        AppDatabase mAppDataBase;

        public IsContactExist(AppDatabase appDatabase) {
            this.mAppDataBase = appDatabase;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            System.out.println("model:"+mAppDataBase.getContactDao().getContactByPhone(params[0]));
            if (mAppDataBase.getContactDao().isContactExit(params[0]) != null)
                return true;
            else
                return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
