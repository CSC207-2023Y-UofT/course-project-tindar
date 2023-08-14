package com.courseproject.tindar.ui.conversationlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.courseproject.tindar.BlankNavViewModel;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.conversationlist.ConversationListController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.ui.chat.ChatActivity;
import com.courseproject.tindar.usecases.conversationlist.ConversationListDsGateway;
import com.courseproject.tindar.usecases.conversationlist.ConversationListInputBoundary;
import com.courseproject.tindar.usecases.conversationlist.ConversationListInteractor;
import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;

import java.util.ArrayList;

/**
 * Layer : UI Layer
 *.
 * What:
 * A fragment representing a list of conversations.
 * This fragment displays a list of active conversations and allows users to click on a conversation
 * to navigate to the chat screen.
 */
public class ConversationListFragment extends Fragment {
    
    /**
     * The argument key for specifying the column count in a bundle or intent.
     */
    private static final String ARG_COLUMN_COUNT = "column-count";

    /**
     * The default column count value for the RecyclerView.
     */
    private final int mColumnCount = 1;

    /**
     * The ID of the user associated with the conversation list.
     */
    String userId;
    
    /**
     * The RecyclerView used to display the list of conversations.
     */
    RecyclerView recyclerView;

    /**
     * The controller responsible for managing the conversation list.
     */
    ConversationListController conversationListController;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConversationListFragment() {
    }


    /**
     * Creates a new instance of the ConversationListFragment.
     *
     * @param columnCount The number of columns in the layout.
     * @return A new instance of ConversationListFragment.
     */
    @SuppressWarnings("unused")
    public static ConversationListFragment newInstance(int columnCount) {
        ConversationListFragment fragment = new ConversationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called when the fragment is being created. This method initializes the fragment's behavior,
     * retrieves arguments, and prepares necessary resources.
     *
     * @param savedInstanceState A Bundle containing saved state information, if available.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);
    }

    /**
     * Called when it's time for the fragment to create its user interface view hierarchy.
     * This method inflates the fragment's layout, configures the UI elements, and sets up listeners.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate views.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState A Bundle containing saved state information, if available.
     * @return The root View of the fragment's layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation_list, container, false);

        // Set the adapter
        recyclerView = view.findViewById(R.id.list);
        if (recyclerView != null) {
            Context context = view.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        // instantiates controller
        ConversationListDsGateway conversationListDatabaseHelper = DatabaseHelper.getInstance(getContext());
        ConversationListInputBoundary conversationListInteractor =
                new ConversationListInteractor(conversationListDatabaseHelper);
        conversationListController = new ConversationListController(conversationListInteractor);

        return view;
    }

    /**
     * Called to retrieve the enter transition that will be used for this fragment
     * when it's entering the screen.
     * This method typically prepares and returns a transition animation.
     *
     * @return An object representing the enter transition animation.
     */
    @Nullable
    @Override
    public Object getEnterTransition() {
        ArrayList<ConversationResponseModel> conversations = conversationListController.getAllActiveConversations(userId);

        if (recyclerView != null) {

            MyConversationRecyclerViewAdapter adapter = new MyConversationRecyclerViewAdapter(conversations);
            recyclerView.setAdapter(adapter);

            // Set click listener for conversation items
            adapter.setOnItemClickListener(conversation -> {
                // Handle the click here, navigate to ChatActivity
                Intent intent = new Intent(requireActivity(), ChatActivity.class);
                intent.putExtra("current_user_id", userId);
                intent.putExtra("conversation_partner_id", conversation.getConversationPartnerId());
                intent.putExtra("conversation_partner_display_name", conversation.getConversationPartnerName());
                // Pass any other necessary data
                startActivity(intent);
            });
        }

        return super.getEnterTransition();
    }
}



