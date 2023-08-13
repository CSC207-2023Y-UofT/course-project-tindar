package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.editaccount.EditAccountController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsGateway;
import com.courseproject.tindar.usecases.editaccount.EditAccountInteractor;

public class ChangeEmailActivity extends AppCompatActivity {
    // Button that sends user back to the settings screen.
    ImageButton changeEmailBackButton;
    // Button that submits changes to the email.
    Button submitEmailChangeButton;
    // Text box to put in new email.
    EditText changeEmailText;
    // Text box to put in new email again.
    EditText changeEmailRetype;
    // Text box to put in the current password for validation.
    EditText changeEmailPasswordValidation;
    // userId of the account.
    String userId;

    /**
     * Creates the email change screen for the user to view.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        // Retrieves userId from the last screen.
        Intent intent0 = getIntent();
        userId = intent0.getStringExtra("user_id");

        // Creates the controller to use controller methods.
        EditAccountDsGateway editAccountDatabaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        EditAccountInteractor editAccountInteractor = new EditAccountInteractor(editAccountDatabaseHelper);
        EditAccountController editAccountController = new EditAccountController(editAccountInteractor);

        // When the button in the top left is clicked, return user to settings screen.
        changeEmailBackButton = findViewById(R.id.button_back_email_change);
        changeEmailBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangeEmailActivity.this, SettingsActivity.class);
            // Sends userId to the next screen.
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        // Allows for usage of text in textboxes for methods.
        changeEmailText = findViewById(R.id.edit_text_email_change_1);
        changeEmailRetype = findViewById(R.id.edit_text_email_change_2);
        changeEmailPasswordValidation = findViewById(R.id.edit_text_email_password);

        // When the submit change button is pressed,
        submitEmailChangeButton = findViewById(R.id.button_submit_email_change);
        submitEmailChangeButton.setOnClickListener(view -> {
            if (TextUtils.isEmpty(changeEmailText.getText().toString())) {
                changeEmailText.setError("Please enter email.");
                return;
            }

            // Checks if the email in both fields is the same.
            // If not, sends a pop-up informing the user the inputs do not match.
            if (changeEmailText.getText().toString().equals(changeEmailRetype.getText().toString())) {
                // Checks if the password inputted is the current password for the account.
                // If not, sends a pop-up informing the user the password is incorrect.
                if (editAccountController.validatePassword(userId, changeEmailPasswordValidation.getText().toString())) {
                    // Checks if the email is already in use by another account.
                    // If so, sends a pop-up informing the user the email is already used.
                    // Will also send that pop-up if email fields are empty.
                    // Otherwise, changes the email and sends the user back to the home screen.
                    if (editAccountController.updateEmail(userId, changeEmailText.getText().toString())) {
                        // Send back to home screen
                        Intent intent = new Intent(ChangeEmailActivity.this, BlankNavActivity.class);
                        // Sends userId to the next screen.
                        intent.putExtra("user_id", userId);
                        startActivity(intent);
                    } else {
                        // This makes the popup window for the email already used alert.
                        View popupView = View.inflate(ChangeEmailActivity.this, R.layout.popup_email_already_used, null);

                        // create the popup window
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        boolean focusable = true; // lets taps outside the popup also dismiss it
                        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                        // show the popup window
                        // which view you pass in doesn't matter, it is only used for the window token
                        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                        // dismiss the popup window when touched
                        popupView.setOnTouchListener((v, event) -> {
                            popupWindow.dismiss();
                            v.performClick();
                            return true;
                        });
                    }
                } else {
                    // This makes the popup for the incorrect password.
                    View popupView = View.inflate(ChangeEmailActivity.this, R.layout.popup_current_password_incorrect, null);

                    // create the popup window
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                    // show the popup window
                    // which view you pass in doesn't matter, it is only used for the window token
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    // dismiss the popup window when touched
                    popupView.setOnTouchListener((v, event) -> {
                        v.performClick();
                        popupWindow.dismiss();
                        return true;
                    });
                }
            }
            else {
                // This creates the popup for if the emails do not match.
                View popupView = View.inflate(ChangeEmailActivity.this, R.layout.popup_inputs_dont_match, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window token
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener((v, event) -> {
                    v.performClick();
                    popupWindow.dismiss();
                    return true;
                });
            }
        });
    }
}