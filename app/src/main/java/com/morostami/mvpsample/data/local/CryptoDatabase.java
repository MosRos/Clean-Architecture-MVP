package com.morostami.mvpsample.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.morostami.mvpsample.domain.Coin;

@Database(entities = {Coin.class}, version = 1, exportSchema = false)
public abstract class CryptoDatabase extends RoomDatabase {
    public abstract CoinsDao coinsDao();
}
