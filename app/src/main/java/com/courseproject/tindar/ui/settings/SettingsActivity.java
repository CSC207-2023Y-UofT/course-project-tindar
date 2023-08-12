package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;

public class SettingsActivity extends AppCompatActivity {
    // Button that sends user to change email screen.
    Button changeEmailButton;
    // Button that sends user to change password screen.
    Button changePasswordButton;
    // Button that sends user back to the home screen.
    ImageButton settingsBackButton;
    // userId of the account.
    String userId;

    /**
     * Creates the settings screen for the user to view.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // pulls userId from the Intent sent by BlankNavActivity and sets userId to hold that info.
        Intent intent0 = getIntent();
        userId = intent0.getStringExtra("user_id");

        // Allows us to use the two buttons.
        changeEmailButton = findViewById(R.id.button_email_change);
        changePasswordButton = findViewById(R.id.button_password_change);

        // When the "Change Email" button is clicked, sends the user to ChangeEmailActivity
        changeEmailButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ChangeEmailActivity.class);
            // Sends the userId to the next screen.
            intent.putExtra("user_id", userId);
            startActivity(intent);});

        // When the "Change Password" button is clicked, sends the user to ChangePasswordActivity
        changePasswordButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
            // Sends the userId to the next screen.
            intent.putExtra("user_id", userId);
            startActivity(intent);});

        // When the back button in the top left is clicked, sends the user back to BlankNavActivity
        settingsBackButton = findViewById(R.id.button_back_settings_change);
        settingsBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, BlankNavActivity.class);
            // Sends the userId to the next screen.
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });
    }
}