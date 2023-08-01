package com.courseproject.tindar.ui.editprofile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.editprofile.EditProfileController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileInteractor;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditProfileActivity extends AppCompatActivity {

    private TextView birthdateTextView;
    private AutoCompleteTextView genderAutoCompleteTextView, locationAutoCompleteTextView;
    private EditText profilePictureLinkEditText, aboutMeEditText;
    private ImageButton birthdateEditButton, genderEditButton, locationEditButton, profilePictureLinkEditButton, aboutMeEditButton;
    private EditProfileController editProfileController;
    private EditProfileDsResponseModel profileDsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // finds components and assigns each to a variable
        birthdateTextView = findViewById(R.id.text_view_birthday);
        genderAutoCompleteTextView = findViewById(R.id.auto_complete_text_view_gender);
        locationAutoCompleteTextView = findViewById(R.id.auto_complete_text_view_location);
        profilePictureLinkEditText = findViewById(R.id.edit_text_profile_picture_link);
        aboutMeEditText = findViewById(R.id.edit_text_about_me);
        birthdateEditButton = findViewById(R.id.button_edit_birthday);
        genderEditButton = findViewById(R.id.button_edit_gender);
        locationEditButton = findViewById(R.id.button_edit_location);
        profilePictureLinkEditButton = findViewById(R.id.button_edit_profile_picture_link);
        aboutMeEditButton = findViewById(R.id.button_edit_about_me);

        // retrieves user Id passed from other activity
        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");

        // gets profile for the user
        EditProfileDsGateway editProfileDatabaseHelper = DatabaseHelper.getInstance(EditProfileActivity.this);
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(editProfileDatabaseHelper);
        editProfileController = new EditProfileController(editProfileInteractor);
        profileDsResponse = editProfileController.getProfile(userId);

        // renders user profile to the screen
        birthdateTextView.setText(DateFormat.getDateInstance().format(profileDsResponse.getBirthdate()));
        genderAutoCompleteTextView.setText(profileDsResponse.getGender());
        locationAutoCompleteTextView.setText(profileDsResponse.getLocation());
        profilePictureLinkEditText.setText(profileDsResponse.getProfilePictureLink());
        aboutMeEditText.setText(profileDsResponse.getAboutMe());

        // creates dropdown menu for the gender
        String[] genders = getResources().getStringArray(R.array.genders);
        ArrayAdapter<String> genderArrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, genders);
        genderAutoCompleteTextView.setAdapter(genderArrayAdapter);

        // creates dropdown menu for the location
        String[] locations = getResources().getStringArray(R.array.locations);
        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, locations);
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
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
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