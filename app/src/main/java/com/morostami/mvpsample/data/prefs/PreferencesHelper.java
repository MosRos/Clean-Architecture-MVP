package com.morostami.mvpsample.data.prefs;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import javax.inject.Inject;

public class PreferencesHelper {
    private static PreferencesHelper INSTANCE;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefsEditor;

    @Inject
    public PreferencesHelper(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
        prefsEditor = preferences.edit();
    }

    public static PreferencesHelper getInstance(SharedPreferences sharedPreferences) {
        if (INSTANCE == null){
            INSTANCE = new PreferencesHelper(sharedPreferences);
        }
        return INSTANCE;
    }

    /* Key and default value */
    @NonNull
    private final Pair<String, Integer> SELECTED_THEME_MODE = new Pair<String, Integer>("theme_mode", 0);
    public Integer getThemeMode() {
        return preferences.getInt(SELECTED_THEME_MODE.first, 0);
    }
    public void setThemeMode(Integer themeMode) {
        putInteger(SELECTED_THEME_MODE.first, themeMode);
    }


    private void putString(String key, String value) {
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    private void putBoolean(String key, Boolean value) {
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    private void putInteger(String key, Integer value) {
        prefsEditor.putInt(key, value);
        prefsEditor.apply();
    }
}
