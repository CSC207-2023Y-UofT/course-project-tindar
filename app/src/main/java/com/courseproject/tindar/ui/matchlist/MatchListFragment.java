package com.courseproject.tindar.ui.matchlist;

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
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.likelist.LikeListController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.likelist.LikeListInteractor;
import com.courseproject.tindar.usecases.likelist.LikeListDsGateway;

public class MatchListFragment extends Fragment {

    private String userId;
    private ListView listView;
    private LikeListController likeListController;

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

        return contentView;
    }
    @Override
    public Object getEnterTransition() {
        String[] displayNamesForMatches = likeListController.getDisplayNamesForMatches(userId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                android.R.layout.simple_list_item_1, displayNamesForMatches);
        listView.setAdapter(adapter);

        return super.getEnterTransition();
    }
}