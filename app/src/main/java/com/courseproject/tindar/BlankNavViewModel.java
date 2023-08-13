package com.courseproject.tindar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * A simple {@link ViewModel} subclass.
 * This is a view model for the blank nav activity to share data
 * among the activity and its fragments.
 */
public class BlankNavViewModel extends ViewModel {

    /**
     * user id for the logged-in user
     */
    private final MutableLiveData<String> userId = new MutableLiveData<>();
    private final MutableLiveData<Integer> viewProfileUserIdIndex = new MutableLiveData<>();

    /**
     * sets the user id passed from the MainActivity once the user logs in
     *
     * @param userId the new user id of the account
     */
    public void setUserId(String userId) {
        this.userId.setValue(userId);
    }

    /**
     * @return the user id that is currently logged in
     */
    public LiveData<String> getUserId() {
        return userId;
    }

    /**
     * Sets the index of the next profile to view in the view profiles dataset.
     *
     * @param viewProfileUserIdIndex The index of the next profile to display.
     */
    public void setViewProfileUserIdIndex(int viewProfileUserIdIndex) {
        this.viewProfileUserIdIndex.setValue(viewProfileUserIdIndex);
    }

    /**
     * Retrieves the index of the current profile being viewed.
     *
     * @return The index in the view profile dataset.
     */
    public LiveData<Integer> getViewProfileUserIdIndex() {
        return viewProfileUserIdIndex;
    }

}
