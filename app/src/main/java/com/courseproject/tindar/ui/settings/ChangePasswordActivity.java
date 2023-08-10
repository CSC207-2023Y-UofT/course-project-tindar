package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;

public class ChangePasswordActivity extends AppCompatActivity {
    ImageButton changePasswordBackButton;
    Button submitPasswordChangeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        changePasswordBackButton = findViewById(R.id.button_back_password_change);
        changePasswordBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangePasswordActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        submitPasswordChangeButton = findViewById(R.id.button_submit_password_change);
        submitPasswordChangeButton.setOnClickListener(view -> {

            Intent intent = new Intent(ChangePasswordActivity.this, BlankNavActivity.class);
            startActivity(intent);
        });
    }
}