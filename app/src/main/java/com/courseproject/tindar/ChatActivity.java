package com.courseproject.tindar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.entities.TindarMessage;

import java.sql.Timestamp;
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

    ArrayList<MessageModel> loadedMessages;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.chatInput = findViewById(R.id.new_chat_input);
        this.sendMessageButton = findViewById(R.id.send_message_button);
        this.backButton = findViewById(R.id.back_button);

        this.loadMessages();

        ChatRecyclerViewAdapter adapter = new ChatRecyclerViewAdapter(this, loadedMessages,
                this.userID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        this.chatRecyclerView = findViewById(R.id.chat_recycler_view);
        this.chatRecyclerView.setAdapter(adapter);
        this.chatRecyclerView.setLayoutManager(linearLayoutManager);
    }

    //TODO: this should actually call a different class for the messages. probably conversation.
    private void loadMessages(){
        TindarMessage testMessage1 = new TindarMessage("first message sent", new Timestamp(2023, 02, 25, 18, 0, 0, 0),"user1", "user2");
        TindarMessage testMessage2 = new TindarMessage("second message sent", new Timestamp(2023, 03, 25, 18, 0, 0, 0),"user1", "user2");
        TindarMessage testMessage3 = new TindarMessage("first message received", new Timestamp(2023, 04, 25, 18, 0, 0, 0),"user2", "user1");
        this.loadedMessages = new ArrayList<MessageModel>();
        this.loadedMessages.add(testMessage1);
        this.loadedMessages.add(testMessage2);
        this.loadedMessages.add(testMessage2);
    }
}