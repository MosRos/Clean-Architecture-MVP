package com.morostami.mvpsample.di;

import android.app.Application;

import androidx.room.Room;

import com.morostami.mvpsample.data.local.CoinsDao;
import com.morostami.mvpsample.data.local.CryptoDatabase;
import com.morostami.mvpsample.data.local.CryptoLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private final String DB_NAME = "crypto_db";
    @Singleton
    @Provides
    CryptoDatabase provideCryptoDataBase(Application app) {
        return Room.databaseBuilder(app.getApplicationContext(), CryptoDatabase.class, DB_NAME)
                .build();
    }

    @Provides
    CoinsDao provideCoinDao(CryptoDatabase database) {
        return database.coinsDao();
    }

    @Singleton
    @Provides
    CryptoLocalDataSource provideCryptoLocalDataSource(CoinsDao coinsDao) {
        return new CryptoLocalDataSource(coinsDao);
    }
}
