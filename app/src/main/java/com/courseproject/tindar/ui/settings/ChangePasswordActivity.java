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

public class ChangePasswordActivity extends AppCompatActivity {
    ImageButton changePasswordBackButton;
    Button submitPasswordChangeButton;
    EditText changePasswordText;
    EditText changePasswordRetype;
    EditText changePasswordValidation;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent intent0 = getIntent();
        userId = intent0.getStringExtra("user_id");


        EditAccountDsGateway editAccountDatabaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        EditAccountInteractor editAccountInteractor = new EditAccountInteractor(editAccountDatabaseHelper);
        EditAccountController editAccountController = new EditAccountController(editAccountInteractor);

        changePasswordBackButton = findViewById(R.id.button_back_password_change);
        changePasswordBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangePasswordActivity.this, SettingsActivity.class);

            startActivity(intent);
        });

        changePasswordText = findViewById(R.id.edit_text_password_change);
        changePasswordRetype = findViewById(R.id.edit_text_password_retyped_change);
        changePasswordValidation = findViewById(R.id.edit_text_password_password);

        submitPasswordChangeButton = findViewById(R.id.button_submit_password_change);
        submitPasswordChangeButton.setOnClickListener(view -> {
            if (changePasswordText.getText().toString().equals(changePasswordRetype.getText().toString())) {
                if (editAccountController.validatePassword(userId, changePasswordValidation.getText().toString())) {
                    editAccountController.updatePassword(userId, changePasswordText.getText().toString());

                    Intent intent = new Intent(ChangePasswordActivity.this, BlankNavActivity.class);
                    intent.putExtra("user_id", userId);
                    startActivity(intent);
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
            } else {
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