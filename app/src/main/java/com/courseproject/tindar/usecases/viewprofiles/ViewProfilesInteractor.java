package com.courseproject.tindar.usecases.viewprofiles;

public class ViewProfilesInteractor implements ViewProfilesInputBoundary{

    final ViewProfilesDsGateway viewProfilesDsGateway;

    public ViewProfilesInteractor(ViewProfilesDsGateway viewProfilesDsGateway) {
        this.viewProfilesDsGateway = viewProfilesDsGateway;
    }

    @Override
    public ViewProfilesDsResponseModel readNextProfile(String userId) {
        return viewProfilesDsGateway.readNextProfile(userId);
    }
}
