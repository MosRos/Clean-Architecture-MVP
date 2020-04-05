package com.morostami.mvpsample.presentation.coinslist;

import com.morostami.mvpsample.domain.Coin;
import com.morostami.mvpsample.presentation.base.BasePresenter;
import com.morostami.mvpsample.presentation.base.BaseView;

import java.util.List;

public interface CoinsListContract {
    interface View extends BaseView<Presenter> {
        void displayCoinsList(List<Coin> coins);
    }

    interface Presenter extends BasePresenter {
        void loadCoinsList();
    }
}
