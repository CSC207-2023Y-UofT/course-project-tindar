package com.courseproject.tindar.ui.chats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.courseproject.tindar.databinding.FragmentChatsBinding;

/**
 * This is a frontend of Conversation list, in the format of a fragment from the side menu bar
 * where it displays a list of conversation between the user and other users
 *
 */
public class ConvoFragment extends Fragment {

    RecyclerView recyclerView;


    private FragmentChatsBinding binding;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConvoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ConvoFragment newInstance(int columnCount) {
        ConvoFragment fragment = new ConvoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentChatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new MychatsRecyclerViewAdapter(PlaceholderContent.ITEMS));
//        }
        return root;
    }
}