//package com.courseproject.tindar;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.courseproject.tindar.entities.ConvoModel;
//
//import java.util.ArrayList;
//
///* TODO: Current implementation of message types (sent vs. received) won't generalize well
//    to future features like file-type messages.
//    In an ideal world, I'd refactor such that each case handles itself,
//    but I'm not currently familiar enough with all the inner workings here
//    and we have other more important features to focus on. Some things to potentially fix:
//    - constants for switch/if-else statements or remove them entirely
// */
//
//
///**
// * I think this feeds into the RecyclerView for the one-on-one chat messages.
// * Still very confused about what it actually does.
// * @author Sophia Wan
// */
//public class ConvoListRecyclerViewAdapter
//        extends RecyclerView.Adapter<ConvoListRecyclerViewAdapter.TindarMessageViewHolder>{
//    /**
//     * userID of current user. Needed check whether a message was sent or received.
//     * Might later refactor such that we don't need this.
//     */
//    private final String _userID;
//    /** List of messages to be displayed. Misnomer, but private so it doesn't really matter. */
//    private ArrayList<ConvoModel> _displayedMessages;
//
//    /**
//     * Constructor. ConvoListRecyclerViewAdapter is intended to be an adapter for a view.
//     * @param displayedMessages list of messages that this adapter will help display
//     * @param userID User ID of the current user using the app
//     */
//    public ConvoListRecyclerViewAdapter(ArrayList<ConvoModel> displayedMessages,
//                                        String userID){
//        this._displayedMessages = displayedMessages;
//        this._userID = userID;
//    }
//
//    /**
//     * Creates and adds TindarMessageViewHolder objects to the parent ViewGroup
//     * @param parent   The ViewGroup into which the new View will be added after it is bound to
//     *                 an adapter position.
//     * @param viewType The view type of the new View.
//     * @return
//     */
//    @NonNull
//    @Override
//    public TindarMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        /*
//        this is where we will inflate the layout;
//        take the layout description from the XML
//        and populate the view hierarchy with actual View objects.
//        TODO: refactor to avoid switch statements
//         */
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view;
//        switch(viewType){
//            case 0:
//                view = inflater.inflate(R.layout.recycler_row_message_sent,
//                        parent, false);
//                return new TindarMessageViewHolder(view);
//            case 1:
//                view = inflater.inflate(R.layout.recycler_row_message_received,
//                        parent, false);
//                return new TindarMessageViewHolder(view);
//        }
//        return null;
//    }
//
//    /**
//     * Updates a message TindarMessageViewHolder with the relevant details
//     * from the corresponding ConvoModel
//     * @param holder   The ViewHolder which should be updated to represent the contents of the
//     *                 item at the given position in the data set.
//     * @param position The position of the item within the adapter's data set.
//     */
//    @Override
//    public void onBindViewHolder(@NonNull TindarMessageViewHolder holder, int position) {
//        // TODO: refactor for easier future support of other message types
//        holder.messageContentLayout.setText(_displayedMessages.get(position).getMessageContent());
//    }
//
//    /**
//     * @return number of items within the adapter's data set.
//     */
//    @Override
//    public int getItemCount() {
//        // I think this is a helper for onBindViewHolder()?
//        return (this._displayedMessages).size();
//    }
//
//    // TODO: refactor
//    /**
//     * Categorizes items being cycled through
//     * Feeds into onCreateViewHolder.
//     * @param position
//     * @return 0 if the message originated from the current user; 1 otherwise
//     */
//    @Override
//    public int getItemViewType(int position){
//        if (this._displayedMessages.get(position).getSentFromId().equals(_userID)){
//            return 0;
//        } else{
//            return 1;
//        }
//    }
//
//    /** Holds the views for individual messages (I think) */
//    public static class TindarMessageViewHolder extends RecyclerView.ViewHolder{
//        // grabs views from row layout file and assigns them to variables
//
//        /** Contains the outer shell for the layout for a given message */
//        RelativeLayout messageLayout;
//        /**
//         * Contains the text portion of the layout for a given message.
//         * May be refactored later for better non-text support.
//         */
//        TextView messageContentLayout;
//
//        /**
//         * TindarMessageViewHolder constructor.
//         * @param itemView the view that this view holder is supposed to hold
//         * @return new TindarMessageViewHolder
//         */
//        public TindarMessageViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            this.messageLayout = itemView.findViewById(R.id.message_layout);
//            this.messageContentLayout = itemView.findViewById(R.id.message_content_layout);
//        }
//    }
//}
