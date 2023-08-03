package com.courseproject.tindar.ui.matchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.courseproject.tindar.R;

public class MatchListFragment extends Fragment {
    String[] test = {"1", "2", "3"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_match_list, container, false);
        ListView listView = contentView.findViewById(R.id.matchListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                android.R.layout.simple_expandable_list_item_1, test);
        listView.setAdapter(adapter);
        return contentView;
    }
}
