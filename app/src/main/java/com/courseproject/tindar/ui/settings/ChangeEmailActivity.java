package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
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

    ImageButton changeEmailBackButton;
    Button submitEmailChangeButton;
    EditText changeEmailText;
    EditText changeEmailRetype;
    EditText changeEmailPasswordValidation;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        Intent intent0 = getIntent();
        userId = intent0.getStringExtra("user_id");

        EditAccountDsGateway editAccountDatabaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        EditAccountInteractor editAccountInteractor = new EditAccountInteractor(editAccountDatabaseHelper);
        EditAccountController editAccountController = new EditAccountController(editAccountInteractor);

        changeEmailBackButton = findViewById(R.id.button_back_email_change);
        changeEmailBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangeEmailActivity.this, SettingsActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        changeEmailText = findViewById(R.id.edit_text_email_change_1);
        changeEmailRetype = findViewById(R.id.edit_text_email_change_2);
        changeEmailPasswordValidation = findViewById(R.id.edit_text_email_password);

        submitEmailChangeButton = findViewById(R.id.button_submit_email_change);
        submitEmailChangeButton.setOnClickListener(view -> {
            if (changeEmailText.getText().toString().equals(changeEmailRetype.getText().toString())) {
                if (editAccountController.validatePassword(userId, changeEmailPasswordValidation.getText().toString())) {
                    if (editAccountController.updateEmail(userId, changeEmailText.getText().toString())) {
                        // Send back to home screen
                        Intent intent = new Intent(ChangeEmailActivity.this, BlankNavActivity.class);
                        intent.putExtra("user_id", userId);
                        startActivity(intent);
                    } else {
                        LayoutInflater inflater = (LayoutInflater)
                                getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = inflater.inflate(R.layout.popup_email_already_used, null);

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
                            return true;
                        });
                    }
                } else {
                    LayoutInflater inflater = (LayoutInflater)
                            getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_current_password_incorrect, null);

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
                        return true;
                    });
                }
            }
            else {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_inputs_dont_match, null);

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
                    return true;
                });
            }
        });
    }
}