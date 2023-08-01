package com.courseproject.tindar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * A simple {@link ViewModel} subclass. This is a view model for the blank nav activity to share data among the activity
 * and its fragments.
 */
public class BlankNavViewModel extends ViewModel {

    /**
     * user id for the logged in user
     */
    private final MutableLiveData<String> userId = new MutableLiveData<>();

    /**
     * sets the user id passed from the MainActivity once the user logs in
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId.setValue(userId);
    }

    /**
     * gets the user id that is currently logged in
     *
     * @return
     */
    public LiveData<String> getUserId() {
        return userId;
    }
}
