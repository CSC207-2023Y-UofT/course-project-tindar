package com.courseproject.tindar.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.courseproject.tindar.R;
import com.courseproject.tindar.usecases.chat.MessageModel;
// TODO: remove TindarMessage import when database is properly connected
import com.courseproject.tindar.usecases.chat.TindarMessage;

// TODO: consider removing Timdstamp import when database is properly connected
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * For the one-on-one chat screen for specific conversations.
 * This displays the messages, bar at the top, navigation buttons, and inputs for conversing.
 * This java file and dictates the bigger picture of one-on-one chat display
 * and functionality of the buttons on the screen.
 *
 * Will add button functionality as other features are implemented.
 *
 * Display layout is in activity_chat.xml.
 * Given a list of messages and basic info,
 * ChatRecyclerViewAdapter.java handles displaying messages.
 *
 * @author Sophia Wan
 */
public class ChatActivity extends AppCompatActivity {
    /*
        TODO:
        - un-hardcode "Username" to be the actual conversation partner's display name
        - un-hardcode userID to be the current user's userID
        - possibly reverse the message list implementation?
        - userID, otherUserID, conversationPartnerDisplayName, and probably a few other things
            should probably be placed in a facade or something
     */

    /** userID of current user. Needed to generate message models with the proper info. */
    private String userID;
    /**
     * userID of current user's conversation partner.
     * Needed to generate message models with the proper info.
     */
    private String otherUserID;
    /**
     * Display name of current user's conversation partner.
     * Needed to display the proper info onscreen so that users know who they're chatting with.
     */
    private String conversationPartnerDisplayName;

    /** Displays name of current user's conversation partner. */
    private TextView conversationPartnerDisplayNameDisplay;
    /** List of messages that are already loaded and ready to be displayed by RecyclerView. */
    private ArrayList<MessageModel> loadedMessages;

    /** Where the user types their messages. */
    private EditText chatInput;
    /** The user presses this button to send their typed message in chatInput */
    private ImageButton sendMessageButton;
    /**
     * Navigation button. Current intention is for this button to take the user to their list of
     * conversations. In future, should take them to wherever they were prior to opening this chat.
     */
    private ImageButton backButton;

    /** Displays messages. */
    private RecyclerView chatRecyclerView;
    /** Still not sure what this does. I think this manages chatRecyclerView. */
    private ChatRecyclerViewAdapter adapter;

    /**
     * I think this runs when a new ChatActivity object is made.
     * @param savedInstanceState still have no idea what this is to be honest.
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This "came with the class." I assume it's right and it works, so I'm not touching it.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat); // tells us the layout follows activity_chat.xml

        // TODO: actually get info passed in, rather than hardcoding this
        // TODO: see if we can have this info passed and stored in some sort of facade or model
        //      - flagged in issue #51
        this.userID = "user1";
        this.otherUserID = "user2";
        this.conversationPartnerDisplayName = "User 2";

        // setting input and view instance variables to match what's in the display
        this.conversationPartnerDisplayNameDisplay
                = findViewById(R.id.conversation_partner_display_name);
        this.chatInput = findViewById(R.id.new_chat_input);
        this.sendMessageButton = findViewById(R.id.button_send_message);
        this.backButton = findViewById(R.id.back_button);

        // getting the screen to display the correct name for the conversation partner
        this.conversationPartnerDisplayNameDisplay.setText(this.conversationPartnerDisplayName);

        // Everything after this in this method handles messages and message display.
        // To be honest, I still don't fully understand all of it

        this.loadMessages(); // calling the method that loads messages from the database

        this.adapter = new ChatRecyclerViewAdapter(loadedMessages,
                this.userID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        this.chatRecyclerView = findViewById(R.id.chat_recycler_view); // matches recycler to layout
        this.chatRecyclerView.setAdapter(adapter);
        this.chatRecyclerView.setLayoutManager(linearLayoutManager);
    }

    // TODO: this should be implemented properly
    // and call a different class for the messages. probably conversation.
    /**
     * Loads or adds to the list of messages to be displayed,
     * which is stored in the instance variable loadedMessages.
     * Called by OnCreate. Should also be called whenever the user wants to load more messages.
     *
     * Currently implemented with hardcoded tests; will be reimplemented with the database.
     */
    private void loadMessages(){
        TindarMessage testMessage1 = new TindarMessage("first message sent", new Timestamp(2023, 02, 25, 18, 0, 0, 0),"user1", "user2");
        TindarMessage testMessage2 = new TindarMessage("second message sent", new Timestamp(2023, 03, 25, 18, 0, 0, 0),"user1", "user2");
        TindarMessage testMessage3 = new TindarMessage("first message received", new Timestamp(2023, 04, 25, 18, 0, 0, 0),"user2", "user1");
        TindarMessage testMessage4 = new TindarMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam maximus erat nisl, a scelerisque leo euismod nec. Ut dapibus auctor augue, quis tempor tellus tincidunt vel. Fusce ut odio mauris. Nam nec finibus enim. Duis et nisi tristique, luctus leo id, facilisis dolor. Phasellus ac auctor odio, non mollis magna. Suspendisse tortor ipsum, consectetur vitae metus et, accumsan luctus erat. Sed gravida, ipsum vel mattis maximus, orci arcu convallis nunc, sed sagittis metus felis ac sapien. Integer non pellentesque massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et dignissim erat. Integer at nibh ac mi ultricies pulvinar. Morbi nec arcu nisi. Duis eu ligula auctor, dictum tortor a, condimentum velit. Suspendisse potenti. Sed fermentum lobortis blandit.", new Timestamp(2023, 05, 25, 18, 0, 0, 0),"user1", "user2");
        TindarMessage testMessage5 = new TindarMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam maximus erat nisl, a scelerisque leo euismod nec. Ut dapibus auctor augue, quis tempor tellus tincidunt vel. Fusce ut odio mauris. Nam nec finibus enim. Duis et nisi tristique, luctus leo id, facilisis dolor. Phasellus ac auctor odio, non mollis magna. Suspendisse tortor ipsum, consectetur vitae metus et, accumsan luctus erat. Sed gravida, ipsum vel mattis maximus, orci arcu convallis nunc, sed sagittis metus felis ac sapien. Integer non pellentesque massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et dignissim erat. Integer at nibh ac mi ultricies pulvinar. Morbi nec arcu nisi. Duis eu ligula auctor, dictum tortor a, condimentum velit. Suspendisse potenti. Sed fermentum lobortis blandit.", new Timestamp(2023, 05, 25, 18, 0, 0, 0),"user2", "user1");

        this.loadedMessages = new ArrayList<MessageModel>();
        this.loadedMessages.add(testMessage1);
        this.loadedMessages.add(testMessage2);
        this.loadedMessages.add(testMessage3);
        this.loadedMessages.add(testMessage3);
        this.loadedMessages.add(testMessage3);
        this.loadedMessages.add(testMessage3);
        this.loadedMessages.add(testMessage3);
        this.loadedMessages.add(testMessage4);
        this.loadedMessages.add(testMessage5);
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
     *
     * Currently just adds the message to loadedMessages.
     * Will probably call some sort of conversationManager class and be slightly reimplemented
     * with the database.
     *
     * @return void
     */
    public void sentMessage(View v){
        String input = (this.chatInput.getText()).toString();
        MessageModel newMessage = new TindarMessage(input, new Timestamp(System.currentTimeMillis()),
                this.userID, this.otherUserID);
        this.loadedMessages.add(newMessage);

        this.adapter.notifyDataSetChanged();
    }
}
