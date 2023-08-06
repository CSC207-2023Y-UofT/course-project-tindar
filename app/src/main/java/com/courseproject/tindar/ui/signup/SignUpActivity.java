package com.courseproject.tindar.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.MainActivity;
import com.courseproject.tindar.R;
import com.courseproject.tindar.controllers.signup.SignUpController;
import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.entities.AccountFactory;
import com.courseproject.tindar.presenters.signup.InvalidCredentials;
import com.courseproject.tindar.presenters.signup.SignUpPresentationFormatter;
import com.courseproject.tindar.usecases.signup.SignUpDsGateway;
import com.courseproject.tindar.usecases.signup.SignUpInputBoundary;
import com.courseproject.tindar.usecases.signup.SignUpInteractor;
import com.courseproject.tindar.usecases.signup.SignUpPresenter;
import com.courseproject.tindar.usecases.signup.SignUpRequestModel;

public class SignUpActivity extends AppCompatActivity {

    Button signUpButton;
    ImageButton backButton;
    EditText displayNameEditText, emailEditText, passwordEditText, retypedPasswordEditText;
    SignUpController signUpController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // finds components and assigns each to a variable
        signUpButton = findViewById(R.id.button_sign_up);
        backButton = findViewById(R.id.button_back_sign_up);
        displayNameEditText = findViewById(R.id.edit_text_display_name_sign_up);
        emailEditText = findViewById(R.id.edit_text_email_sign_up);
        passwordEditText = findViewById(R.id.edit_text_password_sign_up);
        retypedPasswordEditText = findViewById(R.id.edit_text_password_retyped_sign_up);

        // instantiates controller
        SignUpDsGateway signUpDatabaseHelper = DatabaseHelper.getInstance(SignUpActivity.this);
        SignUpPresenter signUpPresentationFormatter = new SignUpPresentationFormatter();
        AccountFactory accountFactory = new AccountFactory();
        SignUpInputBoundary signUpInteractor =
                new SignUpInteractor(signUpDatabaseHelper, signUpPresentationFormatter, accountFactory);
        signUpController = new SignUpController(signUpInteractor);

        AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);

        // sign-up button click listener on submit
        signUpButton.setOnClickListener(view -> {
            SignUpRequestModel accountCredentials = getAccountCredentialsInputValue(
                    displayNameEditText, emailEditText, passwordEditText, retypedPasswordEditText);
            if (accountCredentials != null) {
                try {
                    String successMessage = signUpController.createAccount(accountCredentials);
                    dialog.setMessage(successMessage);
                    dialog.setPositiveButton("OK", (dialogInterface, index) -> {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    });
                    dialog.show();
                } catch (InvalidCredentials e) {
                    dialog.setMessage(e.getMessage());
                    dialog.setPositiveButton("OK", null);
                    dialog.show();
                }
            }
        });

        // back button click listener
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public SignUpRequestModel getAccountCredentialsInputValue(EditText displayNameEditText, EditText emailEditText,
                                                 EditText passwordEditText, EditText retypedPasswordEditText) {

        boolean isNoDisplayName = TextUtils.isEmpty(displayNameEditText.getText().toString());
        boolean isNoEmail = TextUtils.isEmpty(emailEditText.getText().toString());
        boolean isNoPassword = TextUtils.isEmpty(passwordEditText.getText().toString());
        boolean isNoRetypedPassword = TextUtils.isEmpty(retypedPasswordEditText.getText().toString());

        // shows error message if user tries to submit without input value
        if (isNoDisplayName || isNoEmail || isNoPassword || isNoRetypedPassword) {
            if (isNoDisplayName) {displayNameEditText.setError("Please enter name.");}
            if (isNoEmail) {emailEditText.setError("Please enter name.");}
            if (isNoPassword) {passwordEditText.setError("Please enter name.");}
            if (isNoRetypedPassword) {retypedPasswordEditText.setError("Please enter name.");}
            return null;
        }

        return new SignUpRequestModel(
            displayNameEditText.getText().toString(),
            emailEditText.getText().toString(),
            passwordEditText.getText().toString(),
            retypedPasswordEditText.getText().toString()
        );
    }
}
