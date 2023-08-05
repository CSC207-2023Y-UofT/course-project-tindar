package com.courseproject.tindar.entities;


public class ConvoList implements ConvoModel {
    public String DisplayName;
    public String lastTextPreview;
    public String lastTextTime;

    public ConvoList(String DisplayName, String lastTextPreview, String lastTextTime) {
        this.DisplayName = DisplayName;
        this.lastTextPreview = lastTextPreview;
        this.lastTextTime = lastTextTime;
    }



    public void setUsername(String DisplayName) {
        this.DisplayName = DisplayName;
    }


    public void setLastTextPreview(String lastTextPreview) {
        this.lastTextPreview = lastTextPreview;
    }


    public void setLastTextTime(String lastTextTime) {
        this.lastTextTime = lastTextTime;
    }


//    @Override
//    public String getProfilePic() {
//        return null;
//    }

    @Override
    public String getDisplayName() {
        // Implement the logic to get the display name here
        // For example, you can return the DisplayName as the display name.
        return DisplayName;
    }

    @Override
    public String getLastMessageDisplayText() {
        // Implement the logic to get the last message display text here
        // For example, you can return the lastTextPreview.
        return lastTextPreview;
    }

    @Override
    public String getLastMessageTime() {
        // Implement the logic to get the last message time here
        // For example, you can return the lastTextTime.
        return lastTextTime;
    }


}
