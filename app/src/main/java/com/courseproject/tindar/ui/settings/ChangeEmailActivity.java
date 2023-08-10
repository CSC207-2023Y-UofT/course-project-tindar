package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;

public class ChangeEmailActivity extends AppCompatActivity {

    ImageButton changeEmailBackButton;
    Button submitEmailChangeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        changeEmailBackButton = findViewById(R.id.button_back_email_change);
        changeEmailBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangeEmailActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        submitEmailChangeButton = findViewById(R.id.button_submit_email_change);
        submitEmailChangeButton.setOnClickListener(view -> {

            Intent intent = new Intent(ChangeEmailActivity.this, BlankNavActivity.class);
            startActivity(intent);
        });
    }
}