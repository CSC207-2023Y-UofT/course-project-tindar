package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.ui.editfilters.EditFiltersActivity;
import com.courseproject.tindar.ui.editprofile.EditProfileActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editProfileButton = findViewById(R.id.button_edit_profile);
        Button editFiltersButton = findViewById(R.id.button_edit_filters);

        editProfileButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });

        editFiltersButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditFiltersActivity.class);
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });
    }
}