package com.courseproject.tindar.ui.conversationlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courseproject.tindar.databinding.FragmentConversationBinding;
import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ConversationResponseModel}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyConversationRecyclerViewAdapter extends RecyclerView.Adapter<MyConversationRecyclerViewAdapter.ViewHolder> {

    private final List<ConversationResponseModel> mValues;

    public interface OnItemClickListener {
        void onItemClick(ConversationResponseModel conversation);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public MyConversationRecyclerViewAdapter(List<ConversationResponseModel> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConversationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ConversationResponseModel conversation = mValues.get(position);

        holder.mItem = mValues.get(position);
        holder.mUserName.setText(mValues.get(position).getConversationPartnerName());
        holder.mLastMessage.setText(mValues.get(position).getLastMessage());
        holder.mLastMessageTime.setText(mValues.get(position).getLastMessageTime());

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(conversation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mUserName;
        public final TextView mLastMessage;
        public final TextView mLastMessageTime;
        public ConversationResponseModel mItem;

        public ViewHolder(FragmentConversationBinding binding) {
            super(binding.getRoot());
            mUserName = binding.textViewUserName;
            mLastMessage = binding.textViewLastMessage;
            mLastMessageTime = binding.textViewLastMessageTime;
            
            
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mLastMessage.getText() + "'";
        }
    }
}