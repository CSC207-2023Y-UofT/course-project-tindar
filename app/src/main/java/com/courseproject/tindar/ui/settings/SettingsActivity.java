package com.courseproject.tindar.ui.settings;

import android.content.Intent;
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
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent0 = getIntent();
        userId = intent0.getStringExtra("user_id");

        changeEmailButton = findViewById(R.id.button_email_change);
        changePasswordButton = findViewById(R.id.button_password_change);

        changeEmailButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ChangeEmailActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);});

        changePasswordButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);});

        settingsBackButton = findViewById(R.id.button_back_settings_change);
        settingsBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, BlankNavActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });
    }
}