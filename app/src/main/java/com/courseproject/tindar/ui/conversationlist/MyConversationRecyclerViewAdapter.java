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

    /** The list of ConversationResponseModel items to be displayed. */
    private final List<ConversationResponseModel> mValues;

    /** Interface for handling item click events. */
    public interface OnItemClickListener {
        void onItemClick(ConversationResponseModel conversation);
    }

    /** The listener for item click events. */
    private OnItemClickListener mListener;

    /**
     * Sets the listener for item click events.
     *
     * @param listener The listener to be set.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * Constructs a new MyConversationRecyclerViewAdapter with the provided list of items.
     *
     * @param items The list of ConversationResponseModel items.
     */
    public MyConversationRecyclerViewAdapter(List<ConversationResponseModel> items) {
        mValues = items;
    }


    /**
     * Called to create a new ViewHolder for representing an item in the RecyclerView.
     *
     * @param parent The parent ViewGroup into which the new ViewHolder will be added.
     * @param viewType The type of the view to be created.
     * @return A new instance of ViewHolder representing an item view.
     * @throws IllegalArgumentException if {@code parent} is null.
     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConversationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    /**
     * Called when binding data to a ViewHolder representing an item in the RecyclerView.
     *
     * @param holder The ViewHolder instance for the item being bound.
     * @param position The position of the item within the adapter's data set.
     * @throws IndexOutOfBoundsException if the {@code position} is out of bounds.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ConversationResponseModel conversation = mValues.get(position);

        holder.mUserName.setText(mValues.get(position).getConversationPartnerName());
        holder.mLastMessage.setText(mValues.get(position).getLastMessage());
        holder.mLastMessageTime.setText(mValues.get(position).getLastMessageTime());

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(conversation);
            }
        });
    }

    /**
     * Returns the total number of items in the data set.
     *
     * @return The total number of items.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }


    /**
     * ViewHolder class that extends RecyclerView.ViewHolder for holding view components of
     * individual items in the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * TextView displaying the username in the item view.
         */

        public final TextView mUserName;

        /**
         * TextView displaying the last message in the item view.
         */
        public final TextView mLastMessage;

        /**
         * TextView displaying the time of the last message in the item view.
         */
        public final TextView mLastMessageTime;

        /**
         * Constructor to create a ViewHolder instance.
         *
         * @param binding The binding instance for the FragmentConversationBinding.
         */


        public ViewHolder(FragmentConversationBinding binding) {
            super(binding.getRoot());
            mUserName = binding.textViewUserName;
            mLastMessage = binding.textViewLastMessage;
            mLastMessageTime = binding.textViewLastMessageTime;
            
            
        }


        /**
         * Returns a string representation of the ViewHolder.
         *
         * @return A string containing the class name and the text of the last message.
         */

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mLastMessage.getText() + "'";
        }
    }
}