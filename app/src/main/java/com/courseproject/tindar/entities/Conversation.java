package com.courseproject.tindar.entities;

import java.util.ArrayList;

public abstract class Conversation {
    private String user1;
    private String user2;
    private ArrayList<Message> messages;
    private String conversationID;

    protected Conversation(String user1, String user2){
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<Message>(); 
        this.conversationID = user1 + user2; //implementation will probably be changed
    }

    //TODO complete abstract methods
    public boolean addMessage();
    public boolean deleteMessage();
    public boolean addReactionToMessage();
    public boolean flagMessage();

    // Getter and setter methods
    public String getUser1() {
        return this.user1;
    }

    public String getUser2() {
        return this.user2;
    }

    public ArrayList<Message> getMessages() {
        return this.messages;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    protected void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /* 
    public String getConversationID() {
        return this.conversationID;
    }

    protected void setID(String newConversationID) {
        this.conversationID = newConversationID;
    }
    */
}



