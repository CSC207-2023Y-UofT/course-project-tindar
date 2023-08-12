package com.courseproject.tindar.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.courseproject.tindar.BlankNavViewModel;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.userlist.UserListController;
import com.courseproject.tindar.controllers.viewprofiles.ViewProfilesController;
import com.courseproject.tindar.databinding.FragmentSecondViewProfileBinding;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.userlist.UserListDsGateway;
import com.courseproject.tindar.usecases.userlist.UserListInteractor;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsGateway;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsResponseModel;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesInteractor;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SecondViewProfileFragment extends Fragment {

    ViewProfilesDsGateway viewProfilesDatabaseHelper = DatabaseHelper.getInstance(getContext());
    ViewProfilesInteractor viewProfilesInteractor = new ViewProfilesInteractor(viewProfilesDatabaseHelper);
    ViewProfilesController viewProfilesController = new ViewProfilesController(viewProfilesInteractor);

    UserListDsGateway userListDatabaseHelper = DatabaseHelper.getInstance(getContext());
    UserListInteractor userListInteractor = new UserListInteractor(userListDatabaseHelper);
    UserListController userListController = new UserListController(userListInteractor);

    ImageButton nextButton;
    ImageButton previousButton;

    private FragmentSecondViewProfileBinding binding;
    private ArrayList<String> allUserIds;
    private String userId;

    BlankNavViewModel blankNavViewModel;
    int currentViewProfileUserIdIndex;

    TextView displayNameView;
    TextView genderView;
    TextView birthdateView;
    TextView locationView;
    TextView aboutMeView;

    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.CANADA );

    /**
     * Loads initial data for screen.
     *
     * @param savedInstanceState info from blank nav about user and app state.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);
        blankNavViewModel.getViewProfileUserIdIndex().observe(requireActivity(), it -> currentViewProfileUserIdIndex = it);

        allUserIds = userListController.getAllOtherUserIds(userId);
    }

    /**
     * Loads first profile for user to look at.
     *
     * @param inflater the LayoutInflater object.
     * @param container all associated views.
     * @param savedInstanceState info from blank nav about user and app state.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondViewProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // here we pause enter transition animation to load view completely
        postponeEnterTransition();

        // we set the background color of root view to white
        // because navigation animations run on a transparent background by default
        root.setBackgroundColor(Color.WHITE);

        // here we start transition using a handler
        // to make sure transition animation won't be lagged
        root.post(() -> postponeEnterTransition(0, TimeUnit.MILLISECONDS));

        nextButton = root.findViewById(R.id.button_go_next_second_view_profile);
        previousButton = root.findViewById(R.id.button_go_previous_second_view_profile);

        if (currentViewProfileUserIdIndex == allUserIds.size() - 1) {
            nextButton.setEnabled(false);
            nextButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.colorOnError));
        }
        if (currentViewProfileUserIdIndex == 0) {
            previousButton.setEnabled(false);
            previousButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.colorOnError));
        }

        nextButton.setOnClickListener(view -> {
            blankNavViewModel.setViewProfileUserIdIndex(currentViewProfileUserIdIndex + 1);
            goToAnotherProfile();
        });
        previousButton.setOnClickListener(view -> {
            blankNavViewModel.setViewProfileUserIdIndex(currentViewProfileUserIdIndex - 1);
            goToAnotherProfile();
        });

//        new DownloadImage((ImageView) binding.profilePicture).execute(initialProfile.getProfilePictureLink());

        displayNameView = root.findViewById(R.id.text_view_display_name_second_view_profile);
        genderView = root.findViewById(R.id.text_view_gender_second_view_profile);
        birthdateView = root.findViewById(R.id.text_view_birthdate_second_view_profile);
        locationView = root.findViewById(R.id.text_view_location_second_view_profile);
        aboutMeView = root.findViewById(R.id.text_view_about_me_second_view_profile);

        return root;
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        setsProfile();
        return super.getEnterTransition();
    }

    /**
     * Destroys loaded view.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Downloads image from link.
     */
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView profilePicture;

        public DownloadImage(ImageView profilePicture) {
            this.profilePicture = profilePicture;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            profilePicture.setImageBitmap(result);
        }
    }

    private void setsProfile() {
        ViewProfilesDsResponseModel profile =
                viewProfilesController.readNextProfile(allUserIds.get(currentViewProfileUserIdIndex));

        displayNameView.setText(profile.getDisplayName());
        genderView.setText(profile.getGender());
        birthdateView.setText(dateFormat.format(profile.getBirthdate()));
        locationView.setText(profile.getLocation());
        aboutMeView.setText(profile.getAboutMe());
    }

    private void goToAnotherProfile() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.layout_second_view_profile, new ViewProfileFragment(), "first view profile fragment"
        ); //My first Fragment
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}