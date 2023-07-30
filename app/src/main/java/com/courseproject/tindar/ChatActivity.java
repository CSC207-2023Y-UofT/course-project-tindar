package com.courseproject.tindar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class ChatActivity extends AppCompatActivity {
    // Variable here for getting other userID and display name
    // variable here for storing your userID
    EditText chatInput;
    ImageButton sendMessageButton;
    ImageButton backButton;
    RecyclerView chatRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.chatInput = findViewById(R.id.new_chat_input);
        this.sendMessageButton = findViewById(R.id.send_message_button);
        this.backButton = findViewById(R.id.back_button);
        this.chatRecyclerView = findViewById(R.id.chat_recycler_view);
    }



}