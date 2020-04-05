package com.morostami.mvpsample.presentation;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.morostami.mvpsample.MvpApp;
import com.morostami.mvpsample.R;
import com.morostami.mvpsample.data.prefs.PreferencesHelper;
import com.morostami.mvpsample.databinding.ActivityMainBinding;
import com.morostami.mvpsample.di.CoinsComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private ActivityMainBinding activityMainBinding;
    private NavController navController;
    private AlertDialog selectThemeDialog;

    public CoinsComponent coinsComponent;

    @Inject
    PreferencesHelper preferencesHelper;

    final CharSequence[] themeChoices = new CharSequence[]{"Light", "Dark", "Auto"};
    private int selectedTheme = AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coinsComponent = ((MvpApp) getApplication()).getAppComponent().coinsComponent().create();
        coinsComponent.injectMainActivity(this);

        selectedTheme = preferencesHelper.getThemeMode();
        AppCompatDelegate.setDefaultNightMode(selectedTheme);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = activityMainBinding.getRoot();
        setContentView(rootView);
        setUpNavController();
        registerWidgets();
        setListeners();
    }

    @Override
    public void onStart(){
        super.onStart();
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
        initThemeIcon(selectedTheme);
    }

    private void setListeners() {
        activityMainBinding.themeSelectIcon.setOnClickListener(view -> {
           showSelectThemeDialog(selectedTheme);
        });
    }

    private void showSelectThemeDialog(int currentPosition) {
        if (currentPosition == -1) {
            // for correcting current selected theme position in dialog choices
            currentPosition = 3;
        }
        selectThemeDialog = new AlertDialog.Builder(this)
                .setTitle("Select Theme")
                .setSingleChoiceItems(themeChoices, currentPosition - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        applyTheme(i);
                    }
                })
                .create();

        selectThemeDialog.show();
    }

    private void applyTheme(int selected) {
        preferencesHelper.setThemeMode(selected+1);
        selectThemeDialog.dismiss();
        new Handler().postDelayed(() -> {
            MainActivity.this.recreate();
        }, 200);
    }

    private void initThemeIcon(int selected) {

        switch (selected) {
            case 1:
                activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_sun));
            case 2:
                activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_moon));
            case 3:
                activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_brightness_auto));
            default:
                activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_brightness_auto));
        }

        if (selected == 1){
            activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_sun));
        } else if (selected == 2) {
            activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_moon));
        } else {
            activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_brightness_auto));
        }
    }

    private void updateThemeIcon(@DrawableRes int iconId) {
        activityMainBinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(this, iconId));
    }

    private void updateTitle(String title) {
        if (TextUtils.isEmpty(title)) return;
        activityMainBinding.titleTxt.setText(title);
    }
}
