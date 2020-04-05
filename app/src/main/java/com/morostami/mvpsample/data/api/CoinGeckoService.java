package com.morostami.mvpsample.data.api;

import com.haroldadmin.cnradapter.NetworkResponse;
import com.morostami.mvpsample.data.api.responses.CoinGeckoErrorResponse;
import com.morostami.mvpsample.data.api.responses.CoinGeckoPingResponse;
import com.morostami.mvpsample.domain.Coin;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface CoinGeckoService {
    @GET("ping")
    Single<CoinGeckoPingResponse> pingTest();

    @GET("coins/list")
    Single<List<Coin>> getCoinsList();

    @GET("coins/list")
    Observable<List<Coin>> getCoinsListObservable();
}
