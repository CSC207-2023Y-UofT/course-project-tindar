package com.courseproject.tindar.presenters.viewprofiles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.ui.home.HomeViewModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

import java.util.ArrayList;

public class ViewProfilesPresenter extends ViewModel{
    private MutableLiveData<String> DisplayNameText;
    private MutableLiveData<String> GenderText;
    private MutableLiveData<String> BirthdayText;
    private MutableLiveData<String> LocationText;
    private MutableLiveData<String> AboutMeText;
    private EditProfileDsResponseModel nextProfile;

    public ViewProfilesPresenter() {
        DisplayNameText = new MutableLiveData<>();
        GenderText = new MutableLiveData<>();
        BirthdayText = new MutableLiveData<>();
        LocationText = new MutableLiveData<>();
        AboutMeText = new MutableLiveData<>();

        DisplayNameText.setValue("Display Name");
        GenderText.setValue("Gender");
        BirthdayText.setValue("Birthday");
        LocationText.setValue("Location");
        AboutMeText.setValue("About Me");

        HomeViewModel homeViewModel =
                new ViewModelProvider((ViewModelStoreOwner) this).get(HomeViewModel.class);

//        nextProfile = homeViewModel.updateShownProfile();

    }

    public void updateData() {
        HomeViewModel homeViewModel =
            new ViewModelProvider((ViewModelStoreOwner) this).get(HomeViewModel.class);
//        nextProfile = homeViewModel.updateShownProfile();
//
//        DisplayNameText.setValue();
//        GenderText.setValue();
//        BirthdayText.setValue();
//        LocationText.setValue();
//        AboutMeText.setValue();

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
