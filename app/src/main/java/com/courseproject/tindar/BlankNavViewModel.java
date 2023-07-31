package com.courseproject.tindar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BlankNavViewModel extends ViewModel {
    private final MutableLiveData<String> userId = new MutableLiveData<>();

    public void setUserId(String userId) {
        this.userId.setValue(userId);
    }

    public LiveData<String> getUserId() {
        return userId;
    }
}
