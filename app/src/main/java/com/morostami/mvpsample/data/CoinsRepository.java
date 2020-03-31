package com.morostami.mvpsample.data;

import com.morostami.mvpsample.data.api.CoinGeckoService;
import com.morostami.mvpsample.data.local.CryptoLocalDataSource;
import com.morostami.mvpsample.domain.Coin;
import com.morostami.mvpsample.domain.CoinsListRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CoinsRepository implements CoinsListRepository {
    private CoinGeckoService coinGeckoService;
    private CryptoLocalDataSource cryptoLocalDataSource;

    @Inject
    public CoinsRepository(CoinGeckoService geckoService, CryptoLocalDataSource localDataSource) {
        this.coinGeckoService = geckoService;
        this.cryptoLocalDataSource = localDataSource;
    }

    @Override
    public Observable<List<Coin>> getCoinsList() {
        return null;
    }
}
