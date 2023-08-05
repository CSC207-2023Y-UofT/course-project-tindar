package com.courseproject.tindar.ui.editprofile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileInteractor;

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
    private EditText profilePictureLinkEditText, aboutMeEditText;
    private ImageButton birthdateEditButton, genderEditButton, locationEditButton, profilePictureLinkEditButton, aboutMeEditButton;
    private EditProfileController editProfileController;
    private EditProfileDsResponseModel profileDsResponse;

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
        birthdateEditButton = root.findViewById(R.id.button_edit_birthday);
        genderEditButton = root.findViewById(R.id.button_edit_gender);
        locationEditButton = root.findViewById(R.id.button_edit_location);
        profilePictureLinkEditButton = root.findViewById(R.id.button_edit_profile_picture_link);
        aboutMeEditButton = root.findViewById(R.id.button_edit_about_me);

        // instantiates controller
        EditProfileDsGateway editProfileDatabaseHelper = DatabaseHelper.getInstance(getActivity());
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(editProfileDatabaseHelper);
        editProfileController = new EditProfileController(editProfileInteractor);

        // creates dropdown menu for the gender
        String[] genders = getResources().getStringArray(R.array.genders);
        ArrayAdapter<String> genderArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, genders);
        genderAutoCompleteTextView.setAdapter(genderArrayAdapter);

        // creates dropdown menu for the location
        String[] locations = getResources().getStringArray(R.array.locations);
        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, locations);
        locationAutoCompleteTextView.setAdapter(locationArrayAdapter);

        // edit / save button click listeners to update the profile information
        birthdateEditButton.setOnClickListener(view -> {
            Date birthdate = null;
            try {
                birthdate = getDatePickerInputValue(birthdateTextView, birthdateEditButton);
            } catch (ParseException e) {
                birthdateTextView.setText(DateFormat.getDateInstance().format(profileDsResponse.getBirthdate()));
            }
            if (birthdate != null) {
                editProfileController.updateBirthdate(userId, birthdate);
            }
        });
        genderEditButton.setOnClickListener(view -> {
            String gender = getDropdownInputValue("Gender", genderAutoCompleteTextView, genderEditButton);
            if (gender != null) {
                editProfileController.updateGender(userId, gender);
            }
        });
        locationEditButton.setOnClickListener(view -> {
            String location = getDropdownInputValue("Location", locationAutoCompleteTextView, locationEditButton);
            if (location != null) {
                editProfileController.updateLocation(userId, location);
            }
        });
        profilePictureLinkEditButton.setOnClickListener(view -> {
            String profilePictureLink =
                    getEditTextInputValue("Profile Picture Link", profilePictureLinkEditText, profilePictureLinkEditButton);
            if (profilePictureLink != null) {
                editProfileController.updateProfilePictureLink(userId, profilePictureLink);
            }
        });
        aboutMeEditButton.setOnClickListener(view -> {
            String aboutMe = getEditTextInputValue("About Me", aboutMeEditText, aboutMeEditButton);
            if (aboutMe != null) {
                editProfileController.updateAboutMe(userId, aboutMe);
            }
        });

        return root;
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        // gets user profile
        profileDsResponse = editProfileController.getProfile(userId);

        // renders user profile to the screen
        birthdateTextView.setText(DateFormat.getDateInstance().format(profileDsResponse.getBirthdate()));
        genderAutoCompleteTextView.setText(profileDsResponse.getGender());
        locationAutoCompleteTextView.setText(profileDsResponse.getLocation());
        profilePictureLinkEditText.setText(profileDsResponse.getProfilePictureLink());
        aboutMeEditText.setText(profileDsResponse.getAboutMe());

        return super.getEnterTransition();
    }

    /**
     * returns user input value for the date picker. It also toggles edit enabled / disabled and
     * edit / save icon of the button accordingly;
     *
     * @param textView TextView component
     * @param button   edit / save button component
     * @return user input value. Returns null when the user clicks button to start editing.
     */
    private Date getDatePickerInputValue(TextView textView, ImageButton button) throws ParseException {
        if (textView.isEnabled()) {
            textView.setEnabled(false);
            button.setImageResource(R.drawable.ic_edit);
            return DateFormat.getDateInstance(DateFormat.DEFAULT).parse(textView.getText().toString());
        } else {
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
                textView.setEnabled(true);
                button.setImageResource(R.drawable.ic_save);
            };

            // creates and shows DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
            datePickerDialog.show();

            return null;
        }
    }

    /**
     * returns user input value for the dropdown. It also toggles edit enabled / disabled and
     * edit / save icon of the button accordingly;
     *
     * @param field                profile field to be updated
     * @param autoCompleteTextView AutoCompleteTextView component
     * @param button               edit / save button component
     * @return user input value. Returns null when the user clicks button to start editing.
     */
    private String getDropdownInputValue(String field, AutoCompleteTextView autoCompleteTextView, ImageButton button) {
        if (autoCompleteTextView.isEnabled()) {
            autoCompleteTextView.setEnabled(false);
            button.setContentDescription("Click to edit " + field);
            button.setImageResource(R.drawable.ic_edit);
            return autoCompleteTextView.getText().toString();
        } else {
            autoCompleteTextView.setEnabled(true);
            button.setContentDescription("Click to save " + field);
            button.setImageResource(R.drawable.ic_save);
            return null;
        }
    }

    /**
     * returns user input value for the EditText. It also toggles edit enabled / disabled and edit / save icon of
     * the button accordingly;
     *
     * @param field    profile field to be updated
     * @param editText EditText component
     * @param button   edit / save button component
     * @return user input value. Returns null when the user clicks button to start editing.
     */
    private String getEditTextInputValue(String field, EditText editText, ImageButton button) {
        if (editText.isEnabled()) {
            editText.setEnabled(false);
            button.setContentDescription("Click to edit " + field);
            button.setImageResource(R.drawable.ic_edit);
            return editText.getText().toString();
        } else {
            editText.setEnabled(true);
            button.setContentDescription("Click to save " + field);
            button.setImageResource(R.drawable.ic_save);
            return null;
        }
    }
}