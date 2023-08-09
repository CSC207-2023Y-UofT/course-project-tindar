package com.courseproject.tindar.ui.editfilters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;
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
    private ImageButton filtersEditButton;
    private Button filtersSubmitButton;
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
        filtersEditButton = root.findViewById(R.id.button_edit_filters);
        filtersSubmitButton = root.findViewById(R.id.button_submit_filters);

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

        // click listener for filters edit button
        filtersEditButton.setOnClickListener(view -> setEditEnabled(true));

        // click listener for filters submit button to update the filters
        filtersSubmitButton.setOnClickListener(view -> {
            EditFiltersModel newFilters  = getFiltersInputValues();
            if (newFilters != null) {
                try {
                    editFiltersController.updateFilters(userId, newFilters);
                    preferredAgeMinimum = newFilters.getPreferredAgeMinimum();
                    preferredAgeMaximum = newFilters.getPreferredAgeMaximum();
                    setEditEnabled(false);

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

        // text view click listener to pop-up multi-select window for preferred genders
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

        // text view click listener to pop-up multi-select window for preferred locations
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

        return root;
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        // editing is disabled on enter transition
        setEditEnabled(false);

        // gets filters for the user
        EditFiltersModel filtersDsResponse = editFiltersController.getFilters(userId);

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
     * returns filters input values gathering texts from each edit fields
     *
     * @return filters input values.
     */
    private EditFiltersModel getFiltersInputValues() {
        // shows error message if user tries to submit without minimum / maximum preferred age
        boolean isNoMinimumPreferredAge = TextUtils.isEmpty(preferredAgeMinimumEditText.getText().toString());
        boolean isNoMaximumPreferredAge = TextUtils.isEmpty(preferredAgeMaximumEditText.getText().toString());
        if (isNoMinimumPreferredAge || isNoMaximumPreferredAge) {
            if (isNoMinimumPreferredAge) {preferredAgeMinimumEditText.setError("Please enter minimum age.");}
            if (isNoMaximumPreferredAge) {preferredAgeMaximumEditText.setError("Please enter maximum age.");}
            return null;
        }

        return new EditFiltersModel(preferredGenders, preferredLocations,
                Integer.parseInt(preferredAgeMinimumEditText.getText().toString()),
                Integer.parseInt(preferredAgeMaximumEditText.getText().toString())
        );
    }

    /**
     * enables(disables) editing. set all edit fields enabled(disabled), filters submit button enabled(disabled),
     * and filters edit button invisible(visible).
     *
     * @param enabled indicates whether to enable or disable editing
     */
    private void setEditEnabled(boolean enabled) {
        filtersEditButton.setVisibility(enabled ? View.INVISIBLE : View.VISIBLE);
        filtersSubmitButton.setEnabled(enabled);
        preferredGendersTextView.setEnabled(enabled);
        preferredLocationsTextView.setEnabled(enabled);
        preferredAgeMinimumEditText.setEnabled(enabled);
        preferredAgeMaximumEditText.setEnabled(enabled);
    }
}