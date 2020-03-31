package com.morostami.mvpsample.domain;

import java.util.List;
import io.reactivex.Observable;

public interface CoinsListRepository {
    Observable<List<Coin>> getCoinsList();
}
