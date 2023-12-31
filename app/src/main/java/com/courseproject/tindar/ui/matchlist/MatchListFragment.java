package com.courseproject.tindar.ui.matchlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.courseproject.tindar.BlankNavViewModel;
import com.courseproject.tindar.controllers.matchlist.MatchListController;
import com.courseproject.tindar.ui.chat.ChatActivity;
import com.courseproject.tindar.R;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.matchlist.MatchListInputBoundary;
import com.courseproject.tindar.usecases.matchlist.MatchListInteractor;
import com.courseproject.tindar.usecases.matchlist.MatchListDsGateway;
import com.courseproject.tindar.usecases.matchlist.MatchListResponseModel;

/** This fragment class provides the list view for the match list, including a list view display
 * of users by their display names. Also pass through userId of 'clicked' users to ChatActivity
 * for conversations
 */
public class MatchListFragment extends Fragment{

    /** The users userId */
    private String userId;

    /** listView instance to display match list in list format */
    private ListView listView;

    /** matchListController to accept use input */
    private MatchListController matchListController;

    /** list containing userIds in the match list */
    private String[] matchedUserIds;
    MatchListResponseModel matchedUsers;

    /** This method creates the match list fragment activity, allows to save and recover state
     * information
     * @param savedInstanceState  saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);

    }

    /** Creates the list view of the match list. Allows the user to see an actual list of user
     * display names on the screen, pulls userIds and display names to generate lists
     * @param savedInstanceState saved instance state
     * @param container contains resource id to add fragment to
     * @param inflater inflates
     * @return return contentView
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_match_list, container, false);

        listView = contentView.findViewById(R.id.match_list_view);
        MatchListDsGateway matchListDatabaseHelper = DatabaseHelper.getInstance(getContext());
        MatchListInputBoundary matchListInteractor = new MatchListInteractor(matchListDatabaseHelper);
        matchListController = new MatchListController(matchListInteractor);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(requireActivity(), ChatActivity.class);
            intent.putExtra("current_user_id", userId);
            intent.putExtra("conversation_partner_id", matchedUserIds[i]);
            intent.putExtra("conversation_partner_display_name",
                    matchedUsers.getDisplayNames()[i]);
            startActivity(intent);
        });

        return contentView;
    }

    /** This method allows for the match list ot refresh each time it is opened and closed, this
     * is in case a new match is made and the view needs to be updated
     * @return return new view given updated match list
     */
    @Override
    public Object getEnterTransition() {
        matchedUsers = matchListController.getDisplayNamesForMatches(userId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                android.R.layout.simple_list_item_1, matchedUsers.getDisplayNames());
        listView.setAdapter(adapter);
        matchedUserIds = matchedUsers.getUserIds();

        return super.getEnterTransition();
    }
}
