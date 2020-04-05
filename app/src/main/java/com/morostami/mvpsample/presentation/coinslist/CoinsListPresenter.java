package com.morostami.mvpsample.presentation.coinslist;

import android.content.Context;

import com.morostami.mvpsample.di.ActivityScope;
import com.morostami.mvpsample.domain.Coin;
import com.morostami.mvpsample.domain.CoinsUseCase;
import com.morostami.mvpsample.presentation.base.BaseView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@ActivityScope
public final class CoinsListPresenter implements CoinsListContract.Presenter {

    private CoinsListContract.View coinsListView;
    private CoinsUseCase mCoinUseCase;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    CoinsListPresenter(CoinsUseCase coinsUseCase) {
        this.mCoinUseCase = coinsUseCase;
    }

    @Override
    public void attacheView(BaseView view) {
        this.coinsListView = (CoinsListContract.View) view;
    }

    @Override
    public void loadCoinsList() {
        disposable.add(
                mCoinUseCase.getCoinsList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Coin>>() {
                            @Override
                            public void onNext(List<Coin> coins) {
                                coinsListView.displayCoinsList(coins);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.e(e.getMessage().toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }
}
