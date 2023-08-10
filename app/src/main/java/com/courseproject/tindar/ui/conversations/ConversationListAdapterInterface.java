package com.courseproject.tindar.ui.conversations;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

public interface ConversationListAdapterInterface {
    @NonNull
    ConversationListAdapter.ViewHolder onCreateViewHolder(int viewType, @NonNull ViewGroup parent);
}
