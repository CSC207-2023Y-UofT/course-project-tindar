package com.courseproject.tindar.ui.home;

import androidx.lifecycle.ViewModel;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private ArrayList<String> potentialProfiles

    public HomeViewModel() {
        ArrayList<String> potentialProfiles = new ArrayList<String>();

        DatabaseHelper databaseHelper = new DatabaseHelper();
        readProfile(potentialProfiles.get(0));

    }

    public EditProfileDsResponseModel updateShownProfile(){
        String nextProfile = this.potentialProfiles.get(0);
        this.potentialProfiles.remove(0);
        this.potentialProfiles.add(nextProfile);

        return readProfile(nextProfile);
    }


}