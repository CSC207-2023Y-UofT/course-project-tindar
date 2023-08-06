package com.courseproject.tindar.usecases.userlist;

import java.util.ArrayList;

public class UserListInteractor implements UserListInputBoundary{

    final UserListDsGateway userListDsGateway;

    public UserListInteractor(UserListDsGateway userListDsGateway) {
        this.userListDsGateway = userListDsGateway;
    }

    @Override
    public ArrayList<String> getAllUserIds(){
        return userListDsGateway.getAllUserIds();
    }
}
