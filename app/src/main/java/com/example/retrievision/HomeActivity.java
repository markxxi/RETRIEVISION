package com.example.retrievision;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private View underlineView;
    BottomNavigationView bottomNavigationView;

    HomeFragment Home = new HomeFragment();
    RewardsFragment Rewards = new RewardsFragment();

    NotificationsFragment Notif = new NotificationsFragment();
    ProfileFragment Profile = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        constraintLayout = findViewById(R.id.constraint_layout);
        underlineView = findViewById(R.id.underline);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, Home).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            underlineSelectedItem(item);
                    if (item.getItemId() == R.id.nav_home) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, Home).commit();
                        return true;
                    } else if (item.getItemId() == R.id.nav_rank) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, Rewards).commit();
                        return true;
                    } else if (item.getItemId() == R.id.nav_notification) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, Notif).commit();
                        return true;
                    } else if (item.getItemId() == R.id.nav_profile) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, Profile).commit();
                        return true;
                    }

                    return false;

                }
        );   underlineSelectedItem(bottomNavigationView.getMenu().getItem(0));
    }
    private void underlineSelectedItem(MenuItem item) {
        TransitionManager.beginDelayedTransition(constraintLayout);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        if (item.getItemId() == R.id.nav_home) {
            constraintSet.setHorizontalBias(R.id.underline, 0f);
        } else if (item.getItemId() == R.id.nav_rank) {
            constraintSet.setHorizontalBias(R.id.underline, 0.33f);
        } else if (item.getItemId() == R.id.nav_notification) {
            constraintSet.setHorizontalBias(R.id.underline, 0.66f);
        } else if (item.getItemId() == R.id.nav_profile) {
            constraintSet.setHorizontalBias(R.id.underline, 1f);
        }
        constraintSet.applyTo(constraintLayout);
    }
}



