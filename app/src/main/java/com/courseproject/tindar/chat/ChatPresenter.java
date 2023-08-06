package com.courseproject.tindar.chat;

import java.util.ArrayList;

/**
 * Creates and maintains an ArrayList of MessageModels for ChatActivity to display.
 * Pulls information from the message database using a ChatDatabaseGateway.
 */
public class ChatPresenter {
    private ArrayList<MessageModel> messageList;
    private ChatDatabaseGateway chatDatabaseGateway;
    private String[] userIDs;

    public ChatPresenter(String[] userIDs){
        this.userIDs = userIDs;
        this.messageList = new ArrayList<>();
        //this.chatDatabaseGateway = new ChatDatabaseHelper(this.userIDs); // TODO
    }

    public ArrayList<MessageModel> getMessageList(){
        return this.messageList;
    }

    public void loadMessages(){
        // TODO: use ChatDatabaseGateway to get message info, and update the list accordingly
    }
}
