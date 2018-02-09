package com.ananth.databasesample.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.net.Uri;

/**
 * Created by Babu on 10/4/2017.
 */

@Entity
public class ContactModel {

    public int id;
    private String mName;
    private String mEmail;
    @PrimaryKey
    private String mPhone;
    private String mLocation;
    private String mImage;


    public ContactModel(String name, String email, String phone, String location, String image)
    {
        this.mName=name;
        this.mEmail=email;
        this.mPhone=phone;
        this.mLocation=location;
        this.mImage=image;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getLocation() {
        return mLocation;
    }
    public String getImage() {
        return mImage;
    }

}
