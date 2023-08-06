package com.courseproject.tindar.ui.editfilters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.courseproject.tindar.BlankNavViewModel;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.editfilters.EditFiltersController;
import com.courseproject.tindar.databinding.FragmentEditFiltersBinding;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.entities.FiltersFactory;
import com.courseproject.tindar.presenters.editfilters.EditFiltersPresentationFormatter;
import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsGateway;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
import com.courseproject.tindar.usecases.editfilters.EditFiltersInputBoundary;
import com.courseproject.tindar.usecases.editfilters.EditFiltersInteractor;
import com.courseproject.tindar.usecases.editfilters.EditFiltersPresenter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass. This is a fragment for editing filters screen in the blank nav activity.
 */
public class EditFiltersFragment extends Fragment {

    private String userId;
    private TextView preferredGendersTextView, preferredLocationsTextView;
    private EditText preferredAgeMinimumEditText, preferredAgeMaximumEditText;
    private ImageButton preferredGendersEditButton, preferredLocationsEditButton, preferredAgeGroupEditButton;
    private EditFiltersController editFiltersController;
    private ArrayList<String> preferredGenders, preferredLocations;
    private int preferredAgeMinimum, preferredAgeMaximum;
    private String[] genders, locations;
    private boolean[] isGenderPreferredList, isLocationPreferredList;

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
        FragmentEditFiltersBinding binding = FragmentEditFiltersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // finds components and assigns each to a variable
        preferredGendersTextView = root.findViewById(R.id.text_view_preferred_gender);
        preferredLocationsTextView = root.findViewById(R.id.text_view_preferred_location);
        preferredAgeMinimumEditText = root.findViewById(R.id.edit_text_preferred_age_min);
        preferredAgeMaximumEditText = root.findViewById(R.id.edit_text_preferred_age_max);
        preferredGendersEditButton = root.findViewById(R.id.button_edit_preferred_genders);
        preferredLocationsEditButton = root.findViewById(R.id.button_edit_preferred_locations);
        preferredAgeGroupEditButton = root.findViewById(R.id.button_edit_preferred_age_group);

        // instantiates controller
        EditFiltersDsGateway editFiltersDatabaseHelper = DatabaseHelper.getInstance(getContext());
        EditFiltersPresenter editFiltersPresenter = new EditFiltersPresentationFormatter();
        FiltersFactory filtersFactory = new FiltersFactory();
        EditFiltersInputBoundary editFiltersInteractor =
                new EditFiltersInteractor(editFiltersDatabaseHelper, editFiltersPresenter, filtersFactory);
        editFiltersController = new EditFiltersController(editFiltersInteractor);

        // initializes boolean array which will mark for all genders available whether each is preferred by the user
        genders = getResources().getStringArray(R.array.genders);
        isGenderPreferredList = new boolean[genders.length];

        // initializes boolean array which will mark for all locations available whether each is preferred by the user
        locations = getResources().getStringArray(R.array.locations);
        isLocationPreferredList = new boolean[locations.length];

        // text view click listeners to pop-up multi-select window for preferred genders
        preferredGendersTextView.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
            dialog.setTitle("Choose Preferred Gender");
            dialog.setCancelable(false);

            dialog.setMultiChoiceItems(genders, isGenderPreferredList, (dialogInterface, index, isChecked) -> {
                if (isChecked) {
                    preferredGenders.add(genders[index]);
                    Collections.sort(preferredGenders);
                } else {
                    preferredGenders.remove(genders[index]);
                }
            });

            dialog.setPositiveButton("OK", (dialogInterface, index) -> {
                String selectedGendersString = String.join(",", preferredGenders);
                preferredGendersTextView.setText(selectedGendersString);
            });

            dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

            dialog.show();
        });

        // text view click listeners to pop-up multi-select window for preferred locations
        preferredLocationsTextView.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
            dialog.setTitle("Choose Preferred Location");
            dialog.setCancelable(false);

            dialog.setMultiChoiceItems(locations, isLocationPreferredList, (dialogInterface, index, isChecked) -> {
                if (isChecked) {
                    preferredLocations.add(locations[index]);
                    Collections.sort(preferredLocations);
                } else {
                    preferredLocations.remove(locations[index]);
                }
            });

            dialog.setPositiveButton("OK", (dialogInterface, index) -> {
                String selectedLocationsString = String.join(",", preferredLocations);
                preferredLocationsTextView.setText(selectedLocationsString);
            });

            dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

            dialog.show();
        });

        // edit / save button click listeners to update filters information
        preferredGendersEditButton.setOnClickListener(view -> {
            ArrayList<String> selectedGenders = getMultiSelectDropdownInputValue(
                    "Preferred Genders",
                    preferredGendersTextView,
                    preferredGendersEditButton,
                    preferredGenders
            );
            if (selectedGenders != null) {
                editFiltersController.updatePreferredGenders(userId, selectedGenders);
            }
        });
        preferredLocationsEditButton.setOnClickListener(view -> {
            ArrayList<String> selectedLocations = getMultiSelectDropdownInputValue(
                    "Preferred Locations",
                    preferredLocationsTextView,
                    preferredLocationsEditButton,
                    preferredLocations
            );
            if (selectedLocations != null) {
                editFiltersController.updatePreferredLocations(userId, selectedLocations);
            }
        });
        preferredAgeGroupEditButton.setOnClickListener(view -> {
            int[] selectedAgeGroup = getTwoEditTextNumberInputValue(
                    "Preferred Age Group",
                    preferredAgeMinimumEditText,
                    preferredAgeMaximumEditText,
                    preferredAgeGroupEditButton
            );
            if (selectedAgeGroup != null) {
                try {
                    editFiltersController.updatePreferredAgeGroup(userId, selectedAgeGroup[0], selectedAgeGroup[1]);
                    preferredAgeMinimum = selectedAgeGroup[0];
                    preferredAgeMaximum = selectedAgeGroup[1];

                    // if age group the user enters is invalid, error message window will pop-up, and preferred age
                    // values will reset to the old values.
                } catch (InvalidAgeGroup e) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
                    dialog.setMessage(e.getMessage());
                    dialog.setPositiveButton("OK", (dialogInterface, index) -> {
                        preferredAgeMinimumEditText.setText(String.valueOf(preferredAgeMinimum));
                        preferredAgeMaximumEditText.setText(String.valueOf(preferredAgeMaximum));
                    });
                    dialog.show();
                }
            }
        });

        return root;
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        // gets filters for the user
        EditFiltersDsResponseModel filtersDsResponse = editFiltersController.getFilters(userId);

        // assigns each of filters to a variable
        preferredGenders = new ArrayList<>(filtersDsResponse.getPreferredGenders());
        preferredLocations = new ArrayList<>(filtersDsResponse.getPreferredLocations());
        preferredAgeMinimum = filtersDsResponse.getPreferredAgeMinimum();
        preferredAgeMaximum = filtersDsResponse.getPreferredAgeMaximum();

        // renders filters to the screen
        preferredGendersTextView.setText(String.join(", ", preferredGenders));
        preferredLocationsTextView.setText(String.join(", ", preferredLocations));
        preferredAgeMinimumEditText.setText(String.valueOf(preferredAgeMinimum));
        preferredAgeMaximumEditText.setText(String.valueOf(preferredAgeMaximum));

        // mark for all genders available whether each is preferred by the user
        for (int i = 0; i < genders.length; i++) {
            isGenderPreferredList[i] = preferredGenders.contains(genders[i]);
        }

        // mark for all locations available whether each is preferred by the user
        for (int i = 0; i < locations.length; i++) {
            isLocationPreferredList[i] = preferredLocations.contains(locations[i]);
        }


        return super.getEnterTransition();
    }

    /**
     * returns user input value for the multi-select dropdown. It also toggles edit enabled / disabled and
     * edit / save icon of the button accordingly;
     *
     * @param field    filters field to be updated
     * @param textView TextView component
     * @param button   edit / save button component
     * @return user input value. Returns null when the user clicks button to start editing.
     */
    private ArrayList<String> getMultiSelectDropdownInputValue(String field, TextView textView,
                                                               ImageButton button, ArrayList<String> selections) {
        if (textView.isEnabled()) {
            textView.setEnabled(false);
            button.setContentDescription("Click to edit " + field);
            button.setImageResource(R.drawable.ic_edit);
            return selections;
        } else {
            textView.setEnabled(true);
            button.setContentDescription("Click to save " + field);
            button.setImageResource(R.drawable.ic_save);
            return null;
        }
    }

    /**
     * returns user number input value for the two EditTexts. It also toggles edit enabled / disabled and edit / save
     * icon of the button accordingly;
     *
     * @param field     filters field to be updated
     * @param editText1 first EditText component
     * @param editText2 second EditText component
     * @param button    edit / save button component
     * @return user number input values from two edit texts. Returns null when the user clicks button to start editing.
     */
    public int[] getTwoEditTextNumberInputValue(String field, EditText editText1, EditText editText2,
                                                ImageButton button) {
        if (editText1.isEnabled()) {
            // shows error message if user tries to save without input value
            if(TextUtils.isEmpty(editText1.getText().toString())) {
                editText1.setError("Please input some number.");
                return null;
            }
            if (TextUtils.isEmpty(editText2.getText().toString())) {
                editText2.setError("Please input some number.");
                return null;
            }

            editText1.setEnabled(false);
            editText2.setEnabled(false);
            button.setContentDescription("Click to edit " + field);
            button.setImageResource(R.drawable.ic_edit);
            int number1 = Integer.parseInt(editText1.getText().toString());
            int number2 = Integer.parseInt(editText2.getText().toString());
            return new int []{number1, number2};
        } else {
            editText1.setEnabled(true);
            editText2.setEnabled(true);
            button.setContentDescription("Click to save " + field);
            button.setImageResource(R.drawable.ic_save);
            return null;
        }
    }
}