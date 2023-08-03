package com.courseproject.tindar.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

import java.util.ArrayList;
import java.util.Date;

public class HomeViewModel extends ViewModel {

    private ArrayList<String> potentialProfiles;
    private String DisplayNameText;
    private String GenderText;
    private Date BirthdayText;
    private String LocationText;
    private String AboutMeText;
    private String ProfilePicture;

    public HomeViewModel() {


        ArrayList<String> potentialProfiles = new ArrayList<String>();

//        EditProfileDsResponseModel readProfileResponseModel =
//                new EditProfileDsResponseModel(DisplayNameText, BirthdayText, GenderText, LocationText, ProfilePicture, AboutMeText);
//        readProfileResponseModel.readProfile(potentialProfiles.get(0));

    }

//    public EditProfileDsResponseModel updateShownProfile(){
//        String nextProfile = this.potentialProfiles.get(0);
//        this.potentialProfiles.remove(0);
//        this.potentialProfiles.add(nextProfile);
//
//        return readProfile(nextProfile);
//    }


}