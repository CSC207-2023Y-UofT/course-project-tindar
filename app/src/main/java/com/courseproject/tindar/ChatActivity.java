package com.courseproject.tindar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.courseproject.tindar.entities.MessageInterface;

import java.util.ArrayList;

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

    ArrayList<MessageInterface> loadedMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.chatInput = findViewById(R.id.new_chat_input);
        this.sendMessageButton = findViewById(R.id.send_message_button);
        this.backButton = findViewById(R.id.back_button);
        this.chatRecyclerView = findViewById(R.id.chat_recycler_view);

        this.loadMessages();

        ChatRecyclerViewAdapter adapter = new ChatRecyclerViewAdapter(this, loadedMessages);
        this.chatRecyclerView.setAdapter(adapter);
        this.chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //TODO
    private void loadMessages(){
        this.loadedMessages = new ArrayList<MessageInterface>();
    }
}