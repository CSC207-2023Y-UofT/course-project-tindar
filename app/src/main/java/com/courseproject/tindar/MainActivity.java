package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import com.courseproject.tindar.controllers.login.LoginController;

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
        Button signupButton = findViewById(R.id.button_signup);

        LoginController loginController = new LoginController();
        passwordText = (EditText)findViewById(R.id.password);
        emailText = (EditText)findViewById(R.id.email);

        loginButton.setOnClickListener(view -> {
            email = emailText.getText().toString();
            password = passwordText.getText().toString();

            if (loginController.checkUserPassword(email, password)) {
                Intent intent = new Intent(MainActivity.this, BlankNavActivity.class);
                intent.putExtra("user_id", "1");
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
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            intent.putExtra("user_id", "1");
            startActivity(intent);
        });
    }
}