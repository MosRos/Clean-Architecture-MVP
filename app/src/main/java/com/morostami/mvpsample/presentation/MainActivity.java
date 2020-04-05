package com.morostami.mvpsample.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.morostami.mvpsample.MvpApp;
import com.morostami.mvpsample.R;
import com.morostami.mvpsample.data.prefs.PreferencesHelper;
import com.morostami.mvpsample.databinding.ActivityMainBinding;
import com.morostami.mvpsample.di.CoinsComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private ActivityMainBinding activityMainBinding;
    private NavController navController;

    public CoinsComponent coinsComponent;

    @Inject
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coinsComponent = ((MvpApp) getApplication()).getAppComponent().coinsComponent().create();
        coinsComponent.injectMainActivity(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = activityMainBinding.getRoot();
        setContentView(rootView);
        setUpNavController();
        registerWidgets();
        setListeners();
    }

    private void setUpNavController() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getLabel() != null) {
                updateTitle(destination.getLabel().toString());
            }
        });
    }

    private void registerWidgets() {

    }

    private void setListeners() {
        activityMainBinding.themeSelectIcon.setOnClickListener(view -> {
            showThemeSelectionDialog();
        });
    }

    private void updateTitle(String title) {
        if (TextUtils.isEmpty(title)) return;
        activityMainBinding.titleTxt.setText(title);
    }

    private void showThemeSelectionDialog() {

    }
}
