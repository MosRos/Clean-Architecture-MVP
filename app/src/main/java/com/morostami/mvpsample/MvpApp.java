package com.morostami.mvpsample;

import android.app.Application;

import com.morostami.mvpsample.di.AppComponent;
import com.morostami.mvpsample.di.DaggerAppComponent;

import timber.log.Timber;

public class MvpApp extends Application {

    private MvpApp _mvpApp;
    private AppComponent _appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        _mvpApp = this;
        _appComponent = DaggerAppComponent.builder()
                .application(_mvpApp)
                .build();
        initTimber();
    }

    public MvpApp getMvpApplication() {
        return _mvpApp;
    }

    public AppComponent getAppComponent() {
        return _appComponent;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
//        else {
//            Timber.plant(new CrashReportingTree());
//        }
    }

//    private class CrashReportingTree extends Timber.Tree {
//
//        @Override
//        protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
//            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
//                return;
//                /**
//                 * Here We can Report Error Or Crash Logs to Crashlytic or analytic
//                 */
//            }
//        }
//    }
}
