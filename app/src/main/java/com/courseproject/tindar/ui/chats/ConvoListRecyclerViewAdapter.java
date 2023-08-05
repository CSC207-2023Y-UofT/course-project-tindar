package com.courseproject.tindar.ui.chats;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courseproject.tindar.databinding.FragmentChatsBinding;
import com.courseproject.tindar.entities.ConvoModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a conversation preview.
 */
public class ConvoListRecyclerViewAdapter extends RecyclerView.Adapter<ConvoListRecyclerViewAdapter.ViewHolder> {

    private final List<ConvoModel> mValues;

    public ConvoListRecyclerViewAdapter(List<ConvoModel> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentChatsBinding binding = FragmentChatsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        // Set the values for the DisplayName, last text preview, and time in the TextViews
        holder.mUsernameView.setText(mValues.get(position).getDisplayName());
        holder.mLastTextView.setText(mValues.get(position).getLastMessageDisplayText());
        holder.mTimeView.setText(mValues.get(position).getLastMessageTime());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mUsernameView;
        public final TextView mLastTextView;
        public final TextView mTimeView;
        public ConvoModel mItem;

        public ViewHolder(FragmentChatsBinding binding) {
            super(binding.getRoot());
            mUsernameView = binding.userNameText;
            mLastTextView = binding.lastMessageText;
            mTimeView = binding.lastMessageTimeText;
        }
    @Override
    public String toString() {
            return super.toString() + " '" + mLastTextView.getText() + "'";
        }

        // Rest of the ViewHolder code...
    }




}
