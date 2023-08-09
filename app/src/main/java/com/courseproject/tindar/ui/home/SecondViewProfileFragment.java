package com.courseproject.tindar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.courseproject.tindar.BlankNavViewModel;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.userlist.UserListController;
import com.courseproject.tindar.controllers.viewprofiles.ViewProfilesController;
import com.courseproject.tindar.databinding.FragmentHomeBinding;
import com.courseproject.tindar.databinding.FragmentSecondViewProfileBinding;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.userlist.UserListDsGateway;
import com.courseproject.tindar.usecases.userlist.UserListInteractor;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsGateway;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsResponseModel;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesInteractor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class SecondViewProfileFragment extends Fragment implements View.OnClickListener{

    ViewProfilesDsGateway viewProfilesDatabaseHelper = DatabaseHelper.getInstance(getContext());
    ViewProfilesInteractor viewProfilesInteractor = new ViewProfilesInteractor(viewProfilesDatabaseHelper);
    ViewProfilesController viewProfilesController = new ViewProfilesController(viewProfilesInteractor);

    UserListDsGateway userListDatabaseHelper = DatabaseHelper.getInstance(getContext());
    UserListInteractor userListInteractor = new UserListInteractor(userListDatabaseHelper);
    UserListController userListController = new UserListController(userListInteractor);

    private FragmentSecondViewProfileBinding binding;
    private ArrayList<String> allUserIds;
    private String userId;
    Button likeButton;
    Button dislikeButton;

    DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);

        allUserIds = userListController.getAllUserIds();
//        allUserIds.remove(Integer.valueOf(userId));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Random r = new Random();
        binding = FragmentSecondViewProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewProfilesDsResponseModel initialProfile = viewProfilesController.readNextProfile(allUserIds.get((int)(Math.random() * (5))));

        binding.likeButton.setOnClickListener(this);
        binding.dislikeButton.setOnClickListener(this);

//        Button likeButton = (Button) root.findViewById(R.id.likeButton);
//        Button dislikeButton = (Button) root.findViewById(R.id.dislikeButton);
//        likeButton.setOnClickListener(this);
//        dislikeButton.setOnClickListener(this);

        final TextView displayNameView = (TextView) root.findViewById(R.id.displayName);
        displayNameView.setText(initialProfile.getDisplayName());

        final TextView genderView = (TextView) root.findViewById(R.id.gender);
        genderView.setText(initialProfile.getGender());

        final TextView birthdayView = (TextView) root.findViewById(R.id.birthday);
        birthdayView.setText(dateFormat.format(initialProfile.getBirthdate()));

        final TextView locationView = (TextView) root.findViewById(R.id.location);
        locationView.setText(initialProfile.getLocation());

        final TextView aboutMeView = (TextView) root.findViewById(R.id.aboutMe);
        aboutMeView.setText(initialProfile.getAboutMe());

        return root;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_second_view_profile, new ViewProfileFragment(), "third fragment"); //My second Fragment
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}