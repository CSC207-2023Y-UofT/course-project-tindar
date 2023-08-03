package com.courseproject.tindar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courseproject.tindar.entities.MessageInterface;

import java.util.ArrayList;

public class ChatRecyclerViewAdapter
        extends RecyclerView.Adapter<ChatRecyclerViewAdapter.MessageViewHolder>{
    Context context;
    ArrayList<MessageInterface> displayedMessages;

    public ChatRecyclerViewAdapter(Context context, ArrayList<MessageInterface> displayedMessages){
        this.context = context;
        this.displayedMessages = displayedMessages;
    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public ChatRecyclerViewAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        this is where we will inflate the layout;
        take the layout description from the XML
        and populate the view hierarchy with actual View objects
         */
        return null;
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        // I think this is a helper for onBindViewHolder()?
        return (this.displayedMessages).size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        // grabs views from row layout file and assigns them to variables
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
