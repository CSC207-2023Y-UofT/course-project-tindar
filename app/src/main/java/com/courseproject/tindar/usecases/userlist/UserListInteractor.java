package com.courseproject.tindar.usecases.userlist;

import java.util.ArrayList;
/**
 * This class implements the user list use case's input boundary.
 * It interacts with the data source gateway to retrieve a list of user IDs excluding the
 * provided user's ID.
 */
public class UserListInteractor implements UserListInputBoundary{

    final UserListDsGateway userListDsGateway;

    /**
     * Constructs a new UserListInteractor with the specified UserListDsGateway.
     *
     * @param userListDsGateway The data source gateway for user list-related operations.
     */
    public UserListInteractor(UserListDsGateway userListDsGateway) {
        this.userListDsGateway = userListDsGateway;
    }

    /**
     * Retrieves a list of user IDs excluding the provided user's ID.
     *
     * @param userId The ID of the user for whom the list is being generated.
     * @return An ArrayList containing the user IDs of other users, excluding the provided user's ID.
     */
    public ArrayList<String> getAllOtherUserIds(String userId){
        return userListDsGateway.getAllOtherUserIds(userId);
    }
}
