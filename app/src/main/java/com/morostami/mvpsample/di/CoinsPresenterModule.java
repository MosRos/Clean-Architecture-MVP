package com.morostami.mvpsample.di;

import com.morostami.mvpsample.presentation.coinslist.CoinsListContract;

import dagger.Module;
import dagger.Provides;

@Module
public class CoinsPresenterModule {
    private CoinsListContract.View coinsListView;

    public CoinsPresenterModule(CoinsListContract.View view){
        this.coinsListView = view;
    }

    @Provides
    public CoinsListContract.View provideCoinsListView() {
        return coinsListView;
    }
}
