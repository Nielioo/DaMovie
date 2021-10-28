package com.fei.damovie.view.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fei.damovie.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar main_toolbar;
    private NavHostFragment main_navHostFragment;
    private BottomNavigationView main_bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    private void initialize() {
        main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        main_navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_navHostFragment);
        main_bottomNavigationView = findViewById(R.id.main_bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlayingFragment, R.id.upcomingFragment).build();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, main_navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(main_bottomNavigationView, main_navHostFragment.getNavController());

    }

    @Override
    public boolean onSupportNavigateUp() {
        return main_navHostFragment.getNavController().navigateUp() || super.onSupportNavigateUp();
    }
}