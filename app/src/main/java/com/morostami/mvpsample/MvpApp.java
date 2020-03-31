package com.morostami.mvpsample;

import android.app.Application;

import timber.log.Timber;

public class MvpApp extends Application {

    private MvpApp _mvpApp;

    @Override
    public void onCreate() {
        super.onCreate();
        _mvpApp = this;

        initTimber();
    }

    public MvpApp getMvpApplication() {
        return _mvpApp;
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
