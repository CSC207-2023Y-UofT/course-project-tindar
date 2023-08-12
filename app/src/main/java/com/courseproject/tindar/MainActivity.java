package com.courseproject.tindar;


import android.os.Bundle;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.ui.login.LoginActivity;

/**
 * A simple {@link AppCompatActivity} subclass. This is an entry point of the application
 */
public class MainActivity extends AppCompatActivity {

    /**
     * creates Main activity. It instantly loads Login screen.
     *
     * @param savedInstanceState a reference to a Bundle object that is passed into the onCreate method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loads Login screen
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}