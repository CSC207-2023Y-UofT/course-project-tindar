package com.courseproject.tindar.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.MainActivity;
import com.courseproject.tindar.R;
import com.courseproject.tindar.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView displayNameView = binding.displayName;
        homeViewModel.getDisplayName().observe(getViewLifecycleOwner(), displayNameView::setText);

        final TextView genderView = binding.gender;
        homeViewModel.getGender().observe(getViewLifecycleOwner(), genderView::setText);

        final TextView birthdayView = binding.birthday;
        homeViewModel.getBirthday().observe(getViewLifecycleOwner(), birthdayView::setText);

        final TextView locationView = binding.location;
        homeViewModel.getLocation().observe(getViewLifecycleOwner(), locationView::setText);

        final TextView aboutMeView = binding.aboutMe;
        homeViewModel.getAboutMe().observe(getViewLifecycleOwner(), aboutMeView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}