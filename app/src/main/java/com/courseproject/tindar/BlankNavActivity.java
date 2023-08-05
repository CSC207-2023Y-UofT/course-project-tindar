package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.courseproject.tindar.databinding.ActivityBlankNavBinding;

public class BlankNavActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    public FragmentCommunicator fragmentCommunicator;
    public Button likeButton;
    public Button dislikeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        Button likeButton = (Button) findViewById(R.id.likeButton);
        Button dislikeButton = (Button) findViewById(R.id.dislikeButton);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCommunicator.updateShownProfile();
            }
        });

        // retrieves user Id passed from other activity
        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");

        // instantiates the blank nav view model and sets the user id that is currently logged in
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(this).get(BlankNavViewModel.class);
        blankNavViewModel.setUserId(userId);

        ActivityBlankNavBinding binding = ActivityBlankNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarBlankNav.toolbar);
        binding.appBarBlankNav.toolbar.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_match_list, R.id.nav_profile, R.id.nav_filters)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_blank_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blank_nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_blank_nav);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}