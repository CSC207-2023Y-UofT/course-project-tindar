package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;

public class SettingsActivity extends AppCompatActivity {

    Button changeEmailButton;
    Button changePasswordButton;
    ImageButton settingsBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        changeEmailButton = findViewById(R.id.button_email_change);
        changePasswordButton = findViewById(R.id.button_password_change);

        changeEmailButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ChangeEmailActivity.class);
            startActivity(intent);});

        settingsBackButton = findViewById(R.id.button_back_settings_change);
        settingsBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, BlankNavActivity.class);
            startActivity(intent);
        });
    }
}