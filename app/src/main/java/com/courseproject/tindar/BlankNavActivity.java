package com.courseproject.tindar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.courseproject.tindar.ui.login.LoginActivity;
import com.courseproject.tindar.ui.settings.SettingsActivity;
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

/**
 * Class containing code that manages the navigation drawer and settings triple dot dropdown
 */
public class BlankNavActivity extends AppCompatActivity {
    // android class that assists with configuration of the navigation ui
    private AppBarConfiguration mAppBarConfiguration;
    private String userId;

    /**
     * Creates the home page and navigation drawer that persists when user is logged in.
     * Associated with each of the fragments that are accessible from the navigation drawer.
     *
     * @param savedInstanceState last saved state of each activity in the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieves user Id passed from other activity
        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");

        // instantiates the blank nav view model and sets the user id that is currently logged in
        BlankNavViewModel blankNavViewModel = new ViewModelProvider(this).get(BlankNavViewModel.class);
        blankNavViewModel.setUserId(userId);
        blankNavViewModel.setViewProfileUserIdIndex(0);

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
                R.id.nav_home, R.id.nav_match_list, R.id.nav_profile, R.id.nav_filters, R.id.nav_conversations)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_blank_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    /**
     * Populates menu with items
     *
     * @param menu collection of menu items associated with the activity
     * @return true (boolean) when finished
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blank_nav, menu);
        return true;
    }

    /**
     * Checks for a selection from user, then navigates there or logs out.
     *
     * @param item The screen selected by the user.
     * @return The boolean result navigating to the screen.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int currentItemId = item.getItemId();

        if (currentItemId == R.id.action_log_out) {
            Intent intent = new Intent(BlankNavActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        } else if (currentItemId == R.id.action_settings) {
            Intent intent = new Intent(BlankNavActivity.this, SettingsActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Navigation to handle back button and hierarchy navigation.
     *
     * @return The boolean result of checking if screen can go back.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_blank_nav);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}