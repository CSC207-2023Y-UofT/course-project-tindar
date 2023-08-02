package com.courseproject.tindar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
//test
public class ChatActivity extends AppCompatActivity {
    /**
     * For the one-on-one chat screen for specific conversations.
     * Unfinished; will add functionality as other features are implemented.
     * This java file stores stat info, and dictates functionality of the buttons on the screen.
     * Display details are in activity_chat.xml.
     */

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