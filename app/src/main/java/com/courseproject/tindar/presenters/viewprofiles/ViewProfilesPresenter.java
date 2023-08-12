package com.courseproject.tindar.presenters.viewprofiles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The ViewProfilesPresenter class manages data related to user profile information
 * for presentation in the UI.
 */
public class ViewProfilesPresenter extends ViewModel{

    private final MutableLiveData<String> DisplayNameText;
    private final MutableLiveData<String> GenderText;
    private final MutableLiveData<String> BirthdayText;
    private final MutableLiveData<String> LocationText;
    private final MutableLiveData<String> AboutMeText;

    /**
     * Constructs a new ViewProfilesPresenter object and initializes the LiveData fields
     * with default values.
     */
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
    }

    /**
     * Updates the data in the LiveData fields with new values.
     */
    public void updateData() {
        DisplayNameText.setValue("Display Name2");
        GenderText.setValue("Gender2");
        BirthdayText.setValue("Birthday2");
        LocationText.setValue("Location2");
        AboutMeText.setValue("About Me2");

    }



    /**
     * Retrieves the LiveData containing the display name information.
     *
     * @return LiveData containing the display name text.
     */
    public LiveData<String> getDisplayName() {
        return DisplayNameText;
    }

    /**
     * Retrieves the LiveData containing the gender information.
     *
     * @return LiveData containing the gender text.
     */
    public LiveData<String> getGender() {
        return GenderText;
    }

    /**
     * Retrieves the LiveData containing the birthday information.
     *
     * @return LiveData containing the birthday text.
     */
    public LiveData<String> getBirthday() {
        return BirthdayText;
    }

    /**
     * Retrieves the LiveData containing the location information.
     *
     * @return LiveData containing the location text.
     */
    public LiveData<String> getLocation() {
        return LocationText;
    }

    /**
     * Retrieves the LiveData containing the "About Me" information.
     *
     * @return LiveData containing the "About Me" text.
     */
    public LiveData<String> getAboutMe() {
        return AboutMeText;
    }
}
