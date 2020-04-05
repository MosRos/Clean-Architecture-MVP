package com.morostami.mvpsample.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CoinsUseCase {
    private CoinsListRepository coinsListRepository;

    @Inject
    public CoinsUseCase(CoinsListRepository coinsListRepository) {
        this.coinsListRepository = coinsListRepository;
    }

    public Observable<List<Coin>> getCoinsList() {
        return coinsListRepository.getCoinsList();
    }
}
