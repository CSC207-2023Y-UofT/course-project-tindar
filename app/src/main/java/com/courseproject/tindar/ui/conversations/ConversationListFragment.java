package com.courseproject.tindar.ui.conversations;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.courseproject.tindar.R;
import com.courseproject.tindar.usecases.conversations.ConversationResponseModel;

import java.util.ArrayList;
import java.util.List;

public class ConversationListFragment extends Fragment {

    private ListView conversationListView;
    private List<ConversationResponseModel> conversationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conversation_list, container, false);

        conversationListView = rootView.findViewById(R.id.conversationRecyclerView);
        // Replace this sample data with your actual conversation data
        conversationList = new ArrayList<>();
        conversationList.add(new ConversationResponseModel("John Doe",  "1"));
        conversationList.add(new ConversationResponseModel("Jane Smith", "2"));

        ArrayAdapter<ConversationResponseModel> conversationListAdapter = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_list_item_1, conversationList);
        conversationListView.setAdapter(conversationListAdapter);

        conversationListView.setOnItemClickListener((adapterView, view, position, l) -> {
            // Get the selected conversation item
            ConversationResponseModel selectedConversation = conversationList.get(position);

            // Navigate to the chat page passing relevant data
            Bundle args = new Bundle();
            args.putString("username", selectedConversation.getConversationPartnerName());
//            args.putString("message", selectedConversation.getLastMessage());
//            args.putString("timestamp", selectedConversation.getLastMessageTime());

            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_conversations);
            navController.navigate(R.id.conversationRecyclerView, args);
        });

        return rootView;
    }
}

//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.courseproject.tindar.R;
//import com.courseproject.tindar.usecases.conversations.ConversationResponseModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class ConversationListFragment extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_conversation_list, container, false);
//
//        RecyclerView conversationRecyclerView = rootView.findViewById(R.id.conversationRecyclerView);
//        conversationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//
//        // Create sample conversation data
//        List<ConversationResponseModel> conversationList = new ArrayList<>();
//        conversationList.add(new ConversationResponseModel("John Doe", "Hello!", "10:30 AM", "1"));
//        conversationList.add(new ConversationResponseModel("Jane Smith", "Hey there!", "11:15 AM", "2"));
//
//        ConversationListAdapter conversationListAdapter = new ConversationListAdapter(requireContext(), conversationList);
//        conversationRecyclerView.setAdapter(conversationListAdapter);
//
//
//        return rootView;
//    }
//}
//
////
////import android.content.Context;
////import android.os.Bundle;
////
////import androidx.fragment.app.Fragment;
////import androidx.recyclerview.widget.GridLayoutManager;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////
////import com.courseproject.tindar.R;
////import com.courseproject.tindar.ui.conversations.placeholder.PlaceholderContent;
////import com.courseproject.tindar.usecases.conversations.ConversationResponseModel;
////
////import java.util.ArrayList;
////
/////**
//// * A fragment representing a list of Items.
//// */
////public class ConversationFragment extends Fragment {
////
////    // TODO: Customize parameter argument names
////    private static final String ARG_COLUMN_COUNT = "column-count";
////    // TODO: Customize parameters
////    private int mColumnCount = 1;
////
////    /**
////     * Mandatory empty constructor for the fragment manager to instantiate the
////     * fragment (e.g. upon screen orientation changes).
////     */
////    public ConversationFragment() {
////    }
////
////    // TODO: Customize parameter initialization
////    @SuppressWarnings("unused")
////    public static ConversationFragment newInstance(int columnCount) {
////        ConversationFragment fragment = new ConversationFragment();
////        Bundle args = new Bundle();
////        args.putInt(ARG_COLUMN_COUNT, columnCount);
////        fragment.setArguments(args);
////        return fragment;
////    }
////
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        if (getArguments() != null) {
////            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
////        }
////    }
////
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        View view = inflater.inflate(R.layout.fragment_conversation_list, container, false);
////        ArrayList<ConversationResponseModel> convos = new ArrayList<>();
////        convos.add(new ConversationResponseModel("lisbeth", "how do you do", "20:20"));
////        convos.add(new ConversationResponseModel("mikael", "silence", "20:50"));
////        // Set the adapter
////        if (view instanceof RecyclerView) {
////            Context context = view.getContext();
////            RecyclerView recyclerView = (RecyclerView) view;
////            if (mColumnCount <= 1) {
////                recyclerView.setLayoutManager(new LinearLayoutManager(context));
////            } else {
////                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
////            }
////            recyclerView.setAdapter(new MyConversationRecyclerViewAdapter(convos));
////        }
////        return view;
////    }
////}