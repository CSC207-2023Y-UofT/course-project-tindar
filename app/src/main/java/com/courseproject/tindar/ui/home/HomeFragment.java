package com.courseproject.tindar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.courseproject.tindar.R;
import com.courseproject.tindar.databinding.FragmentHomeBinding;
/** This class implements the home screen fragment of the app */
public class HomeFragment extends Fragment {
    /** FragmentHomeBinding instance to help display home page */
    private FragmentHomeBinding binding;
    /** ImageView of home screen */
    ImageView home;

    /**
     * Loads initial data for screen.
     *
     * @param savedInstanceState info from blank nav about user and app state.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Loads View Profile screen to allow for updating the shown profile.
     *
     * @param inflater the LayoutInflater object.
     * @param container all associated views.
     * @param savedInstanceState info from blank nav about user and app state.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button homeButton = root.findViewById(R.id.button_home);
        home = root.findViewById(R.id.image_view_home);
        home.setImageResource(R.drawable.kermit);


        homeButton.setOnClickListener(view -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_home, new ViewProfileFragment(), "view profile fragment"); //My second Fragment
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return root;
    }

    /**
     * Destroys loaded view.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}