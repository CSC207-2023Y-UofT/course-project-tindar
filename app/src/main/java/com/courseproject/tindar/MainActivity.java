package com.courseproject.tindar;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import android.content.Intent;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.controllers.login.LoginController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.ui.signup.SignUpActivity;
import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInteractor;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;

    private String email;
    private String password;

    /**
     * Login Screen
     * Allows user to input their email and password to login to their account
     * or they can use the signup button to go to the signup page to create a new account
     * <p>
     * this layer also checks for user's input being the correct account credentials
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.button_login);
        Button signupButton = findViewById(R.id.button_signup);
        passwordText = (EditText) findViewById(R.id.edit_text_password);
        emailText = (EditText) findViewById(R.id.edit_text_email);

        LoginDsGateway loginDatabaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        LoginInteractor loginInteractor = new LoginInteractor(loginDatabaseHelper);
        LoginController loginController = new LoginController(loginInteractor);

        LoginController finalLoginController = loginController;
        loginButton.setOnClickListener(view -> {
            email = emailText.getText().toString();
            password = passwordText.getText().toString();

            if (finalLoginController.checkUserPassword(email, password)) {
                Intent intent = new Intent(MainActivity.this, BlankNavActivity.class);
                intent.putExtra("user_id", finalLoginController.getUserId(email, password));
                startActivity(intent);
            } else {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_wrong_credentials, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window token
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener((v, event) -> {
                    popupWindow.dismiss();
                    return true;
                });
            }
        });

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}