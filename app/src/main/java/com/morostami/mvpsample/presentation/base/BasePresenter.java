package com.morostami.mvpsample.presentation.base;

public interface BasePresenter<V extends BaseView> {
    void attacheView(V view);
    void subscribe();
    void unsubscribe();
}
