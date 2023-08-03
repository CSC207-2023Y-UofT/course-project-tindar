package com.courseproject.tindar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

/* TODO: Current implementation of message types (sent vs. received) won't generalize well
    to future features like file-type messages.
    In an ideal world, I'd refactor such that each case handles itself,
    but I'm not currently familiar enough with all the inner workings here
    and we have other more important features to focus on. Some things to potentially fix:
    - constants for switch/if-else statements or remove them entirely
 */

public class ChatRecyclerViewAdapter
        extends RecyclerView.Adapter<ChatRecyclerViewAdapter.TindarMessageViewHolder>{
    private Context _context;
    private final String _userID;
    private ArrayList<MessageModel> _displayedMessages;

    public ChatRecyclerViewAdapter(Context context, ArrayList<MessageModel> displayedMessages,
                                   String userID){
        this._context = context;
        this._displayedMessages = displayedMessages;
        this._userID = userID;
    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public TindarMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        this is where we will inflate the layout;
        take the layout description from the XML
        and populate the view hierarchy with actual View objects.
        TODO: refactor to avoid switch statements
         */
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch(viewType){
            case 0:
                view = inflater.inflate(R.layout.recycler_row_message_sent,
                        parent, false);
                return new TindarMessageViewHolder(view);
            case 1:
                view = inflater.inflate(R.layout.recycler_row_message_received,
                        parent, false);
                return new TindarMessageViewHolder(view);
        }
        return null;
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TindarMessageViewHolder holder, int position) {
        // TODO: refactor for easier future support of other message types
        holder.messageContentLayout.setText(_displayedMessages.get(position).getMessageContent());
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        // I think this is a helper for onBindViewHolder()?
        return (this._displayedMessages).size();
    }

    @Override
    // TODO: refactor
    public int getItemViewType(int position){
        if (this._displayedMessages.get(position).getSentFromId().equals(_userID)){
            return 0;
        } else{
            return 1;
        }
    }

    public static class TindarMessageViewHolder extends RecyclerView.ViewHolder{
        // grabs views from row layout file and assigns them to variables

        RelativeLayout messageLayout;
        TextView messageContentLayout;
        public TindarMessageViewHolder(@NonNull View itemView) {
            super(itemView);

            messageLayout = itemView.findViewById(R.id.message_layout);
            messageContentLayout = itemView.findViewById(R.id.message_content_layout);
        }
    }
}
