package com.courseproject.tindar.ui.conversationlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.courseproject.tindar.R;
import com.courseproject.tindar.ui.chat.ChatActivity;
import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class ConversationListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

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

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation_list, container, false);
        ArrayList<ConversationResponseModel> convos = new ArrayList<>();
        convos.add(new ConversationResponseModel("lisbeth", "how do you do", "20:20"));
        convos.add(new ConversationResponseModel("mikael", "silence", "20:50"));
        // Set the adapter

        RecyclerView recyclerView = view.findViewById(R.id.list);
        if (recyclerView != null) {
            Context context = view.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            MyConversationRecyclerViewAdapter adapter = new MyConversationRecyclerViewAdapter(convos);
            recyclerView.setAdapter(adapter);

            // Set click listener for conversation items
            adapter.setOnItemClickListener(new MyConversationRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ConversationResponseModel conversation) {
                    // Handle the click here, navigate to ChatActivity
                    Intent intent = new Intent(requireActivity(), ChatActivity.class);
                    intent.putExtra("conversation_partner_name", conversation.getUserName());
                    // Pass any other necessary data
                    startActivity(intent);
                }
            });
        }

        return view;
    }   }
//        RecyclerView recyclerView = null;
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new MyConversationRecyclerViewAdapter(convos));
//        }
//
//
//        return view;
//    }
