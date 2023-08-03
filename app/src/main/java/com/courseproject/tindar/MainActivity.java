package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.button_login);
        Button signupButton = findViewById(R.id.button_login);

        passwordText = (EditText)findViewById(R.id.password);
        emailText = (EditText)findViewById(R.id.email);

        loginButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, BlankNavActivity.class);
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });
    }
}