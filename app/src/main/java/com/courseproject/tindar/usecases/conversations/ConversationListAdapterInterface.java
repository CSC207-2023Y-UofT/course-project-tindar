package com.courseproject.tindar.usecases.conversations;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.courseproject.tindar.ui.conversations.ConversationListAdapter;

public interface ConversationListAdapterInterface {
    @NonNull
    ConversationListAdapter.ViewHolder onCreateViewHolder(int viewType, @NonNull ViewGroup parent);
}
