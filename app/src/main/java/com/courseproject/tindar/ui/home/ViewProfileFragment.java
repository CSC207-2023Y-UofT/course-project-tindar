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
import com.courseproject.tindar.controllers.viewprofiles.ViewProfilesController;
import com.courseproject.tindar.databinding.FragmentViewProfileBinding;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.likelist.LikeListDsGateway;
import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.likelist.LikeListInteractor;
import com.courseproject.tindar.usecases.userlist.UserListDsGateway;
import com.courseproject.tindar.usecases.userlist.UserListInputBoundary;
import com.courseproject.tindar.usecases.userlist.UserListInteractor;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileDsGateway;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInteractor;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ViewProfileFragment extends Fragment {
    boolean doesLike;
    private int currentViewProfileUserIdIndex;
    private String otherUserId;

    private String userId;
    private ArrayList<String> allUserIds;

    private FragmentViewProfileBinding binding;
    private BlankNavViewModel blankNavViewModel;

    ImageButton likeButton;
    TextView displayNameView;
    TextView genderView;
    TextView birthdateView;
    TextView locationView;
    TextView aboutMeView;

    ViewProfilesController viewProfilesController;

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
        binding = FragmentViewProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // here we pause enter transition animation to load view completely
        postponeEnterTransition();

        // we set the background color of root view to white
        // because navigation animations run on a transparent background by default
        root.setBackgroundColor(Color.WHITE);

        // here we start transition using a handler
        // to make sure transition animation won't be lagged
        root.post(() -> postponeEnterTransition(0, TimeUnit.MILLISECONDS));

        ImageButton nextButton = root.findViewById(R.id.button_go_next_view_profile);
        ImageButton previousButton = root.findViewById(R.id.button_go_previous_view_profile);
        likeButton = root.findViewById(R.id.button_like_view_profile);
        displayNameView = root.findViewById(R.id.text_view_display_name_view_profile);
        genderView = root.findViewById(R.id.text_view_gender_view_profile);
        birthdateView = root.findViewById(R.id.text_view_birthdate_view_profile);
        locationView = root.findViewById(R.id.text_view_location_view_profile);
        aboutMeView = root.findViewById(R.id.text_view_about_me_view_profile);

        ViewProfileDsGateway viewProfilesDatabaseHelper = DatabaseHelper.getInstance(getContext());
        ViewProfileInputBoundary viewProfilesInteractor = new ViewProfileInteractor(viewProfilesDatabaseHelper);
        UserListDsGateway userListDatabaseHelper = DatabaseHelper.getInstance(getContext());
        UserListInputBoundary userListInteractor = new UserListInteractor(userListDatabaseHelper);
        LikeListDsGateway likeListDatabaseHelper = DatabaseHelper.getInstance(getContext());
        LikeListInputBoundary likeListInteractor = new LikeListInteractor(likeListDatabaseHelper);
        viewProfilesController = new ViewProfilesController(viewProfilesInteractor,
                userListInteractor, likeListInteractor);

        allUserIds = viewProfilesController.getAllOtherUserIds(userId);

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
            blankNavViewModel.setViewProfileUserIdIndex(currentViewProfileUserIdIndex -1);
            goToAnotherProfile();
        });

        likeButton.setOnClickListener(view -> {
            if (!doesLike) {
                viewProfilesController.addLike(userId, otherUserId);
                doesLike = true;
                likeButton.setImageResource(R.drawable.ic_star_big_on);
            } else {
                viewProfilesController.removeLike(userId, otherUserId);
                doesLike = false;
                likeButton.setImageResource(R.drawable.ic_star_big_off);
            }
        });

//        new DownloadImage((ImageView) binding.profilePicture).execute(profile.getProfilePictureLink());

        return root;
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        otherUserId = allUserIds.get(currentViewProfileUserIdIndex);
        doesLike = viewProfilesController.checkLiked(userId, otherUserId);

        setsProfile();

        if (doesLike) {
            likeButton.setImageResource(R.drawable.ic_star_big_on);
        } else {
            likeButton.setImageResource(R.drawable.ic_star_big_off);
        }

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
        ViewProfileResponseModel profile =
                viewProfilesController.getProfile(otherUserId);

        displayNameView.setText(profile.getDisplayName());
        genderView.setText(profile.getGender());
        birthdateView.setText(dateFormat.format(profile.getBirthdate()));
        locationView.setText(profile.getLocation());
        aboutMeView.setText(profile.getAboutMe());
    }

    private void goToAnotherProfile() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.layout_view_profile, new SecondViewProfileFragment(), "second view profile fragment"
        ); //My second Fragment
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}