package com.morostami.mvpsample.data;

import com.morostami.mvpsample.data.api.CoinGeckoService;
import com.morostami.mvpsample.data.local.CryptoLocalDataSource;
import com.morostami.mvpsample.domain.Coin;
import com.morostami.mvpsample.domain.CoinsListRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CoinsListRepositoryImpl implements CoinsListRepository {
    private CoinGeckoService coinGeckoService;
    private CryptoLocalDataSource cryptoLocalDataSource;

    @Inject
    public CoinsListRepositoryImpl(CoinGeckoService geckoService, CryptoLocalDataSource localDataSource) {
        this.coinGeckoService = geckoService;
        this.cryptoLocalDataSource = localDataSource;
    }

    @Override
    public Observable<List<Coin>> getCoinsList() {

        return Observable.concatArray(
                loadCoinsFromDB(),
                fetchCoinsFromNet()
        );
    }

    private Observable<List<Coin>> loadCoinsFromDB() {
        Observable<List<Coin>> coinsResult;
        try {
            coinsResult = cryptoLocalDataSource.getAllCoins().toObservable();
        } catch (Exception e) {
            coinsResult = null;
        }
        return coinsResult = cryptoLocalDataSource.getAllCoins().toObservable();
//                cryptoLocalDataSource.getAllCoins().toObservable();
    }

    private Observable<List<Coin>> fetchCoinsFromNet() {
        Observable<List<Coin>> coinsResult;
        try {
            coinsResult = coinGeckoService.getCoinsList()
                    .doOnSuccess(fetchedCoins -> saveCoinsToDB(fetchedCoins))
                    .toObservable();
        } catch (Exception e) {
            coinsResult = null;
        }
        return coinsResult = coinGeckoService.getCoinsList()
                .doOnSuccess(fetchedCoins -> saveCoinsToDB(fetchedCoins))
                .toObservable();
//        return coinGeckoService.getCoinsList().toObservable();
    }

    private void saveCoinsToDB(List<Coin> coinsList) {
        Completable.fromCallable(() -> cryptoLocalDataSource.insertCoins(coinsList)).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("Saved Successfully");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("An Error Occurred in Saving Coins %s", e.getMessage());
                    }
                });
    }
}