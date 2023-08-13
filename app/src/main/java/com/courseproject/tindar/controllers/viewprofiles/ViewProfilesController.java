package com.courseproject.tindar.controllers.viewprofiles;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.userlist.UserListInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInputBoundary;

import java.util.ArrayList;

/**
 * This controller class accepts user input and connects it to back-end view profile, user list,
 * and like list interactors
 */
public class ViewProfilesController {
    /**
     * interactor of the View Profile feature
     */
    final ViewProfileInputBoundary viewProfilesUserInput;
    /**
     * interactor of the User List feature
     */
    final UserListInputBoundary userListUserInput;
    /**
     * interactor of the List List feature
     */
    final LikeListInputBoundary likeListUserInput;

    /**
     * Constructs controller for the view profiles UI to read profile, get all other user list, and like or remove
     * like another user
     *
     * @param viewProfilesUserInput interactor of the View Profile feature
     * @param userListUserInput interactor of the User List feature
     * @param likeListUserInput interactor of the Like List feature
     */
    public ViewProfilesController(ViewProfileInputBoundary viewProfilesUserInput,
                                  UserListInputBoundary userListUserInput, LikeListInputBoundary likeListUserInput) {
        this.viewProfilesUserInput = viewProfilesUserInput;
        this.userListUserInput = userListUserInput;
        this.likeListUserInput = likeListUserInput;
    }

    /**
     * Reads the a user's profile information based on the provided user ID.
     *
     * @param userId The user ID of the user's account.
     * @return A ViewProfilesDsResponseModel containing the above user's profile information.
     */
    public ViewProfileResponseModel getProfile(String userId){
        return viewProfilesUserInput.getProfile(userId);
    }

    public ArrayList<String> getAllOtherUserIds(String userId){return userListUserInput.getAllOtherUserIds(userId);}

    public boolean checkLiked(String userId, String otherUserId) {
        return likeListUserInput.checkLiked(userId, otherUserId);
    }

    /**
     * Accept the users input to like' a profile
     *
     * @param userId the users input to initiate 'like'
     * @param otherUserId the user being 'liked'
     */
    public void addLike(String userId, String otherUserId){
        // User input of userId liking otherUserId
        this.likeListUserInput.addLike(userId, otherUserId);
    }

    /**
     * Accept the users input to remove a 'like' of a profile
     *
     * @param userId the users input to remove 'like'
     * @param otherUserId the user being 'unliked'
     */
    public void removeLike(String userId, String otherUserId){
        // User input of userId unliking otherUserId
        this.likeListUserInput.removeLike(userId, otherUserId);
    }
}
