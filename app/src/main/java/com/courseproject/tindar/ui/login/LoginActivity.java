package com.courseproject.tindar.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.login.LoginController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.ui.signup.SignUpActivity;
import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInteractor;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;

    private String email;
    private String password;

    /**
     * Creates Login Screen
     * Allows user to input their email and password to login to their account
     * or they can use the signup button to go to the signup page to create a new account
     * this layer also checks for user's input being the correct account credentials
     *
     * @param savedInstanceState a reference to a Bundle object that is passed into the onCreate method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.button_login);
        Button signupButton = findViewById(R.id.button_signup);
        passwordText = findViewById(R.id.edit_text_password);
        emailText = findViewById(R.id.edit_text_email);

        LoginDsGateway loginDatabaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        LoginInteractor loginInteractor = new LoginInteractor(loginDatabaseHelper);
        LoginController loginController = new LoginController(loginInteractor);

        loginButton.setOnClickListener(view -> {
            email = emailText.getText().toString();
            password = passwordText.getText().toString();

            if (loginController.checkUserPassword(email, password)) {
                Intent intent = new Intent(LoginActivity.this, BlankNavActivity.class);
                intent.putExtra("user_id", loginController.getUserId(email, password));
                startActivity(intent);
            } else {
                View popupView = View.inflate(LoginActivity.this, R.layout.popup_wrong_credentials, null);

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
                    v.performClick();
                    popupWindow.dismiss();
                    return true;
                });
            }
        });

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}