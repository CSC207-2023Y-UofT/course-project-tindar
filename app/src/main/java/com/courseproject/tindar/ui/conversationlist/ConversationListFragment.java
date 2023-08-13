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
 * A fragment representing a list of Items.
 */
public class ConversationListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    String userId;
    RecyclerView recyclerView;
    ConversationListController conversationListController;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConversationListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ConversationListFragment newInstance(int columnCount) {
        ConversationListFragment fragment = new ConversationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);

        if (getArguments() != null) {
            // TODO: Customize parameters
            int mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

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



