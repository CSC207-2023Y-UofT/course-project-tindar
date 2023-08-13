package com.courseproject.tindar.ui.editprofile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.courseproject.tindar.BlankNavViewModel;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.editprofile.EditProfileController;
import com.courseproject.tindar.databinding.FragmentEditProfileBinding;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileInteractor;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileDsGateway;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInteractor;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass. This is a fragment for editing profile screen in the blank nav activity.
 */
public class EditProfileFragment extends Fragment {

    /**
     * the user id of the current logged-in user
     */
    private String userId;
    /**
     * the edit text to input display name
     */
    private EditText displayNameEditText;
    /**
     * the text view to show user's birthdate
     */
    private TextView birthdateTextView;
    /**
     * the auto complete text view to show user's gender. auto complete text view is used to creates a dropdown for
     * all possible choices of gender on click.
     */
    private AutoCompleteTextView genderAutoCompleteTextView;
    /**
     * the auto complete text view to show user's location. auto complete text view is used to creates a dropdown
     * for all possible choices of gender on click.
     */
    private AutoCompleteTextView locationAutoCompleteTextView;
    /**
     * the array adapter to contain all possible choices of gender
     */
    private ArrayAdapter<String> genderArrayAdapter;
    /**
     * the array adapter to contain all possible choices of location
     */
    private ArrayAdapter<String> locationArrayAdapter;
    /**
     * the edit text to input link of profile picture
     */
    private EditText profilePictureLinkEditText;
    /**
     * the edit text to input statement to introduce the user to other users
     */
    private EditText aboutMeEditText;
    /**
     * the button to enable editing on the Edit Profile screen
     */
    private ImageButton profileEditButton;
    /**
     * the button to submit the current input values of the profile information to update
     */
    private Button profileSubmitButton;
    /**
     * controller for the Edit Profile UI
     */
    private EditProfileController editProfileController;
    /**
     * saved profile information retrieved
     */
    private ViewProfileResponseModel profileDsResponse;

    /**
     * creates Edit Profile fragment. Shared View Model is used to retrieve currently logged in user id, which is
     * shared among the blank nav activity and the fragments under the activity
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);
    }

    /**
     * creates Edit Profile view. It instantiates Edit Profile controller, prepares dropdown menu for the
     * possible choices of genders and locations, defines behaviour for the edit button and
     * submit button, and define behaviour of date-picker pop-up for the birthdate edit on click of birthdate
     * text view
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return created view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflates the layout for this fragment
        FragmentEditProfileBinding binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // finds components and assigns each to a variable
        displayNameEditText = root.findViewById(R.id.edit_text_display_name);
        birthdateTextView = root.findViewById(R.id.text_view_birthday);
        genderAutoCompleteTextView = root.findViewById(R.id.auto_complete_text_view_gender);
        locationAutoCompleteTextView = root.findViewById(R.id.auto_complete_text_view_location);
        profilePictureLinkEditText = root.findViewById(R.id.edit_text_profile_picture_link);
        aboutMeEditText = root.findViewById(R.id.edit_text_about_me);
        profileEditButton = root.findViewById(R.id.button_edit_profile);
        profileSubmitButton = root.findViewById(R.id.button_submit_profile);

        // instantiates controller
        EditProfileDsGateway editProfileDatabaseHelper = DatabaseHelper.getInstance(getActivity());
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(editProfileDatabaseHelper);
        ViewProfileDsGateway viewProfileDatabaseHelper = DatabaseHelper.getInstance(getActivity());
        ViewProfileInputBoundary viewProfileInteractor = new ViewProfileInteractor(viewProfileDatabaseHelper);
        editProfileController = new EditProfileController(editProfileInteractor, viewProfileInteractor);

        // prepares dropdown menu for the gender
        String[] genders = getResources().getStringArray(R.array.genders);
        genderArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, genders);

        // prepares dropdown menu for the location
        String[] locations = getResources().getStringArray(R.array.locations);
        locationArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, locations);

        // click listener for profile edit button
        profileEditButton.setOnClickListener(view -> setEditEnabled(true));

        // click listener for profile submit button to update the profile
        profileSubmitButton.setOnClickListener(view -> {
            try {
                EditProfileRequestModel newProfile = getProfileInputValues();
                if (newProfile != null) {
                    editProfileController.updateProfile(userId, newProfile);
                    setEditEnabled(false);
                }
            } catch (ParseException e) {
                birthdateTextView.setText(DateFormat.getDateInstance().format(profileDsResponse.getBirthdate()));
            }
        });

        // click listener for birthdate text view to pop-up date picker
        birthdateTextView.setOnClickListener(view -> {
            // gets year, month, day of the birthdate currently saved
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(profileDsResponse.getBirthdate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // overrides onDateSetListener for the DatePickerDialog
            DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year1, month1, day1) -> {
                Date newBirthdate = new GregorianCalendar(year1, month1, day1).getTime();
                birthdateTextView.setText(DateFormat.getDateInstance().format(newBirthdate));
            };

            // creates and shows DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
            datePickerDialog.show();
        });
        return root;
    }

    /**
     * defines behaviour when user enters the fragment. It disables edit on the screen and re-fetches the data whenever
     * user leaves and re-enters the fragment, so non-saved data wouldn't have remained on the screen.
     * It also connects auto complete text view with the dropdown menu prepared.
     *
     * @return the Transition that will be used to move Views into the initial scene.
     */
    @Nullable
    @Override
    public Object getEnterTransition() {
        // editing is disabled on enter transition
        setEditEnabled(false);

        // gets user profile
        profileDsResponse = editProfileController.getProfile(userId);

        // renders user profile to the screen
        displayNameEditText.setText(profileDsResponse.getDisplayName());
        birthdateTextView.setText(DateFormat.getDateInstance().format(profileDsResponse.getBirthdate()));
        genderAutoCompleteTextView.setText(profileDsResponse.getGender());
        locationAutoCompleteTextView.setText(profileDsResponse.getLocation());
        profilePictureLinkEditText.setText(profileDsResponse.getProfilePictureLink());
        aboutMeEditText.setText(profileDsResponse.getAboutMe());

        // connects dropdown menu for gender field
        genderAutoCompleteTextView.setAdapter(genderArrayAdapter);

        // connects dropdown menu for location field
        locationAutoCompleteTextView.setAdapter(locationArrayAdapter);

        return super.getEnterTransition();
    }

    /**
     * returns profile input values gathering texts from each edit fields
     *
     * @return profile input values.
     */
    private EditProfileRequestModel getProfileInputValues()  throws ParseException {
        // shows error message if user tries to submit without display name
        if (TextUtils.isEmpty(displayNameEditText.getText().toString())) {
            displayNameEditText.setError("Please enter name.");
            return null;
        }

        return new EditProfileRequestModel(
            displayNameEditText.getText().toString(),
            DateFormat.getDateInstance(DateFormat.DEFAULT).parse(birthdateTextView.getText().toString()),
            genderAutoCompleteTextView.getText().toString(),
            locationAutoCompleteTextView.getText().toString(),
            profilePictureLinkEditText.getText().toString(),
            aboutMeEditText.getText().toString()
        );
    }

    /**
     * enables(disables) editing. set all edit fields enabled(disabled), profile submit button enabled(disabled),
     * and profile edit button invisible(visible).
     *
     * @param enabled indicates whether to enable or disable editing
     */
    private void setEditEnabled(boolean enabled) {
        profileEditButton.setVisibility(enabled ? View.INVISIBLE : View.VISIBLE);
        profileSubmitButton.setEnabled(enabled);
        displayNameEditText.setEnabled(enabled);
        birthdateTextView.setEnabled(enabled);
        genderAutoCompleteTextView.setEnabled(enabled);
        locationAutoCompleteTextView.setEnabled(enabled);
        profilePictureLinkEditText.setEnabled(enabled);
        aboutMeEditText.setEnabled(enabled);
    }
}