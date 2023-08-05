package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BlankNavActivity.class);
//            change BlankNavActivity or login name, currently like this since i'm not sure what we want the landing page to be
//            used to test the blank nav and for peeps to check it out and extend off it
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });
    }
}