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
/** This class implements the ability to change the users password after creating an account
 */
public class ChangePasswordActivity extends AppCompatActivity {
    /** Button that sends the user back to the settings screen.*/
    ImageButton changePasswordBackButton;
    /** Button that submits the changes to the password.*/
    Button submitPasswordChangeButton;
    /** Text box that contains the new password.*/
    EditText changePasswordText;
    /** Text box to type the new password a second time.*/
    EditText changePasswordRetype;
    /** Text box to type the current password for validation.*/
    EditText changePasswordValidation;
    /** The user id of the account.*/
    String userId;

    /**
     * Creates the password change screen for the user to view.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        // Retrieves the user id from the previous screen.
        Intent intent0 = getIntent();
        userId = intent0.getStringExtra("user_id");

        // Creates the controller to use methods to change account details.
        EditAccountDsGateway editAccountDatabaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        EditAccountInteractor editAccountInteractor = new EditAccountInteractor(editAccountDatabaseHelper);
        EditAccountController editAccountController = new EditAccountController(editAccountInteractor);

        // When the button in the top left is pressed, sends the user back to the settings screen.
        changePasswordBackButton = findViewById(R.id.button_back_password_change);
        changePasswordBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangePasswordActivity.this, SettingsActivity.class);
            // Sends the user id to the next screen.
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        // Allows for the usage of text in text boxes to be used.
        changePasswordText = findViewById(R.id.edit_text_password_change);
        changePasswordRetype = findViewById(R.id.edit_text_password_retyped_change);
        changePasswordValidation = findViewById(R.id.edit_text_password_password);

        // When the button to submit password changes is pressed,
        submitPasswordChangeButton = findViewById(R.id.button_submit_password_change);
        submitPasswordChangeButton.setOnClickListener(view -> {
            if (TextUtils.isEmpty(changePasswordText.getText().toString())) {
                changePasswordText.setError("Please enter password.");
                return;
            }

            // Checks if both new passwords are the same.
            // If not, sends a pop-up saying inputs do not match.
            if (changePasswordText.getText().toString().equals(changePasswordRetype.getText().toString())) {
                // Checks if the current password is correct.
                // If not, sends a message saying password is incorrect.
                if (editAccountController.validatePassword(userId, changePasswordValidation.getText().toString())) {
                    // Checks if the new password is less than 6 characters long.
                    // If so, sends a message saying new password not long enough.
                    if (editAccountController.updatePassword(userId, changePasswordText.getText().toString())) {
                        // Sends the user back to the home screen.
                        Intent intent = new Intent(ChangePasswordActivity.this, BlankNavActivity.class);
                        // Sends the user id to the next screen.
                        intent.putExtra("user_id", userId);
                        startActivity(intent);
                    }
                    else {
                        // Creates popup window to say passwords is not long enough
                        View popupView = View.inflate(ChangePasswordActivity.this, R.layout.popup_password_not_long, null);

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
                    // Creates pop window to say the current password is incorrect.
                    View popupView = View.inflate(ChangePasswordActivity.this, R.layout.popup_current_password_incorrect, null);

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
                // Creates the popup window to say passwords do not match.
                View popupView = View.inflate(ChangePasswordActivity.this, R.layout.popup_inputs_dont_match, null);

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