package com.morostami.mvpsample.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.morostami.mvpsample.data.prefs.PreferencesHelper;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class AppModule {
    private final String preferencesName = "mvp_sample";

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application app) {
//        String masterKeyAlias = null;
//        SharedPreferences sharedPreferences = null;
//        if (23 <= Build.VERSION.SDK_INT) {
//            try {
//                masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (masterKeyAlias != null) {
//            try {
//                Timber.e("Preferences try Start Execution");
//                sharedPreferences = EncryptedSharedPreferences.create(
//                        preferencesName,
//                        masterKeyAlias,
//                        app,
//                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//                );
//                Timber.e("Preferences try Executed Successfully");
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (sharedPreferences == null) {
//                    Timber.e("Preferences finally: sharedPreferences IS NULL");
//                    sharedPreferences = app.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
//                } else {
//                    Timber.e("Preferences finally: sharedPreferences IS NOT null");
//                }
//            }
//        } else {
//            sharedPreferences = app.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
//        }
        return app.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    PreferencesHelper providePreferencesHelper(SharedPreferences preferences) {
        return new PreferencesHelper(preferences);
    }
}
