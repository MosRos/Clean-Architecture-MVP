package com.morostami.mvpsample.di;


import com.morostami.mvpsample.presentation.MainActivity;
import com.morostami.mvpsample.presentation.coinslist.CoinsListFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface CoinsComponent {

    @Subcomponent.Factory
    interface Factory {
        CoinsComponent create();
    }

    void injectMainActivity(MainActivity mainActivity);
    void injectCoinsListFragment(CoinsListFragment coinsListFragment);
}
