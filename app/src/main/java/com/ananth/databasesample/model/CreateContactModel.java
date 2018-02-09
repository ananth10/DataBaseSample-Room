package com.ananth.databasesample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;


/**
 * Created by Babu on 2/9/2018.
 */

public class CreateContactModel extends BaseObservable {

    private String name;
    private String email;
    private String phone;
    private String location;
    private String image;
    private String buttonText;
    private String headerText;
    private boolean phoneEnabled;

    public CreateContactModel(String name,String email,String phone,String location,String image)
    {
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.location=location;
        this.image=image;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }
    @Bindable
    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        notifyPropertyChanged(BR.buttonText);
    }

    @Bindable
    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
        notifyPropertyChanged(BR.headerText);
    }

    @Bindable
    public boolean getPhoneEnabled() {
        return phoneEnabled;
    }
    public void setPhoneEnabled(boolean phoneEnabled) {
        this.phoneEnabled = phoneEnabled;
        notifyPropertyChanged(BR.phoneEnabled);
    }
}
