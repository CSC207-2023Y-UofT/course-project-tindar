package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.ui.editfilters.EditFiltersActivity;
import com.courseproject.tindar.ui.editprofile.EditProfileActivity;
import com.courseproject.tindar.ui.home.HomeFragment;
import com.courseproject.tindar.SignupFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editProfileButton = findViewById(R.id.button_edit_profile);
        Button editFiltersButton = findViewById(R.id.button_edit_filters);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, blankNav.class);
//            change blankNav or login name, currently like this since i'm not sure what we want the landing page to be
//            used to test the blank nav and for peeps to check it out and extend off it
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });

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