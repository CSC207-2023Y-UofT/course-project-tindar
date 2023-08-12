package com.courseproject.tindar.usecases.conversations;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.courseproject.tindar.ui.conversations.ConversationListAdapter;

/**
 * Layers:
 *
 * What is it :
 *
 * Why does it exist:
 */
public interface ConversationListAdapterInterface {
    @NonNull
    ConversationListAdapter.ViewHolder onCreateViewHolder(int viewType, @NonNull ViewGroup parent);
}
