package com.courseproject.tindar.ui.editprofile;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileInteractor;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass. This is a fragment for editing profile screen in the blank nav activity.
 */
public class EditProfileFragment extends Fragment {

    private String userId;
    private TextView birthdateTextView;
    private AutoCompleteTextView genderAutoCompleteTextView, locationAutoCompleteTextView;
    private ArrayAdapter<String> genderArrayAdapter, locationArrayAdapter;
    private EditText profilePictureLinkEditText, aboutMeEditText;
    private Button profileSubmitButton;
    private ImageButton profileEditButton;
    private EditProfileController editProfileController;
    private EditProfileResponseModel profileDsResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(requireActivity()).get(BlankNavViewModel.class);
        blankNavViewModel.getUserId().observe(requireActivity(), it -> userId = it);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflates the layout for this fragment
        FragmentEditProfileBinding binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // finds components and assigns each to a variable
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
        editProfileController = new EditProfileController(editProfileInteractor);

        // creates dropdown menu for the gender
        String[] genders = getResources().getStringArray(R.array.genders);
        genderArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, genders);

        // creates dropdown menu for the location
        String[] locations = getResources().getStringArray(R.array.locations);
        locationArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, locations);

        // click listener for profile edit button
        profileEditButton.setOnClickListener(view -> {
            setEditEnabled(true);
        });

        // click listener for profile submit button to update the profile
        profileSubmitButton.setOnClickListener(view -> {
            try {
                EditProfileRequestModel updatedProfile = getProfileInputValue();
                editProfileController.updateProfile(userId, updatedProfile);
                setEditEnabled(false);
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

    @Nullable
    @Override
    public Object getEnterTransition() {
        // editing is disabled on enter transition
        setEditEnabled(false);

        // gets user profile
        profileDsResponse = editProfileController.getProfile(userId);

        // renders user profile to the screen
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
    private EditProfileRequestModel getProfileInputValue()  throws ParseException {
        return new EditProfileRequestModel(
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
        birthdateTextView.setEnabled(enabled);
        genderAutoCompleteTextView.setEnabled(enabled);
        locationAutoCompleteTextView.setEnabled(enabled);
        profilePictureLinkEditText.setEnabled(enabled);
        aboutMeEditText.setEnabled(enabled);
    }
}