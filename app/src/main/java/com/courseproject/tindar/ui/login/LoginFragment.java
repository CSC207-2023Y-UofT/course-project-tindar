package com.courseproject.tindar.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.courseproject.tindar.R;
import com.courseproject.tindar.SignupFragment;
import com.courseproject.tindar.ui.home.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText email;
    private EditText password;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        Button signupButton = (Button) rootView.findViewById(R.id.button_signup);
        Button loginButton = (Button) rootView.findViewById(R.id.button_login);
        email = (EditText) rootView.findViewById(R.id.et_email);
        password = (EditText) rootView.findViewById(R.id.password);

        signupButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        int id = view.getId();
        if (id == R.id.button_signup) {
            fragment = new SignupFragment();
            replaceFragment(fragment);
        } else if (id == R.id.button_login) { //will be linked to blank nav page when implemented
            // connect to controller and check if password is correct

            // if correct then go to home
            fragment = new HomeFragment();
            replaceFragment(fragment);

            // if not correct then display error

        }

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_login, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}