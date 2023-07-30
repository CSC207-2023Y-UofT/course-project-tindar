package com.courseproject.tindar.entities;

import java.util.ArrayList;
import java.sql.Timestamp;

public abstract class Conversation {
    private String user1;
    private String user2;
    private ArrayList<Message> messages;
    private String conversationId;
    private Timestamp timeOfLastMessage;

    protected Conversation(String user1, String user2){
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<Message>(); 
        this.conversationId = user1 + user2; //implementation will probably be changed
    }

    // TODO: when would this return false? 
    // should ensure that this.messages is always in order of oldest to newest. 
    public boolean addMessage(Message newMessage){
        int index = this.messages.size(); 
        while(index >= 1 && ((this.messages).get(index - 1)).getCreationTime().compareTo(newMessage.getCreationTime()) > 0) {
            index = index - 1; 
        }
        this.messages.add(index, newMessage);

        if (index == this.messages.size() - 1){
            this.timeOfLastMessage = newMessage.getCreationTime(); 
        }
        
        return true; 
    }

    // TODO 
    public abstract boolean deleteMessage();
    // public abstract boolean addReactionToMessage();
    // public abstract boolean flagMessage();

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

    public String getConversationId() {
        return this.conversationId;
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

    protected void setId(String newConversationId) {
        this.conversationId = newConversationId;
    }
}



