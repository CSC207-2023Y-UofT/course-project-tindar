package com.courseproject.tindar.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> DisplayNameText;
    private final MutableLiveData<String> GenderText;
    private final MutableLiveData<String> BirthdayText;
    private final MutableLiveData<String> LocationText;
    private final MutableLiveData<String> AboutMeText;

    public HomeViewModel() {
        DisplayNameText = new MutableLiveData<>();
        GenderText = new MutableLiveData<>();
        BirthdayText = new MutableLiveData<>();
        LocationText = new MutableLiveData<>();
        AboutMeText = new MutableLiveData<>();
//        add code to get list of profiles that exist in database

        DisplayNameText.setValue("Display Name");
        GenderText.setValue("Gender");
        BirthdayText.setValue("Birthday");
        LocationText.setValue("Location");
        AboutMeText.setValue("About Me");
    }

    public void updateShownProfile(){
        // get values of next profile and set values as the new ones
        DisplayNameText.setValue("Display Name");
        GenderText.setValue("Gender");
        BirthdayText.setValue("Birthday");
        LocationText.setValue("Location");
        AboutMeText.setValue("About Me");
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