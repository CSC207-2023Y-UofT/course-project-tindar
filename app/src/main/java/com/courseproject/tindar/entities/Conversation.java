package com.courseproject.tindar.entities;

import java.util.ArrayList;

public abstract class Conversation {
    private String users1;
    private String users2;
    private ArrayList<Message> messages;
    private String id;


    //TODO complete abstract methods


    public abstract void addMessage();
    public abstract void deleteMessage();
    public abstract void addReactionToMessage();
    public abstract void flagMessage();

    // Getter methods
    public String getUsers1() {
        return users1;
    }

    public String getUsers2() {
        return users2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getId() {
        return id;
    }

    // Setter methods
    public void setUsers1(String users1) {
        this.users1 = users1;
    }

    public void setUsers2(String users2) {
        this.users2 = users2;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void setId(String id) {
        this.id = id;
    }
}



