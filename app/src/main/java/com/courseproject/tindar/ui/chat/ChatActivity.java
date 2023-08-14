package com.courseproject.tindar.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.courseproject.tindar.R;

import com.courseproject.tindar.controllers.chat.ChatController;
import com.courseproject.tindar.ds.DatabaseHelper;

// TODO: remove TindarMessage import when database is properly connected
import com.courseproject.tindar.usecases.chat.ChatInputBoundary;
import com.courseproject.tindar.usecases.chat.ChatInteractor;
import com.courseproject.tindar.usecases.chat.ChatRequestModel;

// TODO: consider removing Timestamp import when database is properly connected
import java.sql.Timestamp;

/**
 * For the one-on-one chat screen for specific conversations.
 * This displays the messages, bar at the top, navigation buttons, and inputs for conversing.
 * This java file and dictates the bigger picture of one-on-one chat display
 * and functionality of the buttons on the screen.
 * <p>
 * Will add button functionality as other features are implemented.
 * <p>
 * Display layout is in activity_chat.xml.
 * Given a list of messages and basic info,
 * ChatRecyclerViewAdapter.java handles displaying messages.
 *
 * @author Sophia Wan
 */
public class ChatActivity extends AppCompatActivity {
    /*
        TODO:
        - display conversation partner's display name and not userID
        - possibly reverse the message list implementation?
        - userID, otherUserID, conversationPartnerDisplayName, and probably a few other things
            should probably be placed in a facade or something
     */

    /** userID of current user. Needed to generate message models with the proper info. */
    private String userId;
    /**
     * userID of current user's conversation partner.
     * Needed to generate message models with the proper info.
     */
    private String otherUserId;

    private String conversationId;

    /** Chat controller handling user inputs */
    private ChatController chatController;

    /** Where the user types their messages. */
    private EditText chatInput;

    /** Still not sure what this does. I think this manages chatRecyclerView. */
    private ChatRecyclerViewAdapter adapter;

    /**
     * I think this runs when a new ChatActivity object is made.
     * @param savedInstanceState still have no idea what this is to be honest.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This "came with the class." I assume it's right and it works, so I'm not touching it.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat); // tells us the layout follows activity_chat.xml

        Intent intent = this.getIntent();

        // TODO: actually get info passed in, rather than hardcoding this
        // TODO: see if we can have this info passed and stored in some sort of facade or model
        //      - flagged in issue #51
        this.userId = intent.getStringExtra("current_user_id");
        this.otherUserId = intent.getStringExtra("conversation_partner_id");
        String conversationPartnerDisplayName = intent.getStringExtra("conversation_partner_display_name");

        ChatInputBoundary chatInteractor =
                new ChatInteractor(DatabaseHelper.getInstance(getApplicationContext()));
        this.chatController = new ChatController(chatInteractor);

        // setting input and view instance variables to match what's in the display
        TextView conversationPartnerDisplayNameDisplay = findViewById(R.id.conversation_partner_display_name);
        this.chatInput = findViewById(R.id.new_chat_input);

        // getting the screen to display the correct name for the conversation partner
        conversationPartnerDisplayNameDisplay.setText(conversationPartnerDisplayName);

        this.conversationId = this.chatController.getConversationId(this.userId, this.otherUserId);

        // Everything after this in this method handles messages and message display.
        // To be honest, I still don't fully understand all of it

        this.adapter = new ChatRecyclerViewAdapter(this.chatController.getMessageList(this.conversationId),
                this.userId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        RecyclerView chatRecyclerView = findViewById(R.id.chat_recycler_view); // matches recycler to layout
        chatRecyclerView.setAdapter(adapter);
        chatRecyclerView.setLayoutManager(linearLayoutManager);
    }

    // TODO: this should be refactored.
    //      both to make future implementation of other message types easier,
    //      and because I feel like the sending and creation of the new message should go elsewhere.
    //      I don't think we should be importing Tindar messages.
    // TODO: timezone support?

    // TODO: chatInput should clear once the button is pressed, so that people don't have to delete
    //          the previous message to send a new one
    // TODO: shouldn't send empty messages
    /**
     * Called when sendMessageButton is called. Creates the new message, and informs adapter
     * that it needs to update the display.
     * ---------------------------------------------------------------------------------------------
     * Currently just adds the message to loadedMessages.
     * Will probably call some sort of conversationManager class and be slightly reimplemented
     * with the database.
     */
    public void sentMessage(View v){
        String input = (this.chatInput.getText()).toString();
        if(!input.isEmpty()){
            ChatRequestModel newMessage = new ChatRequestModel(input, new Timestamp(System.currentTimeMillis()),
                    this.userId, this.otherUserId, conversationId);
            this.chatController.sendMessage(newMessage);

            // UI
            this.chatInput.getText().clear();
            this.adapter.setMessageList(this.chatController.getMessageList(conversationId));
            this.adapter.notifyItemChanged(-1);
        }
    }

    public void backButtonPressed(View v){
        super.onBackPressed();
    }
}
