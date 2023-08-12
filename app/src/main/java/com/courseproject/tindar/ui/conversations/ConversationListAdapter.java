package com.courseproject.tindar.ui.conversations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courseproject.tindar.R;
import com.courseproject.tindar.ui.chat.ChatActivity;
import com.courseproject.tindar.usecases.conversations.ConversationListAdapterInterface;
import com.courseproject.tindar.usecases.conversations.ConversationResponseModel;

import java.util.List;

 public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ViewHolder> implements ConversationListAdapterInterface {

    private final List<ConversationResponseModel> conversations;
    private final Context context;

    public ConversationListAdapter(Context context, List<ConversationResponseModel> conversations) {
        this.context = context;
        this.conversations = conversations;
    }

     @NonNull
     @Override
     public ConversationListAdapter.ViewHolder onCreateViewHolder(int viewType, @NonNull ViewGroup parent) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_conversation, parent, false);
         return new ViewHolder(view);
     }


     @NonNull
     @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return null;
     }

     @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ConversationResponseModel conversation = conversations.get(position);

        holder.partnerNameTextView.setText(conversation.getConversationPartnerName());
//        holder.lastMessageTextView.setText(conversation.getLastMessage());
//        holder.lastMessageTimeTextView.setText(conversation.getLastMessageTime());

        holder.itemView.setOnClickListener(v -> {
            // Handle click event and open the chat activity here
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("conversationId", conversation.getConversationId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView partnerNameTextView;
        TextView lastMessageTextView;
        TextView lastMessageTimeTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            partnerNameTextView = itemView.findViewById(R.id.text_view_partner_name);
            lastMessageTextView = itemView.findViewById(R.id.text_view_last_message);
            lastMessageTimeTextView = itemView.findViewById(R.id.text_view_last_message_time);
        }
    }
}

//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.courseproject.tindar.databinding.FragmentConversationBinding;
//import com.courseproject.tindar.usecases.conversations.ConversationResponseModel;
//
//import java.util.List;
//
///**
// * {@link RecyclerView.Adapter} that can display a {@link ConversationResponseModel}.
// * TODO: Replace the implementation with code for your data type.
// */
//public class MyConversationRecyclerViewAdapter extends RecyclerView.Adapter<MyConversationRecyclerViewAdapter.ViewHolder> {
//
//    private final List<ConversationResponseModel> mValues;
//
//    public MyConversationRecyclerViewAdapter(List<ConversationResponseModel> items) {
//        mValues = items;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        return new ViewHolder(FragmentConversationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mUserName.setText(mValues.get(position).getUserName());
//        holder.mLastMessage.setText(mValues.get(position).getLastMessage());
//        holder.mLastMessageTime.setText(mValues.get(position).getLastMessageTime());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mValues.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final TextView mUserName;
//        public final TextView mLastMessage;
//        public final TextView mLastMessageTime;
//        public ConversationResponseModel mItem;
//
//        public ViewHolder(FragmentConversationBinding binding) {
//            super(binding.getRoot());
//            mUserName = binding.textViewUserName;
//            mLastMessage = binding.textViewLastMessage;
//            mLastMessageTime = binding.textViewLastMessageTime;
//
//
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + mLastMessage.getText() + "'";
//        }
//    }
//}