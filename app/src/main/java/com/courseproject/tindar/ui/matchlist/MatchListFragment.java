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
import com.courseproject.tindar.ChatActivity;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.likelist.LikeListController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.likelist.LikeListInteractor;
import com.courseproject.tindar.usecases.likelist.LikeListDsGateway;
import com.courseproject.tindar.usecases.likelist.LikeListResponseModel;

public class MatchListFragment extends Fragment{

    private String userId;
    private ListView listView;
    private LikeListController likeListController;
    private String[] matchedUserIds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_match_list, container, false);

        listView = contentView.findViewById(R.id.match_list_view);
        LikeListDsGateway likeListDatabaseHelper = DatabaseHelper.getInstance(getContext());
        LikeListInputBoundary likeListInteractor = new LikeListInteractor(likeListDatabaseHelper);
        likeListController = new LikeListController(likeListInteractor);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(requireActivity(), ChatActivity.class);
            intent.putExtra("user_id", matchedUserIds[i]);
            startActivity(intent);
        });

        return contentView;
    }

    @Override
    public Object getEnterTransition() {
        LikeListResponseModel matchedUsers = likeListController.getDisplayNamesForMatches(userId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                android.R.layout.simple_list_item_1, matchedUsers.getDisplayNames());
        listView.setAdapter(adapter);
        matchedUserIds = matchedUsers.getUserIds();

        return super.getEnterTransition();
    }
}
