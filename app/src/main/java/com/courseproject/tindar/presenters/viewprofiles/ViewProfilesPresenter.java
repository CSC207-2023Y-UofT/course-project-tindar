package com.courseproject.tindar.presenters.viewprofiles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.courseproject.tindar.ds.DatabaseHelper;

import java.util.ArrayList;

public class ViewProfilesPresenter extends ViewModel{
    private MutableLiveData<String> DisplayNameText;
    private MutableLiveData<String> GenderText;
    private MutableLiveData<String> BirthdayText;
    private MutableLiveData<String> LocationText;
    private MutableLiveData<String> AboutMeText;

    public ViewProfilesPresenter() {
        DisplayNameText = new MutableLiveData<>();
        GenderText = new MutableLiveData<>();
        BirthdayText = new MutableLiveData<>();
        LocationText = new MutableLiveData<>();
        AboutMeText = new MutableLiveData<>();
    }

    public LiveData<String> getDisplayName() {
        return DisplayNameText;
    }
    public LiveData<String> getGender() {
        return GenderText;
    }
    public LiveData<String> getBirthday() {
        return BirthdayText;
    }
    public LiveData<String> getLocation() {
        return LocationText;
    }
    public LiveData<String> getAboutMe() {
        return AboutMeText;
    }
}
