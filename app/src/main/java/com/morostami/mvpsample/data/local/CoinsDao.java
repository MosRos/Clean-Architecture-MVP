package com.morostami.mvpsample.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.morostami.mvpsample.domain.Coin;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insetCoin(Coin coin);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCoins(List<Coin> coins);

    @Query("SELECT * FROM Coin")
    Single<List<Coin>> getAllCoins();

    @Delete
    void deleteCoin(Coin coin);

    @Delete
    void deleteCoins(List<Coin> coins);

    @Query("DELETE FROM Coin")
    void deleteAllCoins();
}
