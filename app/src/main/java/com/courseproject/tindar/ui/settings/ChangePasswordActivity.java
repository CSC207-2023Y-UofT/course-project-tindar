package com.courseproject.tindar.ui.settings;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.courseproject.tindar.BlankNavActivity;
import com.courseproject.tindar.R;

public class ChangePasswordActivity extends AppCompatActivity {
    ImageButton changePasswordBackButton;
    Button submitPasswordChangeButton;
    EditText changePasswordText;
    EditText changePasswordRetype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        changePasswordBackButton = findViewById(R.id.button_back_password_change);
        changePasswordBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChangePasswordActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        changePasswordText = findViewById(R.id.edit_text_password_change);
        changePasswordRetype = findViewById(R.id.edit_text_password_retyped_change);

        submitPasswordChangeButton = findViewById(R.id.button_submit_password_change);
        submitPasswordChangeButton.setOnClickListener(view -> {
            if (changePasswordText.getText().toString().equals(changePasswordRetype.getText().toString())) {

                Intent intent = new Intent(ChangePasswordActivity.this, BlankNavActivity.class);
                startActivity(intent);
            }
            else {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_inputs_dont_match, null);

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
    }
}